# Do OO ao funcional: mudando o modelo mental

Quem vem de Java costuma tropeçar menos na sintaxe de Clojure e mais na forma de pensar o programa. As perguntas são as mesmas (onde fica o estado? onde mora a regra de negócio? como reaproveitar código?), mas as respostas mudam. Esta nota compara os dois estilos lado a lado, sem torcer por nenhum: várias diferenças são de **padrão da linguagem**, não de capacidade.

## Objetos vs transformação de dados

Em OO, o sistema é um conjunto de objetos que guardam estado e sabem fazer coisas com ele. A pergunta guia é "quem faz o quê?": um `Pedido` tem itens e sabe calcular o próprio total.

```java
class Pedido {
    private final List<Item> itens = new ArrayList<>();

    void adicionarItem(Item item) { itens.add(item); }

    double calcularTotal() { /* soma quantidade * preço de cada item */ }
}
```

Em Clojure, o sistema é um fluxo de dados que passa por funções. A pergunta guia vira "como transformo estes dados no resultado que preciso?". O pedido é só um mapa, e o cálculo é uma função separada:

```clojure
(def pedido {:itens [{:quantidade 2 :preco-unitario 80}
                     {:quantidade 1 :preco-unitario 40}]})

(defn calcular-total [pedido]
  (reduce + (map #(* (:quantidade %) (:preco-unitario %)) (:itens pedido))))

(calcular-total pedido)
;; => 200
```

## Funções independentes vs métodos

`pedido.calcularTotal()` pertence à classe `Pedido`: o comportamento vive junto do dado. `(calcular-total pedido)` é uma função solta que recebe o dado como argumento. Isso tem um efeito prático: qualquer mapa com a chave `:itens` serve, venha de onde vier.

```clojure
(calcular-total {:itens [{:quantidade 1 :preco-unitario 10}]})
;; => 10
```

Em Java também dá para escrever um método estático que recebe uma lista de itens, então não é que Clojure "permita" algo impossível. A diferença é o caminho natural: em Java, o método mora na classe por padrão; em Clojure, a função é independente por padrão, o que facilita reaproveitar e compor sem criar interfaces ou hierarquias para isso.

## Poucas estruturas de dados genéricas em vez de uma classe por conceito

Clojure trabalha com um conjunto pequeno de estruturas (lista, vetor, mapa e conjunto, apresentadas em [Coleções em Clojure](/labs/clojure/colecoes/1-introducao/)) e com muitas funções que operam sobre elas: `map`, `filter`, `reduce`, `assoc`, `get-in` e por aí vai. A filosofia da linguagem, descrita no documento de rationale, é preferir muitas funções sobre poucas estruturas a muitas estruturas cada uma com suas funções.

Na prática, um `Pedido`, um `Cliente` e um `Item` são todos mapas. As mesmas ferramentas servem para os três, e é fácil imprimir, comparar, serializar ou testar qualquer um deles.

Existe um preço. Em Java, o compilador sabe que `Pedido` tem um campo `itens`. Em Clojure, que é uma linguagem dinâmica, a "forma" do mapa é uma convenção do seu código: nada impede de passar um mapa sem `:itens`. Ferramentas como o `clojure.spec` validam essa forma em tempo de execução, e a [Diplomat Architecture](/labs/clojure/tests/4-arquitetura-diplomat/) do Nubank usa `clojure.spec` justamente para isso.

## Estado, mutabilidade e valores

O padrão em classes Java é o estado mudar no lugar: `pedido.setTotal(100)` altera o objeto que já existe, e quem tinha uma referência a ele passa a ver a mudança. Em Clojure, "mudar" um dado é produzir uma nova versão e deixar a antiga como estava:

```clojure
(def pedido {:itens [] :total 0})

(assoc pedido :total 100)
;; => {:itens [], :total 100}

pedido
;; => {:itens [], :total 0}
```

Rich Hickey, criador do Clojure, organiza isso em três ideias:

- **Valor**: algo que não muda. O número `42` e o mapa `{:saldo 100}` são valores.
- **Identidade**: uma entidade estável que, ao longo do tempo, aponta para valores diferentes. "A conta da Maria" é uma identidade.
- **Estado**: o valor que a identidade tem em um determinado momento.

Em Clojure, o que muda no tempo é a identidade (guardada num `atom`, por exemplo), não os valores:

```clojure
(def conta (atom {:saldo 100}))

@conta
;; => {:saldo 100}

(swap! conta update :saldo + 50)
;; => {:saldo 150}

@conta
;; => {:saldo 150}
```

`{:saldo 100}` e `{:saldo 150}` são dois valores, cada um imutável. A `conta` é a identidade que passou de um para o outro. Os detalhes de `atom` e `swap!` estão em [Átomos, swap! e retries](/labs/clojure/refs/3-atomos-e-retries/).

Um ponto de honestidade: imutabilidade não é exclusividade de linguagens funcionais. Desde o JDK 16, os _records_ de Java são portadores de dados imutáveis por padrão (os campos são `final`):

```java
record Item(String nome, int quantidade, double precoUnitario) {}
```

