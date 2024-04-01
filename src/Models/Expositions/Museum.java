package Models.Expositions;

public class Museum {
    private String name;
    private String contactEmail;
    private Address address;

    public Museum(String name, String contactEmail, Address address) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.address = address;
    }

    public Museum(Museum m) {
        this.name = m.name;
        this.contactEmail = m.contactEmail;
        this.address = new Address(m.address);
    }

}
