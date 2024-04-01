package Models.Art;

public class Sculpture {
    private int weight;
    private String material;
    private String color;

    public Sculpture(int weight, String material, String color) {
        this.weight = weight;
        this.material = material;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
