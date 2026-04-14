# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

This is an educational repository about Clojure, published as a documentation site using Astro + Starlight. It has two independent parts:

- `src/content/docs/` - Markdown notes rendered as the Starlight site (in Portuguese)
- `examples/` - A Leiningen project with hands-on Clojure code organized by topic

## Site (Astro + Starlight)

```bash
npm install
npm run dev      # http://localhost:4321
npm run build    # Production build
npm run preview  # Preview production build
```

The site is deployed to `https://caramelotech.com.br/clojure-labs`. The `base` path in `astro.config.mjs` must stay as `/clojure-labs`.

## Examples (Leiningen project)

```bash
cd examples
lein repl    # Start REPL (default ns: codes.core)
lein run
```

Namespaces follow the topic structure: `codes.*` for introductory syntax/functions, `collections.*` for advanced collection examples.

## Content structure

The docs trilha is organized into numbered sections that control sidebar order:

```
src/content/docs/
├── indice.md
├── 1-introducao/   - Getting started, syntax, IDE shortcuts, threading, best practices
├── 2-colecoes/     - Collections: intro, reduce, maps, sorting, lazy vs eager
├── 3-refs/         - Concurrency: queues, threads, atoms, mapv/partial
└── x-tests/        - Testing with clojure.test
```

## Content conventions

- All documentation is written in Portuguese (pt-BR)
- Each doc file uses Starlight frontmatter (`title`, `description`, `lastUpdated`, `sidebar.order`, `tags`)
- Do not repeat the `title` as `# h1` - Starlight renders it automatically
- Use `##` and `###` for sections
- Clojure code examples include expected output in comments using `;;`
- One concept per namespace in `examples/src/`
- Do not use `---` to separate sections in Markdown files

## sidebar.order

**`sidebar.order` é sequencial por diretório**, não global. A ordem entre seções é controlada pelo array `sidebar` em `astro.config.mjs`. Dentro de cada pasta, numere os arquivos a partir de 1.

Para adicionar uma nova seção superior (ex: `nova-categoria/`):
1. Crie o diretório em `src/content/docs/nova-categoria/`
2. Adicione um arquivo `index.md` como página de entrada
3. Adicione uma entrada `autogenerate` em `astro.config.mjs`:
   ```javascript
   {
     label: "Título da Seção",
     autogenerate: { directory: "nova-categoria" },
   }
   ```
