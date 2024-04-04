package App;

import Models.Art.ArtProject;
import Models.Art.ArtisticMovement;
import Models.Art.Painting;
import Models.Art.Sculpture;
import Models.Creators.Author;
import Models.Expositions.Address;
import Models.Expositions.Exposition;
import Models.Expositions.Museum;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

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
            switch (op) {
                case 1 -> {
                    String name;
                    ArtisticMovement artisticMovement;
                    Author author;
                    int year, height, length;
                    System.out.print("Name: ");
                    name = reader.nextLine();
                    System.out.print("Year: ");
                    year = reader.nextInt();
                    System.out.print("Height: ");
                    height = reader.nextInt();
                    System.out.print("Length: ");
                    length = reader.nextInt();
                    artisticMovement = readArtisticMovement();
                    author = readAuthor();

                    reader.nextLine();
                    artProject = new Painting(name, author, artisticMovement, year, height, length);
                }
                case 2 -> {
                    String name, material;
                    ArtisticMovement artisticMovement;
                    Author author;
                    int year, weight;
                    System.out.print("Name: ");
                    name = reader.nextLine();
                    System.out.print("Year: ");
                    year = reader.nextInt();
                    System.out.print("Weight: ");
                    weight = reader.nextInt();
                    System.out.print("Material: ");
                    material = reader.nextLine();
                    artisticMovement = readArtisticMovement();
                    author = readAuthor();
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
            System.out.print("Nationality: ");
            nat = reader.nextLine();
            System.out.print("Age: ");
            age = reader.nextInt();
            reader.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return null;
        }

        return new Author(name, nat, age);
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
            System.out.print("Country: ");
            country = reader.nextLine();
            System.out.print("City: ");
            city = reader.nextLine();
            System.out.print("Street: ");
            street = reader.nextLine();
            System.out.print("Number: ");
            number = reader.nextInt();
            reader.nextLine();
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
            System.out.print("Name: ");
            name = reader.nextLine();
            System.out.print("Country: ");
            country = reader.nextLine();
            System.out.print("What is the first artist? ");
            firstArtist = reader.nextLine();
            System.out.print("What year did it appear: ");
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
            System.out.print("Email: ");
            contact = reader.nextLine();
            address = readAddress();
            reader.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
            return null;
        }

        return new Museum(name, contact, address);
    }

    public Exposition readExposition(){
        LocalDate date;
        int price, noProjects;
        String description, name;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.print("Name of the exposition: ");
            name = reader.nextLine();
            System.out.print("Date: ");
            date = LocalDate.parse(reader.nextLine());
            System.out.print("Description: ");
            description = reader.nextLine();
            System.out.print("Price: ");
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

    public Object sellPainting() {

    }
}