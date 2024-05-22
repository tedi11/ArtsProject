package repositories;

import app.Audit;
import app.DBFunctions;
import app.DataDictionary;
import models.creators.Author;

import java.io.IOException;
import java.sql.*;

public class AuthorRepository {

    private static AuthorRepository instance;
    private static Audit audit = Audit.getInstance();

    private AuthorRepository() { }

    static {
        try {
            instance = new AuthorRepository();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating DBFunctions singleton instance");
        }
    }

    public static AuthorRepository getInstance() {
        if (instance == null) {
            instance = new AuthorRepository();
        }
        return instance;
    }

    public void createTable()
    {

        String query1 = "CREATE TABLE IF NOT EXISTS AUTHOR " +
                "(id_author SERIAL PRIMARY KEY, " +
                "authorName varchar(100), " +
                "nationality varchar(25), " +
                "age int);";

        Statement statement;
        try{
            DBFunctions db = DBFunctions.getInstance();
            Connection conn = db.connect_to_db();
            statement=conn.createStatement();
            statement.executeUpdate(query1);
            System.out.println("Table Created");
            audit.logAction("Author table created");
            DBFunctions.closeDatabaseConnection();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void addAuthor(Author author)
    {
        try
        {
            String insertAuthorSql = "INSERT INTO author(authorName, nationality, age) VALUES(?, ?, ?);";
            DBFunctions db = DBFunctions.getInstance();
            Connection conn = db.connect_to_db();
            PreparedStatement authorStatement = conn.prepareStatement(insertAuthorSql, Statement.RETURN_GENERATED_KEYS);
            authorStatement.setString(1, author.getName());
            authorStatement.setString(2, author.getNationality());
            authorStatement.setInt(3, author.getAge());


            int rows = authorStatement.executeUpdate();
            if (rows == 0) {

                throw new SQLException("Inserting author failed, no rows affected.");
            }
            else{
                ResultSet generatedKeys = authorStatement.getGeneratedKeys();
                System.out.println("Author inserted successfully.");
                audit.logAction("Added new Author");
                int authorId;
                if (generatedKeys.next()) {
                    authorId = generatedKeys.getInt(1);
                    DataDictionary dataDictionary = DataDictionary.getInstance();
                    dataDictionary.addAuthors(authorId);
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

    public void printAuthors()
    {
        String selectSql = "SELECT * FROM AUTHOR;";
        DBFunctions db = DBFunctions.getInstance();
        Connection conn = db.connect_to_db();

        try (Statement stmt = conn.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                if(empty){
                    System.out.println("List of all authors:");
                    System.out.println("----------" );
                }
                empty = false;
                System.out.println("AuthorID: " + resultSet.getString(1) );
                System.out.print("Author Name: " + resultSet.getString(2) + " " + '\n');
                System.out.println("Nationality: " + resultSet.getString(3) );
                System.out.print("Age: " + resultSet.getString(4) + " " + '\n');
                ///System.out.println(resultSet.getString(7) + " " + resultSet.getString(8) + " " + resultSet.getString(9));
                System.out.println("----------");
            }
            audit.logAction("Printed all authors");
            DBFunctions.closeDatabaseConnection();
            if (empty)
            {
                System.out.println("No existing authors!");
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
