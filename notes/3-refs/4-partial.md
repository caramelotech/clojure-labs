## 🗺️ Utilizar `mapv` para forçar execução de uma função

Diferente de `map`, que é lazy, `mapv` executa imediatamente e retorna um vetor com os resultados:

```clojure
(mapv println ["Olá" "Clojure" "!"])
;; => "Olá" "Clojure" "!"
```

Muito útil quando a função tem efeitos colaterais e você quer garantir que ela seja chamada.

## 🧼 Aumentar legibilidade extraindo responsabilidades

Funções pequenas e com responsabilidade única facilitam leitura e manutenção:

```clojure
(defn calcular-imposto [valor taxa]
  (* valor taxa))

(defn calcular-total [valor taxa]
  (+ valor (calcular-imposto valor taxa)))
```

## 🔧 Chamada parcial com `partial`

`partial` cria uma nova função preenchendo parcialmente os argumentos:

```clojure
(def soma10 (partial + 10))
(soma10 5)
;; => 15
```

Muito útil para pré-configurar funções.

## 🔁 Iterar com `doseq`

`doseq` executa efeitos colaterais em cada elemento da sequência:

```clojure
(doseq [nome ["Ana" "João" "Lia"]]
  (println "Olá," nome))
```

## 🔂 Repetição com `dotimes`

Executa algo um número fixo de vezes:

```clojure
(dotimes [i 3]
  (println "Executando" i))
```

Ideal para loops simples baseados em contagem.

Clojure oferece diversas ferramentas para trabalhar com filas, controle de fluxo e concorrência de forma segura e expressiva. Evitar símbolos globais mutáveis e usar `atom` e `swap!` são práticas essenciais para garantir consistência e segurança em ambientes concorrentes.

Ferramentas como `mapv`, `partial`, `doseq` e `dotimes` ajudam a escrever código mais legível, funcional e conciso.
