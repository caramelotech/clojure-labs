# Introducao ao clojure.test

## 🎯 O que testar: funções puras

Antes de sair escrevendo `deftest` para tudo, vale entender o que faz sentido testar. O alvo ideal de um teste unitário é uma **função pura**: dada a mesma entrada, ela sempre devolve a mesma saída, e não tem nenhum efeito colateral (não escreve em arquivo, não faz requisição HTTP, não lê nem altera estado global).

```clojure
(defn soma [a b]
  (+ a b))
```

Chame `soma` com `1` e `2` uma vez, mil vezes, em qualquer ordem: o resultado é sempre `3`. É isso que torna o teste confiável e repetível, sem depender de rede, banco de dados ou timing de execução.

Essa ideia já apareceu na nota [Sintaxe Básica](/labs/clojure/introducao/2-sintaxe-basica/), na seção sobre programação funcional - vale revisá-la se o conceito de função pura ainda não estiver claro antes de seguir para o `deftest`.

## ✅ Definir um teste com `deftest`

No namespace de testes:

```clojure
(ns meu-app.core-test
  (:require [clojure.test :refer :all]
            [meu-app.core :as core]))

(deftest soma-deve-funcionar
  (is (= 4 (core/soma 2 2))))
```

Use `lein test` ou execute no REPL:

```clojure
(run-tests)
```

## 📚 Utilizar `:refer :all`

Permite acessar todas as vars de um namespace:

```clojure
(ns meu-ns
  (:require [clojure.set :refer :all]))

(union #{1 2} #{2 3})
;; => #{1 2 3}
```

Use com cuidado para evitar conflitos de nomes.

## 📏 Boundary tests com checklist

Verificam limites e bordas da lógica:

```clojure
(deftest limites-da-soma
  (are [a b esperado] (= esperado (+ a b))
    0 0 0
    1 0 1
    -1 1 0
    999999 1 1000000))
```

Use `are` para definir vários casos de teste com clareza.

## 🧵 Refatorar com `some->`

A macro `some->` é útil para encadear chamadas que podem retornar `nil`:

```clojure
(defn obter-nome [usuario]
  (some-> usuario :perfil :nome))

(obter-nome {:perfil {:nome "Maria"}})
;; => "Maria"

(obter-nome nil)
;; => nil
```

Evita `NullPointerException` e torna o código mais legível.

## Referências

- [clojure.test - Clojure v1.12 API documentation](https://clojure.github.io/clojure/clojure.test-api.html) - Clojure core team, en
- [deftest - ClojureDocs](https://clojuredocs.org/clojure.test/deftest) - ClojureDocs (comunidade), en
