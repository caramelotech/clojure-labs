(ns codes.maps-vals-and-keys)

;; :arroz, por exemplo, é um keyword que representa um produto e 10 é o valor associado a ele.
(def estoque {:arroz 10 :feijao 20 :macarrao 30 :carne 40})

(println estoque)
(println "Os valores são" (vals estoque))
(println "As chaves são" (keys estoque))
(println "Temos" (count estoque) "itens em estoque")

(println (assoc estoque :cuscuz 15))
;; => {:arroz 10, :feijao 20, :macarrao 30, :carne 40, :cuscuz 15}

(println (dissoc estoque :carne))
;; => {:arroz 10, :feijao 20, :macarrao 30}

(defn tira-um
  [valor]
  (- valor 1))

(println (update estoque :arroz tira-um))
;; => {:arroz 9, :feijao 20, :macarrao 30, :carne 40}


;; ## Mapas aninhados

(def pedido {:arroz    {:quantidade 2 :preco 10}
             :feijao   {:quantidade 3 :preco 20}
             :macarrao {:quantidade 1 :preco 0}})

(println pedido)

(println "Arroz" (get pedido :arroz))
;; => Arroz {:quantidade 2, :preco 10}

;; `get` só busca uma chave por vez - não existe um "caminho" de chaves:
(println "Quantidade de arroz via get" (get pedido [:arroz :quantidade]))
;; => nil, porque [:arroz :quantidade] não é uma chave do mapa, é um vetor

;; Para navegar por chaves aninhadas, use get-in:
(println "Quantidade de arroz via get-in" (get-in pedido [:arroz :quantidade]))
;; => 2

;; Atalhos equivalentes para o mesmo caminho:
(println "Feijão" (:feijao pedido))
(println (:quantidade (:arroz pedido)))
;; => 2

;; Chave que não existe: get-in/keyword devolvem nil (ou o valor padrão informado)
(println "Bolo?" (:bolo pedido))
;; => nil
(println "Bolo?" (:bolo pedido {}))
;; => {}

;; Usando a threading macro (->) para encadear os mesmos acessos
(-> pedido
    :arroz
    :quantidade
    inc
    println)
;; => 3
