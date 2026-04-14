---
title: "Filas, Concorrencia e Threads em Clojure"
description: "Entenda threads e processamento concorrente em exemplos praticos com filas e funcoes puras."
lastUpdated: 2026-04-13
sidebar:
  order: 2
tags: ["clojure","threads","concorrencia","filas"]
---

# ⏳ Filas, Concorrência e Threads em Clojure

Este capítulo complementa o uso de `clojure.lang.PersistentQueue` e aborda conceitos de concorrência, controle de fluxo, tratamento de erros e interoperação com Java para trabalhar com `Thread`.

## 🚦 Implementar um limite à fila usando `if`

Para controlar o tamanho da fila, podemos usar `if` e a função `count`:

```clojure
(def limite 5)

(defn adicionar-na-fila [fila elemento]
  (if (< (count fila) limite)
    (conj fila elemento)
    (do
      (println "Fila cheia!")
      fila)))

(def fila clojure.lang.PersistentQueue/EMPTY)
(def fila (adicionar-na-fila fila :a))
;; => #queue [:a]
```

## 🔢 Verificar o tamanho da fila com `count`

A função `count` funciona com qualquer coleção Clojure:

```clojure
(count fila)
;; => 1
```

## ❗ Tratar erros não previstos com `ex-info`

Para lançar exceções com informações contextuais:

```clojure
(throw (ex-info "Erro de processamento" {:codigo 500 :detalhe "Falha no servidor"}))
```

Para capturar:

```clojure
(try
  (throw (ex-info "Algo deu errado" {:motivo :desconhecido}))
  (catch clojure.lang.ExceptionInfo e
    (println "Erro capturado:" (.getMessage e))
    (println "Dados:" (ex-data e))))
```

## 🧵 Criar uma `Thread` com compatibilidade Java

Clojure é 100% compatível com Java. Podemos usar `Thread` diretamente:

```clojure
(def t (Thread. (fn []
                  (println "Rodando em outra thread..."))))
```

Ou mais idiomaticamente com `proxy` ou `reify` para implementar interfaces:

```clojure
(def t (Thread. (reify Runnable
                 (run [_]
                   (println "Executando via reify!")))))
```

## ▶️ Iniciar uma `Thread` com o método `start`

Uma vez criada a `Thread`, iniciamos com `start`:

```clojure
(.start t)
```

## ⚠️ Problemas com símbolos globais e concorrência

Evitar estados globais mutáveis é essencial. Quando várias threads acessam e modificam um mesmo símbolo global, surgem problemas clássicos de **race condition** e inconsistência:

```clojure
(def contador (atom 0))

(defn incrementar []
  (dotimes [_ 1000]
    (swap! contador inc)))

;; Executando em paralelo
(doseq [_ (range 10)]
  (.start (Thread. incrementar)))

;; Resultado correto com atom, mas com var mutável normal daria erro
```

Por isso, Clojure favorece o uso de:

- `atom` (atualização atômica simples)
- `ref` + `dosync` (transações coordenadas)
- `agent` (atualização assíncrona)
- `future`, `promise`, `core.async`, entre outras

