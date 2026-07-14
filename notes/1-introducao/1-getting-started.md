# Configurando o ambiente

# Configurando o ambiente

## Clojure

Clojure é uma linguagem de programação funcional moderna, dinâmica e de propósito geral. Criada por Rich Hickey e lançada em 2007, Clojure é conhecida por sua simplicidade, imutabilidade e foco em programação funcional.
Algumas características importantes:

1. **Dialeto LISP**: Clojure é um dialeto da família LISP, uma das primeiras linguagens de programação funcional. Isso significa que ela herda a sintaxe baseada em expressões S (s-expressions), que é extremamente consistente e poderosa.
2. **Roda na JVM**: Clojure é executada na Java Virtual Machine (JVM), o que permite interoperabilidade com bibliotecas e frameworks Java. Isso a torna uma escolha popular para desenvolvimento de aplicações empresariais e sistemas distribuídos.
3. **Imutabilidade por Padrão**: Estruturas de dados em Clojure são imutáveis, o que facilita a escrita de código seguro e livre de efeitos colaterais.
4. **Concorrência Simplificada**: Clojure oferece ferramentas poderosas para lidar com concorrência, como atoms, agents, refs e channels (via bibliotecas como `core.async`).
5. **Sintaxe Concisa**: A sintaxe de Clojure é minimalista e expressiva, o que permite escrever código conciso e fácil de entender.

## Pré-requisitos

- Java
- [Clojure](https://clojure.org/guides/install_clojure)
- [Leiningen](https://leiningen.org/)
- [Cursive](https://cursive-ide.com/) para o Intelijj
- [Calva](https://marketplace.visualstudio.com/items?itemName=betterthantomorrow.calva) para VSCode

### Leiningen (Gerenciador de Projetos)

Leiningen é uma ferramenta para gerenciamento de projetos Clojure, útil para automatizar tarefas.

- **Instalação no Linux/macOS:**
  ```sh
  curl -O https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
  chmod +x lein
  sudo mv lein /usr/local/bin/
  lein
  ```
- **Windows:** Baixe de [https://github.com/technomancy/leiningen/releases](https://github.com/technomancy/leiningen/releases).
- **Verifique a instalação:**
  ```sh
  lein version
  ```

## O que é o REPL?

O REPL (**Read-Eval-Print Loop**) é um ambiente interativo para executar código Clojure em tempo real. Ele é essencial para testar funções rapidamente e desenvolver de forma interativa.

- **Executando o REPL com Leiningen:**
  ```sh
  lein repl
  ```
- **Executando o REPL com Clojure CLI:**
  ```sh
  clojure
  ```
- **Testando comandos no REPL:**
  ```clojure
  (+ 1 2 3)  ; Retorna 6
  ```

## Configuração de Ambiente

Embora seja possível programar Clojure apenas com um terminal e um editor de texto, algumas ferramentas tornam o desenvolvimento mais produtivo:

- VS Code + Calva
- Emacs + CIDER
- IntelliJ IDEA + Cursive
- Neovim + Conjure

