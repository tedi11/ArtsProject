package Models.Art;

import Models.Creators.Author;

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
