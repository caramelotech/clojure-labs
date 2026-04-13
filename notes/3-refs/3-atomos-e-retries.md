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
```

Por isso, Clojure favorece o uso de:

- `atom` (atualização atômica simples)
- `ref` + `dosync` (transações coordenadas)
- `agent` (atualização assíncrona)
- `future`, `promise`, `core.async`, entre outras

## ❌ Evitar o uso de símbolos globais (Root Binding)

Símbolos globais definidos com `def` são compartilhados entre todas as threads. Alterá-los diretamente pode causar comportamentos imprevisíveis e bugs difíceis de rastrear:

```clojure
(def fila-global (atom clojure.lang.PersistentQueue/EMPTY)) ;; uso com cuidado!
```

Em vez disso, prefira **passar estados como argumentos** ou encapsular estados mutáveis com `atom` e acesso controlado.

## 🔄 Tornar um mapa imutável mutável com `atom`

Para manipular um mapa imutável com segurança, encapsule-o em um `atom`:

```clojure
(def sistema (atom {:fila clojure.lang.PersistentQueue/EMPTY}))
```

## 📦 Dereferenciar o `atom` com `deref` para acessar a fila

Para acessar os dados dentro do `atom`, use `@` (forma curta de `deref`):

```clojure
(:fila @sistema)
;; => #queue []
```

## 🕶️ Usar Shadowing para “esconder” um símbolo local

Você pode criar variáveis locais com o mesmo nome de símbolos globais (shadowing). Isso **não altera** o valor global:

```clojure
(def nome "global")

(let [nome "local"]
  (println nome))
;; => "local"

(println nome)
;; => "global"
```

## 🧠 Alterar o conteúdo de um `atom` com `swap!`

O `swap!` aplica uma função ao valor atual de um atom e atualiza-o com o resultado:

```clojure
(swap! sistema update :fila conj :evento1)
;; => {:fila #queue [:evento1]}
```

## 🛡️ Usar `swap!` para evitar concorrência

`swap!` garante que mesmo sob múltiplas threads, as atualizações sejam **atômicas** e seguras:

```clojure
(defn adicionar-evento [evento]
  (swap! sistema update :fila conj evento))

(doseq [e [:a :b :c :d]]
  (.start (Thread. #(adicionar-evento e))))

;; Ao final, a fila conterá todos os eventos corretamente
```

Clojure oferece diversas ferramentas para trabalhar com filas, controle de fluxo e concorrência de forma segura e expressiva. Evitar símbolos globais mutáveis e usar `atom` e `swap!` são práticas essenciais para garantir consistência e segurança em ambientes concorrentes.

Apesar da abordagem mais comum de Clojure ser o sistema de retry de transações com átomos, a linguagem disponibiliza uma forma de trabalhar com locking também como o uso de travas de monitoramento com https://clojuredocs.org/clojure.core/locking
