package app.service;

import app.Reader;
import models.art.ArtProject;
import models.creators.Author;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class ServiceCreators {
    static List<Author> authors = new ArrayList<>();

    private static ServiceCreators instance;
    private ServiceCreators() { }

    static {
        try {
            instance = new ServiceCreators();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Service singleton instance");
        }
    }

    public static ServiceCreators getInstance() {
        if (instance == null) {
            instance = new ServiceCreators();
        }
        return instance;
    }

    public static void addAuthor(Author newAuthor) {
        if (newAuthor != null)
        {
            authors.add(newAuthor);
        }
    }

    public void showAuthor(int no){
        Reader objReader = Reader.getInstance();
        Author resultSet = authors.get(no);
        try {
            System.out.println("Name of the artistic author: " + resultSet.getName());
            System.out.println("Nationality of the artistic author: " + resultSet.getNationality());
            System.out.println("Age of the artistic author: " + resultSet.getAge());
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
        }
    }

    public void showAuthors(){
        for (int i = 0; i < authors.size(); i++)
            try {
                Author resultSet = authors.get(i);
                System.out.println("---------------");
                System.out.println("Name of the artistic author: " + resultSet.getName());
                System.out.println("Nationality of the artistic author: " + resultSet.getNationality());
                System.out.println("Age of the artistic author: " + resultSet.getAge());
            }catch (InputMismatchException e){
                System.out.println("Invalid input!");
            }
    }

    public static void showAuthorFromArt(ArtProject artProject){
        Author author = artProject.getAuthor();
        try {
            System.out.println("Name of the artistic author: " + author.getName());
            System.out.println("Nationality of the artistic author: " + author.getNationality());
            System.out.println("Age of the artistic author: " + author.getAge());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
    }

}
