(ns collections.map)

;; `meu-mapa` é uma função recursiva que aplica uma função dada a cada elemento de uma sequência.
;;
;; Parâmetros:
;; - `fun`: Uma função a ser aplicada a cada elemento da sequência.
;; - `seq`: Uma sequência de elementos à qual a função será aplicada.
;;
;; A função processa a sequência aplicando `fun` ao primeiro elemento e, em seguida, chamando-se
;; recursivamente com o restante da sequência até que a sequência esteja vazia.
(defn meu-mapa
  [fun seq]
  (let [primeiro-elemento (first seq)]
    (if (not (nil? primeiro-elemento))
      (do
        (fun primeiro-elemento)
        (meu-mapa fun (rest seq))))))

(println "----------------")
(println "Chamadas de `meu-mapa` com diferentes sequências:")
(meu-mapa println [1 2 3 4 5])
(println "----------------")
(meu-mapa println ["Daniele" "João" false "Maria"])
(println "----------------")
(meu-mapa println [])
(println "----------------")


;; `meu-mapa-otimizado` é uma função recursiva otimizada que aplica uma função dada a cada elemento de uma sequência.
;;
;; Parâmetros:
;; - `fun`: Uma função a ser aplicada a cada elemento da sequência.
;; - `seq`: Uma sequência de elementos à qual a função será aplicada.
;;
;; Esta função utiliza `recur` para realizar a recursão de forma mais eficiente, evitando o estouro de pilha (stack overflow)
;; que pode ocorrer com a recursão tradicional usada em `meu-mapa`. O `recur` é uma boa prática em Clojure para recursão
;; porque ele reutiliza a mesma chamada de função, otimizando o uso de memória e melhorando o desempenho.
(defn meu-mapa-otimizado
  [fun seq]
  (let [primeiro-elemento (first seq)]
    (if (not (nil? primeiro-elemento))
      (do
        (fun primeiro-elemento)
        (recur fun (rest seq))))))

;(println "\n----------------")
;(println "Chamada de `meu-mapa-otimizado` com um range de 10000 elementos:")
;(meu-mapa-otimizado println (range 10000))
;(println "----------------")

(defn valor-descontado
  "Retorno do valor com o desconto de 10%"
  [valor-bruto]
  (let [taxa-de-desconto (/ 10 100)
        desconto (* valor-bruto taxa-de-desconto)]          ; O let define um símbolo local que só é válido dentro do escopo do let
    (- valor-bruto desconto)))

(def precos [500, 20, 100, 44, 89])

;(map valor-descontado precos)








