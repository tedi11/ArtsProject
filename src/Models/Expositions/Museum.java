package Models.Expositions;

import Models.Art.ArtProject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Museum {
    private String name;
    private String contactEmail;
    private Address address;
    private Set<ArtProject> artProjects;

    public Museum(String name, String contactEmail, Address address) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.address = address;
        this.artProjects = new HashSet<>();
    }

    public Museum(String name, String contactEmail, Address address, Set<ArtProject> artProjects) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.address = address;
        this.artProjects = artProjects;

    }
}
