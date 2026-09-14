(ns refs.threads)

;; ## Limitando o tamanho de uma fila

(def limite 3)

(defn adicionar-na-fila
  [fila elemento]
  (if (< (count fila) limite)
    (conj fila elemento)
    (do
      (println "Fila cheia!")
      fila)))

(def fila clojure.lang.PersistentQueue/EMPTY)
(def fila (adicionar-na-fila fila :a))
(def fila (adicionar-na-fila fila :b))
(def fila (adicionar-na-fila fila :c))
(def fila (adicionar-na-fila fila :d))
;; => imprime "Fila cheia!" na 4a tentativa, a fila continua com 3 itens
(println (vec fila))
;; => [:a :b :c]

;; count funciona em qualquer coleção do Clojure, não só em fila
(println (count fila))
;; => 3


;; ## Tratando erros com ex-info

;; ex-info cria uma exceção que carrega dados extras junto com a mensagem
(try
  (throw (ex-info "Erro de processamento" {:codigo 500 :detalhe "Falha no servidor"}))
  (catch clojure.lang.ExceptionInfo e
    (println "Erro capturado:" (.getMessage e))
    (println "Dados:" (ex-data e))))
;; => Erro capturado: Erro de processamento
;; => Dados: {:codigo 500, :detalhe "Falha no servidor"}


;; ## Criando e iniciando uma Thread

;; Clojure roda sobre a JVM e é 100% compatível com Java - dá pra usar Thread direto
(def ^Thread t1 (Thread. (fn [] (println "Rodando em outra thread..."))))
(.start t1)
(.join t1)                                                    ; espera a thread terminar antes de continuar

;; reify implementa uma interface Java (aqui, Runnable) de forma mais idiomática
(def ^Thread t2 (Thread. (reify Runnable
                            (run [_] (println "Executando via reify!")))))
(.start t2)
(.join t2)
