---
title: "PersistentQueue e mapas em Clojure"
description: "Use filas imutaveis e update em mapas para modelar processamento FIFO de forma funcional."
lastUpdated: 2026-04-13
sidebar:
  order: 1
tags: ["clojure","persistentqueue","mapas","concorrencia"]
---

## 🧵 Trabalhando com filas (`PersistentQueue`) e mapas em Clojure

Em vez de simular filas com vetores, podemos usar `clojure.lang.PersistentQueue`, uma estrutura de dados imutável nativa de Clojure, própria para comportamentos de fila FIFO.

### 🟢 **Criar uma fila vazia**

```clojure
(def fila clojure.lang.PersistentQueue/EMPTY)
;; => #queue []
```

### ➕ **Adicionar elementos com `conj`**

Assim como vetores, podemos usar `conj` para adicionar elementos **ao final** da fila:

```clojure
(def fila (conj fila :a))
;; => #queue [:a]

(def fila (conj fila :b :c))
;; => #queue [:a :b :c]
```

### 🔍 **Selecionar o primeiro elemento com `peek`**

`peek` retorna o **primeiro elemento da fila**, sem removê-lo:

```clojure
(peek fila)
;; => :a
```

### ➖ **Remover o primeiro elemento com `pop`**

`pop` remove o **primeiro elemento** (diferente do comportamento em vetores):

```clojure
(def fila (pop fila))
;; => #queue [:b :c]
```

### 🔄 **Atualizar valores em um mapa com `update`**

`update` aplica uma função ao valor de uma chave específica, ideal para contadores ou registros:

```clojure
(def status {:processadas 0})

(update status :processadas inc)
;; => {:processadas 1}

(update status :processadas + 5)
;; => {:processadas 6}
```

### 🚀 Exemplo completo usando fila + contador

```clojure
(def fila clojure.lang.PersistentQueue/EMPTY)

(def fila (conj fila :msg1 :msg2 :msg3))
;; => #queue [:msg1 :msg2 :msg3]

(peek fila)
;; => :msg1

(def fila (pop fila))
;; => #queue [:msg2 :msg3]

(def status {:processadas 0})

(def status (update status :processadas inc))
;; => {:processadas 1}
```

### 📝 Observações

- `PersistentQueue` não é tão conhecido quanto outras coleções básicas, mas é ideal quando você precisa de um comportamento FIFO verdadeiro.
- Como todas as estruturas em Clojure, ele é **imutável** - cada operação retorna uma nova fila.

