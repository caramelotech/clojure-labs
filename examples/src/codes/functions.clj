(ns codes.functions)

(defn imprimir-mensagem                                     ; Nome da função
  []                                                        ; Parâmetros
  (println "Bem vindo ao sistema de estoque"))

(defn valor-descontado
  "Retorno do valor descontado que é 90% do valor bruto"
  [valor-bruto]
  (* valor-bruto 0.9))

(defn valor-descontado-otimizado
  "Retorno do valor com o desconto de 10%"
  [valor-bruto]
  (* valor-bruto (- 1 0.10)))

; Ambas as funções retornam o mesmo resultado, no entanto, a segunda definição é mais explícita sobre o cálculo do desconto,
; subtraindo diretamente 0.10 de 1, o que torna o código mais legível e fácil de entender.
; A expressão (- 1 0.10) é mais clara e direta sobre a intenção de aplicar um desconto de 10%.