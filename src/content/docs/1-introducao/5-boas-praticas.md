---
title: "Boas Praticas"
description: "Reuna orientacoes de legibilidade, design funcional e organizacao para escrever Clojure melhor."
lastUpdated: 2026-04-13
sidebar:
  order: 5
tags: ["clojure","boas praticas","design","qualidade"]
---

## Complexidade Ciclomática

A complexidade ciclomática é uma métrica usada em engenharia de software para medir a complexidade de um programa. Ela é calculada com base no grafo de controle de fluxo do programa, onde os nós representam blocos de código e as arestas representam os caminhos possíveis entre esses blocos. A fórmula básica para calcular a complexidade ciclomática é:

```
M = E - N + 2P
```

Onde:

- `M` é a complexidade ciclomática.
- `E` é o número de arestas no grafo de controle de fluxo.
- `N` é o número de nós no grafo de controle de fluxo.
- `P` é o número de componentes conectados (geralmente 1 para um único programa).

### Aplicação em Clojure

Em Clojure, a complexidade ciclomática pode ser aplicada da mesma forma que em outras linguagens de programação. No entanto, devido à natureza funcional de Clojure, algumas práticas podem ajudar a manter a complexidade ciclomática baixa:

1. **Funções Pequenas e Simples**: Escreva funções pequenas que realizam uma única tarefa. Isso facilita o entendimento e a manutenção do código.
2. **Uso de Recursão**: Em vez de loops complexos, utilize recursão e funções de ordem superior como `map`, `reduce` e `filter`.
3. **Desconstrução de Problemas**: Divida problemas complexos em partes menores e resolva cada parte com uma função específica.
4. **Evite Condicionais Aninhadas**: Utilize estruturas como `cond` e `case` para evitar condicionais aninhadas que aumentam a complexidade.

Exemplo de uma função simples em Clojure:

```clojure
(defn sum-of-squares [numbers]
    (reduce + (map #(* % %) numbers)))
```

Essa função tem uma complexidade ciclomática baixa, pois não possui ramificações complexas ou loops aninhados.

Manter a complexidade ciclomática baixa em Clojure, assim como em qualquer outra linguagem, resulta em código mais legível, fácil de manter e menos propenso a erros.

## Funções Lambda

Funções lambda, também conhecidas como funções anônimas, são funções que não possuem um nome explícito. Em Clojure, elas são frequentemente usadas para operações simples e de curta duração. Vamos explorar o conceito e algumas boas práticas de uso.

Em Clojure, uma função lambda pode ser definida usando a notação #(). Por exemplo:

Neste exemplo, #(\* % %) é uma função lambda que recebe um argumento (representado por %) e retorna o quadrado desse argumento.

### Boas Práticas de Uso

1. **Clareza e Simplicidade:** Use funções lambda para operações simples e curtas. Se a lógica da função começar a ficar complexa, considere definir uma função nomeada para melhorar a legibilidade.

2. **Evite Lambdas Complexas:** Funções lambda devem ser usadas para expressões simples. Se a função lambda começa a ter múltiplas operações ou lógica complexa, é melhor definir uma função nomeada.

3. **Documentação e Comentários:** Embora funções lambda sejam úteis para operações rápidas, elas podem ser difíceis de entender sem contexto. Adicione comentários explicativos se a intenção da função não for imediatamente clara.

4. **Reutilização:** Se você perceber que a mesma função lambda está sendo usada em vários lugares, considere movê-la para uma função nomeada. Isso facilita a manutenção e a reutilização do código.

### Exemplo de Uso

Um exemplo que ilustra o uso de uma função lambda e uma função nomeada para comparação:

```clojure
;; Usando uma função lambda para calcular o quadrado dos números
(def numbers [1 2 3 4 5])
(def squares (map #(* % %) numbers))
(println "Quadrados usando função lambda:" squares)

;; Definindo uma função nomeada para calcular o quadrado dos números
(defn square [x]
  (* x x))
(def named-squares (map square numbers))
(println "Quadrados usando função nomeada:" named-squares)
```

No primeiro exemplo, uma função lambda é utilizada para calcular o quadrado dos números. No segundo exemplo, é definido uma função nomeada square para a mesma operação. A função nomeada pode ser mais clara e reutilizável em diferentes partes do código.

Funções lambda são uma ferramenta poderosa em Clojure, permitindo que você escreva código conciso e expressivo.
No entanto, é importante usá-las com cuidado para manter a clareza e a manutenibilidade do código.
Seguindo as boas práticas mencionadas, você pode aproveitar ao máximo as funções lambda sem comprometer a qualidade do seu código.

