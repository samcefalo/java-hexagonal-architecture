# Projeto Exemplo - Arquitetura Hexagonal com Java

Este é um projeto exemplo que demonstra a aplicação da arquitetura hexagonal em uma aplicação Java. O projeto gerencia
entidades de Address e Customer através de endpoints REST e utiliza o Apache Kafka para consumir mensagens.

## Visão Geral da Arquitetura Hexagonal

A arquitetura hexagonal (também conhecida como Arquitetura de Ports and Adapters ou Arquitetura de Onion) é um padrão
arquitetural que visa isolar o núcleo da aplicação (domínio) das camadas externas, como interfaces de entrada (adapters)
e tecnologias externas, como banco de dados e frameworks (ports). A arquitetura hexagonal promove uma maior separação de
preocupações e facilita a testabilidade, manutenção e evolução da aplicação.

A arquitetura é dividida em três camadas principais:

**Domínio (Core)**: Contém as regras de negócio (casos de uso) e as entidades centrais da aplicação, neste caso, os
domínios Address e Customer.

**Portas**: Responsáveis por definir as interfaces de comunicação entre o domínio e as camadas externas. Neste projeto,

**Adapters (Entrada e Saída)**: Responsáveis por lidar com as interações externas à aplicação, como interfaces de
entrada e tecnologias externas. Neste projeto, utilizamos os endpoints REST e o Kafka como entradas (input). Como
saída (output), utilizamos o banco de dados Mongodb e chamadas de outros serviços.

**Configuração**: Contém a configuração e a inicialização da aplicação, ligando as dependências.

## Endpoints REST

#### Inserir um novo cliente:

Método: POST

Endpoint: /api/v1/customer

Consumes: application/json

Produces: application/json

Exemplo de requisição:

```json
{
  "name": "John Doe",
  "cpf": "12345678900",
  "zipCode": "12345678"
}
```

#### Buscar cliente por ID:

Método: GET

Endpoint: /api/v1/customer/{id}

Consumes: application/json

Produces: application/json

Exemplo de resposta:

```json
{
  "name": "John Doe",
  "cpf": "12345678900",
  "isValidCpf": true,
  "address": {
    "street": "Rua 1",
    "city": "São Paulo",
    "state": "SP"
  }
}
```

#### Atualizar cliente por ID:

Método: PUT

Endpoint: /api/v1/customer/{id}

Consumes: application/json

Produces: application/json

Exemplo de requisição:

```json
{
  "name": "John Doe",
  "cpf": "12345678900",
  "zipCode": "12345678"
}
```

#### Excluir cliente por ID:

Método: DELETE

Endpoint: /api/v1/customer/{id}

Consumes: application/json

Produces: application/json

## Kafka

O projeto utiliza um Kafka Consumer chamado ReceiveValidatedCpfConsumer que consome mensagens do tópico "
tp-cpf-validated" com o grupo "cefalo".

#### Configurações

KafkaConsumerConfig: Contém as configurações do consumer.

KafkaProducerConfig: Contém as configurações do producer.

## Testes Automatizados

O projeto possui testes automatizados para garantir a qualidade e o correto funcionamento das funcionalidades
implementadas. Os testes estão localizados na pasta /src/test/.