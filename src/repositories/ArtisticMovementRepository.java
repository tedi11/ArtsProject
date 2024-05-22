package repositories;

import app.Audit;
import app.DBFunctions;
import app.DataDictionary;
import models.art.ArtisticMovement;
import models.creators.Author;

import java.io.IOException;
import java.sql.*;

public class ArtisticMovementRepository {

    private static ArtisticMovementRepository instance;
    private static Audit audit = Audit.getInstance();

    private ArtisticMovementRepository() { }

    static {
        try {
            instance = new ArtisticMovementRepository();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating DBFunctions singleton instance");
        }
    }

    public static ArtisticMovementRepository getInstance() {
        if (instance == null) {
            instance = new ArtisticMovementRepository();
        }
        return instance;
    }

    public void createTable()
    {


        String query1 = "CREATE TABLE IF NOT EXISTS ARTISTICMOVEMENT " +
                "(id_artistic_movement SERIAL PRIMARY KEY, " +
                "name varchar(100), " +
                "yearApperence int, " +
                "first_artist varchar(100), " +
                "country varchar(100));";

        Statement statement;
        try{
            DBFunctions db = DBFunctions.getInstance();
            Connection conn = db.connect_to_db();
            statement=conn.createStatement();
            statement.executeUpdate(query1);
            System.out.println("Table Created");
            audit.logAction("ArtisticMovement table created");
            DBFunctions.closeDatabaseConnection();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void addArtisticMovement(ArtisticMovement artisticMovement)
    {
        try
        {
            String insertArtisticMovementSql = "INSERT INTO ARTISTICMOVEMENT(name, yearApperence, first_artist, country) VALUES(?, ?, ?, ?);";
            DBFunctions db = DBFunctions.getInstance();
            Connection conn = db.connect_to_db();
            PreparedStatement artisticMovementStatement = conn.prepareStatement(insertArtisticMovementSql, Statement.RETURN_GENERATED_KEYS);
            artisticMovementStatement.setString(1, artisticMovement.getName());
            artisticMovementStatement.setInt(2, artisticMovement.getYearApperence());
            artisticMovementStatement.setString(3, artisticMovement.getFirstArtist());
            artisticMovementStatement.setString(4, artisticMovement.getCountry());


            int rows = artisticMovementStatement.executeUpdate();
            if (rows == 0) {

                throw new SQLException("Inserting artistic movement failed, no rows affected.");
            }
            else{
                ResultSet generatedKeys = artisticMovementStatement.getGeneratedKeys();
                System.out.println("Artistic Movement inserted successfully.");
                audit.logAction("Added new Artistic Movement");
                int artisticMovementId;
                if (generatedKeys.next()) {
                    artisticMovementId = generatedKeys.getInt(1);
                    DataDictionary dataDictionary = DataDictionary.getInstance();
                    dataDictionary.addArtisticMovement(artisticMovementId);
                }
            }

        }
        catch (SQLException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println("Error with audit: " + e);
        }
    }

    public void printArtisticMovements()
    {
        String selectSql = "SELECT * FROM ARTISTICMOVEMENT;";
        DBFunctions db = DBFunctions.getInstance();
        Connection conn = db.connect_to_db();

        try (Statement stmt = conn.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                if(empty){
                    System.out.println("List of all artistic movements:");
                    System.out.println("----------" );
                }
                empty = false;
                System.out.println("Artistic movement id: " + resultSet.getString(1) );
                System.out.print("Name: " + resultSet.getString(2) + " " + '\n');
                System.out.println("Year of apperence: " + resultSet.getString(3) );
                System.out.print("First artist: " + resultSet.getString(4) + " " + '\n');
                System.out.print("Contry of apperence: " + resultSet.getString(5) + " " + '\n');
                System.out.println("----------");
            }
            audit.logAction("Printed all artistic movements");
            DBFunctions.closeDatabaseConnection();
            if (empty)
            {
                System.out.println("No existing artistic movements!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("Error with audit: " + e);
        }
    }

}
