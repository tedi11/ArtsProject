package Models.Expositions;

import Models.Art.ArtProject;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Exposition {
    private Set<ArtProject> artProjects;
    private LocalDate date;
    private int price;
    private String description;
    private int noProjects;

    public Exposition(LocalDate date, int price, String description, int noProjects) {
        this.artProjects = new HashSet<>();
        this.date = date;
        this.price = price;
        this.description = description;
        this.noProjects = noProjects;
    }
}
