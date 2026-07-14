# Como o reduce funciona

## Como o `reduce` funciona

O `reduce` é uma função de alta ordem em Clojure que aplica uma função a uma coleção de elementos, acumulando o resultado. Ele é útil para transformar uma coleção em um único valor.

```clojure
(reduce + [1 2 3 4 5])
;; => 15
```

## Implementar o `reduce`

Você pode implementar sua própria versão do `reduce` para entender melhor como ele funciona.

```clojure
(defn my-reduce [f init coll]
    (if (empty? coll)
        init
        (recur f (f init (first coll)) (rest coll))))

(my-reduce + 0 [1 2 3 4 5])
;; => 15
```

## Variação de parâmetros na função

O `reduce` pode aceitar diferentes números de parâmetros, permitindo flexibilidade na sua utilização.

```clojure
(reduce (fn [acc x] (+ acc (* x x))) 0 [1 2 3 4])
;; => 30
```

## Utilizar o `loop`

O `loop` pode ser usado para criar loops explícitos em Clojure, o que pode ser útil em algumas situações.

```clojure
(loop [acc 0 coll [1 2 3 4 5]]
    (if (empty? coll)
        acc
        (recur (+ acc (first coll)) (rest coll))))
;; => 15
```

## Fazer recorrência dentro do `loop`

Você pode usar a função `recur` dentro de um `loop` para criar recursões eficientes.

```clojure
(defn sum [coll]
    (loop [acc 0 coll coll]
        (if (empty? coll)
            acc
            (recur (+ acc (first coll)) (rest coll)))))

(sum [1 2 3 4 5])
;; => 15
```

[Voltar para a secao](/labs/clojure/2-colecoes/)

