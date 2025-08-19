# Fórum Hub API 🚀

![Status do Projeto](https://img.shields.io/badge/status-conclu%C3%ADdo-brightgreen)

## 📖 Sobre o Projeto

**Fórum Hub** é uma API REST completa para uma plataforma de fórum de debates, desenvolvida como parte do programa ONE (Oracle Next Education) em parceria com a Alura. O projeto implementa todas as funcionalidades essenciais para o gerenciamento de tópicos, incluindo um sistema de autenticação e autorização seguro utilizando tokens JWT.

A API foi construída seguindo as melhores práticas de desenvolvimento, com uma arquitetura bem definida, validações de dados e tratamento de regras de negócio.

---

## ✨ Funcionalidades

-   ✅ **Autenticação de Usuários:** Sistema de login com geração de token JWT para acesso seguro aos endpoints.
-   ✅ **CRUD de Tópicos:**
    -   **Cadastro:** Criação de novos tópicos com validação de campos e prevenção de duplicidade.
    -   **Listagem:** Visualização de todos os tópicos de forma paginada e ordenada.
    -   **Detalhamento:** Consulta de um tópico específico por ID.
    -   **Atualização:** Edição das informações de um tópico existente.
    -   **Exclusão:** Remoção de um tópico do sistema.
-   ✅ **Segurança:** Acesso aos endpoints (exceto login) protegido, exigindo um token JWT válido no cabeçalho da requisição.
-   ✅ **Versionamento de Banco de Dados:** Uso do Flyway para gerenciar as alterações no schema do banco de dados de forma automática e controlada.

---

## 🛠️ Tecnologias Utilizadas

O projeto foi desenvolvido com as seguintes tecnologias:

-   **Backend:**
    -   Java 17
    -   Spring Boot 3
    -   Spring Security
    -   Spring Data JPA
-   **Banco de Dados:**
    -   MySQL
    -   Flyway (Migrations)
-   **Segurança:**
    -   JSON Web Token (JWT)
-   **Ferramentas:**
    -   Maven
    -   Lombok

---

## ⚙️ Como Executar o Projeto

Siga os passos abaixo para rodar a aplicação localmente.

**Pré-requisitos:**

-   Java JDK 17 ou superior
-   Maven 3.8 ou superior
-   MySQL 8.0 ou superior

**1. Clone o repositório:**

```bash
git clone [https://github.com/seu-usuario/forum-hub-api.git](https://github.com/seu-usuario/forum-hub-api.git)
cd forum-hub-api
```

## 2. Configure o Banco de Dados

1. Crie um banco de dados no MySQL com o nome **`forum_hub_db`**.  
2. Abra o arquivo `src/main/resources/application.properties`.  
3. Altere as seguintes propriedades com suas credenciais do MySQL:

```properties
spring.datasource.username=SEU_USUARIO_MYSQL
spring.datasource.password=SUA_SENHA_MYSQL
```

# 3. Execute a aplicação

Pelo terminal, na raiz do projeto, execute o comando:

```bash
mvn spring-boot:run
```

A API estará disponível em: http://localhost:8080

# Endpoints da API

## 🔐 Autenticação

### `POST /login`
Autentica um usuário e retorna um token JWT.

**Corpo da Requisição:**
```json
{
  "login": "ana.souza",
  "senha": "123456"
}
```
**Resposta de Sucesso (200 OK):
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

# 📌 Tópicos

Todos os endpoints de tópicos requerem um token JWT no cabeçalho `Authorization`.

## Endpoints

### `GET /topicos`
Lista todos os tópicos de forma paginada.

### `GET /topicos/{id}`
Detalha um tópico específico.

### `POST /topicos`
Cadastra um novo tópico.

**Corpo da Requisição:**

```json
{
  "titulo": "Dúvida sobre Spring Security",
  "mensagem": "Como configuro o SecurityFilter?",
  "autor": "Seu Nome",
  "curso": "Spring Boot 3"
}
```
## PUT /topicos/{id}

Atualiza um tópico existente.

**Corpo da Requisição:**

```json
{
  "titulo": "Novo título atualizado",
  "mensagem": "Nova mensagem atualizada."
}
```
## DELETE /topicos/{id}

Exclui um tópico

✒️ **Autor**<br>
Desenvolvido por Brener Brito.
