package br.com.dio.desafio.dominio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Bootcamp {

    private String name;
    private String description;

    // The bootcamp starts today and lasts 2 months
    private final LocalDate startDate = LocalDate.now();
    private final LocalDate endDate = startDate.plusMonths(2);

    // Set avoids duplicate content
    private Set<Content> contents = new LinkedHashSet<>();
    // Devs enrolled in this bootcamp
    private Set<Dev> enrolledDevs = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Set<Content> getContents() {
        return contents;
    }

    public void setContents(Set<Content> contents) {
        this.contents = contents;
    }

    public Set<Dev> getEnrolledDevs() {
        return enrolledDevs;
    }

    public void setEnrolledDevs(Set<Dev> enrolledDevs) {
        this.enrolledDevs = enrolledDevs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bootcamp bootcamp = (Bootcamp) o;
        return Objects.equals(name, bootcamp.name) &&
               Objects.equals(description, bootcamp.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}