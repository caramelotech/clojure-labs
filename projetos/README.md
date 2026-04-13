# 🚀 Projetos

Projetos práticos para consolidar o aprendizado de Clojure.

## Como usar

1. Leia o objetivo e os requisitos antes de começar
2. Implemente no projeto Leiningen em `/codes` ou crie um projeto novo com `lein new`
3. Use o REPL para validar cada etapa durante o desenvolvimento

## Projeto 01 - Sistema de Gerenciamento de Tarefas

**Objetivo:** Construir um sistema de tarefas em memória usando as estruturas de dados e princípios de imutabilidade do Clojure.

**Contexto:**
Você vai implementar um gerenciador de tarefas via REPL. O sistema precisa manter estado de forma segura usando `atom`, demonstrando os princípios de programação funcional e concorrência segura do Clojure.

**Requisitos:**

- [ ] Estado global encapsulado em um único `atom` com a estrutura `{:tarefas [] :contador 0}`
- [ ] Função `adicionar-tarefa` que recebe título e cria uma tarefa com `:id`, `:titulo` e `:status :pendente`
- [ ] Função `concluir-tarefa` que atualiza o status de uma tarefa por `:id` para `:concluida`
- [ ] Função `listar-pendentes` que retorna apenas as tarefas com status `:pendente`
- [ ] Função `resumo` que retorna um mapa com contagem de pendentes e concluídas

**Entregável esperado:**

Um namespace `tarefas.core` com as funções acima funcionando no REPL:

```clojure
(adicionar-tarefa "Aprender Clojure")
(adicionar-tarefa "Praticar atoms")
(concluir-tarefa 1)
(resumo)
;; => {:pendentes 1 :concluidas 1}
```

**[← Voltar ao README Principal](../README.md)**
