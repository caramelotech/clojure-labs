(ns codes.vectors-get-and-update)

(def precos [10 20 30 40 50])

(println (precos 0))
(println (get precos 0))
(println (get precos 17 0))                                 ; Busca pelo indice 17, se não existe retorna 0
(println (conj precos 60))                                  ; Adiciona um novo elemento ao final do vetor
(println (assoc precos 2 35))                               ; Atualiza o valor do indice 2
(println (update precos 2 inc))                             ; Atualiza o valor do indice 2 incrementando em 1
(println (update precos 2 #(* % 2)))                        ; Atualiza o valor do indice 2 multiplicando por 2

; O valor de `precos` não é alterado após as operações acima
(println precos)
