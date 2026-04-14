# Exercicios

Pratique o que aprendeu nas anotacoes e exemplos.

## Como usar

1. Leia o enunciado do exercicio
2. Tente resolver no REPL antes de buscar ajuda
3. Consulte `src/content/docs/` se precisar revisar algum conceito

## Exercicio 01 - Trabalhando com Colecoes

**Objetivo:** Praticar operacoes fundamentais sobre colecoes em Clojure.

**Instrucao:**
Dado o vetor `pedidos`, resolva cada item usando funcoes de colecao como `filter`, `map`, `reduce` e `sort-by`.

1. Filtre apenas os pedidos com status `:pago`
2. Extraia somente os nomes dos produtos dos pedidos pagos
3. Calcule o total dos valores dos pedidos pagos com `reduce`
4. Ordene todos os pedidos pelo valor em ordem crescente

**Criterios de sucesso:**

- [ ] Cada item e resolvido com uma unica expressao
- [ ] Usa `filter`, `map`, `reduce` e `sort-by` corretamente
- [ ] O resultado do item 3 e um numero, nao uma colecao
- [ ] Todos os testes passam no REPL

## Exercicio 02 - Concorrencia com Atom

**Objetivo:** Usar `atom` e `swap!` para gerenciar estado compartilhado com seguranca.

**Instrucao:**
Implemente um sistema simples de fila de mensagens usando `atom`.

**Criterios de sucesso:**

- [ ] Usa `atom` para encapsular o estado mutavel
- [ ] Todas as atualizacoes usam `swap!`
- [ ] O estado final tem `:fila []` e `:processadas 5`
- [ ] Funciona corretamente mesmo com chamadas concorrentes
