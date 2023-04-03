package test;

import dal.DBConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBTest {

    @Test
    public void testDBConnection() throws SQLException {
        //Arrange
        DBConnection db = DBConnection.getInstance();

        //Act
        Connection con = db.getConnection();
        boolean open = db.getOpenStatus();

        //Assert
        assertNotNull(con);
        assertTrue(open);
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        DBConnection db = DBConnection.getInstance();
        db.closeConnection();
    }
}