Então a diferença real é de padrão. Em Java você opta por dados imutáveis; em Clojure, tudo (inclusive as coleções da biblioteca padrão) é imutável, e você opta por ter uma referência mutável quando precisa.

## Composição em vez de herança e patterns

Sem hierarquia de classes para orquestrar, a forma comum de montar comportamento é encadear funções. A macro `->` (veja [Threading Macro](/labs/clojure/introducao/5-threading/)) deixa o encadeamento legível:

```clojure
(defn aplicar-desconto [pedido] (update pedido :total * 0.9))
(defn somar-frete [pedido] (update pedido :total + 20))

(-> {:total 100}
    aplicar-desconto
    somar-frete)
;; => {:total 110.0}
```

Muitos patterns de OO existem para passar comportamento de um lado para outro, e em Clojure isso é só passar uma função. O Strategy, por exemplo, deixa de precisar de interface e classes concretas:

```clojure
(defn desconto-natal [valor] (* valor 0.9))
(defn desconto-vip [valor] (* valor 0.8))

(defn total-com-desconto [estrategia valor]
  (estrategia valor))

(total-com-desconto desconto-vip 100)
;; => 80.0
```

Isso é o que a nota de [Sintaxe Básica](/labs/clojure/introducao/2-sintaxe-basica/) chama de função de alta ordem.

Já o polimorfismo, que em OO costuma vir de herança e interfaces, tem outros caminhos em Clojure. Um deles é o _multimethod_, que escolhe a implementação a partir de uma propriedade do dado:

```clojure
(defmulti calcular-frete :tipo)
(defmethod calcular-frete :normal [_] 20)
(defmethod calcular-frete :expresso [_] 45)

(calcular-frete {:tipo :expresso})
;; => 45
```

O outro são os _protocols_ (`defprotocol`), que despacham pelo tipo do primeiro argumento. Eles permitem estender tipos que você não controla, sem criar relação de herança, e geram uma interface Java correspondente, então o código Clojure continua interoperando com Java.

## Imutabilidade e concorrência

Valores imutáveis ajudam em concorrência por um motivo simples: quem só lê um valor que nunca muda não precisa combinar nada com as outras threads. A documentação do Clojure descreve isso dizendo que o valor de uma referência pode ser observado sem coordenação e compartilhado livremente entre threads.

Só que o programa ainda precisa de coisas que mudam com o tempo (o saldo de uma conta, uma fila de eventos). Para isso, Clojure oferece referências explícitas, cada uma com uma estratégia diferente:

| Tipo    | O que coordena                           | Como muda                    | Mecanismo                                                              |
| ------- | ---------------------------------------- | ---------------------------- | ---------------------------------------------------------------------- |
| `atom`  | um valor independente                    | síncrono                     | compare-and-set com nova tentativa (`swap!`)                           |
| `ref`   | vários valores que precisam mudar juntos | síncrono, dentro de `dosync` | STM (software transactional memory)                                    |
| `agent` | um valor independente                    | assíncrono                   | ações enviadas com `send`/`send-off`, executadas em um pool de threads |

Dois detalhes que costumam gerar confusão:

- **STM é o mecanismo dos `ref`s**, não uma quarta opção ao lado de atom, ref e agent. Os agents apenas se integram a ele: um `send` feito dentro de uma transação só é despachado depois do commit.
- **A função passada ao `swap!` precisa ser livre de efeitos colaterais.** Se outra thread alterar o atom no meio do caminho, o Clojure descarta o resultado e chama a função de novo, então ela pode rodar mais de uma vez.

E vale desconfiar da versão simplificada "Java é difícil e Clojure é seguro". Java também tem objetos imutáveis, que segundo o tutorial da Oracle não sofrem interferência entre threads, e o pacote `java.util.concurrent.atomic` (`AtomicInteger`, `AtomicReference`) oferece variáveis atômicas. O que Clojure muda é o ponto de partida: os dados já nascem imutáveis, e o estado compartilhado é uma escolha explícita com regras claras, em vez de ser o padrão.

Por enquanto, só o `atom` tem exemplos executáveis no lab ([Átomos, swap! e retries](/labs/clojure/refs/3-atomos-e-retries/) e a pasta `examples/src/refs/`). `ref`/`dosync` e `agent` ainda não têm nota própria.

## Referências

- [Values and Change: Clojure's approach to Identity and State](https://clojure.org/about/state) - Rich Hickey, clojure.org, en
- [Rationale](https://clojure.org/about/rationale) - Rich Hickey, clojure.org, en
- [Refs and Transactions](https://clojure.org/reference/refs) - clojure.org, en
- [Agents](https://clojure.org/reference/agents) - clojure.org, en
- [JEP 395: Records](https://openjdk.org/jeps/395) - OpenJDK, en
- [Immutable Objects (The Java Tutorials)](https://docs.oracle.com/javase/tutorial/essential/concurrency/immutable.html) - Oracle, en
- [Conceitos de programação funcional em F#](https://learn.microsoft.com/pt-br/dotnet/fsharp/tutorials/functional-programming-concepts) - Microsoft Learn, pt-BR (tradução automática, exemplos em F#)
