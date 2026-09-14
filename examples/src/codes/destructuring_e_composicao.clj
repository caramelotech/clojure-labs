(ns codes.destructuring-e-composicao
  (:require [codes.maps-vals-and-keys :refer [pedido]]))

;; ## Desestruturação

;; Cada entrada de um mapa, quando percorrida com `map`, chega como um vetor [chave valor].
;; Desestruturar esse vetor direto nos parâmetros da função evita ter que escrever
;; (first entrada) e (second entrada) toda hora.
(defn imprime-e-15
  [[chave valor]]
  (println chave "<e>" valor)
  15)

(println (map imprime-e-15 pedido))
;; => imprime cada par chave/valor do pedido, e devolve (15 15 15)

(defn preco-dos-produtos
  [[_ valor]]                                                ; O underline indica que a chave não é usada aqui
  (* (:quantidade valor) (:preco valor)))

(println "Preço total do pedido" (reduce + (map preco-dos-produtos pedido)))
;; => Preço total do pedido 80

(defn total-do-pedido
  [pedido]
  (reduce + (map preco-dos-produtos pedido)))

(println "Preço total do pedido" (total-do-pedido pedido))


;; A mesma função, agora escrita com ->> (threading last): o resultado da
;; expressão anterior entra como último argumento da próxima, em vez de primeiro (->).
(defn total-do-pedido
  [pedido]
  (->> pedido
       (map preco-dos-produtos)
       (reduce +)))

(println "Preço total do pedido" (total-do-pedido pedido))


;; ## Composição de funções

(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))

;; Filtrando direto nos valores do mapa
(println (filter gratuito? (vals pedido)))

;; Filtrando nas entradas do mapa (pares [chave valor]) - duas formas equivalentes
(println (filter (fn [[chave valor]] (gratuito? valor)) pedido))
(println (filter #(gratuito? (second %)) pedido))

(defn pago?
  [item]
  (not (gratuito? item)))

(println (filter pago? (vals pedido)))
;; => (macarrão vira gratuito? nesse pedido, o resto fica em pago?)

;; `comp` cria uma nova função combinando outras - aqui, pago? vira apenas
;; "não gratuito?", sem precisar escrever `not` toda vez que chamarmos a função.
(def pago-via-comp? (comp not gratuito?))
(println (filter pago-via-comp? (vals pedido)))
;; => mesmo resultado do pago? acima
