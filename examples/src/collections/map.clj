(ns collections.map)

;; `meu-mapa` reimplementa a ideia por tras do `map`: aplica uma funcao a cada
;; elemento de uma sequencia, um a um, chamando a si mesma recursivamente.
;;
;; Parametros:
;; - `fun`: funcao a ser aplicada a cada elemento
;; - `seq`: sequencia de elementos
(defn meu-mapa
  [fun seq]
  (let [primeiro-elemento (first seq)]
    (if (not (nil? primeiro-elemento))
      (do
        (fun primeiro-elemento)
        (meu-mapa fun (rest seq))))))

(meu-mapa println [1 2 3 4 5])
;; => imprime 1, 2, 3, 4 e 5, cada um em uma linha

(meu-mapa println ["Daniele" "João" false "Maria"])
;; => imprime cada item da lista, um por linha

(meu-mapa println [])
;; => nao imprime nada, a sequencia esta vazia

;; `meu-mapa-otimizado` faz a mesma coisa, mas usando `recur` em vez de chamar
;; a funcao recursivamente. Isso evita estouro de pilha (stack overflow) em
;; sequencias grandes, porque o Clojure reaproveita o mesmo frame de chamada.
(defn meu-mapa-otimizado
  [fun seq]
  (let [primeiro-elemento (first seq)]
    (if (not (nil? primeiro-elemento))
      (do
        (fun primeiro-elemento)
        (recur fun (rest seq))))))

(meu-mapa-otimizado println (range 5))
;; => imprime 0, 1, 2, 3 e 4

;; Repare que meu-mapa e meu-mapa-otimizado nao devolvem uma nova sequencia
;; como o `map` de verdade - elas so aplicam `fun` a cada item (efeito colateral).
;; Compare com o map nativo, que devolve os resultados em vez de so imprimi-los:
(println (map inc [1 2 3 4 5]))
;; => (2 3 4 5 6)
