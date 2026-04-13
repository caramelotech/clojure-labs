(ns codes.lambda)

(defn mais-caro-que-100?
  [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado
  "Retorna o valor com desconto de 10% se deve aplicar desconto"
  [aplica? valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

; Passando uma função como argumento
; O que é chamado de Higher Order Function
(println (valor-descontado mais-caro-que-100? 200))
(println (valor-descontado mais-caro-que-100? 100))


; Passando uma função anônima como argumento
(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 200))
(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 100))

; Função anônima com shorthand
(println (valor-descontado #(> % 100) 200))
(println (valor-descontado #(> % 100) 100))

; Definindo nome para uma função anonima para deixar mais claro o que ela faz
(def mais-caro-que-100? #(> % 100))