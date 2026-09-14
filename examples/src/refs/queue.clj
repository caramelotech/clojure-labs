(ns refs.queue)

;; PersistentQueue é uma estrutura de dados imutável nativa do Clojure,
;; feita para comportamento de fila FIFO (First In, First Out) de verdade -
;; diferente de simular fila com vetor.
;;
;; Fora do REPL, println mostra a fila como objeto Java cru
;; (#object[clojure.lang.PersistentQueue ...]), então usamos `vec` só para
;; visualizar o conteúdo - isso não muda a fila, é só pra exibição.

(def fila clojure.lang.PersistentQueue/EMPTY)
(println (vec fila))
;; => []

;; conj adiciona ao FINAL da fila, como em vetores
(def fila (conj fila :a))
(println (vec fila))
;; => [:a]

(def fila (conj fila :b :c))
(println (vec fila))
;; => [:a :b :c]

;; peek olha o PRIMEIRO elemento sem removê-lo
(println (peek fila))
;; => :a

;; pop remove o PRIMEIRO elemento (diferente de vetor, onde pop remove o último!)
(def fila (pop fila))
(println (vec fila))
;; => [:b :c]


;; ## update em mapas - útil para contadores junto com a fila

(def status {:processadas 0})

(println (update status :processadas inc))
;; => {:processadas 1}

(println (update status :processadas + 5))
;; => {:processadas 5}


;; ## Juntando fila + contador

(def fila clojure.lang.PersistentQueue/EMPTY)
(def fila (conj fila :msg1 :msg2 :msg3))
(println (vec fila))
;; => [:msg1 :msg2 :msg3]

(println (peek fila))
;; => :msg1

(def fila (pop fila))
(println (vec fila))
;; => [:msg2 :msg3]

(def status (update status :processadas inc))
(println status)
;; => {:processadas 1}

;; Como toda estrutura de Clojure, a fila é imutável: cada operação
;; (conj, pop) devolve uma fila NOVA - a original nunca muda.
