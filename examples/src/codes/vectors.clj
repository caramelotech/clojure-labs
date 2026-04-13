(ns codes.vectors)

; Exemplos de definição de símbolos
(def total-de-produtos 15)
(println "Total de produtos:" total-de-produtos)

(def total-de-produtos 20)
(println "Total de produtos:" total-de-produtos)

; Em Clojure, os símbolos são imutáveis, mas as variáveis podem ser redefinidas
; O símbolo total-de-produtos é definido várias vezes. Cada vez que o def é utilizado, é criando uma nova definição para o símbolo, que aponta para um novo valor
;
; Cada redefinição cria uma nova ligação para o símbolo total-de-produtos, mas o valor anterior não é alterado,
; apenas a referência do símbolo é atualizada para apontar para o novo valor

(def total-de-produtos (+ total-de-produtos 10))
(println "Total de produtos:" total-de-produtos)

; Exemplo de definição de vetorL
(def estoque ["Mochila" "Caneta"])
(println "Estoque:" estoque)
; Vetores podem ser tratada como funções a depender do que é passado como argumento
; Nesse exemplo estamos passando a posição do vetor que queremos acessar
(println "Primeiro item" (estoque 0))
(println "Segundo item" (estoque 1))

; Ao chamar uma posição que não existe, é retornado um erro
;(println "Terceiro item" (estoque 2))

; Para que esse erro não ocorra, a função get pode ser utilizada, então o Clojure vai retornar nil
(println "Terceiro item" (get estoque 2))

; Para retornar um valor padrão caso a posição não exista no vetor:
(println "Terceiro item" (get estoque 2 "Invalido"))

(conj estoque "Cadeira")
(println "Estoque:" estoque)                                ; Estoque: [Mochila Caneta]
(def new-estoque (conj estoque "Cadeira"))
(println "Novo estoque:" new-estoque)                       ; Novo estoque: [Mochila Caneta Cadeira]

; Por Clojure ser imutável, a função conj não altera o vetor original, ela retorna um novo vetor com o novo item adicionado
; Para adicionar o novo vetor, é necessário atribuir o retorno da função conj a uma nova variável ou reatribuir para o símbolo existente

; Em Clojure, a redefinição de símbolos e a manipulação de dados imutáveis são otimizadas através do uso de estruturas de dados persistentes
; Essas estruturas de dados são implementadas para compartilhar a maioria dos dados entre as versões antigas e novas,
; minimizando a necessidade de cópias completas e, portanto, reduzindo o custo de processamento

; Por exemplo, ao adicionar um item a um vetor usando a função conj, Clojure cria um novo vetor que compartilha a maioria dos elementos com o vetor original
; Isso é possível porque as estruturas de dados imutáveis em Clojure são implementadas usando técnicas como árvores de hash e vetores de árvore,
; que permitem a reutilização eficiente de partes dos dados
; Essa abordagem garante que as operações em estruturas de dados imutáveis sejam eficientes em termos de tempo e espaço, evitando o custo elevado de cópias completas e
; permitindo que Clojure seja altamente performático mesmo com a imutabilidade

(def estoque (conj estoque "Cadeira"))

; Para realizar uma operação com um item do vetor, a função update pode ser utilizada:
(def precos [400 700 1000])
(println "Lista de preços:" precos)
; Incrementa 1 ao valor da posição 0 do vetor
(println "Soma 1 no primeiro item de preços:" (update precos 0 inc))
; Incrementa 5 ao valor da posição 0 do vetor
(println "Soma 5 no primeiro item de preços:" (update precos 0 #(+ % 5)))

;Porém o vetor permanece inalterado porque é imutável
(println "Lista de precos:" precos)
