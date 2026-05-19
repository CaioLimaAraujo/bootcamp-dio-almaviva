# Board Manager

Sistema de gerenciamento de tarefas em estilo Kanban com persistência em banco de dados MySQL.  
Desenvolvido como projeto do **Bootcamp Java & QA — DIO × Almaviva** (M7.P5 — Técnicas Avançadas, Padrões e Persistência).

---

## Apresentação e Contextualização

O projeto consiste em uma aplicação de console que permite criar e gerenciar boards no estilo Kanban, com persistência completa em banco de dados relacional.

Cada board é composto por colunas customizáveis — uma coluna inicial, zero ou mais colunas pendentes, uma coluna final e uma coluna de cancelamento — seguindo regras rígidas de movimentação entre elas. Os cards navegam pelas colunas em ordem, podendo ser bloqueados com justificativa e cancelados a qualquer momento, exceto quando já concluídos.

---

## Documentação Técnica

### Tecnologias

- Java 17
- Maven
- MySQL 8
- JDBC (`mysql-connector-j 8.3.0`)

### Arquitetura

```
Main
 └── MainMenu (UI)
      ├── BoardService ──── BoardDAO       ──── MySQL
      │                └── BoardColumnDAO
      └── BoardMenu (UI)
           ├── BoardService
           └── CardService ─── CardDAO    ──── MySQL
                           └── BlockDAO
```

### Estrutura de Pacotes

```
br.com.dio.board
├── entity/          → Entidades do domínio (Board, BoardColumn, Card, BlockRecord, BoardColumnType)
├── persistence/
│   ├── config/      → Configuração de conexão JDBC (ConnectionConfig)
│   └── dao/         → Acesso ao banco de dados (BoardDAO, BoardColumnDAO, CardDAO, BlockDAO)
├── service/         → Regras de negócio (BoardService, CardService)
├── ui/              → Interface de console (MainMenu, BoardMenu)
└── Main.java        → Ponto de entrada da aplicação
```

### Banco de Dados

```sql
BOARD               → Armazena os boards criados
BOARD_COLUMN        → Colunas de cada board, com tipo e ordem
CARD                → Cards vinculados às colunas
BLOCK               → Histórico de bloqueios e desbloqueios
CARD_COLUMN_HISTORY → Histórico de movimentação dos cards entre colunas
```

### Regras de Negócio

| Regra | Implementação |
|-------|---------------|
| Board com 1 INITIAL, 1 FINAL, 1 CANCELLED e 0..N PENDING | `BoardService.createBoard()` |
| Ordem obrigatória: INITIAL → PENDING... → FINAL → CANCELLED | `col_order` no banco + `BoardColumnDAO` |
| Cards só avançam na ordem, sem pular etapas | `CardService.moveCard()` |
| CANCELLED recebe cards de qualquer coluna exceto FINAL | `CardService.cancelCard()` |
| Card bloqueado não pode ser movido nem cancelado | Verificação em `moveCard()` e `cancelCard()` |
| Bloquear e desbloquear exige justificativa | Parâmetro obrigatório + registro em `BLOCK` |

---

## Como Executar

### Pré-requisitos

- JDK 17+
- Maven 3.8+
- MySQL 8+

### Configuração do banco

```bash
mysql -u root -p -e "CREATE DATABASE board_db;"
mysql -u root -p board_db < schema.sql
```

### Variáveis de ambiente

A senha do banco não está hardcoded no código — é lida via variáveis de ambiente para evitar exposição no repositório:

```bash
export DB_USER=root
export DB_PASSWORD=sua_senha
```

### Compilar e executar

```bash
mvn compile
mvn exec:java -Dexec.mainClass="br.com.dio.board.Main"
```

---

## Relatório Pessoal

A experiência de desenvolver esse projeto foi agradável e reveladora. Ao iniciar os estudos em programação, tive a sensação de que muitas coisas seriam extremamente difíceis de realizar. Esse projeto, assim como os outros do bootcamp, mostrou que superestimei bastante a dificuldade de desenvolver aplicações — mesmo as mais simples. Foi muito satisfatório ver na prática como uma aplicação real é estruturada.

Uma das observações mais interessantes foi perceber que há padrões universais que se repetem entre os projetos: a estrutura de pastas, a separação de responsabilidades em camadas, a forma como os arquivos se organizam. Fica claro que as coisas não são aleatórias — há uma lógica consistente por trás de tudo.

Outro ponto que me chamou atenção foi a questão da segurança: percebi que deixar a senha do banco de dados hardcoded no código seria uma vulnerabilidade real caso o projeto fosse publicado no GitHub. Resolver isso com variáveis de ambiente antes do push foi uma lição prática de boas práticas que ficou bem fixada.

A principal dificuldade continua sendo a execução de comandos no terminal — saber exatamente quando, onde e como executar cada comando ainda gera alguma confusão, mas cada projeto tem ajudado a tornar isso mais natural.
