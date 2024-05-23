package app.service;

import app.Reader;
import models.art.ArtProject;
import models.art.Sculpture;
import models.expositions.Address;
import models.expositions.Exposition;
import models.expositions.Museum;

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
        if (newAddress != null && !addresses.contains(newAddress))
            addresses.add(newAddress);
    }
public void calculateAverageYear(){
        for (Exposition exposition : expositions)
        {
            int total = 0;
            for(ArtProject artProject : exposition.getArtProjects())
                total += artProject.getYearApperence();
            System.out.println("Average year of appearance for exposition " + exposition.getName() + " is " + ((int) total/exposition.getArtProjects().size()) );
        }
        return;
}
    public void sellPainting(int nrMuseum, int nrArt){
        Reader objReader = Reader.getInstance();
        try {
            int i = 0;
            ArtProject artProject2 = null;
            for (ArtProject artProject : museums.get(nrMuseum).getArtProjects()) {
                if (i == nrArt)
                    artProject2 = artProject;
                i++;
            }
            museums.get(nrMuseum).getArtProjects().remove(artProject2);
        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
        }
    }

    public void addArtToMuseum(int nrMuseum, int nrArt){
        Reader objReader = Reader.getInstance();
        try {
            int i = 0;
            for (ArtProject artProject : ServiceArt.getArtProjects()){
                if (i == nrArt)
                {museums.get(nrMuseum).addProject(artProject);

            }i++;}
                    //museums.get(nrMuseum).getArtProjects().add(artProject);


        }catch (InputMismatchException e){
            System.out.println("Invalid input!");
        }
    }

    public void showHeaviest(int nrMuseum){
        Reader objReader = Reader.getInstance();
        try {
            int g = -1;
            ArtProject artProject1 = new ArtProject();

            for (ArtProject artProject : museums.get(nrMuseum).getArtProjects())
                if (artProject instanceof Sculpture)
                    if (((Sculpture) artProject).getWeight() > g) {
                        g = ((Sculpture) artProject).getWeight();
                        artProject1 = artProject;
                    }
            if (g == -1)
                System.out.println("There are no sculptures in this museum!");
            else if (artProject1.getAuthor() != null) {
                System.out.println("The heaviest sculpture is: ");
                ServiceArt.showArt(artProject1);
            }



        }
            //museums.get(nrMuseum).getArtProjects().add(artProject);


        catch (InputMismatchException e){
            System.out.println("Invalid input!");
        }
    }

    public void addArtToExposition(int nrMuseum, int nrArt){
        Reader objReader = Reader.getInstance();
        try {
            int i = 0;
            for (ArtProject artProject : ServiceArt.getArtProjects())
                if (i == nrArt)
                {expositions.get(nrMuseum).getArtProjects().add(artProject);
                i++;
            }

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
                System.out.println("Name of the museum: " + resultSet.getName());
                System.out.println("Email of the museum: " + resultSet.getContactEmail());
                showAddress(resultSet.getAddress());
                showArtOfMuseum(resultSet);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    public void showAddress(Address address) {
        Reader objReader = Reader.getInstance();
            try {
                System.out.println("Details about the address:");
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
                ServiceArt.showArt(artProject);
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
                System.out.println("Name of the artistic exposition: " + resultSet.getName());
                System.out.println("Date of the artistic exposition: " + resultSet.getDate());
                System.out.println("Price of the artistic exposition: " + resultSet.getPrice());
                System.out.println("Description of the artistic exposition: " + resultSet.getDescription());
                System.out.println("Art projects of the artistic exposition:");
                for (ArtProject artProject : expositions.get(i).getArtProjects())
                {
                    System.out.println("###########");
                    ServiceArt.showArt(artProject);
                }

            }catch (InputMismatchException e){
                System.out.println("Invalid input!");
            }
    }

}