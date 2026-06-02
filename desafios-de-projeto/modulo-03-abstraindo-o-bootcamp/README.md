# Projeto I — Abstraindo um Bootcamp com Orientação a Objetos em Java

## Apresentação e Contextualização

Esse foi o primeiro *Projeto de Código* do Módulo **Orientação a Objetos com Java**, parte do **Bootcamp Java Backend & QA**.

O desafio propunha modelar o domínio de um bootcamp aplicando na prática os quatro pilares da Programação Orientada a Objetos. O sistema deveria representar cursos, mentorias, desenvolvedores e o progresso de inscrição — simulando de forma abstraída o próprio bootcamp que estávamos cursando.

---

## Documentação Técnica

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

**Paradigma:** Orientado a Objetos  
**Recursos utilizados:** Classes abstratas, herança, encapsulamento, polimorfismo, `LinkedHashSet`, `ArrayDeque`

### Os Quatro Pilares Aplicados

| Pilar | Como foi aplicado |
|-------|-------------------|
| **Abstração** | Classe abstrata `Content` modela o conceito genérico de conteúdo |
| **Encapsulamento** | Todos os atributos são `private`, acessados via getters e setters |
| **Herança** | `Course` e `Mentorship` herdam atributos e comportamentos de `Content` |
| **Polimorfismo** | `calculateXp()` tem implementações distintas em `Course` e `Mentorship` |

### Estrutura de Classes

    src/br/com/dio/desafio/dominio/
    ├── Content.java       → Classe abstrata base (Abstração)
    ├── Course.java        → Herda de Content; XP = 10 × carga horária
    ├── Mentorship.java    → Herda de Content; XP = 30 (fixo)
    ├── Bootcamp.java      → Agrega conteúdos e devs inscritos
    ├── Dev.java           → Aluno que se inscreve, progride e acumula XP
    └── Main.java          → Ponto de entrada e simulação do sistema

### Sistema de XP

| Conteúdo | Fórmula | Exemplo |
|----------|---------|---------|
| `Course` | `10 × carga horária` | 8h → 80 XP |
| `Mentorship` | `10 + 20` (fixo) | sempre → 30 XP |

### Como Executar

    # Compilar
    javac -d out src/br/com/dio/desafio/dominio/*.java

    # Executar
    java -cp out br.com.dio.desafio.dominio.Main

---

## Relatório Pessoal

Esse foi o primeiro *Projeto de Código* do Bootcamp, onde pude exercitar alguns dos pilares da Programação Orientada a Objetos, com destaque para o pilar de Abstração. Foi um projeto muito interessante pois foi a minha primeira experiência com a codificação de algo mais complexo do que um único arquivo com poucas linhas de código, e também pois tive a oportunidade de aprender na prática não só como seria a codificação de um projeto/software mais complexo, como até mesmo de compreender melhor os pilares da POO.