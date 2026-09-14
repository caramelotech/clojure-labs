# Threading Macro

# Threading Macro

Em Clojure, `->` e `->>` são macros de encadeamento (threading macros) que facilitam a leitura e a escrita de código ao aplicar uma série de funções a um valor inicial. A diferença entre elas está na posição em que o valor inicial é inserido nas chamadas de função subsequentes.

## `->` (Thread-First Macro)

A macro `->` insere o valor inicial como o primeiro argumento de cada função subsequente.

Exemplo:

```clojure
(-> 5
    (+ 3)    ; (+ 5 3)
    (* 2))   ; (* 8 2)
```

Resultado: `16`

## `->>` (Thread-Last Macro)

A macro `->>` insere o valor inicial como o último argumento de cada função subsequente.

Exemplo:

```clojure
(->> 5
     (inc)    ; (inc 5)
     (* 2))   ; (* 2 6)
```

Resultado: `12`

## Exemplo Comparativo

Para ilustrar a diferença, considere as seguintes funções:

```clojure
(defn add [a b] (+ a b))
(defn multiply [a b] (* a b))
```

Usando `->`:

```clojure
(-> 5
    (add 3)       ; (add 5 3)
    (multiply 2)) ; (multiply 8 2)
```

Resultado: `16`

Usando `->>`:

```clojure
(->> 5
     (add 3)       ; (add 3 5)
     (multiply 2)) ; (multiply 2 8)
```

Resultado: `16`

Note que, no caso acima, o resultado é o mesmo, mas a posição dos argumentos nas funções `add` e `multiply` é diferente.

## Referências e Material Complementar

- [Threading Macros Guide - Clojure.org](https://clojure.org/guides/threading_macros)

