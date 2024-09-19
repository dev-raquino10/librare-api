# Librare-API

## 1. Arquitetura de Solução e Arquitetura Técnica

### 1.1 Descrição da Solução

A **Librare-API** é uma API REST desenvolvida para gerenciar dados de uma livraria, permitindo consultas de livros, autores e gêneros literários. A solução foi construída utilizando as seguintes tecnologias:

- **Java 17**
- **Spring Boot**
   - Spring Data JPA
   - Spring Cache
   - Spring Web
- **PostgreSQL** como banco de dados relacional
- **Flyway** para migração de banco de dados
- **Docker** e **Docker Compose** para conteinerização e reprodutibilidade
- **GPT-4** para geração de dados de livros

### 1.2 Decisões de Design

A solução foi projetada em uma arquitetura limpa, separando responsabilidades e pacotes em camadas de domínios. Além disso, o Spring Cache foi utilizado para armazenar os dados visualizados recentemente, evitando o uso excessivo do banco de dados e garantindo alta performance nas requisições de consultas frequentes.

---

## 2. Explicação sobre o Case Desenvolvido (Plano de Implementação)

### 2.1 Funcionalidades Implementadas

A **Librare-API** oferece os seguintes endpoints principais:

- **GET /books**: Retorna todos os livros.
- **GET /books/{id}**: Retorna um livro pelo seu ID.
- **GET /books/genre/{genre}**: Busca livros por gênero.
- **GET /authors/{name}**: Busca autores por nome.
- **GET /recent**: Retorna os últimos 10 itens visualizados (livros, autores ou gêneros).

### 2.2 Lógica de Negócios

#### 2.2.1 Gerenciamento de Livros, Autores e Gêneros
As entidades principais do sistema são **BookEntity**, **AuthorEntity** e **GenreEntity**. Cada gênero e autor podem possuir vários livros, estabelecendo um relacionamento `@ManyToMany` entre essas entidades. O **BookRepository**, **AuthorRepository**, e **GenreRepository** foram implementados para realizar as operações de CRUD dessas entidades.

#### 2.2.2 Visualizações Recentes
Para cada livro, autor ou gênero consultado, o **RecentViewsService** armazena as visualizações em cache, limitando o número de itens visualizados recentemente a 10 por categoria. Essa abordagem permite consultas rápidas de itens visualizados anteriormente.

---

## 3. Melhorias e Considerações Finais

### 3.1 Possíveis Melhorias
- **Paginação e Filtros**: Implementar paginação nos endpoints de listagem, melhorando a performance quando houver muitos registros.
- **Autenticação e Autorização**: Adicionar autenticação usando **OAuth2** ou **JWT** para proteger os endpoints sensíveis.
- **Testes de Integração**: Incrementar a cobertura de testes de integração para garantir o comportamento adequado da API em cenários mais complexos.
- **Documentação Swagger**: Incluir documentação **Swagger** para facilitar o entendimento e teste dos endpoints expostos.

### 3.2 Desafios
- **Configuração dos conteiners Docker**: Durante o desenvolvimento, houve desafios relacionados à integração e comunicação entre os serviços no Docker, principalmente na sincronização entre aplicação e PostgreSQL. Além disso, ajustar corretamente os volumes para persistência de dados entre execuções do Docker, e garantir que as redes estivessem configuradas corretamente para permitir que a aplicação Spring Boot acessasse os serviços de cache e banco de dados, foi uma tarefa crítica para garantir a estabilidade do ambiente.

---

## 4. Instruções para Rodar o Projeto com Docker

### 4.1 Pré-requisitos

- Docker instalado ([Instruções](https://docs.docker.com/get-docker/))
- Docker Compose instalado ([Instruções](https://docs.docker.com/compose/install/))

### 4.2 Passos para Execução

1. Clone o repositório do projeto:
   ```bash
   git clone https://github.com/dev-raquino10/librare-api.git
   cd librare-api docker-compose up --build

2. Execute o Docker Compose para subir a aplicação:
   ```bash
   docker-compose up --build

3. Acesse a API via http://localhost:8080. 


### 4.3 Executando Testes

    ./mvnw test

4. Coleção Postman
   Na raiz do projeto há uma **_collection_** Postman (collection-librare-api.json) que pode ser importada para facilitar a execução dos endpoints e o teste das funcionalidades da API.

## Feedback
Se tiver algum feedback, por favor entre em contato em rafa.aquino10@gmail.com, ou se preferir, ligue ou envie uma mensagem para (11) 97480-6212.