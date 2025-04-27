# 📚 Projeto Livraria

Bem-vindo ao **Projeto Livraria**! Este é um sistema de gerenciamento de livros, utilizando **Spring Boot**, **Spring Data JPA**, **PostgreSQL** e **Swagger** para a documentação da API. Através deste sistema, você pode cadastrar, listar, buscar, atualizar e deletar livros e escritores na livraria.

## 🔧 Tecnologias Utilizadas

- **Spring Boot**: Framework Java para construir aplicações de nível corporativo com facilidade.
- **Spring Data JPA**: Simplificação do uso do JPA para interação com bancos de dados.
- **PostgreSQL**: Banco de dados relacional robusto para armazenar informações da livraria.
- **Swagger**: Ferramenta para gerar documentação interativa da API.
- **Docker**: Para facilitar a containerização e orquestração da aplicação e do banco de dados.

## 🚀 Como Rodar o Projeto

### Requisitos

- Docker (para rodar os containers)
- Java 21 (ou versão mais recente)

### Passos para Executar

1. **Clone o repositório**:

  Abra o terminal e execute o comando para clonar o repositório:

  ```bash
  git clone https://github.com/seu-usuario/livraria.git
  cd livraria
  ```

2. **Construir e iniciar os containers**:

  Utilize o Docker Compose para construir e rodar os containers da aplicação e do banco de dados:

  ```bash
  docker-compose up --build
  ```

  Este comando irá:
  - Criar e inicializar o PostgreSQL.
  - Criar e inicializar a aplicação Spring Boot.

  A aplicação será acessível na porta `8082` e o PostgreSQL na porta `5432`.

3. **Acessando a API**:

  Após a inicialização, você pode acessar:
  - **API REST**: [http://localhost:8082](http://localhost:8082)
  - **Swagger UI**: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

## 📜 Endpoints da API

Aqui estão os principais endpoints disponíveis na API:

1. **Cadastrar Livro**  
  - **Método**: POST  
  - **Endpoint**: `/livro/cadastrar`  
  - **Descrição**: Cadastra um novo livro.  
  - **Exemplo de Request**:
    ```json
    {
     "titulo": "Dom Casmurro",
     "escritor": "Machado de Assis",
     "editora": "Editora ABC",
     "anoPublicacao": "1899",
     "genero": "Ficção"
    }
    ```

2. **Listar Todos os Livros**  
  - **Método**: GET  
  - **Endpoint**: `/livro/listar-todos`  
  - **Descrição**: Lista todos os livros cadastrados na livraria.

3. **Buscar Livro por Nome**  
  - **Método**: GET  
  - **Endpoint**: `/livro/buscar-por-nome`  
  - **Descrição**: Busca um livro pelo título.  
  - **Exemplo de Request**: `?nome=Dom Casmurro`

4. **Atualizar Livro**  
  - **Método**: PUT  
  - **Endpoint**: `/livro/atualizar`  
  - **Descrição**: Atualiza as informações de um livro existente.  
  - **Exemplo de Request**:
    ```json
    {
     "titulo": "Dom Casmurro - Edição Especial",
     "escritor": "Machado de Assis",
     "editora": "Editora XYZ",
     "anoPublicacao": "1899",
     "genero": "Ficção"
    }
    ```

5. **Deletar Livro**  
  - **Método**: DELETE  
  - **Endpoint**: `/livro/deletar`  
  - **Descrição**: Deleta um livro pelo ID.

## 📂 Estrutura do Projeto

A estrutura de diretórios do projeto está organizada da seguinte forma:

```
livraria/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── com/
│   │   │           └── livraria/
│   │   │               ├── components/        # Componentes reutilizáveis da aplicação
│   │   │               ├── configurations/    # Configurações do Spring Boot
│   │   │               ├── controllers/       # Controladores responsáveis pelos endpoints da API
│   │   │               ├── dtos/              # Objetos de transferência de dados (DTOs)
│   │   │               ├── entities/          # Entidades JPA que representam as tabelas do banco de dados
│   │   │               ├── handlers/          # Manipuladores de exceções
│   │   │               ├── repositories/      # Interfaces para interação com o banco de dados
│   │   │               ├── services/          # Lógica de negócios da aplicação
│   │   │               └── LivrariaApplication.java # Classe principal da aplicação
│   │   ├── resources/
│   │   │   └── application.properties         # Configurações gerais da aplicação
│   ├── test/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── com/
│   │   │           └── livraria/
│   │   │               └── LivrariaApplicationTests.java # Testes principais da aplicação
├── docker-compose.yml                         # Configuração para executar a aplicação e o banco no Docker
├── pom.xml                                    # Configuração do Maven
├── mvnw                                       # Script do Maven Wrapper
├── mvnw.cmd                                   # Script do Maven Wrapper para Windows
├── README.md                                  # Arquivo de documentação
```

### 📁 Descrição das Pastas

- **components**: Contém componentes reutilizáveis da aplicação.
- **configurations**: Inclui configurações gerais da aplicação, como segurança e beans.
- **controllers**: Controladores responsáveis por expor os endpoints da API.
- **dtos**: Objetos de transferência de dados (DTOs), usados para estruturar requisições e respostas da API.
- **entities**: Entidades JPA que representam as tabelas do banco de dados.
- **handlers**: Manipuladores de exceções para tratar erros de forma centralizada.
- **repositories**: Interfaces para interação com o banco de dados utilizando Spring Data JPA.
- **services**: Contém a lógica de negócios da aplicação, realizando operações de manipulação de dados.

Essa organização facilita a manutenção e escalabilidade do projeto, separando responsabilidades de forma clara.
