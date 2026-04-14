---
title: "Mapas e Banco em Memoria"
description: "Use mapas para modelar dados e simular operacoes de persistencia em memoria."
lastUpdated: 2026-04-13
sidebar:
  order: 4
tags: ["clojure","mapas","dados","memoria"]
---

# 🗺️ Mapas e Banco em Memória

## 🏦 Simular um banco de dados em memória

Em Clojure é comum representar registros como mapas e coleções de mapas como "tabelas" em memória. Cada pedido é um mapa com chaves e valores:

```clojure
(def pedido1 {:usuario 15
              :itens   {:mochila  {:id :mochila, :quantidade 2, :preco-unitario 80}
                        :camiseta {:id :camiseta, :quantidade 3, :preco-unitario 40}
                        :tenis    {:id :tenis, :quantidade 1}}})

(def pedido2 {:usuario 1
              :itens   {:mochila  {:id :mochila, :quantidade 2, :preco-unitario 80}
                        :camiseta {:id :camiseta, :quantidade 3, :preco-unitario 40}}})

(defn todos-os-pedidos []
  [pedido1 pedido2])
```

Encapsular os dados em uma função em vez de uma `def` evita que o estado fique exposto globalmente e facilita substituir a fonte de dados no futuro.

## 📦 Utilizar `require` para importar namespaces

O `:require` importa outro namespace para o arquivo atual. É declarado no `ns` do arquivo:

```clojure
(ns collections.sort-by
  (:require [collections.db :as db]))
```

Aqui `collections.db` é o namespace do arquivo `db.clj` e `:as db` cria um alias para acessar suas funções de forma abreviada.

## 🔗 Utilizar `:as` para abreviação

O `:as` evita repetir o nome completo do namespace a cada chamada:

```clojure
;; Sem alias - verboso
(collections.db/todos-os-pedidos)

;; Com alias - conciso
(db/todos-os-pedidos)
```

Convenção comum no projeto: usar prefixos descritivos como `l.db` (layer + db) ou simplesmente `db`:

```clojure
(:require [collections.db :as l.db]
          [collections.logic :as l.logic])

(l.db/todos-os-pedidos)
(l.logic/resumo-por-usuario pedidos)
```

## 🗂️ Agrupar dados com `group-by`

`group-by` divide uma coleção em grupos baseados no resultado de uma função. Retorna um mapa onde cada chave é o resultado da função e o valor é um vetor de elementos que produziram aquela chave:

```clojure
(def pedidos (todos-os-pedidos))

(group-by :usuario pedidos)
;; => {15 [{:usuario 15, :itens {...}} {:usuario 15, :itens {...}} ...]
;;     1  [{:usuario 1,  :itens {...}}]
;;     10 [{:usuario 10, :itens {...}}]
;;     20 [{:usuario 20, :itens {...}}]}
```

Também funciona com uma função anônima ou nomeada:

```clojure
(defn id-do-usuario [pedido]
  (:usuario pedido))

(group-by id-do-usuario pedidos)
;; => mesmo resultado que acima
```

### 🔢 Contar pedidos por usuário

Combinando `group-by`, `vals` e `map count`:

```clojure
(->> pedidos
     (group-by :usuario)
     vals
     (map count))
;; => (4 1 1 1)
```

### 💰 Calcular total gasto por usuário

Usando `group-by` + funções auxiliares para calcular o total de cada pedido:

```clojure
(defn total-do-item [[_ detalhes]]
  (* (get detalhes :quantidade 0)
     (get detalhes :preco-unitario 0)))

(defn total-do-pedido [pedido]
  (->> pedido
       (map total-do-item)
       (reduce +)))

(defn total-dos-pedidos [pedidos]
  (->> pedidos
       (map :itens)
       (map total-do-pedido)
       (reduce +)))

(defn resumo-por-usuario [[usuario pedidos]]
  {:usuario-id       usuario
   :total-de-pedidos (count pedidos)
   :preco-total      (total-dos-pedidos pedidos)})

(->> pedidos
     (group-by :usuario)
     (map resumo-por-usuario))
;; => ({:usuario-id 15, :total-de-pedidos 4, :preco-total 1360}
;;     {:usuario-id 1,  :total-de-pedidos 1, :preco-total 340}
;;     ...)
```

[Voltar para a secao](/clojure-labs/2-colecoes/)

