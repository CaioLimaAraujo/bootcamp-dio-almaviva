# Desafio II — Validação de Nomes de Projetos em TI

## Apresentação e Contextualização

Esse foi o segundo *Desafio de Código* do Módulo **Primeiros Passos na Sintaxe Java**. Ele é uma evolução direta do primeiro desafio do mesmo módulo.

O contexto é o mesmo: uma empresa de TI modernizando seus sistemas e padronizando os nomes de projetos internos. Desta vez, porém, o desafio adicionou uma camada de **validação**: os nomes poderiam chegar com espaços desnecessários no início ou no fim, e alguns poderiam estar completamente vazios ou conter apenas espaços — situações que deveriam ser sinalizadas como inválidas.

A tarefa era desenvolver um programa que, dado o nome de um projeto, retornasse o nome em letras maiúsculas (após remover espaços extras das bordas), ou a mensagem `INVALIDO` caso o nome fosse vazio ou contivesse apenas espaços.

| Entrada        | Saída          |
|----------------|----------------|
| `projeto beta` | `PROJETO BETA` |
| `   `          | `INVALIDO`     |
| ` Sistema3 `   | `SISTEMA3`     |

---

## Documentação Técnica

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

**Paradigma:** Orientado a Objetos  
**Recursos utilizados:** Classe `Scanner` (`java.util`), métodos `trim()`, `isEmpty()` e `toUpperCase()` da classe `String`, estrutura condicional `if/else`

### Código-fonte

    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            String nomeProjeto = scanner.nextLine();

            String nomeTratado = nomeProjeto.trim();

            if (nomeTratado.isEmpty()) {
                System.out.println("INVALIDO");
            } else {
                System.out.println(nomeTratado.toUpperCase());
            }

            scanner.close();
        }
    }

### Explicação linha a linha

---

**Linha 1 — `import java.util.Scanner;`**

Importa a classe `Scanner` da biblioteca padrão do Java, tornando-a disponível para uso no programa.

- `import` — comando que torna uma classe externa acessível no arquivo atual.
- `java.util` — pacote padrão do Java que agrupa classes utilitárias.
- `Scanner` — classe responsável por realizar a leitura de dados de entrada.

---

**Linha 3 — `public class Main {`**

Declara a única classe da aplicação.

- `public` — modificador de acesso que torna a classe visível para toda a aplicação.
- `class` — palavra-chave que declara uma classe em Java.
- `Main` — nome da classe, idêntico ao nome do arquivo (`Main.java`).

---

**Linha 4 — `public static void main(String[] args) {`**

Declara o método principal da aplicação — o ponto de entrada que a JVM executa primeiro.

- `public` — o método precisa ser acessível pela JVM.
- `static` — permite que a JVM chame o método sem instanciar a classe.
- `void` — o método não retorna nenhum valor.
- `main` — nome obrigatório reconhecido pela JVM como ponto de entrada.
- `String[] args` — parâmetro para argumentos externos via linha de comando (não utilizado neste desafio).

---

**Linha 5 — `Scanner scanner = new Scanner(System.in);`**

Cria um objeto do tipo `Scanner` configurado para ler dados do teclado.

- `Scanner scanner` — declara a variável `scanner` do tipo `Scanner`.
- `new Scanner(...)` — instancia um novo objeto dessa classe.
- `System.in` — indica que a fonte de leitura é a entrada padrão do sistema (teclado).

---

**Linha 7 — `String nomeProjeto = scanner.nextLine();`**

Declara uma variável `String` e a preenche com a linha digitada pelo usuário.

- `String` — tipo de dado que representa texto em Java.
- `nomeProjeto` — nome escolhido para a variável.
- `scanner.nextLine()` — lê uma linha inteira da entrada, incluindo espaços.

---

**Linha 9 — `String nomeTratado = nomeProjeto.trim();`**

Cria uma nova variável `String` com o conteúdo de `nomeProjeto` sem os espaços das bordas.

- `nomeTratado` — nova variável que armazenará a string tratada.
- `trim()` — método da classe `String` que remove todos os espaços em branco do início e do fim da string. O conteúdo interno (espaços entre palavras) é preservado.

A variável `nomeProjeto` permanece inalterada; `nomeTratado` é uma nova string derivada dela.

---

**Linha 11 — `if (nomeTratado.isEmpty()) {`**

Inicia uma estrutura condicional que verifica se a string tratada está vazia.

- `if` — palavra-chave que inicia uma estrutura condicional: *"se a condição for verdadeira, execute o bloco."*
- `nomeTratado.isEmpty()` — método da classe `String` que retorna `true` se a string não contiver nenhum caractere, e `false` caso contrário.

A verificação é feita sobre `nomeTratado` (e não `nomeProjeto`) intencionalmente: queremos saber se, após remover os espaços das bordas, ainda sobrou algum conteúdo válido.

---

**Linha 12 — `System.out.println("INVALIDO");`**

Executado quando a condição do `if` é verdadeira — ou seja, quando a string está vazia após o tratamento.

- `System.out.println()` — imprime o texto no terminal e quebra a linha ao final.
- `"INVALIDO"` — mensagem literal impressa quando o nome é considerado inválido.

---

**Linha 13 — `} else {`**

Define o bloco alternativo da estrutura condicional, executado quando a condição do `if` é falsa — ou seja, quando a string possui conteúdo válido.

- `else` — palavra-chave que indica: *"caso contrário, execute este bloco."*

---

**Linha 14 — `System.out.println(nomeTratado.toUpperCase());`**

Imprime o nome tratado em letras maiúsculas.

- `toUpperCase()` — método da classe `String` que retorna uma nova string com todos os caracteres convertidos para maiúsculas.
- O resultado impresso já virá sem os espaços extras das bordas, pois partimos de `nomeTratado`.

---

**Linha 17 — `scanner.close();`**

Encerra o objeto `scanner`, liberando o recurso de entrada do sistema operacional.

- `close()` — método que fecha o `Scanner` após o uso, evitando vazamento de recursos.

---

## Relatório Pessoal

Esse foi o segundo e último *Desafio de Código* do Bootcamp, tendo novamente a proposta de resolver um problema simples para exercitar tanto a lógica quanto a sintaxe básica do desenvolvimento em Java, mas desta vez acrescentando uma camada extra de complexidade. Foi uma experiência legal e interessante resolver esse desafio, assim como em perceber como de uma certa maneira, ele é quase como se fosse uma evolução do primeiro desafio, pois ele remete ao problema do primeiro desafio, mas ao incrementar uma camada extra de requisitos, ele me lembrou sobre a escalabilidade no desenvolvimento de softwares, quase como se o código no primeiro desafio fosse a versão 1 de um determinado software, e o código do segundo desafio fosse uma espécie de melhoramento/versão 2 desse mesmo software, visando atender necessidades que só foram percebidas e/ou surgidas depois.