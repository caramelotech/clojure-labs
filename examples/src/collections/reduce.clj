(ns collections.reduce)

(defn conta
  ([elementos]
   (conta 0 elementos))

  ([total-ate-agora elementos]
   (if (seq elementos)
     (recur (inc total-ate-agora) (next elementos))
     total-ate-agora)))

(println (conta [1 2 3 4 5 6 7 8 9 10]))


(defn conta-loop
  [elementos]
  (loop [total-ate-agora 0
         elementos-restantes elementos]
    (if (seq elementos-restantes)
      (recur (inc total-ate-agora) (next elementos-restantes))
      total-ate-agora)))

(println (conta-loop [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]))