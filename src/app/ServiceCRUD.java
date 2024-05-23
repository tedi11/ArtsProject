package app;

import models.art.ArtProject;
import models.art.Painting;
import models.art.Sculpture;
import models.creators.Author;
import repositories.ArtisticMovementRepository;
import repositories.AuthorRepository;
import models.art.ArtisticMovement;
import repositories.AuthorRepository;
import repositories.ArtProjectRepository;

import java.io.IOException;

public final class ServiceCRUD {
    private static AuthorRepository authorRepository = AuthorRepository.getInstance();
    private static ArtisticMovementRepository artisticMovementRepository = ArtisticMovementRepository.getInstance();
    private static ArtProjectRepository  artProjectRepository = ArtProjectRepository.getInstance();
    private Audit audit = Audit.getInstance();
    private static ServiceCRUD instance;

    static {
        try {
            instance = new ServiceCRUD();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Service singleton instance");
        }
    }

    public static ServiceCRUD getInstance() {
        if (instance == null) {
            instance = new ServiceCRUD();
        }
        return instance;
    }

    public void configureTables()
    {
        authorRepository.createTable();
        artisticMovementRepository.createTable();
        artProjectRepository.createTable();
        try
        {
            audit.logAction("configure tables");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
    public static void addAuthors(Author newAuthor){
        if(newAuthor != null)
            authorRepository.addAuthor(newAuthor);
    }

    public static void printAuthors(){
            authorRepository.printAuthors();
    }

    public static void addArtisticMovement(ArtisticMovement newArtisticMovement){
        if(newArtisticMovement != null)
            artisticMovementRepository.addArtisticMovement(newArtisticMovement);
    }

    public static void printArtisticMovements(){
        artisticMovementRepository.printArtisticMovements();
    }

    public void addArtProject(ArtProject artProject){
        if(artProject != null)
            artProjectRepository.addArtProject(artProject);
    }

    public void updateAuthor(int ID, Author author){
        if(author != null)
            authorRepository.updateAuthor(ID, author);
    }

    public static void printArts(){
        artProjectRepository.printArtProjects();
    }



}


