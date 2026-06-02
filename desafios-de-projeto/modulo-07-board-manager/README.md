# Projeto IV — Task Board Manager

## Apresentação e Contextualização

Esse foi o quarto *Projeto de Código* do Módulo **Banco de Dados para Desenvolvimento Backend em Java**.

O projeto consiste em uma aplicação de console que permite criar e gerenciar boards no estilo Kanban, com persistência completa em banco de dados relacional. Cada board é composto por colunas customizáveis — uma coluna inicial, zero ou mais colunas pendentes, uma coluna final e uma coluna de cancelamento — seguindo regras rígidas de movimentação entre elas. Os cards navegam pelas colunas em ordem, podendo ser bloqueados com justificativa e cancelados a qualquer momento, exceto quando já concluídos.

---

## Documentação Técnica

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

**Paradigma:** Orientado a Objetos  
**Recursos utilizados:** JDBC (`mysql-connector-j 8.3.0`), SQL (DDL/DML), transações, variáveis de ambiente

### Arquitetura

    Main
     └── MainMenu (UI)
          ├── BoardService ──── BoardDAO       ──── MySQL
          │                └── BoardColumnDAO
          └── BoardMenu (UI)
               ├── BoardService
               └── CardService ─── CardDAO    ──── MySQL
                               └── BlockDAO

### Estrutura de Pacotes

    br.com.dio.board
    ├── entity/          → Entidades do domínio (Board, BoardColumn, Card, BlockRecord, BoardColumnType)
    ├── persistence/
    │   ├── config/      → Configuração de conexão JDBC (ConnectionConfig)
    │   └── dao/         → Acesso ao banco de dados (BoardDAO, BoardColumnDAO, CardDAO, BlockDAO)
    ├── service/         → Regras de negócio (BoardService, CardService)
    ├── ui/              → Interface de console (MainMenu, BoardMenu)
    └── Main.java        → Ponto de entrada da aplicação

### Banco de Dados

    BOARD               → Armazena os boards criados
    BOARD_COLUMN        → Colunas de cada board, com tipo e ordem
    CARD                → Cards vinculados às colunas
    BLOCK               → Histórico de bloqueios e desbloqueios
    CARD_COLUMN_HISTORY → Histórico de movimentação dos cards entre colunas

### Regras de Negócio

| Regra | Implementação |
|-------|---------------|
| Board com 1 INITIAL, 1 FINAL, 1 CANCELLED e 0..N PENDING | `BoardService.createBoard()` |
| Ordem obrigatória: INITIAL → PENDING... → FINAL → CANCELLED | `col_order` no banco + `BoardColumnDAO` |
| Cards só avançam na ordem, sem pular etapas | `CardService.moveCard()` |
| CANCELLED recebe cards de qualquer coluna exceto FINAL | `CardService.cancelCard()` |
| Card bloqueado não pode ser movido nem cancelado | Verificação em `moveCard()` e `cancelCard()` |
| Bloquear e desbloquear exige justificativa | Parâmetro obrigatório + registro em `BLOCK` |

### Segurança

A senha do banco de dados não está hardcoded no código-fonte — ela é lida via variáveis de ambiente, evitando a exposição de credenciais no repositório. Essa decisão foi tomada proativamente antes de subir o projeto, ao identificar que seguir o passo a passo sem esse ajuste criaria uma brecha de segurança real em um ambiente de produção.

    export DB_USER=root
    export DB_PASSWORD=sua_senha

### Como Executar

**Pré-requisitos:** JDK 17+, Maven 3.8+, MySQL 8+

    # Configurar o banco de dados
    mysql -u root -p -e "CREATE DATABASE board_db;"
    mysql -u root -p board_db < schema.sql

    # Compilar e executar
    mvn compile
    mvn exec:java -Dexec.mainClass="br.com.dio.board.Main"

---

## Relatório Pessoal

Esse foi meu primeiro contato prático com banco de dados relacionais, e acredito que talvez tenha sido o projeto mais complexo do Bootcamp, e até mesmo mais significativo para mim, pois pude perceber uma possível brecha de segurança antes mesmo de concluir o projeto e subir ele no GitHub. Ao longo do Bootcamp, utilizei o Claude como colaborador e ferramenta para me auxiliar no desenvolvimento dos *Desafios de Código* e *Desafios de Projeto*, e caso eu estivesse cegamente copiando e colando os códigos que ele me escrevia e cegamente seguindo o passo a passo que ele me orientava, eu teria exposto a senha do banco de dados no código-fonte, e ter percebido essa possível brecha de segurança e previnido ela em vez de remediar ela no futuro, me é motivo de muito orgulho, pois isso vai de encontro com aquilo que acredito ser o emprego sensato da inteligência artificial em desenvolvimento de software (e até mesmo em geral): usar ela como uma ferramenta de auxílio, e no máximo como colaborador, e nunca como um substituto completo ao terceirizar tanto nosso pensamento crítico para ela.