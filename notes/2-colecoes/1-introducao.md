# Coleções em Clojure

## Introdução

Em Clojure, **coleções** são estruturas de dados fundamentais para armazenar e manipular conjuntos de valores. Elas seguem os princípios da **imutabilidade** (não podem ser modificadas diretamente) e são projetadas para uso em programação funcional. Clojure oferece várias coleções prontas, com implementações eficientes e uma interface uniforme.

## Tipos de Coleções em Clojure

1. **Listas** (`list` ou `'()`)
2. **Vetores** (`vector` ou `[]`)
3. **Conjuntos** (`set` ou `#{}`)
4. **Mapas** (`map` ou `{}`)

Essas coleções compartilham características comuns:

- **Persistência:** Elas não são alteradas diretamente, mas retornam versões modificadas.
- **Interface uniforme:** Podem ser usadas com funções como `map`, `filter`, `reduce`, etc.

### 1. Listas

- São sequências ligadas (linked lists), imutáveis e avaliadas _lazy_ (preguiçosamente, se necessário).
- Usadas principalmente para representar código ou sequências lineares.

**Exemplo de criação:**

```clojure
'(1 2 3)    ; Lista literal (com apóstrofo)
(list 1 2 3) ; Usando a função list
```

**Operações comuns:**

```clojure
(first '(1 2 3))  ; => 1
(rest '(1 2 3))   ; => (2 3)
(cons 0 '(1 2 3)) ; => (0 1 2 3)
```

### 2. Vetores

- São coleções indexadas, como arrays, mas imutáveis.
- Mais rápidos que listas para acesso por índice.

**Exemplo de criação:**

```clojure
[1 2 3]       ; Vetor literal
(vector 1 2 3) ; Usando a função vector
```

**Operações comuns:**

```clojure
(get [1 2 3] 1)    ; => 2 (acesso por índice)
(conj [1 2 3] 4)   ; => [1 2 3 4] (adiciona no final)
(assoc [1 2 3] 1 10) ; => [1 10 3] (modifica um índice)
```

### 3. Conjuntos

- Coleções de elementos únicos, sem ordem definida.
- Usados para garantir que não existam duplicatas.

**Exemplo de criação:**

```clojure
#{1 2 3}      ; Literal
(set [1 2 2 3]) ; Cria um conjunto a partir de um vetor
```

**Operações comuns:**

```clojure
(contains? #{1 2 3} 2) ; => true
(conj #{1 2 3} 4)      ; => #{1 2 3 4}
(disj #{1 2 3} 2)      ; => #{1 3}
```

### 4. Mapas

- Estruturas de pares chave-valor, usados para associar valores.
- Podem ter qualquer tipo de chave e valor.

**Exemplo de criação:**

```clojure
{:a 1 :b 2 :c 3}      ; Literal
(hash-map :a 1 :b 2)  ; Função hash-map
```

**Operações comuns:**

```clojure
(get {:a 1 :b 2} :a)        ; => 1 (acesso pelo valor da chave)
(:a {:a 1 :b 2})            ; => 1 (atalho para `get`)
(assoc {:a 1 :b 2} :c 3)    ; => {:a 1 :b 2 :c 3}
(dissoc {:a 1 :b 2} :b)     ; => {:a 1}
```

## Comparação entre Coleções

| **Tipo**     | **Uso principal**             | **Vantagens**                                  |
| ------------ | ----------------------------- | ---------------------------------------------- |
| **Lista**    | Sequências de dados ordenados | Lazy, simples e eficiente para uso como pilha. |
| **Vetor**    | Dados indexados               | Acesso rápido por índice.                      |
| **Conjunto** | Dados únicos                  | Garantia de unicidade.                         |
| **Mapa**     | Associação chave-valor        | Lookup eficiente por chave.                    |

## Exemplo Prático com Coleções

**Cenário:** Suponha que você tenha uma lista de usuários e deseja processar essas informações.

```clojure
(def usuarios
  [{:nome "Alice" :idade 30}
   {:nome "Bob" :idade 25}
   {:nome "Carol" :idade 35}])

;; Filtrando usuários acima de 30 anos:
(filter #(> (:idade %) 30) usuarios)
;; => ({:nome "Carol", :idade 35})

;; Extraindo apenas os nomes:
(map :nome usuarios)
;; => ("Alice" "Bob" "Carol")

;; Criando um conjunto de idades únicas:
(set (map :idade usuarios))
;; => #{25 30 35}
```

## Transformações com `map`, `filter`, e `reduce`

Clojure oferece funções de ordem superior para manipular coleções:

1. **`map`**: Transforma elementos.
2. **`filter`**: Filtra elementos.
3. **`reduce`**: Combina elementos em um único valor.

### **Exemplo com Vetores**

```clojure
(def numeros [1 2 3 4 5])

;; Multiplica cada número por 2
(map #(* % 2) numeros)
;; => (2 4 6 8 10)

;; Filtra números ímpares
(filter odd? numeros)
;; => (1 3 5)

;; Soma todos os números
(reduce + numeros)
;; => 15
```
