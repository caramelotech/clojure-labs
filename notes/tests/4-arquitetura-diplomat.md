# Diplomat Architecture: o Ports & Adapters do Nubank

## O que é

- Nome que o Nubank dá à sua aplicação da Arquitetura Hexagonal (Ports & Adapters, de Alistair Cockburn), com viés de programação funcional
- Padroniza a estrutura de pastas de cerca de 1000 microsserviços Clojure no Nubank
- Facilita a movimentação de engenheiros entre times, já que todo serviço segue a mesma estrutura

## As 4 camadas

- Business Logic: funções puras, dados imutáveis, invariantes validadas com `clojure.spec`, testes generativos
- Controllers: conectam pontos de entrada a efeitos colaterais, trabalham só com schemas internos, delegam para funções puras
- Adapters, apelidados de "Diplomat": interface com as portas, lidam com HTTP e Kafka, convertem schema externo em schema interno - é essa camada que dá nome à arquitetura
- Ports: componentes inicializados no startup, serializam dados para formato de transporte (JSON, Transit), código compartilhado entre serviços

## Como cada camada é testada

- Business Logic: testes unitários e testes generativos (`clojure.spec`)
- Adapters/Diplomat: testados com fakes/mocks das portas
- Ports: testados com testes de integração

## Comunicação entre microsserviços

- Sempre via portas (HTTP ou Kafka), nunca por banco de dados compartilhado
- Respostas HTTP carregam hipermídia, permitindo trocar um serviço sem quebrar clientes

## Templates de referência

- Templates Leiningen da comunidade que geram serviços prontos no estilo diplomat-architecture
- Exemplo: `diplomat-http-w-datomic-service-template`, com portas de entrada HTTP e saída para Datomic
