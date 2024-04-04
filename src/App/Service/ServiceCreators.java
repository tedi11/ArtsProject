package App.Service;

import Models.Creators.Author;

import java.util.ArrayList;
import java.util.List;

public class ServiceCreators {
    List<Author> authors = new ArrayList<>();

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
}
