# Acervo API

## Apresentação

Este projeto foi desenvolvido como parte do Bootcamp Java Backend & QA (Almaviva + DIO),
no módulo de Design Patterns com Java e Spring Framework.

A **Acervo API** é uma API REST de gerenciamento de obras intelectuais — livros, ensaios
e vídeo-ensaios — construída com Spring Boot e aplicando padrões de projeto clássicos do GoF.
O tema foi escolhido por ser pessoalmente significativo: sou escritor e vídeo-ensaísta amador,
e o acervo inicial reflete obras e autores que me influenciam diretamente.

---

## Documentação Técnica

### Tecnologias Utilizadas

| Tecnologia | Versão |
|---|---|
| Java (Amazon Corretto) | 25 |
| Spring Boot | 3.5.14 |
| Spring Data JPA | — |
| H2 Database | — |
| Lombok | — |

### Design Patterns Aplicados

| Padrão | Onde |
|---|---|
| **Factory Method** | `ObraFactory` — instancia o subtipo correto conforme o campo `tipo` |
| **Strategy** | `BuscaStrategy` — alterna entre busca por autor e por gênero |
| **Facade** | `AcervoService` — orquestra factory, strategy e repository em uma interface simples |

### Estrutura de Pacotes

```
src/main/java/com/caio/acervoapi/
├── controller/       → ObraController (endpoints REST)
├── factory/          → ObraFactory (Factory Method)
├── model/            → Obra, Livro, Ensaio, VideoEnsaio
├── repository/       → ObraRepository (Spring Data JPA)
├── service/          → AcervoService (Facade)
└── strategy/         → BuscaStrategy, BuscaPorAutor, BuscaPorGenero
```

### Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/obras` | Lista todas as obras |
| `GET` | `/obras/{id}` | Busca obra por ID |
| `GET` | `/obras/buscar?tipo=autor&valor=Kafka` | Busca com Strategy |
| `POST` | `/obras` | Cadastra uma obra nova |
| `DELETE` | `/obras/{id}` | Remove uma obra |

### Como Executar

```bash
# Na pasta do projeto
mvnw.cmd spring-boot:run
```

A API sobe em `http://localhost:8080`.  
O console H2 fica disponível em `http://localhost:8080/h2-console`.

---

## Relatório Pessoal

Este foi meu primeiro projeto completo com Spring Boot, desenvolvido do zero com
configuração de ambiente, estruturação de pacotes e implementação de Design Patterns
aplicados a um domínio real.

A escolha do tema — um acervo de obras intelectuais — foi deliberada: quis construir
algo com identidade própria em vez de reproduzir um exemplo genérico. O acervo inicial
contém obras que de fato fazem parte das minhas referências como escritor e estudante.

O maior aprendizado deste módulo foi perceber como os padrões GoF aparecem de forma
natural quando se pensa bem na separação de responsabilidades — especialmente o
**Strategy**, que tornou a lógica de busca extensível sem modificar o código existente.
