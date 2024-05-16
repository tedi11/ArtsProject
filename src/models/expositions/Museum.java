package models.expositions;

import models.art.ArtProject;

import java.util.HashSet;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Address getAddress() {
        return address;
    }
    public void addProject(ArtProject artProject){
        artProjects.add(artProject);
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<ArtProject> getArtProjects() {
        return artProjects;
    }

    public void setArtProjects(Set<ArtProject> artProjects) {
        this.artProjects = artProjects;
    }
}
