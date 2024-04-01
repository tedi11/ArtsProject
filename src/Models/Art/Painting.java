package Models.Art;

import Models.Creators.Author;

public final class Painting extends ArtProject {

    private int height;
    private int length;


    public Painting(String name, Author author, ArtisticMovement artisticMovement, int yearAppearance, int height, int length) {
        super(name, author, artisticMovement, yearAppearance);
        this.height = height;
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


}
