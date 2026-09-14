# Exemplos

Esta pasta concentra o material pratico do repositorio.

## Estrutura do projeto

```text
examples/
├── src/
│   ├── codes/         -> Exemplos organizados por topico
│   └── collections/   -> Exemplos de colecoes avancadas
├── test/              -> Espaco para testes do projeto
├── project.clj        -> Configuracao do projeto Leiningen
├── exercises.md       -> Exercicios da trilha
└── projects.md        -> Projetos praticos
```

## Como executar

```bash
cd examples
lein repl
lein run
```

## Indice

| Caminho            | Tipo       | Descricao                                                               |
| ------------------ | ---------- | ----------------------------------------------------------------------- |
| `src/codes/`       | Exemplos   | Namespaces introdutorios sobre sintaxe, funcoes e estruturas basicas    |
| `src/collections/` | Exemplos   | Namespaces praticos sobre colecoes, group-by, lazy vs eager e ordenacao |
| `exercises.md`     | Exercicios | Desafios para praticar os conceitos das notas                           |
| `projects.md`      | Projetos   | Propostas de mini projetos para consolidar o aprendizado                |

## Ordem de leitura sugerida

Cada namespace foca em um conceito so. Va na ordem abaixo, testando no REPL a cada arquivo.

**`src/codes/` (fundamentos):**

1. `core.clj` - ponto de entrada do REPL (`:init-ns` no `project.clj`)
2. `vectors.clj` - `def`, redefinicao de simbolos, vetores e imutabilidade
3. `vectors_get_and_update.clj` - `get`, `assoc`, `update` em vetores
4. `maps_vals_and_keys.clj` - mapas, mapas aninhados, `get-in`, threading `->`
5. `destructuring_e_composicao.clj` - desestruturacao em parametros, `->>`, `comp`
6. `functions.clj` - `defn`, docstring, formas equivalentes de escrever a mesma funcao
7. `conditions.clj` - `if`, `let`, evoluindo uma funcao em etapas
8. `lambda.clj` - funcoes anonimas, `fn`, `#()`, higher-order functions
9. `map_filter_reduce.clj` - `map`, `filter` e `reduce` nativos combinados
10. `symbols_and_namespace.clj` - escopo de `def`/`let`, tipos numericos (`N`, `M`)

**`src/collections/` (avancado, com dados compartilhados via `db.clj`):**

1. `db.clj` - dados de apoio (pedidos fake) usados pelos exemplos seguintes
2. `group_by.clj` - agrupando pedidos por usuario com `group-by`
3. `logic.clj` - funcoes prontas de resumo por usuario, usadas pelos exemplos seguintes
4. `sort_by.clj` - `sort-by`, `reverse`, `get-in`, `first`/`second`/`nth`/`take`
5. `lazy_eager.clj` - `filter` vs `keep`, avaliacao lazy, chunking
6. `map.clj` - reimplementando `map` do zero com recursao e `recur`
7. `reduce.clj` - reimplementando `reduce` do zero com `recur`/`loop`
