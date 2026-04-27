package br.com.dio.desafio.dominio;

import java.time.LocalDate;

public class Mentorship extends Content {

    // A mentorship grants a fixed XP bonus on top of the default
    private LocalDate date;

    @Override
    public double calculateXp() {
        return DEFAULT_XP + 20d;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Mentorship{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", date=" + date +
                '}';
    }
}