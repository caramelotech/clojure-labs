---
title: "Hands On"
description: "Veja exemplos praticos de operacoes com colecoes para exercitar a base funcional do Clojure."
lastUpdated: 2026-04-13
sidebar:
  order: 2
tags: ["clojure","hands on","colecoes","pratica"]
---

# Hands On

## Map

A função map em Clojure aplica uma função a cada elemento de uma coleção, retornando uma nova coleção com os resultados.

No arquivo [map.clj](../../examples/src/collections/map.clj) é implementado uma função chamada `meu-mapa` para **exemplificar** a funcionalidade da função `map` do clojure:

```clojure
(defn meu-mapa
  [fun seq]
  (let [primeiro-elemento (first seq)]
    (if (not (nil? primeiro-elemento))
      (do
        (fun primeiro-elemento)
        (meu-mapa fun (rest seq))))))
```

Nessa função é processada uma sequência aplicando `fun` ao primeiro elemento e, em seguida, chamando-se recursivamente com o restante da sequência até que a sequência esteja vazia.

O problema dessa função é que ela utiliza recursividade sem otimização de cauda (tail call optimization). Em Clojure, a recursividade sem otimização de cauda pode levar a um estouro de pilha (stack overflow) quando aplicada a sequências muito longas, pois cada chamada recursiva adiciona um novo frame à pilha de chamadas.

No mesmo arquivo temos a função meu-mapa-otimizado utilizando a função `recur` do clojure para otimizar a execução:

```clojure
(defn meu-mapa-otimizado
  [fun seq]
  (let [primeiro-elemento (first seq)]
    (if (not (nil? primeiro-elemento))
      (do
        (fun primeiro-elemento)
        (recur fun (rest seq))))))
```

A recursão em cauda é uma técnica onde a chamada recursiva é a última operação em uma função, permitindo que a linguagem reutilize o mesmo frame de pilha para cada chamada recursiva, ou seja, a pilha de chamadas não cresce. Isso evita o estouro de pilha (stack overflow) em sequências muito longas, que é um problema comum quando a recursão não é otimizada. Sem `recur`, cada chamada recursiva adiciona um novo frame à pilha de chamadas, o que pode levar ao estouro de pilha se a sequência for muito longa.

