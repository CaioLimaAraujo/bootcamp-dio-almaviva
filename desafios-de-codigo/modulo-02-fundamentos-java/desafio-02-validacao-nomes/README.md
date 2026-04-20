# 📁 Java Project Name Standardization Challenge

## 📌 Apresentação e Contextualização

Esse foi o segundo Desafio de Código do módulo **Primeiros Passos na Sintaxe Java**, parte do **Bootcamp Backend Java & QA** — uma parceria entre a **DIO (Digital Innovation One)** e a **Almaviva**. Ele é uma evolução direta do primeiro desafio do mesmo módulo.

O contexto é o mesmo: uma empresa de TI modernizando seus sistemas e padronizando os nomes de projetos internos. Desta vez, porém, o desafio adicionou uma camada de **validação**: os nomes poderiam chegar com espaços desnecessários no início ou no fim, e alguns poderiam estar completamente vazios ou conter apenas espaços — situações que deveriam ser sinalizadas como inválidas.

A tarefa era desenvolver um programa que, dado o nome de um projeto, retornasse o nome em letras maiúsculas (após remover espaços extras das bordas), ou a mensagem `INVALIDO` caso o nome fosse vazio ou contivesse apenas espaços.

**Exemplo:**

| Entrada        | Saída         |
|----------------|---------------|
| `projeto beta` | `PROJETO BETA` |
| `   `          | `INVALIDO`    |
| ` Sistema3 `   | `SISTEMA3`    |

---

## 🛠️ Documentação Técnica

**Linguagem:** Java  
**Paradigma:** Orientado a Objetos  
**Recursos utilizados:** Classe `Scanner` (`java.util`), métodos `trim()`, `isEmpty()` e `toUpperCase()` da classe `String`, estrutura condicional `if/else`

### Código-fonte

```java
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
```

### Explicação linha a linha

---

**Linha 1 — `import java.util.Scanner;`**

Importa a classe `Scanner` da biblioteca padrão do Java, tornando-a disponível para uso no programa.

- `import` — comando que torna uma classe externa acessível no arquivo atual.
- `java.util` — pacote (biblioteca) padrão do Java que agrupa classes utilitárias.
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

⚫ **Linha 5 — Bloco de leitura de entrada**

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

⚫ **Linha 9 — Bloco de tratamento da entrada**

---

**Linha 9 — `String nomeTratado = nomeProjeto.trim();`**

Cria uma nova variável `String` com o conteúdo de `nomeProjeto` sem os espaços das bordas.

- `nomeTratado` — nova variável que armazenará a string tratada.
- `trim()` — método da classe `String` que remove todos os espaços em branco do início e do fim da string. O conteúdo interno (espaços entre palavras) é preservado.

A variável `nomeProjeto` permanece inalterada; `nomeTratado` é uma nova string derivada dela.

---

⚫ **Linhas 11 a 15 — Bloco de validação e saída**

---

**Linha 11 — `if (nomeTratado.isEmpty()) {`**

Inicia uma estrutura condicional que verifica se a string tratada está vazia.

- `if` — palavra-chave que inicia uma estrutura condicional: *"se a condição for verdadeira, execute o bloco."*
- `nomeTratado.isEmpty()` — método da classe `String` que retorna `true` se a string não contiver nenhum caractere, e `false` caso contrário.

A verificação é feita sobre `nomeTratado` (e não `nomeProjeto`) intencionalmente: queremos saber se, **após remover os espaços das bordas**, ainda sobrou algum conteúdo válido.

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

## 📝 Relatório Pessoal

Esse segundo desafio foi uma evolução direta do primeiro, introduzindo o conceito de **validação de entrada** — algo fundamental em qualquer aplicação real. Através dele pude exercitar e consolidar os seguintes conhecimentos:

- O uso do método `trim()` para sanitizar strings antes de processá-las, removendo ruídos de formatação.
- O uso do método `isEmpty()` para verificar se uma string está vazia após o tratamento.
- A construção de estruturas condicionais `if/else` para controlar o fluxo da aplicação com base em uma condição.
- A importância de operar sobre a variável **tratada** (`nomeTratado`) e não sobre a original (`nomeProjeto`) na validação — um detalhe sutil, mas decisivo para o funcionamento correto da lógica.
- A distinção entre **método** e **função**: em Java, todo bloco de código pertence a uma classe, sendo portanto um método.

A combinação `trim()` + `isEmpty()` é um padrão de validação muito comum na prática profissional, e reconhecer isso já nesse estágio foi um aprendizado valioso.
