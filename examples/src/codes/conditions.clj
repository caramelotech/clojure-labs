(ns codes.conditions)

(defn valor-descontado
  "Retorno do valor com o desconto de 10% se o valor bruto for estritamente maior que 100"
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))                             ; Para verdadeiro
    valor-bruto)                                            ; Para falso
  )                                                         ; Se não houvesse instrução para falso, o retorno seria nil


(defn aplica-desconto?
  "Retorna verdadeiro se o valor bruto for estritamente maior que 100"
  [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado-otimizado
  "Retorno do valor com o desconto de 10% se o valor bruto for estritamente maior que 100"
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado-otimizado 100))
(println (valor-descontado-otimizado 150))