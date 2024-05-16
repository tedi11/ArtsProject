package models.expositions;

import models.art.ArtProject;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Exposition {
    private String name;
    private Set<ArtProject> artProjects;
    private LocalDate date;
    private int price;
    private String description;


    public Exposition(String name, LocalDate date, int price, String description) {
        this.artProjects = new HashSet<>();
        this.name = name;
        this.date = date;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ArtProject> getArtProjects() {
        return artProjects;
    }
    public void addProject(ArtProject artProject){
        artProjects.add(artProject);
    }
    public void setArtProjects(Set<ArtProject> artProjects) {
        this.artProjects = artProjects;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
