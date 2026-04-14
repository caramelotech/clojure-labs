---
title: "Lazy vs Eager"
description: "Compare avaliacoes lazy e eager para entender custo, eficiencia e previsibilidade."
lastUpdated: 2026-04-13
sidebar:
  order: 6
tags: ["clojure","lazy","eager","performance"]
---

# 😴 Lazy vs Eager

## 🔍 `keep` vs `filter`

`keep` e `filter` são parecidos, mas com um comportamento diferente: `filter` retorna os elementos onde a função é truthy; `keep` retorna os valores retornados pela função (excluindo `nil`).

```clojure
(defn gastou-bastante? [info]
  (> (:preco-total info) 500))

;; filter retorna os mapas originais onde a condição é verdadeira
(filter gastou-bastante? resumo)
;; => ({:usuario-id 10, :preco-total 1760}
;;     {:usuario-id 15, :preco-total 1360}
;;     {:usuario-id 20, :preco-total 560})

;; keep retorna o que a função retorna (true/false neste caso)
(keep gastou-bastante? resumo)
;; => (true true true)
```

`keep` é mais útil quando a função de transformação pode retornar `nil` e você quer filtrar esses nulos automaticamente:

```clojure
(keep :email [{:nome "Ana" :email "ana@ex.com"}
              {:nome "João"}
              {:nome "Lia" :email "lia@ex.com"}])
;; => ("ana@ex.com" "lia@ex.com")
;; João foi excluído porque :email retornou nil
```

## 😴 Comportamento LAZY (preguiçoso)

Em Clojure, a maioria das funções de sequência (`map`, `filter`, `take`, `range`) é **lazy**: os elementos só são computados quando realmente necessários.

```clojure
;; range não gera 10 trilhões de números imediatamente
(take 2 (range 10000000000000))
;; => (0 1)   - apenas 2 elementos foram computados
```

Isso permite trabalhar com sequências potencialmente infinitas sem consumir memória:

```clojure
(def numeros (range 1000000))

(take 5 numeros)
;; => (0 1 2 3 4)   - só computou 5 elementos

(take 5 numeros)
;; => (0 1 2 3 4)   - imutável, mesmo resultado
```

O `map` também é lazy - não processa nada até que alguém consuma a sequência:

```clojure
(defn com-log [x]
  (println "processando" x)
  x)

;; Nenhum "processando" é impresso aqui:
(def resultado (map com-log [1 2 3 4 5]))

;; A impressão acontece ao consumir:
(first resultado)
;; processando 1
;; processando 2   <- chunks! (veja abaixo)
;; ...
;; => 1
```

## 🍖 EAGER (guloso)

Comportamento **eager** significa que toda a coleção é computada imediatamente. Vetores são eager por natureza. A função `mapv` força execução imediata e retorna um vetor:

```clojure
;; mapv é eager - processa tudo na hora
(mapv com-log [1 2 3 4 5])
;; processando 1
;; processando 2
;; processando 3
;; processando 4
;; processando 5
;; => [1 2 3 4 5]
```

Comparando comportamento em pipeline:

```clojure
;; lazy: map encadeia sem processar elementos intermediários desnecessariamente
(->> (range 10)
     (map filtro1)
     (map filtro2)
     (take 2))
;; => processa apenas os elementos necessários

;; eager: mapv força execução de cada etapa inteira antes da próxima
(->> (range 10)
     (mapv filtro1)
     (mapv filtro2)
     (take 2))
;; => processa os 10 elementos em cada mapv antes de take
```

## 📦 Chunks de 32

Clojure otimiza sequências lazy sobre **vetores** processando em blocos de 32 elementos (chunks). Isso é um detalhe de implementação para melhorar performance, mas pode parecer surpreendente:

```clojure
(defn com-log [x]
  (println "processando" x)
  x)

;; Com vetor: ao pedir o primeiro elemento, Clojure processa um chunk de 32
(->> (range 50)
     (map com-log)
     first)
;; processando 0
;; processando 1
;; ... até 31 (chunk de 32)
;; => 0
```

Com uma **lista ligada** o comportamento é 100% lazy, sem chunking:

```clojure
(->> '(0 1 2 3 4 5 6 7 8 9)
     (map com-log)
     first)
;; processando 0
;; => 0   <- processou apenas 1 elemento
```

## 🔗 Lista Ligada

Em Clojure, listas literais são criadas com `'(...)` ou `list`. Diferente de vetores `[...]`, elas são listas ligadas encadeadas (linked lists):

```clojure
'(1 2 3 4 5)
;; => (1 2 3 4 5)

(list 1 2 3)
;; => (1 2 3)
```

Diferenças práticas em relação a vetores:

| Característica       | Vetor `[...]`      | Lista `'(...)`     |
| -------------------- | ------------------ | ------------------ |
| Acesso por índice    | O(1)               | O(n)               |
| `conj` adiciona em   | No final           | No início          |
| Chunking com `map`   | Sim (blocos de 32) | Não (100% lazy)    |
| Uso idiomático       | Coleção de dados   | Expressões de código |

A principal razão para preferir vetores na maioria dos casos é o acesso O(1) e a compatibilidade com `get`/`nth`. Listas são úteis quando você quer garantia de laziness total.

[Voltar para a secao](/clojure-labs/2-colecoes/)

