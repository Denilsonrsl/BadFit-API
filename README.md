# BadFit API

Projeto desenvolvido para a disciplina de **Banco de Dados II (BD II)** - IFPB.

A aplicação consiste em uma API REST para gerenciamento de uma academia, integrando o backend Spring Boot com o banco de dados PostgreSQL hospedado no **Supabase** e controle de acesso via **Row Level Security (RLS)**.

## 🛠️ Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL (**Supabase**)
* PostgreSQL **Row Level Security (RLS)**
* Swagger / OpenAPI
* Bean Validation
* Maven

## 📁 Scripts de Banco de Dados

Os scripts SQL para estrutura, segurança e povoamento do banco encontram-se na pasta `src/main/resources/`:

* **`schema.sql`**: Script DDL com a criação de todas as tabelas (`tb_usuario`, `tb_treino`, `tb_exercicio`, etc.).
* **`rls-policies.sql`**: Ativação do Row Level Security e definição das políticas de acesso por perfil (`ALUNO`, `INSTRUTOR`, `ADMIN`).
* **`data.sql`**: Povoamento inicial com dados de teste.

## 🔐 Segurança e Níveis de Acesso (RLS)

A camada de banco de dados utiliza RLS para garantir a privacidade e restrição de dados:
* **ALUNO**: Visualiza apenas seus próprios treinos, avaliações físicas e mensalidades.
* **INSTRUTOR**: Gerencia exercícios, cria treinos e registra avaliações físicas para os alunos.
* **ADMIN**: Possui acesso total de leitura e escrita em todas as tabelas do sistema.

## ⚙️ Configuração do Banco de Dados

O projeto está configurado para conectar ao **Supabase**. As credenciais podem ser definidas no `application.properties` ou via variáveis de ambiente:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
