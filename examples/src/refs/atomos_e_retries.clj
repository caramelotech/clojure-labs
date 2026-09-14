(ns refs.atomos-e-retries)

(defn- inicia! [^Thread t] (.start t))
(defn- espera! [^Thread t] (.join t))

;; ## O problema: estado global mutável + concorrência

;; Rodar várias threads incrementando um número comum, sem proteção, perde
;; atualizações - cada thread lê, soma e escreve em passos separados, e pode
;; sobrescrever o trabalho de outra thread no meio do caminho (race condition).
(def contador-inseguro (atom 0))

(defn incrementa-inseguro!
  []
  (dotimes [_ 1000]
    (reset! contador-inseguro (inc @contador-inseguro))))     ; ler e escrever separados NÃO é atômico

(def threads-inseguras (mapv (fn [_] (Thread. ^Runnable incrementa-inseguro!)) (range 10)))

(run! inicia! threads-inseguras)
(run! espera! threads-inseguras)

(println "Esperado: 10000 | Real (inseguro):" @contador-inseguro)
;; => o valor real costuma vir MENOR que 10000 - algumas atualizações se perderam


;; ## A solução: swap!

;; swap! aplica uma função ao valor atual do atom de forma atômica - o Clojure
;; garante que nenhuma outra thread consegue "pisar" na atualização no meio do caminho
(def contador-seguro (atom 0))

(defn incrementa-seguro!
  []
  (dotimes [_ 1000]
    (swap! contador-seguro inc)))

(def threads-seguras (mapv (fn [_] (Thread. ^Runnable incrementa-seguro!)) (range 10)))

(run! inicia! threads-seguras)
(run! espera! threads-seguras)

(println "Esperado: 10000 | Real (seguro):" @contador-seguro)
;; => sempre 10000


;; ## Encapsulando um mapa imutável num atom

(def sistema (atom {:fila clojure.lang.PersistentQueue/EMPTY}))

;; @ é a forma curta de (deref sistema) - acessa o valor atual do atom
(println (vec (:fila @sistema)))
;; => []

(defn adicionar-evento!
  [evento]
  (swap! sistema update :fila conj evento))

(def threads-eventos (mapv (fn [e] (Thread. #(adicionar-evento! e))) [:a :b :c :d]))

(run! inicia! threads-eventos)
(run! espera! threads-eventos)

(println (vec (:fila @sistema)))
;; => [:a :b :c :d] (a ordem pode variar entre execuções, mas os 4 eventos sempre aparecem)


;; ## Shadowing: uma variável local pode "esconder" um símbolo global sem alterá-lo

(def nome "global")

(let [nome "local"]
  (println nome))
;; => local

(println nome)
;; => global

;; Clojure também tem `ref` + `dosync` (transações coordenadas entre vários
;; refs), `agent` (atualização assíncrona) e primitivas de Java como
;; `locking` - swap! em atom cobre a grande maioria dos casos do dia a dia.
