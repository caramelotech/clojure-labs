# 🧪 Exercícios

Pratique o que aprendeu nas anotações e exemplos.

## Como usar

1. Leia o enunciado do exercício
2. Tente resolver no REPL antes de buscar ajuda
3. Consulte as anotações em `/notes` se precisar revisar algum conceito

## Exercício 01 - Trabalhando com Coleções

**Objetivo:** Praticar operações fundamentais sobre coleções em Clojure.

**Instrução:**
Dado o seguinte vetor de pedidos:

```clojure
(def pedidos
  [{:id 1 :produto "caneta" :valor 2.50 :status :pago}
   {:id 2 :produto "caderno" :valor 15.00 :status :pendente}
   {:id 3 :produto "mochila" :valor 89.90 :status :pago}
   {:id 4 :produto "régua" :valor 3.00 :status :cancelado}
   {:id 5 :produto "lápis" :valor 1.50 :status :pago}])
```

Resolva cada item usando funções de coleção (`filter`, `map`, `reduce`, `sort-by`):

1. Filtre apenas os pedidos com status `:pago`
2. Extraia somente os nomes dos produtos dos pedidos pagos
3. Calcule o total dos valores dos pedidos pagos com `reduce`
4. Ordene todos os pedidos pelo valor em ordem crescente

**Critérios de sucesso:**

- [ ] Cada item é resolvido com uma única expressão (sem variáveis intermediárias)
- [ ] Usa `filter`, `map`, `reduce` e `sort-by` corretamente
- [ ] O resultado do item 3 é um número, não uma coleção
- [ ] Todos os testes passam no REPL

## Exercício 02 - Concorrência com Atom

**Objetivo:** Usar `atom` e `swap!` para gerenciar estado compartilhado com segurança.

**Instrução:**
Implemente um sistema simples de fila de mensagens usando `atom`:

1. Crie um `atom` que contenha um mapa `{:fila [] :processadas 0}`
2. Implemente uma função `enfileirar` que adiciona uma mensagem à fila com `swap!`
3. Implemente uma função `processar` que remove a primeira mensagem e incrementa `:processadas`
4. Teste com 5 mensagens, processando-as uma a uma, e verifique o estado final

**Critérios de sucesso:**

- [ ] Usa `atom` para encapsular o estado mutável
- [ ] Todas as atualizações usam `swap!` (nunca `reset!` diretamente)
- [ ] O estado final tem `:fila []` e `:processadas 5`
- [ ] Funciona corretamente mesmo com chamadas concorrentes

**[← Voltar ao README Principal](../README.md)**
