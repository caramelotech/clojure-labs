(ns codes.maps-vals-and-keys)


; :arroz, por exemplo, é um keyword que representa um produto e 10 é o valor associado a ele.
(def estoque {:arroz 10 :feijao 20 :macarrao 30 :carne 40})

(println estoque)
(println "Os valores são" (vals estoque))
(println "As chaves são" (keys estoque))
(println "Temos" (count estoque) "itens em estoque")

(println (assoc estoque :cuscuz 15))
(println (dissoc estoque :carne))

(defn tira-um
  [valor]
  (- valor 1))

(println (update estoque :arroz tira-um))


(def pedido {
             :arroz    {:quantidade 2 :preco 10}
             :feijao   {:quantidade 3 :preco 20}
             :macarrao {:quantidade 1 :preco 0}})

(println pedido)
(println "Arroz" pedido :arroz)
(println "Quantidade de arroz" (get pedido [:arroz :quantidade]))
(println "Feijão" (:feijao pedido))
(println "Bolo?" (:bolo pedido))
(println "Bolo?" (:bolo pedido {}))

(println (:quantidade (:arroz pedido)))

; Utilizando o get-in
(println (get-in pedido [:arroz :quantidade]))

; Utilizando threading macro
(-> pedido
    :arroz
    :quantidade
    inc
    println)


; Desestruturação
(defn imprime-e-15
  [[chave valor]]
  (println chave "<e>" valor)
  15)

(println (map imprime-e-15 pedido))

(defn preco-dos-produtos
  [[_ valor]]                                               ; Caso não queira usar a chave, pode-se usar o underline
  (* (:quantidade valor) (:preco valor)))

(println "Preço total do pedido" (reduce + (map preco-dos-produtos pedido)))



(defn total-do-pedido
  [pedido]
  (reduce + (map preco-dos-produtos pedido)))

(println "Preço total do pedido" (total-do-pedido pedido))


; Diferente do -> (threading first), o ->> (threading last) passa o resultado da expressão anterior como último argumento da próxima expressão.
(defn total-do-pedido
  [pedido]
  (->> pedido
       (map preco-dos-produtos,,,)
       (reduce +,,,)))

(println "Preço total do pedido" (total-do-pedido pedido))


(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))

(println (filter gratuito? (vals pedido)))



(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))

(println (filter (fn [[chave valor]] (gratuito? valor)) pedido))
(println (filter #(gratuito? (second %)) pedido))


(defn pago?
  [item]
  (not (gratuito? item)))

(println (filter pago? (vals pedido)))

; Trabalhando com composição de função
(def pago? (comp not gratuito?))
(println (filter pago? (vals pedido)))