package repositories;

import app.Audit;
import app.DBFunctions;
import app.DataDictionary;
import models.art.ArtProject;
import models.art.ArtisticMovement;
import models.art.Painting;
import models.art.Sculpture;
import models.creators.Author;

import java.io.IOException;
import java.sql.*;

public class ArtProjectRepository {

    private static ArtProjectRepository instance;
    private static Audit audit = Audit.getInstance();
    private ArtProjectRepository() { }

    static {
        try {
            instance = new ArtProjectRepository();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating DBFunctions singleton instance");
        }
    }

    public static ArtProjectRepository getInstance() {
        if (instance == null) {
            instance = new ArtProjectRepository();
        }
        return instance;
    }

    public void createTable()
    {
        String query1 = "CREATE TABLE IF NOT EXISTS AUTHOR " +
                "(id_author SERIAL PRIMARY KEY, " +
                "authorName varchar(100), " +
                "nationality varchar(100), " +
                "age int);";

        String query2 = "CREATE TABLE IF NOT EXISTS ARTISTICMOVEMENT " +
                "(id_artistic_movement SERIAL PRIMARY KEY, " +
                "name varchar(100), " +
                "yearApperence int, " +
                "first_artist varchar(100), " +
                "country varchar(100));";

        String query3 = "CREATE TABLE IF NOT EXISTS PAINTING " +
                "(id_painting SERIAL PRIMARY KEY, " +
                "name varchar(100), " +
                "id_author int, " +
                "id_artistic_movement int, " +
                "year_aperence_painting int, " +
                "height int, " +
                "length int); " +
                "ALTER TABLE PAINTING " +
                "ADD FOREIGN KEY (id_author) REFERENCES AUTHOR(id_author) ON DELETE CASCADE;";


        String query4 = "CREATE TABLE IF NOT EXISTS SCULPTURE " +
                "(id_sculpture SERIAL PRIMARY KEY, " +
                "name varchar(100), " +
                "id_author int, " +
                "id_artistic_movement int, " +
                "year_aperence_sculpture int, " +
                "material varchar(100), " +
                "weight int); " +
                "ALTER TABLE SCULPTURE " +
                "ADD FOREIGN KEY (id_author) REFERENCES AUTHOR(id_author) ON DELETE CASCADE;";

        String query5 = "ALTER TABLE SCULPTURE " +
                "ADD FOREIGN KEY (id_artistic_movement) REFERENCES ARTISTICMOVEMENT(id_artistic_movement) ON DELETE CASCADE;";

        String query6 = "ALTER TABLE PAINTING " +
                "ADD FOREIGN KEY (id_artistic_movement) REFERENCES ARTISTICMOVEMENT(id_artistic_movement) ON DELETE CASCADE;";


        Statement statement;
        try{
            DBFunctions db = DBFunctions.getInstance();
            Connection conn = db.connect_to_db();
            statement=conn.createStatement();
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            statement.executeUpdate(query3);
            statement.executeUpdate(query4);
            statement.executeUpdate(query5);
            statement.executeUpdate(query6);
            DBFunctions.closeDatabaseConnection();
            audit.logAction("All 2 art projects tables have been crated");
            System.out.println("Table Created");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void addArtProject(ArtProject artProject) {
        Author a = artProject.getAuthor();
        String insertAuthorSql = "INSERT INTO AUTHOR(authorName, nationality, age) VALUES(?, ?, ?);";

        ArtisticMovement am = artProject.getArtisticMovement();
        String insertArtisticSql = "INSERT INTO ARTISTICMOVEMENT(name, yearApperence, first_artist, country) VALUES(?, ?, ?, ?);";

        try {
            DBFunctions db = DBFunctions.getInstance();
            Connection conn = db.connect_to_db();

            // Insert artistic movement and get generated keys
            PreparedStatement artisticStatement = conn.prepareStatement(insertArtisticSql, Statement.RETURN_GENERATED_KEYS);
            artisticStatement.setString(1, am.getName());
            artisticStatement.setInt(2, am.getYearApperence());
            artisticStatement.setString(3, am.getFirstArtist());
            artisticStatement.setString(4, am.getCountry());

            int rows = artisticStatement.executeUpdate();

            if (rows == 0) {
                throw new SQLException("Inserting artistic movement failed, no rows affected.");
            }

            ResultSet generatedKeys2 = artisticStatement.getGeneratedKeys();
            if (!generatedKeys2.next()) {
                throw new SQLException("Inserting artistic movement failed, no ID obtained.");
            }
            int artisticId = generatedKeys2.getInt(1);

            // Insert author and get generated keys
            PreparedStatement authorStatement = conn.prepareStatement(insertAuthorSql, Statement.RETURN_GENERATED_KEYS);
            authorStatement.setString(1, a.getName());
            authorStatement.setString(2, a.getNationality());
            authorStatement.setInt(3, a.getAge());

            int rows2 = authorStatement.executeUpdate();

            if (rows2 == 0) {
                throw new SQLException("Inserting author failed, no rows affected.");
            }

            ResultSet generatedKeys = authorStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("Inserting author failed, no ID obtained.");
            }
            int authorId = generatedKeys.getInt(1);

            System.out.println("Author and artistic movement inserted successfully.");
            audit.logAction("Added new Author and artistic movement");

            DataDictionary dataDictionary = DataDictionary.getInstance();
            dataDictionary.addAuthors(authorId);
            dataDictionary.addArtisticMovement(artisticId);

            try {
                if (artProject instanceof Painting) {
                    String insertPaintingSql = "INSERT INTO PAINTING(name, id_author, id_artistic_movement, year_aperence_painting, height, length) VALUES(?, ?, ?, ?, ?, ?);";

                    PreparedStatement artProjectStatement = conn.prepareStatement(insertPaintingSql, Statement.RETURN_GENERATED_KEYS);
                    artProjectStatement.setString(1, artProject.getName());
                    artProjectStatement.setInt(2, authorId);
                    artProjectStatement.setInt(3, artisticId);
                    artProjectStatement.setInt(4, artProject.getYearApperence());
                    artProjectStatement.setInt(5, ((Painting) artProject).getHeight());
                    artProjectStatement.setInt(6, ((Painting) artProject).getLength());

                    int rowsInserted = artProjectStatement.executeUpdate();

                    if (rowsInserted == 0) {
                        throw new SQLException("Inserting painting failed, no rows affected.");
                    }

                    ResultSet generatedKeys3 = artProjectStatement.getGeneratedKeys();
                    if (generatedKeys3.next()) {
                        int paintingId = generatedKeys3.getInt(1);
                        dataDictionary.addPainting(paintingId);
                        System.out.println("Painting inserted successfully.");
                        audit.logAction("Added new painting");
                    } else {
                        throw new SQLException("Inserting painting failed, no ID obtained.");
                    }

                } else if (artProject instanceof Sculpture) {
                    String insertSculptureSql = "INSERT INTO SCULPTURE(name, id_author, id_artistic_movement, year_aperence_painting, material, weight) VALUES(?, ?, ?, ?, ?, ?);";

                    PreparedStatement artProjectStatement = conn.prepareStatement(insertSculptureSql, Statement.RETURN_GENERATED_KEYS);
                    artProjectStatement.setString(1, artProject.getName());
                    artProjectStatement.setInt(2, authorId);
                    artProjectStatement.setInt(3, artisticId);
                    artProjectStatement.setInt(4, artProject.getYearApperence());
                    artProjectStatement.setString(5, ((Sculpture) artProject).getMaterial());
                    artProjectStatement.setInt(6, ((Sculpture) artProject).getWeight());

                    int rowsInserted = artProjectStatement.executeUpdate();

                    if (rowsInserted == 0) {
                        throw new SQLException("Inserting sculpture failed, no rows affected.");
                    }

                    ResultSet generatedKeys3 = artProjectStatement.getGeneratedKeys();
                    if (generatedKeys3.next()) {
                        int sculptureId = generatedKeys3.getInt(1);
                        dataDictionary.addSculpture(sculptureId);
                        System.out.println("Sculpture inserted successfully.");
                        audit.logAction("Added new sculpture");
                    } else {
                        throw new SQLException("Inserting sculpture failed, no ID obtained.");
                    }
                } else {
                    System.out.println("Error: Unknown art project type.");
                }
            } catch (SQLException | IOException e) {
                System.out.println(e);
            } finally {
                DBFunctions.closeDatabaseConnection();
            }
        } catch (SQLException | IOException e) {
            System.out.println(e);
        }
    }

}


