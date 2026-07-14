# Sintaxe Basica

# Sintaxe Básica

## Sintaxe e Criação de Funções em Clojure

### Estrutura de uma Função

Em Clojure, uma função é definida usando a macro `defn`. A estrutura básica de uma função é composta por:

1. **Nome da função**: Identificador único para a função.
2. **Parâmetros**: Lista de argumentos que a função recebe.
3. **Corpo da função**: Bloco de código que define o comportamento da função.
4. **Valor de retorno**: O resultado da função, que é o valor da última expressão avaliada no corpo.

Aqui está um exemplo básico:

```clojure
(defn saudacao [nome]
  (str "Olá, " nome "!"))
```

- **`defn`**: Define uma função.
- **`saudacao`**: Nome da função.
- **`[nome]`**: Lista de parâmetros (neste caso, um único parâmetro chamado `nome`).
- **`(str "Olá, " nome "!")`**: Corpo da função, que concatena strings e retorna o resultado.

Chamando a função:

```clojure
(saudacao "Maria") ; => "Olá, Maria!"
```

### Funções Anônimas

Funções sem nome podem ser criadas usando `fn` ou a sintaxe abreviada `#()`.

```clojure
(def dobrar (fn [x] (* 2 x)))
(dobrar 4) ; => 8

;; Sintaxe abreviada
(#(* 2 %) 4) ; => 8
```

### Funções com Múltiplas Expressões

O corpo da função pode ter várias expressões. O valor de retorno é o resultado da última expressão.

```clojure
(defn calcular [x y]
  (println "Calculando...")
  (+ x y))
(calcular 2 3) ; Imprime "Calculando..." e retorna 5
```

### Funções com Documentação

Você pode adicionar uma docstring para documentar a função.

```clojure
(defn quadrado
  "Retorna o quadrado de um número."
  [x]
  (* x x))
```

Para acessar a documentação:

```clojure
(doc quadrado)
```

## Exemplo Completo: Função para Calcular Média

Aqui está um exemplo de uma função pura que calcula a média de uma lista de números:

```clojure
(defn media [numeros]
  (let [soma (reduce + numeros)
        quantidade (count numeros)]
    (/ soma quantidade)))

(media [10 20 30 40]) ; => 25.0
```

- **`let`**: Usado para definir variáveis locais (`soma` e `quantidade`).
- **`reduce`**: Soma todos os elementos da lista.
- **`count`**: Conta o número de elementos.
- **`/`**: Divide a soma pela quantidade para calcular a média.

## Programação Funcional em Clojure

1. **Use Funções Puras**:
   Funções puras são aquelas que não dependem de estado externo e não causam efeitos colaterais. Isso torna o código mais previsível e fácil de testar.

   ```clojure
   (defn quadrado [x]
     (* x x))
   ```

2. **Preze pela Imutabilidade**:
   Em Clojure, dados são imutáveis por padrão. Em vez de modificar um valor existente, crie um novo valor a partir do anterior.

   ```clojure
   (def lista [1 2 3])
   (def nova-lista (conj lista 4)) ; Cria uma nova lista com 4 adicionado
   nova-lista ; => [1 2 3 4]
   ```

3. **Recursão em vez de Loops**:
   Em linguagens funcionais, a recursão é frequentemente usada no lugar de loops imperativos. A função `recur` é usada para otimizar chamadas recursivas.

   ```clojure
   (defn fatorial [n]
     (loop [contador n, acumulador 1]
       (if (<= contador 1)
         acumulador
         (recur (dec contador) (* acumulador contador)))))
   ```

4. **Higher-Order Functions**:
   Funções que recebem ou retornam outras funções são comuns em Clojure. Exemplos incluem `map`, `filter`, e `reduce`.

   ```clojure
   (defn aplicar-funcao [f x]
     (f x))
   (aplicar-funcao #(* 2 %) 5) ; => 10
   ```

5. **Use `map`, `filter`, e `reduce`**:
   Essas funções são fundamentais para trabalhar com coleções de forma funcional.

   ```clojure
   (def numeros [1 2 3 4 5])
   (map #(* % %) numeros) ; => (1 4 9 16 25)
   (filter even? numeros) ; => (2 4)
   (reduce + numeros) ; => 15
   ```

6. **Evite Efeitos Colaterais**:
   Em programação funcional, efeitos colaterais (como modificar estado global) são evitados. Em Clojure, use estruturas imutáveis e funções puras sempre que possível.

7. **Composição de Funções**:
   Use `comp` para criar novas funções a partir da composição de outras.

   ```clojure
   (def dobrar-e-incrementar (comp inc #(* 2 %)))
   (dobrar-e-incrementar 5) ; => 11
   ```

## Nomeando funções

Na programação funcional, a forma declarativa é geralmente preferida sobre a forma imperativa ao nomear funções. Aqui estão as diferenças entre as duas abordagens:

### Forma Imperativa

- **Foco no "como"**: Descreve passo a passo como a tarefa deve ser realizada.
- **Exemplo de nome**: `calcular-desconto` (indica que a função está realizando uma ação específica).

### Forma Declarativa

- **Foco no "o quê"**: Descreve o que a função faz, sem detalhar como a tarefa é realizada.
- **Exemplo de nome**: `desconto` (indica o resultado ou o conceito que a função representa).

### Programação Funcional

Na programação funcional, a forma declarativa é preferida porque:

- **Clareza**: Nomes declarativos são mais claros e concisos, facilitando a compreensão do código.
- **Imutabilidade**: A programação funcional enfatiza a imutabilidade e a ausência de efeitos colaterais, o que se alinha bem com nomes declarativos.
- **Composição**: Funções declarativas são mais fáceis de compor e reutilizar.

### Exemplo

Imperativo:

```clojure
(defn calcular-desconto
  [valor-bruto]
  (* valor-bruto 0.9))
```

Declarativo:

```clojure
(defn desconto
  [valor-bruto]
  (* valor-bruto 0.9))
```

No exemplo acima, `desconto` é um nome mais declarativo e apropriado para programação funcional, pois descreve o que a função representa, não como ela faz isso.

