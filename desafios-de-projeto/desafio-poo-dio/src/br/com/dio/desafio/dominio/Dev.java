package br.com.dio.desafio.dominio;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Dev {

    private String name;

    // Contents the dev is enrolled in but hasn't completed yet
    private Set<Content> enrolledContents = new LinkedHashSet<>();
    // Contents the dev has already completed
    private Set<Content> completedContents = new HashSet<>();

    // Enrolls the dev in all contents of a given bootcamp
    public void enrollIn(Bootcamp bootcamp) {
        this.enrolledContents.addAll(bootcamp.getContents());
        bootcamp.getEnrolledDevs().add(this);
    }

    // Moves the first pending content to the completed set
    public void progress() {
        Optional<Content> content = this.enrolledContents.stream().findFirst();
        if (content.isPresent()) {
            this.completedContents.add(content.get());
            this.enrolledContents.remove(content.get());
        } else {
            System.err.println("You are not enrolled in any content!");
        }
    }

    // Sums the XP of all completed contents
    public double calculateTotalXp() {
        return this.completedContents
                .stream()
                .mapToDouble(Content::calculateXp)
                .sum();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Content> getEnrolledContents() {
        return enrolledContents;
    }

    public void setEnrolledContents(Set<Content> enrolledContents) {
        this.enrolledContents = enrolledContents;
    }

    public Set<Content> getCompletedContents() {
        return completedContents;
    }

    public void setCompletedContents(Set<Content> completedContents) {
        this.completedContents = completedContents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(name, dev.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}