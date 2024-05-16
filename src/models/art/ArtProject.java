package models.art;

import models.creators.Author;

import java.util.Objects;

public class ArtProject {
    protected String name;
    protected Author author;
    protected ArtisticMovement artisticMovement;
    protected int yearApperence;

    public ArtProject(String name, Author author, ArtisticMovement artisticMovement, int yearApperence) {
        this.name = name;
        this.author = author;
        this.artisticMovement = artisticMovement;
        this.yearApperence = yearApperence;
    }

    public ArtProject() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtProject that = (ArtProject) o;
        return yearApperence == that.yearApperence &&
                Objects.equals(name, that.name) &&
                Objects.equals(author, that.author) &&
                Objects.equals(artisticMovement, that.artisticMovement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, artisticMovement, yearApperence);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ArtisticMovement getArtisticMovement() {
        return artisticMovement;
    }

    public void setArtisticMovement(ArtisticMovement artisticMovement) {
        this.artisticMovement = artisticMovement;
    }

    public int getYearApperence() {
        return yearApperence;
    }

    public void setYearApperence(int yearApperence) {
        this.yearApperence = yearApperence;
    }
}
