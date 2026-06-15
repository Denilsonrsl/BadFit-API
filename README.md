# BadFit API

Projeto desenvolvido para a disciplina de **Desenvolvimento de Aplicações Web II (DAW2)**.

A aplicação consiste em uma API REST para gerenciamento de uma academia, permitindo o cadastro e gerenciamento de alunos, planos, matrículas, pagamentos, instrutores, fichas de treino, exercícios e itens da ficha.

## Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL / Neon DB
* Swagger / OpenAPI
* Bean Validation
* Maven

## Principais funcionalidades

* CRUD de alunos
* CRUD de planos
* CRUD de exercícios
* CRUD de instrutores
* CRUD de matrículas
* CRUD de pagamentos
* CRUD de fichas de treino
* CRUD de itens da ficha
* Filtros em endpoints
* Paginação
* Validação de dados
* Tratamento de erros com Exception Handler
* Documentação da API com Swagger

## Entidades principais

* Aluno
* Plano
* Matricula
* Pagamento
* Instrutor
* Ficha
* Exercicio
* ItemFicha

## Configuração do banco de dados

O projeto utiliza variáveis de ambiente para conexão com o banco:

```properties
DB_URL=jdbc:postgresql://host-do-neon/neondb?sslmode=require
DB_USERNAME=usuario
DB_PASSWORD=senha
```

No arquivo `application.properties`, a conexão é feita assim:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

## Como executar

1. Clonar o repositório.
2. Configurar as variáveis de ambiente do banco de dados.
3. Abrir o projeto no IntelliJ IDEA.
4. Executar a classe principal `BadfitApiApplication`.
5. Acessar o Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```
