# Abstraindo um Bootcamp com Orientação a Objetos em Java 🚀

Projeto desenvolvido como parte do **Bootcamp Java Backend & QA** da [DIO](https://www.dio.me/) em parceria com a **Almaviva**.

---

## 🎯 Objetivo

Modelar o domínio de um Bootcamp aplicando na prática os **4 pilares da Programação Orientada a Objetos (POO)** em Java.

---

## 🏛️ Os 4 Pilares Aplicados

| Pilar | Como foi aplicado |
|---|---|
| **Abstração** | Classe abstrata `Content` modela o conceito genérico de conteúdo |
| **Encapsulamento** | Todos os atributos são `private`, acessados via getters e setters |
| **Herança** | `Course` e `Mentorship` herdam atributos e comportamentos de `Content` |
| **Polimorfismo** | `calculateXp()` tem implementações distintas em `Course` e `Mentorship` |

---

## 🗂️ Estrutura do Projeto

```
src/br/com/dio/desafio/dominio/
├── Content.java       → Classe abstrata base (Abstração)
├── Course.java        → Herda de Content, XP = 10 * workload
├── Mentorship.java    → Herda de Content, XP = 10 + 20 (fixo)
├── Bootcamp.java      → Agrega conteúdos e devs inscritos
├── Dev.java           → Aluno que se inscreve, progride e acumula XP
└── Main.java          → Ponto de entrada e simulação do sistema
```

---

## ⚙️ Sistema de XP

| Conteúdo | Fórmula | Exemplo |
|---|---|---|
| `Course` | `10 × workload (horas)` | 8h → 80 XP |
| `Mentorship` | `10 + 20` (fixo) | sempre → 30 XP |

---

## 🛠️ Tecnologias

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

---

## ▶️ Como Executar

```bash
# Compilar
javac -d out src/br/com/dio/desafio/dominio/*.java

# Executar
java -cp out br.com.dio.desafio.dominio.Main
```

---

## 👤 Autor

Feito por **Caio Lima Araujo** — Graduando em TI pela UNIVESP.  
[![GitHub](https://img.shields.io/badge/GitHub-kλiryøu-181717?style=flat&logo=github)](https://github.com/SEU_USUARIO)
