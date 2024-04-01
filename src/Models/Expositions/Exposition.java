package Models.Expositions;

import java.time.LocalDate;
import java.util.Date;

public class Exposition {
    private Museum museum;
    private LocalDate date;
    private int price;
    private String description;
    private int noProjects;

    public Exposition(Museum museum, LocalDate date, int price, String description, int noProjects) {
        this.museum = museum;
        this.date = date;
        this.price = price;
        this.description = description;
        this.noProjects = noProjects;
    }
}
