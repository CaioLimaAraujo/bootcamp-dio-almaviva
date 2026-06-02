# Projeto II — Sudoku em Java

## Apresentação e Contextualização

Esse foi o segundo *Projeto de Código* do Módulo **Domínio de Collections e Streams API**.

O desafio consistia em implementar um jogo de Sudoku funcional em Java, aplicando os conceitos de Orientação a Objetos, Collections Framework, enums e separação de responsabilidades entre camadas do projeto. O enunciado original pedia uma interface via terminal; optei por desenvolver a interface gráfica com a biblioteca **Swing**, tornando o projeto mais próximo de uma aplicação real.

Os números iniciais do tabuleiro são fornecidos via argumentos de linha de comando (`args`), no formato `coluna,linha,valor,fixo`.

---

## Documentação Técnica

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

**Paradigma:** Orientado a Objetos  
**Recursos utilizados:** Swing, Collections Framework, enums, separação em camadas (`model`, `service`, `view`)

### Estrutura de Classes

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

### Descrição das Classes

**`GameStatus.java`** — enum com três estados possíveis do jogo: `NOT_STARTED`, `INCOMPLETE` e `COMPLETE`.

**`Cell.java`** — modela uma célula individual do tabuleiro com os atributos `value` (número atual), `fixed` (se é número inicial), `solution` (resposta correta) e `draftNumbers` (rascunhos do jogador).

**`SudokuBoard.java`** — gerencia o estado do tabuleiro, implementando as operações de colocar número, remover número, limpar jogo, verificar erros e atualizar o status da partida.

**`SudokuValidator.java`** — valida o tabuleiro inteiro por linhas, colunas e blocos 3×3, separando a responsabilidade de validação global da lógica incremental do `SudokuBoard`.

**`BoardPanel.java`** — componente Swing que desenha o tabuleiro célula por célula, destacando células selecionadas (azul), fixas (cinza) e com erro (vermelho).

**`SudokuFrame.java`** — janela principal com o tabuleiro e os botões: Remover, Limpar Jogo, Verificar, Status e Sair.

### Como Executar

    # Compilar
    javac -d out src/model/Cell.java src/model/GameStatus.java src/model/SudokuBoard.java src/service/SudokuValidator.java src/view/BoardPanel.java src/view/SudokuFrame.java Main.java

    # Executar
    java -cp out Main "col,lin,valor,fixo" ...

**Exemplo de argumento:** `"0,0,4,false"` → coluna 0, linha 0, valor 4, célula não fixa (aparece vazia para o jogador preencher).

---

## Relatório Pessoal

Esse foi um dos *Projetos de Código* que mais gostei de fazer, pois tive a oportunidade de desenvolver um pequeno frontend com o Java Swing, já tive um breve contato com HTML/CSS em cursos livres anteriormente, mas foi nesse Bootcamp aqui onde tive a oportunidade de fazer algo muito mais complexo e responsivo do que uma simples landing page. Conseguir executar o programa e ver ele rodando na prática da maneira responsiva e visual foi emocionante.