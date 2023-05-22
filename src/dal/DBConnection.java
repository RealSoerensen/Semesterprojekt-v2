package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DBConnection class is a singleton class that creates a connection to the database.
 * It is a singleton class because it ensures that only one connection to the database exists at any given time,
 * which prevents multiple connections from being opened simultaneously and causing errors in our program.
 */
public class DBConnection {
    private static Connection con;
    private static DBConnection instance;
    private static final String DBNAME = "DMA-CSD-S223_10461275";
    private static final String SERVERNAME = "hildur.ucn.dk";
    private static final String USERNAME = "DMA-CSD-S223_10461275";
    private static final String PASSWORD = "Password1!";

    /**
     * The DBConnection function is a constructor that creates a connection to the database.
     */
    private DBConnection() throws SQLException {
        String urlString = String.format("jdbc:sqlserver://%s;databaseName=%s;encrypt=false", SERVERNAME,
                DBNAME);
        con = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
    }

    /**
     * The getInstance function is a static function that returns an instance of the DBConnection class.
     * If there is no existing instance, it creates one and returns it. Otherwise, it just returns the
     * existing instance. This ensures that only one connection to the database exists at any given time,
     * which prevents multiple connections from being opened simultaneously and causing errors in our program.
     *
     * @return The instance of the dbConnection class.
     */
    public static DBConnection getInstance() throws SQLException {
        if(instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

    /**
     * The getOpenStatus function checks the status of the connection to the database.
     *
     * @return A boolean value indicating whether the connection is open or not.
     */
    public boolean getOpenStatus() {
        boolean isOpen = false;
        try {
            isOpen = (!con.isClosed());
        } catch (Exception sclExc) {
            System.out.println("Error checking connection status");
        }
        return isOpen;
    }

    /**
     * The closeConnection function closes the connection to the database.
     */
    public void closeConnection() {
        try {
            con.close();
            instance = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void startTransaction() throws SQLException {
    	con.setAutoCommit(false);
    }
    
    public void commitTransaction() throws SQLException {
    	con.commit();
    	con.setAutoCommit(true);
    }
    
    public void rollbackTransaction() throws SQLException {
    	con.rollback();
    	con.setAutoCommit(true);
    }
}
