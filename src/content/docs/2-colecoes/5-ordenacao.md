---
title: "Ordenacao e Acesso a Elementos"
description: "Ordene colecoes e acesse elementos com funcoes idiomaticas do ecossistema Clojure."
lastUpdated: 2026-04-13
sidebar:
  order: 5
tags: ["clojure","ordenacao","colecoes","acesso"]
---

# 🔢 Ordenação e Acesso a Elementos

Os exemplos desta seção usam o resumo de pedidos por usuário gerado com `group-by` e `map` (veja [Mapas e Banco em Memória](./4-mapas.md)).

```clojure
;; resumo é uma sequência de mapas como:
;; ({:usuario-id 15, :total-de-pedidos 4, :preco-total 1360}
;;  {:usuario-id 1,  :total-de-pedidos 1, :preco-total 340}
;;  {:usuario-id 10, :total-de-pedidos 1, :preco-total 1760}
;;  {:usuario-id 20, :total-de-pedidos 1, :preco-total 560})
```

## ↕️ Ordenar com `sort-by`

`sort-by` ordena uma coleção pelo valor retornado por uma função. Por padrão, a ordem é crescente:

```clojure
(sort-by :preco-total resumo)
;; => ({:usuario-id 1,  :preco-total 340}
;;     {:usuario-id 20, :preco-total 560}
;;     {:usuario-id 15, :preco-total 1360}
;;     {:usuario-id 10, :preco-total 1760})
```

Também funciona com ordenação por `:usuario-id`:

```clojure
(sort-by :usuario-id resumo)
;; => ({:usuario-id 1, ...} {:usuario-id 10, ...} {:usuario-id 15, ...} {:usuario-id 20, ...})
```

## 🔄 Inverter a ordem com `reverse`

`reverse` inverte qualquer sequência. Usado em conjunto com `sort-by` para obter ordem decrescente:

```clojure
(reverse (sort-by :preco-total resumo))
;; => ({:usuario-id 10, :preco-total 1760}
;;     {:usuario-id 15, :preco-total 1360}
;;     {:usuario-id 20, :preco-total 560}
;;     {:usuario-id 1,  :preco-total 340})
```

Combinando numa função com threading:

```clojure
(defn resumo-ordenado-por-gasto [pedidos]
  (->> pedidos
       resumo-por-usuario
       (sort-by :preco-total)
       reverse))
```

## 🔍 Acessar elemento por índice com `get`

`get` retorna o elemento em determinado índice em um vetor (ou valor de uma chave em um mapa). Retorna `nil` se o índice não existir:

```clojure
(def resumo-ordenado (resumo-ordenado-por-gasto pedidos))

(get resumo-ordenado 0)
;; => {:usuario-id 10, :preco-total 1760}

(get resumo-ordenado 1)
;; => {:usuario-id 15, :preco-total 1360}

(get resumo-ordenado 99)
;; => nil
```

Para mapas aninhados, use `get-in`:

```clojure
(get-in pedidos [0 :itens :mochila :quantidade])
;; => 2
```

## 🎯 Acessar o enésimo elemento com `nth`

`nth` acessa o elemento na posição `n` (base zero). Diferente de `get`, lança exceção se o índice estiver fora dos limites:

```clojure
(nth resumo-ordenado 0)
;; => {:usuario-id 10, :preco-total 1760}

(nth resumo-ordenado 1)
;; => {:usuario-id 15, :preco-total 1360}

(nth resumo-ordenado 99)
;; => IndexOutOfBoundsException
```

`nth` funciona em qualquer sequência, inclusive listas ligadas, enquanto `get` funciona principalmente em vetores e mapas.

## ✂️ Pegar os primeiros N elementos com `take`

`take` retorna uma sequência lazy com os primeiros `n` elementos:

```clojure
(take 2 resumo-ordenado)
;; => ({:usuario-id 10, :preco-total 1760}
;;     {:usuario-id 15, :preco-total 1360})

(defn top-2 [resumo]
  (take 2 resumo))

(top-2 resumo-ordenado)
;; => os dois usuários com maior gasto
```

## ✅ Verificar condição em coleção com `some`

`some` retorna o primeiro valor truthy retornado pela função, ou `nil` se nenhum existir. É mais eficiente que `filter` quando você só precisa saber se existe ao menos um elemento:

```clojure
;; filter retorna todos os elementos que satisfazem a condição
(filter #(> (:preco-total %) 500) resumo-ordenado)
;; => ({:usuario-id 10, ...} {:usuario-id 15, ...} {:usuario-id 20, ...})

;; some retorna o primeiro valor truthy (e para de percorrer)
(some #(> (:preco-total %) 500) resumo-ordenado)
;; => true

;; some retorna nil se nenhum elemento satisfaz
(some #(> (:preco-total %) 9999) resumo-ordenado)
;; => nil
```

Para verificar existência de forma mais idiomática:

```clojure
(not (empty? (filter #(> (:preco-total %) 500) resumo-ordenado)))
;; => true

;; Equivalente, mas mais simples com some:
(boolean (some #(> (:preco-total %) 500) resumo-ordenado))
;; => true
```

[Voltar para a secao](/clojure-labs/2-colecoes/)

