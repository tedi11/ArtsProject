package Models.Expositions;

import java.util.List;
import java.util.Set;

public class Museum {
    private String name;
    private String contactEmail;
    private Address address;
    //private Set<>

    public Museum(String name, String contactEmail, Address address, boolean acceptsVouchers) {
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
