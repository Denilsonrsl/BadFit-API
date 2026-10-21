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

## Cache-aside em `GET /api/exercicios/{id}`

Foi aplicada a estratégia **cache-aside** na busca de exercício por id (`ExercicioService.buscarPorId`).

**Como funciona**
1. A requisição chega ao service, que procura o exercício no cache.
2. *Cache hit*: devolve o valor guardado, sem consultar o banco.
3. *Cache miss*: consulta o banco, guarda o resultado no cache e devolve.
4. `atualizar` e `excluir` removem a entrada do cache (invalidação), para a leitura seguinte buscar o dado atual.

**Decisões**
- Cache em memória com Caffeine (o projeto não tinha Redis). TTL de 5 minutos e no máximo 500 entradas.
- Endpoint escolhido porque o catálogo de exercícios muda pouco e é igual para todos os usuários, sem risco de entregar dado de uma pessoa a outra.
- A listagem (`GET /api/exercicios`) não usa cache, pois depende de página, tamanho e filtro.
- `null` (id inexistente) não é guardado no cache.
- Limitação: o cache vive na memória de cada instância da API. Com várias instâncias, cada uma teria o seu, e um cache compartilhado (ex.: Redis) seria o próximo passo.

**Como validar** (com a API rodando em `localhost:8080`; as mensagens `CACHE ...` aparecem no console)
```bash
curl http://localhost:8080/api/exercicios/1   # CACHE MISS (consulta o banco)
curl http://localhost:8080/api/exercicios/1   # CACHE HIT (sem novo select)
```
Invalidação: crie um exercício (`POST`), leia (`MISS`, `HIT`), altere com `PUT` (`INVALIDATE`) e leia de novo: deve vir o dado novo (`MISS`). Depois exclua com `DELETE` (`INVALIDATE`).

Expiração: espere mais de 5 minutos após uma leitura e leia de novo: deve aparecer `CACHE MISS`.
