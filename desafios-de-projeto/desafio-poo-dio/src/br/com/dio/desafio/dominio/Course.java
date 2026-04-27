package br.com.dio.desafio.dominio;

public class Course extends Content {

    // A course grants XP based on how long it is
    private int workload;

    @Override
    public double calculateXp() {
        return DEFAULT_XP * workload;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", workload=" + workload +
                '}';
    }
}