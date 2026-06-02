# Desafio I — Padronização de Nomes de Projetos em TI

## Apresentação e Contextualização

Esse foi o primeiro *Desafio de Código* do Módulo **Primeiros Passos na Sintaxe Java**.

O desafio simulava uma situação real de consultoria: uma empresa de TI precisava padronizar a identificação de seus projetos internos. Os nomes chegavam em formatos variados, e o sistema legado exigia que todos fossem armazenados em **letras maiúsculas**. Além disso, a equipe precisava saber o **número de caracteres** de cada nome, para garantir que ele não ultrapassasse o limite do sistema.

A tarefa era desenvolver um programa que, dado o nome de um projeto, retornasse o nome em letras maiúsculas seguido da quantidade de caracteres, separados por um espaço.

| Entrada         | Saída              |
|-----------------|--------------------|
| `projeto alpha` | `PROJETO ALPHA 14` |
| `Sistema2`      | `SISTEMA2 8`       |

---

## Documentação Técnica

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

**Paradigma:** Orientado a Objetos  
**Recursos utilizados:** Classe `Scanner` (`java.util`), métodos `toUpperCase()` e `length()` da classe `String`

### Código-fonte

    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            String nomeProjeto = scanner.nextLine();

            String nomeEmMaiusculas = nomeProjeto.toUpperCase();
            int comprimento = nomeProjeto.length();

            System.out.println(nomeEmMaiusculas + " " + comprimento);

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

Sem essa linha, o compilador não reconheceria `Scanner` nas linhas seguintes.

---

**Linha 3 — `public class Main {`**

Declara a única classe da aplicação.

- `public` — modificador de acesso que torna a classe visível para toda a aplicação.
- `class` — palavra-chave que declara uma classe em Java.
- `Main` — nome escolhido para a classe. Em Java, o nome da classe deve ser idêntico ao nome do arquivo (`Main.java`).

Em Java, todo o código obrigatoriamente reside dentro de uma classe.

---

**Linha 4 — `public static void main(String[] args) {`**

Declara o método principal da aplicação — o ponto de entrada que a JVM executa primeiro.

- `public` — o método precisa ser acessível pela JVM, portanto deve ser público.
- `static` — permite que a JVM chame o método sem precisar criar um objeto da classe primeiro.
- `void` — indica que o método não retorna nenhum valor.
- `main` — nome obrigatório reconhecido pela JVM como ponto de entrada.
- `String[] args` — parâmetro que permite receber argumentos externos via linha de comando (não utilizado neste desafio).

---

**Linha 5 — `Scanner scanner = new Scanner(System.in);`**

Cria um objeto do tipo `Scanner` configurado para ler dados do teclado.

- `Scanner scanner` — declara uma variável chamada `scanner` do tipo `Scanner`.
- `new Scanner(...)` — instancia um novo objeto dessa classe.
- `System.in` — indica a fonte de leitura: a entrada padrão do sistema, ou seja, o teclado.

---

**Linha 7 — `String nomeProjeto = scanner.nextLine();`**

Declara uma variável do tipo `String` e a preenche com a linha digitada pelo usuário.

- `String` — tipo de dado que representa texto em Java.
- `nomeProjeto` — nome escolhido para a variável.
- `scanner.nextLine()` — método que lê uma linha inteira da entrada, incluindo espaços, até o usuário pressionar Enter.

---

**Linha 9 — `String nomeEmMaiusculas = nomeProjeto.toUpperCase();`**

Cria uma nova variável `String` com o conteúdo de `nomeProjeto` convertido integralmente para letras maiúsculas.

- `toUpperCase()` — método da classe `String` que retorna uma nova string com todos os caracteres em maiúsculas.

A variável `nomeProjeto` original permanece inalterada; `nomeEmMaiusculas` é uma nova string derivada dela.

---

**Linha 10 — `int comprimento = nomeProjeto.length();`**

Cria uma variável inteira com o número total de caracteres de `nomeProjeto`.

- `int` — tipo de dado para números inteiros.
- `comprimento` — nome escolhido para a variável.
- `length()` — método da classe `String` que retorna a quantidade de caracteres da string, incluindo espaços e símbolos.

---

**Linha 12 — `System.out.println(nomeEmMaiusculas + " " + comprimento);`**

Imprime o resultado no formato exigido pelo desafio: nome em maiúsculas, um espaço, e o número de caracteres.

- `System.out` — objeto responsável pela saída padrão (o terminal).
- `println()` — método que imprime o texto e quebra a linha ao final.
- O operador `+` concatena os três elementos: a string em maiúsculas, um espaço literal `" "` e o valor inteiro do comprimento.

---

**Linha 14 — `scanner.close();`**

Encerra o objeto `scanner`, liberando o recurso de entrada do sistema operacional.

- `close()` — método que fecha o `Scanner` e libera os recursos que ele estava utilizando.

Boa prática sempre fechar o `Scanner` após o uso para evitar vazamento de recursos.

---

## Relatório Pessoal

Essa foi minha introdução prática à Sintaxe Básica do Java, e por mais que esse tenha sido um desafio simples, foi uma experiência muito legal ter escrito esse código e ver ele funcionando depois, assim como ir entendo toda a lógica por trás do código, como um passo a passo algorítmico para resolver o problema que foi proposto pelo desafio.