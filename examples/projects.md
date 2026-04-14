# Projetos

Projetos praticos para consolidar o aprendizado de Clojure.

## Como usar

1. Leia o objetivo e os requisitos antes de comecar
2. Implemente no projeto Leiningen em `examples/` ou crie um projeto novo com `lein new`
3. Use o REPL para validar cada etapa durante o desenvolvimento

## Projeto 01 - Sistema de Gerenciamento de Tarefas

**Objetivo:** Construir um sistema de tarefas em memoria usando estruturas de dados e principios de imutabilidade do Clojure.

**Contexto:**
Voce vai implementar um gerenciador de tarefas via REPL. O sistema precisa manter estado de forma segura usando `atom`.

**Requisitos:**

- [ ] Estado global encapsulado em um unico `atom` com a estrutura `{:tarefas [] :contador 0}`
- [ ] Funcao `adicionar-tarefa`
- [ ] Funcao `concluir-tarefa`
- [ ] Funcao `listar-pendentes`
- [ ] Funcao `resumo` com contagem de pendentes e concluidas
