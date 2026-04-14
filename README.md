<p align="center">
  <img src="./public/assets/clojure-logo.png" alt="Clojure Logo" width="100" height="100">
</p>

# Clojure Labs

Base de conhecimento sobre **Clojure** da Caramelo Tech, com foco em programacao funcional para iniciantes.

[![GitHub Pages](https://img.shields.io/badge/GitHub-Pages-blue)](https://caramelotech.github.io/clojure-labs)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Contributions Welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg)](.github/CONTRIBUTING.md)

## O que voce vai encontrar

- Anotacoes sobre fundamentos de Clojure e programacao funcional
- Exemplos praticos em um projeto Leiningen
- Exercicios hands-on para fixacao de conceitos
- Mini projetos aplicados

## Conteudo

| Topico | Descricao | Link |
| --- | --- | --- |
| Introducao ao Clojure | Configuracao, sintaxe, threading e boas praticas | [1-introducao/](src/content/docs/1-introducao/index.md) |
| Colecoes | Vetores, mapas, reduce, ordenacao, lazy vs eager | [2-colecoes/](src/content/docs/2-colecoes/index.md) |
| Refs, Atomos e Concorrencia | PersistentQueue, threads, atoms e `swap!` | [3-refs/](src/content/docs/3-refs/index.md) |
| Testes | `clojure.test`, `deftest` e boundary tests | [x-tests/](src/content/docs/x-tests/index.md) |
| Exemplos de codigo | Projeto Leiningen com exemplos por topico | [examples/README.md](examples/README.md) |

## Estrutura do repositorio

```text
clojure-labs/
├── src/content/docs/   -> Anotacoes e estudos publicados no site
├── examples/           -> Projeto Leiningen, exercicios e projetos praticos
├── public/assets/      -> Imagens publicadas pelo site
└── .github/            -> Workflows, templates e guias de contribuicao
```

## Como usar

1. Comece pelas anotacoes em `src/content/docs/`
2. Explore o projeto Leiningen em `examples/` via REPL
3. Resolva os desafios em `examples/exercises.md`
4. Construa os projetos em `examples/projects.md`

## Rodando localmente

```bash
npm install
npm run dev
```

O servidor local fica em `http://localhost:4321`.

Outros comandos uteis:

```bash
npm run build
npm run preview
```

Versao publicada:

`https://caramelotech.github.io/clojure-labs`

## Trabalhando com os exemplos Clojure

O diretorio `examples/` continua sendo um projeto Leiningen funcional.

```bash
cd examples
lein repl
lein run
```

## Adicionando notas

Novas anotacoes devem ser criadas em `src/content/docs/`, preservando as subpastas da trilha quando fizer sentido.

Exemplo de frontmatter padrao Starlight:

```md
---
title: "Titulo da nota"
description: "Resumo curto explicando o foco da pagina."
lastUpdated: 2026-01-01
sidebar:
  order: 4
tags: ["clojure", "tema", "iniciante"]
---
```

## Contribuicao

Contribuicoes sao bem-vindas. Veja o [Guia de Contribuicao](.github/CONTRIBUTING.md) para detalhes.

## Licenca

MIT

## Contribuidores

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="http://felurye.com.br"><img src="https://avatars.githubusercontent.com/u/37555137?v=4?s=100" width="100px;" alt="Daniele Araujo"/><br /><sub><b>Daniele Araujo</b></sub></a><br /><a href="#code-felurye" title="Code">💻</a> <a href="#content-felurye" title="Content">🖋</a> <a href="#example-felurye" title="Examples">💡</a> <a href="#ideas-felurye" title="Ideas, Planning, & Feedback">🤔</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://www.linkedin.com/in/samantha-kellen/"><img src="https://avatars.githubusercontent.com/u/42253793?v=4?s=100" width="100px;" alt="Samantha Kellen"/><br /><sub><b>Samantha Kellen</b></sub></a><br /><a href="#code-SamGomes52" title="Code">💻</a> <a href="#content-SamGomes52" title="Content">🖋</a> <a href="#example-SamGomes52" title="Examples">💡</a> <a href="#ideas-SamGomes52" title="Ideas, Planning, & Feedback">🤔</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->
