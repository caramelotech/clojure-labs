# Guia de Contribuicao

Obrigado por querer contribuir com o Clojure Labs.

## O que pode ser contribuido

- Melhorias e correcoes nas anotacoes em `src/content/docs/`
- Novos exemplos praticos no projeto Leiningen em `examples/`
- Exercicios adicionais em `examples/exercises.md`
- Projetos praticos em `examples/projects.md`
- Melhorias no site Astro + Starlight

## Processo

1. Crie uma branch a partir de `main` seguindo o padrao:

   ```text
   feature/descricao-curta
   fix/descricao-curta
   docs/descricao-curta
   ```

2. Faca commits atomicos com mensagens no padrao de Conventional Commits:

   ```text
   feat: adicionar anotacoes sobre protocols
   fix: corrigir exemplo de reduce com mapa
   docs: melhorar introducao sobre atoms
   ```

3. Abra um Pull Request usando o template disponivel.

4. Apos aprovacao, o merge sera feito por um mantenedor.

## Rodando o site localmente

```bash
npm install
npm run dev
```

O site fica disponivel em `http://localhost:4321`.

Para validar antes de abrir o PR:

```bash
npm run build
npm run preview
```

## Padroes de conteudo

### Anotacoes em `src/content/docs/`

- Escreva em portugues
- Use titulos hierarquicos (`##`, `###`)
- Inclua exemplos de codigo Clojure com resultado esperado nos comentarios (`;;`)
- Preservar as subpastas da trilha quando fizer sentido
- Use frontmatter Starlight completo

### Exemplos em `examples/`

- Um conceito por namespace
- Inclua comentarios explicando o que cada expressao faz
- Teste no REPL antes de submeter

### Exercicios em `examples/exercises.md`

- Descreva claramente o objetivo
- Inclua trecho de codigo inicial quando necessario
- Inclua criterios de sucesso em formato de checklist

### Projetos em `examples/projects.md`

- Descreva o objetivo e o contexto
- Liste os requisitos em formato de checklist
- Inclua ao menos um exemplo de entregavel esperado

## Duvidas

Abra uma issue com a tag `question`.
