(ns collections.lazy-eager
  (:require [collections.db :as l.db]
            [collections.logic :as l.logic]))

;; `filter` e `keep` parecem fazer a mesma coisa, mas sao diferentes:
;; - filter mantem os elementos originais que passam num predicado (true/false)
;; - keep mantem o retorno da funcao para cada elemento, descartando so nil/false

(defn gastou-bastante?
  [info-do-usuario]
  (> (:preco-total info-do-usuario) 500))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)]
  (println "filter" (filter gastou-bastante? resumo))
  ;; => mantem os mapas completos que passaram no filtro
  (println "keep" (keep gastou-bastante? resumo)))
  ;; => mantem so os `true`, sem os mapas originais


;; ## Lazy vs eager

;; Sequencias em Clojure sao avaliadas de forma preguicosa (lazy) por padrao:
;; o valor de cada item so e calculado quando alguem pede por ele.

(println (take 2 (range 10000000000000)))
;; => (0 1) - funciona mesmo com um range gigante, porque so pede 2 itens

(let [sequencia (range 1000000)]
  (println (take 2 sequencia))
  (println (take 2 sequencia)))
;; => a mesma sequencia e reaproveitada nas duas chamadas, porque e imutavel

(defn log-map-1 [x] (println "map-1" x) x)
(defn log-map-2 [x] (println "map-2" x) x)

;; Repare na ordem dos prints: cada item passa pelos DOIS maps antes do proximo comecar
(->> (range 5)
     (map log-map-1)
     (map log-map-2)
     println)


;; ## Chunking

;; Vetores e ranges grandes sao processados em blocos de 32 elementos (chunks),
;; entao o comportamento "lazy" as vezes processa mais itens de uma vez do que se espera
(->> (range 40)
     (map log-map-1)
     (map log-map-2)
     println)

;; Listas ligadas nao tem chunking - sao 100% lazy, item por item
(->> '(0 1 2 3 4 5 6 7 8 9)
     (map log-map-1)
     (map log-map-2)
     println)
