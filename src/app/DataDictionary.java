package app;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.sql.*;

public class DataDictionary {
    private Hashtable<Integer, Boolean> authors;
    private Hashtable<Integer, Boolean> artisticMovements;
    private Hashtable<Integer, Boolean> paintings;
    private Hashtable<Integer, Boolean> sculptures;
    private static DataDictionary instance;
    private DataDictionary() {
        authors = new Hashtable<>();

        String selectCLSql = "SELECT * FROM AUTHOR;";
        DBFunctions db = DBFunctions.getInstance();
        Connection conn = db.connect_to_db();

        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectCLSql);
            while (resultSet.next()) {
                Integer key=resultSet.getInt(1);
                addAuthors(key);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        artisticMovements = new Hashtable<>();

        String selectCLSql2 = "SELECT * FROM ARTISTICMOVEMENTS;";

        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectCLSql);
            while (resultSet.next()) {
                Integer key=resultSet.getInt(1);
                addArtisticMovement(key);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        paintings = new Hashtable<>();

        String selectCLSql3 = "SELECT * FROM PAINTING;";

        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectCLSql);
            while (resultSet.next()) {
                Integer key=resultSet.getInt(1);
                addPainting(key);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        sculptures = new Hashtable<>();

        String selectCLSql4 = "SELECT * FROM SCULPTURE;";

        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectCLSql);
            while (resultSet.next()) {
                Integer key=resultSet.getInt(1);
                addSculpture(key);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            instance = new DataDictionary();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating DataDictionary singleton instance");
        }
    }

    public static DataDictionary getInstance() {
        if (instance == null) {
            instance = new DataDictionary();
        }
        return instance;
    }

    public void addAuthors(Integer id) {
        this.authors.put(id, true);
    }
    public void addArtisticMovement(Integer id) {
        this.artisticMovements.put(id, true);
    }

    public void addPainting(Integer id) {
        this.paintings.put(id, true);
    }
    public void addSculpture(Integer id) {
        this.sculptures.put(id, true);
    }


}
