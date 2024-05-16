package models.art;

import models.creators.Author;

public final class Sculpture extends ArtProject{
    private int weight;
    private String material;


    public Sculpture(String name, Author author, ArtisticMovement artisticMovement, int yearApperence, int weight, String material) {
        super(name, author, artisticMovement, yearApperence);
        this.material = material;
        this.weight = weight;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

}
