package app;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.sql.*;

public class DataDictionary {
    private Hashtable<Integer, Boolean> authors;
    private static DataDictionary instance;
    private DataDictionary() {
        authors = new Hashtable<>();

        String selectCLSql = "SELECT * FROM CLIENT;";
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

}
