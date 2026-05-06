# 🎮 Sudoku em Java com Interface Gráfica Swing

## Apresentação / Contextualização

Este projeto foi desenvolvido como parte do **Módulo 5 — Programação Orientada a Objetos e Estruturas de Dados com Java** do Bootcamp Backend Java & QA, parceria entre **Almaviva** e **DIO (Digital Innovation One)**.

O desafio consiste em implementar um jogo de Sudoku funcional em Java, com interface gráfica desenvolvida com a biblioteca **Swing**, aplicando os conceitos de **Orientação a Objetos**, **Collections Framework**, **enums** e **separação de responsabilidades** entre camadas do projeto.

Os números iniciais do tabuleiro são fornecidos via argumentos de linha de comando (`args`), no formato `coluna,linha,valor,fixo`.

---

## Documentação Técnica

### Estrutura do Projeto

```
modulo-05-sudoku-em-java/
├── Main.java                      → Ponto de entrada; parsing dos args e inicialização da UI
└── src/
    ├── model/
    │   ├── Cell.java              → Representa uma célula do tabuleiro
    │   ├── GameStatus.java        → Enum com os estados do jogo
    │   └── SudokuBoard.java       → Lógica central do tabuleiro
    ├── service/
    │   └── SudokuValidator.java   → Validação completa do tabuleiro
    └── view/
        ├── BoardPanel.java        → Painel visual do tabuleiro (Swing)
        └── SudokuFrame.java       → Janela principal com menu de ações
```

### Descrição das Classes

**`GameStatus.java`** — enum com três estados possíveis do jogo:
- `NOT_STARTED` → jogo ainda não iniciado
- `INCOMPLETE` → jogo em andamento
- `COMPLETE` → jogo concluído com sucesso

**`Cell.java`** — modela uma célula individual do tabuleiro com os atributos `value` (número atual), `fixed` (se é número inicial), `solution` (resposta correta) e `draftNumbers` (rascunhos do jogador).

**`SudokuBoard.java`** — gerencia o estado do tabuleiro, implementando as operações de colocar número, remover número, limpar jogo, verificar erros e atualizar o status da partida.

**`SudokuValidator.java`** — valida o tabuleiro inteiro por linhas, colunas e blocos 3×3, separando a responsabilidade de validação global da lógica incremental do `SudokuBoard`.

**`BoardPanel.java`** — componente Swing que desenha o tabuleiro célula por célula, destacando células selecionadas (azul), fixas (cinza) e com erro (vermelho).

**`SudokuFrame.java`** — janela principal com o tabuleiro e os botões: Remover, Limpar Jogo, Verificar, Status e Sair.

### Como Executar

**Compilar:**
```bash
javac -d out src/model/Cell.java src/model/GameStatus.java src/model/SudokuBoard.java src/service/SudokuValidator.java src/view/BoardPanel.java src/view/SudokuFrame.java Main.java
```

**Executar:**
```bash
java -cp out Main "col,lin,valor,fixo" ...
```

**Exemplo de argumento:** `"0,0,4,false"` → coluna 0, linha 0, valor 4, célula não fixa (aparece vazia para o jogador preencher).

---

## Relatório Pessoal

Este foi o projeto mais complexo do bootcamp até o momento. A principal dificuldade foi compreender o formato dos argumentos de entrada — que incluem não só a posição e o valor, mas também se a célula é fixa ou não, e a solução correta embutida em cada célula não fixa.

A separação em camadas (`model`, `service`, `view`) foi fundamental para manter o código organizado e legível, aplicando na prática o princípio de **separação de responsabilidades** da OOP. O `SudokuBoard` cuida da lógica incremental célula a célula, enquanto o `SudokuValidator` valida o estado global do tabuleiro — duas responsabilidades distintas que justificam classes distintas.

A interface gráfica com **Swing** foi um diferencial pessoal em relação ao menu de terminal exigido pelo enunciado, tornando o projeto mais próximo de uma aplicação real e mais relevante para o portfólio.
