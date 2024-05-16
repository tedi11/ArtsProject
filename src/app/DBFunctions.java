package app;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBFunctions {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "pao";
    private static final String PASSWORD = "123456";
    private static Connection databaseConnection = null;
    private static DBFunctions instance;
    private static Audit audit = Audit.getInstance();

    private DBFunctions() { }

    static {
        try {
            instance = new DBFunctions();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating DBFunctions singleton instance");
        }
    }

    public static DBFunctions getInstance() {
        if (instance == null) {
            instance = new DBFunctions();
        }
        return instance;
    }

    public Connection connect_to_db(){
        try{
            System.out.println("Asta e1");
            Class.forName("org.postgresql.Driver");
            System.out.println("Asta e2");
            if (databaseConnection == null || databaseConnection.isClosed()) {
                System.out.println("Asta e3");
                databaseConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Asta e4");
                if (databaseConnection != null) {
                    audit.logAction("Open connection with database");
                } else {
                    System.out.println("Connection Failed");
                    audit.logAction("Connection failed with database!");
                }
            }

        }catch (Exception e){
            System.out.println(e);
            System.out.println("Asta e");
        }
        return databaseConnection;
    }

    public static void closeDatabaseConnection()
    {
        try
        {
            if (databaseConnection != null && !databaseConnection.isClosed())
            {
                databaseConnection.close();
                audit.logAction("Close connection with database");
            }
        }
        catch (IOException | SQLException e)
        {
            e.printStackTrace();
        }
    }

}
