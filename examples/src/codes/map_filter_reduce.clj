(ns codes.map-filter-reduce)

(def precos [10 20 30 40 100 150 200])

(defn aplica-desconto?
  "Retorna verdadeiro se o valor bruto for estritamente maior que 100"
  [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado
  "Retorno do valor com o desconto de 10% se o valor bruto for estritamente maior que 100"
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (map valor-descontado precos))
(println (filter even? (range 10)))
(println (filter aplica-desconto? precos))
(println (map valor-descontado (filter aplica-desconto? precos)))


(println (reduce + precos))
(println (reduce + (map valor-descontado precos)))