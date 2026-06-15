# BadFit API

Projeto desenvolvido para a disciplina de Desenvolvimento de Aplicações Web II (DAW2).

A aplicação consiste em uma API REST para gerenciamento de uma academia, permitindo o cadastro e gerenciamento de alunos, planos, matrículas, pagamentos, instrutores, fichas de treino, exercícios e itens da ficha.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL / Neon DB
- Swagger / OpenAPI
- Bean Validation
- Maven

## Principais funcionalidades

- CRUD de alunos
- CRUD de planos
- CRUD de exercícios
- CRUD de instrutores
- CRUD de matrículas
- CRUD de pagamentos
- CRUD de fichas de treino
- CRUD de itens da ficha
- Filtros em endpoints
- Paginação
- Validação de dados
- Tratamento de erros com Exception Handler
- Documentação da API com Swagger

## Entidades principais

- Aluno
- Plano
- Matrícula
- Pagamento
- Instrutor
- Ficha
- Exercício
- ItemFicha

## Banco de dados

O projeto utiliza PostgreSQL hospedado no Neon DB.

A conexão com o banco é configurada por variáveis de ambiente:

```properties
DB_URL=jdbc:postgresql://host-do-neon/neondb?sslmode=require
DB_USERNAME=usuario
DB_PASSWORD=senha
