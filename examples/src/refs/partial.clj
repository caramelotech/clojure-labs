(ns refs.partial)

;; ## mapv: como map, mas ansioso (não lazy) e devolve um vetor

;; Ótimo quando a função tem efeito colateral e você quer garantir que ela rodou
(mapv println ["Olá" "Clojure" "!"])
;; => imprime as 3 linhas na hora, sem precisar forçar a sequência com outra função


;; ## Funções pequenas, cada uma com uma responsabilidade

(defn calcular-imposto
  [valor taxa]
  (* valor taxa))

(defn calcular-total
  [valor taxa]
  (+ valor (calcular-imposto valor taxa)))

(println (calcular-total 100 0.1))
;; => 110.0


;; ## partial: "pré-preenche" os primeiros argumentos de uma função

(def soma10 (partial + 10))

(println (soma10 5))
;; => 15
(println (soma10 100))
;; => 110

;; Útil para configurar uma função genérica para um caso específico
(defn aplicar-desconto
  [taxa valor]
  (- valor (* valor taxa)))

(def desconto-de-10-por-cento (partial aplicar-desconto 0.10))

(println (desconto-de-10-por-cento 200))
;; => 180.0
(println (desconto-de-10-por-cento 50))
;; => 45.0


;; ## doseq: percorrer uma sequência só pelo efeito colateral (não guarda retorno)

(doseq [nome ["Ana" "João" "Lia"]]
  (println "Olá," nome))


;; ## dotimes: repetir algo um número fixo de vezes

(dotimes [i 3]
  (println "Executando" i))
