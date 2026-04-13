# Guia de Contribuição

Obrigado por querer contribuir com o Clojure Labs!

## O que pode ser contribuído

- Melhorias e correções nas anotações (`/notes`)
- Novos exemplos práticos no projeto Leiningen (`/codes`)
- Exercícios adicionais em `/exercicios`
- Projetos práticos em `/projetos`

## Processo

1. Crie uma branch a partir de `main` seguindo o padrão:

   ```
   feature/descricao-curta
   fix/descricao-curta
   docs/descricao-curta
   ```

2. Faça commits atômicos com mensagens no padrão de Conventional Commits:

   ```
   feat: adicionar anotações sobre protocols
   fix: corrigir exemplo de reduce com mapa
   docs: melhorar introdução sobre atoms
   ```

   Tipos válidos: `feat`, `fix`, `docs`, `style`, `refactor`, `chore`

3. Abra um Pull Request usando o template disponível e aguarde revisão.

4. Após aprovação, o merge será feito por um mantenedor.

## Padrões de conteúdo

### Anotações (Markdown)

- Escreva em português
- Use títulos hierárquicos (`##`, `###`)
- Inclua exemplos de código Clojure com o resultado esperado nos comentários (`;;`)
- Adicione o arquivo no subdiretório correto dentro de `/notes`
- Atualize o `README.md` do subdiretório ao adicionar um novo arquivo
- Atualize `notes/README.md` ao adicionar um novo subdiretório

### Exemplos (`/codes`)

- Um conceito por namespace
- Inclua comentários explicando o que cada expressão faz
- Teste no REPL antes de submeter

### Exercícios (`/exercicios`)

- Descreva claramente o objetivo
- Inclua um trecho de código inicial quando necessário
- Inclua critérios de sucesso em formato de checklist

### Projetos (`/projetos`)

- Descreva o objetivo e o contexto
- Liste os requisitos em formato de checklist
- Inclua ao menos um exemplo de entregável esperado

## Dúvidas?

Abra uma issue com a tag `question`.
