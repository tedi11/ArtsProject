package App.Service;

import App.Reader;
import Models.Art.ArtProject;
import Models.Art.Painting;
import Models.Art.Sculpture;
import Models.Creators.Author;
import Models.Expositions.Address;
import Models.Expositions.Exposition;
import Models.Expositions.Museum;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class ServiceExpositions {
    List<Address> addresses = new ArrayList<>();
    List<Exposition> expositions = new ArrayList<>();
    List<Museum> museums = new ArrayList<>();

    private static ServiceExpositions instance;

    private ServiceExpositions() {
    }

    static {
        try {
            instance = new ServiceExpositions();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Service singleton instance");
        }
    }

    public static ServiceExpositions getInstance() {
        if (instance == null) {
            instance = new ServiceExpositions();
        }
        return instance;
    }

    public void addAddress(Address newAddress) {
        if (newAddress != null)
            addresses.add(newAddress);
    }

    public void sellPainting(int nrMuseum, int nrArt){
        Reader objReader = Reader.getInstance();
        try {
            int i = 0;
            ArtProject artProject2 = null;
            for (ArtProject artProject : museums.get(nrMuseum).getArtProjects())
                if (i == nrArt)
                    artProject2 = artProject;

            museums.get(nrMuseum).getArtProjects().remove(artProject2);
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
        }
    }

    public void addArtToMuseum(int nrMuseum, int nrArt){
        Reader objReader = Reader.getInstance();
        try {
            int i = 0;
            ArtProject artProject2 = null;
            for (ArtProject artProject : museums.get(nrMuseum).getArtProjects())
                if (i == nrArt)
                    artProject2 = artProject;

            museums.get(nrMuseum).getArtProjects().add(artProject2);
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
        }
    }

    public void addExposition(Exposition newExposition) {
        if (newExposition != null)
            expositions.add(newExposition);
    }
    public void addMuseum(Museum newMuseum) {
        if (newMuseum != null)
            museums.add(newMuseum);
    }

//    public void listAuthors(){
//        authors.printAuthors();
//    }



    public void showMuseums() {
        Reader objReader = Reader.getInstance();
        for (int i = 0; i < museums.size(); i++) {
            Museum resultSet = museums.get(i);
            System.out.println("---------------");
            try {
                System.out.println("Name: " + resultSet.getName());
                System.out.println("Email: " + resultSet.getContactEmail());
                showAddress(resultSet.getAddress());
                showArtOfMuseum();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    public void showAddress(Address address) {
        Reader objReader = Reader.getInstance();
            try {
                System.out.println("Country: " + address.getCountry());
                System.out.println("City: " + address.getCity());
                System.out.println("Street: " + address.getStreet());
                System.out.println("Number: " + address.getNumber());

            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
        }
    }



    public void showArtOfMuseum(Museum museum) {
        Reader objReader = Reader.getInstance();

        try {
            for (ArtProject artProject : museum.getArtProjects()){
                showArt(artProject);
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
    }

    public void showExpositions(){
        Reader objReader = Reader.getInstance();
        for (int i = 0; i < expositions.size(); i++)
            try {
                Exposition resultSet = expositions.get(i);
                System.out.println("---------------");
                System.out.println("Name: " + resultSet.getName());
                System.out.println("Date: " + resultSet.getDate());
                System.out.println("Price: " + resultSet.getPrice());
                System.out.println("Description: " + resultSet.getDescription());
                System.out.println("Art projects:");
                for (ArtProject artProject : expositions.get(i).getArtProjects())
                {
                    System.out.println("###########");
                    showArt(artProject);
                }

            }catch (InputMismatchException e){
                System.out.println("Invalid input!");
            }
    }

}