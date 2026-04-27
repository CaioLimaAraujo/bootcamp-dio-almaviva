package br.com.dio.desafio.dominio;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // --- Creating the Courses ---
        Course javaCore = new Course();
        javaCore.setTitle("Java Core");
        javaCore.setDescription("Learn the fundamentals of the Java language");
        javaCore.setWorkload(8);

        Course springBoot = new Course();
        springBoot.setTitle("Spring Boot");
        springBoot.setDescription("Build REST APIs with Spring Boot");
        springBoot.setWorkload(4);

        // --- Creating the Mentorship ---
        Mentorship mentorship = new Mentorship();
        mentorship.setTitle("Java Career Mentorship");
        mentorship.setDescription("Get guidance from a Java specialist");
        mentorship.setDate(LocalDate.now());

        // --- Creating and setting up the Bootcamp ---
        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setName("Java Backend Developer Bootcamp");
        bootcamp.setDescription("From Java basics to production-ready APIs");
        bootcamp.getContents().add(javaCore);
        bootcamp.getContents().add(springBoot);
        bootcamp.getContents().add(mentorship);

        // --- Creating the Devs ---
        Dev caio = new Dev();
        caio.setName("Caio");
        caio.enrollIn(bootcamp);

        Dev joao = new Dev();
        joao.setName("João");
        joao.enrollIn(bootcamp);

        // --- Simulating Caio's progress ---
        System.out.println("====== CAIO ======");
        System.out.println("Enrolled contents: " + caio.getEnrolledContents());
        caio.progress(); // completes Java Core
        caio.progress(); // completes Spring Boot
        System.out.println("Enrolled contents after progress: " + caio.getEnrolledContents());
        System.out.println("Completed contents: " + caio.getCompletedContents());
        System.out.println("Total XP: " + caio.calculateTotalXp());

        System.out.println();

        // --- Simulating João's progress ---
        System.out.println("====== JOÃO ======");
        System.out.println("Enrolled contents: " + joao.getEnrolledContents());
        joao.progress(); // completes Java Core
        System.out.println("Enrolled contents after progress: " + joao.getEnrolledContents());
        System.out.println("Completed contents: " + joao.getCompletedContents());
        System.out.println("Total XP: " + joao.calculateTotalXp());
    }
}