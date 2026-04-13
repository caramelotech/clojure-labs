(ns codes.symbols-and-namespace)

; É raro precisar de um símbolo global para representar valores que não sejam funções.
; Da mesma forma que em outras linguagens de programação, evitamos variáveis globais sempre que possível.
; Quanto maior o escopo, menor o controle e maior a complexidade da manutenção do código.

(defn valor-descontado
  "Retorno do valor com o desconto de 10%"
  [valor-bruto]
  (let [taxa-de-desconto (/ 10 100)
        desconto (* valor-bruto taxa-de-desconto)]          ; O let define um símbolo local que só é válido dentro do escopo do let
    (- valor-bruto desconto)))                              ; O retorno é o resultado da última interação dentro do let

(println (class 90N))
(println (class 90M))

(defn converte-anos-de-cachorro
  "Converte anos de cachorro em idade humana"
  [idade-do-cachorro]
  (let [numero-multiplicador 5]
    (* idade-do-cachorro numero-multiplicador)))

(converte-anos-de-cachorro 6)

