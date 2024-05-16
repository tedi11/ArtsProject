package app;

import models.creators.Author;
import repositories.AuthorRepository;

import java.io.IOException;

public final class ServiceCRUD {
    private AuthorRepository authorRepository = AuthorRepository.getInstance();
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
        try
        {
            audit.logAction("configure tables");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void addAuthors(Author newAuthor){
        if(newAuthor != null)
            authorRepository.addAuthor(newAuthor);
    }
}


