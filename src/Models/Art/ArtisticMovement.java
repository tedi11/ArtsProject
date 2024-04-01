package Models.Art;

public class ArtisticMovement {
    private String name;
    private int yearApperence;
    private String firstArtist;
    private String country;

    public ArtisticMovement(String name, int yearApperence, String firstArtist, String country) {
        this.name = name;
        this.yearApperence = yearApperence;
        this.firstArtist = firstArtist;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearApperence() {
        return yearApperence;
    }

    public void setYearApperence(int yearApperence) {
        this.yearApperence = yearApperence;
    }

    public String getFirstArtist() {
        return firstArtist;
    }

    public void setFirstArtist(String firstArtist) {
        this.firstArtist = firstArtist;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
