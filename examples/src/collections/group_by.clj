(ns collections.group-by
  (:require [collections.db :as c.db]
            [collections.logic :as c.logic]))

;; `group-by` divide uma colecao em grupos, usando o retorno de uma funcao como chave.
;; O resultado e sempre um mapa: {resultado-da-funcao [elementos-que-deram-esse-resultado]}

(println (group-by :usuario (c.db/todos-os-pedidos)))
;; => {15 [pedido1 pedido3 pedido4], 1 [pedido2], 10 [pedido5], 20 [pedido6]}

;; A "chave" pode vir de qualquer funcao, nao so de uma keyword
(defn minha-funcao-de-agrupamento
  [elemento]
  (:usuario elemento))

(println (group-by minha-funcao-de-agrupamento (c.db/todos-os-pedidos)))
;; => o mesmo resultado de group-by :usuario

;; Contando quantos pedidos cada usuario fez
(->> (c.db/todos-os-pedidos)
     (group-by :usuario)
     vals
     (map count)
     println)
;; => (3 1 1 1)

;; group-by devolve um mapa, entao cada entrada vira um par [usuario pedidos] ao iterar com map
(defn conta-total-por-usuario
  [[usuario pedidos]]
  {:usuario-id       usuario
   :total-de-pedidos (count pedidos)})

(->> (c.db/todos-os-pedidos)
     (group-by :usuario)
     (map conta-total-por-usuario)
     println)
;; => ({:usuario-id 15, :total-de-pedidos 3} {:usuario-id 1, :total-de-pedidos 1} ...)

;; Essa mesma ideia, mas somando tambem o valor gasto por usuario, ja esta pronta
;; em collections.logic (veja logic.clj) - e a versao "final" que os outros
;; exemplos (sort-by, lazy-eager) usam a partir daqui.
(->> (c.db/todos-os-pedidos)
     c.logic/resumo-por-usuario
     println)
;; => ({:usuario-id 15, :total-de-pedidos 3, :preco-total ...} ...)
