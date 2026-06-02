# Projeto III — Acervo API

## Apresentação e Contextualização

Esse foi o terceiro *Projeto de Código* do Módulo **APIs Rest com Java, Spring Boot & Persistência de Dados**.

A **Acervo API** é uma API REST de gerenciamento de obras intelectuais — livros, ensaios e vídeo-ensaios — construída com Spring Boot e aplicando padrões de projeto clássicos do GoF. O tema foi escolhido por ser pessoalmente significativo: sou escritor e vídeo-ensaísta amador, e o acervo inicial reflete obras e autores que me influenciam diretamente.

---

## Documentação Técnica

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![H2](https://img.shields.io/badge/H2-004088?style=for-the-badge&logo=h2&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

**Paradigma:** Orientado a Objetos  
**Recursos utilizados:** Spring Boot, Spring Data JPA, H2 Database, Lombok, Design Patterns (GoF), Postman

### Design Patterns Aplicados

| Padrão | Onde |
|--------|------|
| **Factory Method** | `ObraFactory` — instancia o subtipo correto conforme o campo `tipo` |
| **Strategy** | `BuscaStrategy` — alterna entre busca por autor e por gênero |
| **Facade** | `AcervoService` — orquestra factory, strategy e repository em uma interface simples |

### Estrutura de Pacotes

    src/main/java/com/caio/acervoapi/
    ├── controller/       → ObraController (endpoints REST)
    ├── factory/          → ObraFactory (Factory Method)
    ├── model/            → Obra, Livro, Ensaio, VideoEnsaio
    ├── repository/       → ObraRepository (Spring Data JPA)
    ├── service/          → AcervoService (Facade)
    └── strategy/         → BuscaStrategy, BuscaPorAutor, BuscaPorGenero

### Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/obras` | Lista todas as obras |
| `GET` | `/obras/{id}` | Busca obra por ID |
| `GET` | `/obras/buscar?tipo=autor&valor=Kafka` | Busca com Strategy |
| `POST` | `/obras` | Cadastra uma obra nova |
| `DELETE` | `/obras/{id}` | Remove uma obra |

Os endpoints foram validados manualmente via **Postman** durante o desenvolvimento.

### Como Executar

    # Na pasta do projeto
    mvnw.cmd spring-boot:run

A API sobe em `http://localhost:8080`.  
O console H2 fica disponível em `http://localhost:8080/h2-console`.

---

## Relatório Pessoal

Esse foi o meu primeiro contato com o desenvolvimento e consumo de APIs, foi um projeto mais complexo e trabalhoso quando comparado com os projetos anteriores, e por mais que eu tive algumas dificuldades durante o desenvolvimento desse projeto, ao final quando pude validar manualmente essa API com o Postman, ver que tudo estava funcionando conforme o planejado foi muito satisfatório, assim como sentir na pele como é feito todo o trabalho para o desenvolvimento e consumo de uma API, por mais básica que ela seja.