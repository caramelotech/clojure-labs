(ns collections.reduce)

;; `conta` e `conta-loop` mostram duas formas de contar elementos usando recursao
;; com `recur` - a base para entender como o `reduce` nativo funciona por baixo dos panos.

(defn conta
  ([elementos]
   (conta 0 elementos))

  ([total-ate-agora elementos]
   (if (seq elementos)
     (recur (inc total-ate-agora) (next elementos))
     total-ate-agora)))

(println (conta [1 2 3 4 5 6 7 8 9 10]))
;; => 10

(defn conta-loop
  [elementos]
  (loop [total-ate-agora 0
         elementos-restantes elementos]
    (if (seq elementos-restantes)
      (recur (inc total-ate-agora) (next elementos-restantes))
      total-ate-agora)))

(println (conta-loop [0 1 2 3 4 5 6 7 8 9 10]))
;; => 11

;; Generalizando essa ideia: `reduce` e o mesmo padrao de recursao, mas recebendo
;; de fora a funcao que combina cada elemento com o acumulado (`f`) e o valor inicial.
(defn meu-reduce
  [f valor-inicial elementos]
  (loop [acumulado valor-inicial
         restantes elementos]
    (if (seq restantes)
      (recur (f acumulado (first restantes)) (rest restantes))
      acumulado)))

(println (meu-reduce + 0 [1 2 3 4 5]))
;; => 15

(println (meu-reduce * 1 [1 2 3 4 5]))
;; => 120

;; Confirmando que bate com o reduce nativo do Clojure:
(println (= (reduce + [1 2 3 4 5]) (meu-reduce + 0 [1 2 3 4 5])))
;; => true
