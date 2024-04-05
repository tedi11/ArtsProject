package App.Service;

import App.Reader;
import Models.Art.ArtProject;
import Models.Art.ArtisticMovement;
import Models.Art.Painting;
import Models.Art.Sculpture;
import Models.Creators.Author;
import Models.Expositions.Address;
import Models.Expositions.Exposition;
import Models.Expositions.Museum;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class ServiceArt {

    static List<ArtisticMovement> artisticMovements = new ArrayList<>();
    static List<ArtProject> artProjects = new ArrayList<>();

    private static ServiceArt instance;

    private ServiceArt() {
    }

    static {
        try {
            instance = new ServiceArt();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Service singleton instance");
        }
    }

    public static ServiceArt getInstance() {
        if (instance == null) {
            instance = new ServiceArt();
        }
        return instance;
    }

    public static List<ArtProject> getArtProjects() {
        return artProjects;
    }

    public static void showArt(ArtProject artProject) {
        Reader objReader = Reader.getInstance();
        System.out.println("Am ajuns in showArt");
        try {
            System.out.println("Name of the project: " + artProject.getName());
            ServiceCreators.showAuthorFromArt(artProject);
            ServiceArt.showArtisticMovement(artProject.getArtisticMovement());
            System.out.println("Year of appearance of the project: " + artProject.getYearApperence());
            if (artProject instanceof Painting)
            {
                System.out.println("Height of the project: " + ((Painting)artProject).getHeight());
                System.out.println("Length of the project: " + ((Painting)artProject).getLength());
            } else if (artProject instanceof Sculpture) {
                System.out.println("Material of the project: " + ((Sculpture)artProject).getMaterial());
                System.out.println("Weight of the project: " + ((Sculpture)artProject).getWeight());
            }
            else
                System.out.println("Error! in showArt ");




        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
    }

    public void addPainting(ArtProject newArtProject){
        if(newArtProject != null)
            artProjects.add(newArtProject);
    }

    public void addSculpture(ArtProject newArtProject){
        if(newArtProject != null)
            artProjects.add(newArtProject);
    }

    public static void addArtisticMovement(ArtisticMovement artisticMovement){
        if(artisticMovement != null && !artisticMovements.contains(artisticMovement))
            artisticMovements.add(artisticMovement);
    }



    public void showArts() {
        Reader objReader = Reader.getInstance();
        try {
            for (ArtProject artProject : artProjects)
            {
                System.out.println("------------------");
                showArt(artProject);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
    }

    public void showArtsByAuthor(String name) {
        Reader objReader = Reader.getInstance();
        try {
            for (ArtProject artProject : artProjects)
                if (artProject.getAuthor().getName().equals(name))
                {
                    System.out.println("------------------");
                    showArt(artProject);
                }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
    }


    public static void showArtisticMovement(ArtisticMovement artisticMovement) {
        Reader objReader = Reader.getInstance();
        try {
            System.out.println("Name of the artistic movement: " + artisticMovement.getName());
            System.out.println("Year of appearance of the artistic movement: " + artisticMovement.getYearApperence());
            System.out.println("First artist of the artistic movement: " + artisticMovement.getFirstArtist());
            System.out.println("Country of the artistic movement: " + artisticMovement.getCountry());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
    }

    public void showArtisticMovements() {
        Reader objReader = Reader.getInstance();
        try {
            for (ArtisticMovement artisticMovement : artisticMovements) {
                System.out.println("------------------");
                showArtisticMovement(artisticMovement);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
    }


}
