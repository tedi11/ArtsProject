package app;

import app.service.ServiceArt;
import app.service.ServiceCreators;
import models.art.ArtProject;
import models.art.ArtisticMovement;
import models.art.Painting;
import models.art.Sculpture;
import models.creators.Author;
import models.expositions.Address;
import models.expositions.Exposition;
import models.expositions.Museum;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public final class Reader {
    private static Reader instance;

    private Reader() {
    }

    static {
        try {
            instance = new Reader();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Service singleton instance");
        }
    }

    public static Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }

    private void typeOfArtProject(String op){
        System.out.println("What type of art project do you want to " + op + "?");
        System.out.println("1. Painting");
        System.out.println("2. Sculpture");
    }
    public ArtProject readArtProject(){
        int op;
        Scanner reader = new Scanner(System.in);
        ArtProject artProject;
        typeOfArtProject("add");
        try {
            op = reader.nextInt();
            reader.nextLine();
            System.out.print("Details about the project: ");
            switch (op) {
                case 1 -> {
                    String name;
                    ArtisticMovement artisticMovement;
                    Author author;
                    int year, height, length;
                    System.out.print("Name of the project: ");
                    name = reader.nextLine();
                    System.out.print("The year when the project was published: ");
                    year = reader.nextInt();
                    System.out.print("Height of the project: ");
                    height = reader.nextInt();
                    System.out.print("Length of the project: ");
                    length = reader.nextInt();
                    artisticMovement = readArtisticMovement();
                    author = readAuthor();
                    ServiceCreators.addAuthor(author);
                    ServiceArt.addArtisticMovement(artisticMovement);
                    reader.nextLine();
                    artProject = new Painting(name, author, artisticMovement, year, height, length);
                }
                case 2 -> {
                    String name, material;
                    ArtisticMovement artisticMovement;
                    Author author;
                    int year, weight;
                    System.out.print("Name of the project: ");
                    name = reader.nextLine();
                    System.out.print("The year when the project was published: ");
                    year = reader.nextInt();
                    System.out.print("Weight of the project: ");
                    weight = reader.nextInt();
                    System.out.print("Material of the project: ");
                    material = reader.nextLine();
                    String nothing = reader.nextLine();
                    artisticMovement = readArtisticMovement();
                    author = readAuthor();
                    ServiceCreators.addAuthor(author);
                    ServiceArt.addArtisticMovement(artisticMovement);
                    reader.nextLine();
                    artProject = new Sculpture(name, author, artisticMovement, year, weight, material);
                }

                default -> {
                    System.out.println("Invalid option!");
                    artProject = null;
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return null;
        }

        return artProject;
    }

    public Author readAuthor(){
        String name, nat;
        int age;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.print("Name of the author: ");
            name = reader.nextLine();
            System.out.print("Nationality of the author: ");
            nat = reader.nextLine();
            System.out.print("Age of the author: ");
            age = reader.nextInt();
            reader.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return null;
        }

        return new Author(name, nat, age);
    }

    public String readAuthorName(){
        String name;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.print("Name of the author: ");
            name = reader.nextLine();
            reader.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return null;
        }

        return name;
    }

    public int readMuseumNr(){

        int nr;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.print("Number of the museum: ");
            nr = reader.nextInt();
            reader.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return -1;
        }

        return nr;
    }

    public int readExpoNr(){

        int nr;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.print("Number of the exposition: ");
            nr = reader.nextInt();
            reader.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return -1;
        }

        return nr;
    }

    public int readArtNr(){

        int nr;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.print("Number of the art project: ");
            nr = reader.nextInt();
            reader.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return -1;
        }

        return nr;
    }

    public Address readAddress(){
        String country, city, street;
        int number;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.print("Details about address: ");
            System.out.print("Country: ");
            country = reader.nextLine();
            System.out.print("City: ");
            city = reader.nextLine();
            System.out.print("Street: ");
            street = reader.nextLine();
            System.out.print("Number: ");
            number = reader.nextInt();

        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return null;
        }

        return new Address(country, city, street, number);
    }

    public ArtisticMovement readArtisticMovement(){
        String name, firstArtist, country;
        int yearApperence;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.print("Details about the artistic movement: ");
            System.out.print("Name of the artistic movement: ");
            name = reader.nextLine();
            System.out.print("Country where the artistic movement first appeared: ");
            country = reader.nextLine();
            System.out.print("What is the first artist of the artistic movement? ");
            firstArtist = reader.nextLine();
            System.out.print("What year did it appear? ");
            yearApperence = reader.nextInt();
            reader.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return null;
        }

        return new ArtisticMovement(name, yearApperence, firstArtist, country);
    }

    public Museum readMuseum(){
        String name, contact;
        Address address;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.print("Name of the museum: ");
            name = reader.nextLine();
            System.out.print("Email to contact the museum: ");
            contact = reader.nextLine();
            address = readAddress();

        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return null;
        }

        return new Museum(name, contact, address);
    }

    public Exposition readExposition(){
        LocalDate date = null;
        boolean validDate = false;
        int price, noProjects;
        String description, name;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.print("Name of the exposition: ");
            name = reader.nextLine();
            while (!validDate) {
                System.out.print("Date of the exposition (yyyy-mm-dd): ");
                String dateInput = reader.nextLine();
                try {
                    date = LocalDate.parse(dateInput);
                    validDate = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format yyyy-mm-dd.");
                }
            }
            System.out.print("Description of the exposition: ");
            description = reader.nextLine();
            System.out.print("Price of the exposition: ");
            price = Integer.parseInt(reader.nextLine());
            reader.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return null;
        }

        return new Exposition(name, date, price, description);
    }

    public String readName(String type){
        Scanner reader = new Scanner(System.in);
        String name;
        try {

            System.out.print("Name of the " + type + "? ");
            name = reader.nextLine();
            reader.nextLine();
        }catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            return null;
        }
        return name;
    }
}