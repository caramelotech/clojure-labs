# CLAUDE.md

Orientações para agentes de IA que trabalham neste repositório.

## Visão geral

Repositório de **conteúdo puro** do Clojure Labs (Caramelo Tech). Contém notas em Markdown e um projeto Leiningen de exemplos - não há build de site, dependências Node ou linting.

As notas são publicadas no site do Caramelo Labs em `https://caramelotech.com.br/labs/clojure/`. Quem monta e publica o site é o repositório hub [labs](https://github.com/caramelotech/labs): a cada push em `main` que altere `notes/` ou `sidebar.json`, o workflow `.github/workflows/notify-hub.yml` dispara o rebuild do hub via `repository_dispatch`.

## Estrutura

```
notes/            # Notas em Markdown puro - cada arquivo vira uma página no site
  index.md        # Página de entrada do lab no site
  indice.md       # Índice de todas as anotações
  1-introducao/   # Ambiente, sintaxe, atalhos, threading, boas práticas
  2-colecoes/     # Vetores, mapas, reduce, ordenação, lazy vs eager
  3-refs/         # Filas, threads, átomos e retries
  x-tests/        # clojure.test
sidebar.json      # Seções da barra lateral no site (labels e ordem)
examples/         # Projeto Leiningen com namespaces práticos (não publicado no site)
```

## Escrevendo notas

As notas NÃO usam frontmatter. Regras:

- **A primeira linha da nota deve ser o título como `# H1`** - no site, ela vira o `title` da página (o hub injeta o frontmatter automaticamente)
- Use `##` e `###` para as demais seções (apenas um `#` por arquivo, na primeira linha)
- Prefixo numérico no nome do arquivo controla a ordem na barra lateral dentro da pasta: `1-nome.md`, `2-nome.md`
- Imagens ficam junto das notas (ex: `notes/secao/assets/img.png`), referenciadas com caminho relativo em sintaxe Markdown: `![descrição](./assets/img.png)` - nunca use tags HTML `<img>` nem caminhos absolutos
- Links para outras notas do site usam o caminho completo: `/labs/clojure/<secao>/<nota>/`
- Ao adicionar uma nota, considere atualizá-la em `notes/indice.md`
- Exemplos de código Clojure incluem a saída esperada em comentários `;;`

### Nova seção de tema

1. Crie a subpasta em `notes/nova-secao/` com as notas
2. Adicione a seção em `sidebar.json`:
   ```json
   { "label": "Título da Seção", "directory": "nova-secao" }
   ```

## Exemplos (projeto Leiningen)

```bash
cd examples
lein repl    # REPL (ns padrão: codes.core)
lein run
```

Namespaces seguem a estrutura dos temas: `codes.*` para sintaxe/funções introdutórias, `collections.*` para coleções. Um conceito por namespace.

## Convenções e preferências

- Idioma: português brasileiro (pt-BR)
- Usar hífens (-) em vez de travessões (—) em todos os textos
- Em Markdown, NÃO usar `---` para separar seções (exceto para notas/atribuições no final do arquivo)
- **Git:** Nunca fazer `git commit` ou `git push` automaticamente - apenas quando explicitamente solicitado

## Recursos úteis

- [labs (hub)](https://github.com/caramelotech/labs) - estrutura do site, script de fetch e deploy
