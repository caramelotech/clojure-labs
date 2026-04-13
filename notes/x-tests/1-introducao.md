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
