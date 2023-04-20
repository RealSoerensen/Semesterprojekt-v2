package test;

import dal.DBConnection;
import dal.DBUtils;
import dal.person.PersonContainer;
import dal.person.PersonDataAccessIF;
import model.Address;
import model.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class PersonTest {
    private final PersonDataAccessIF personDB;

    public PersonTest() {
        personDB = PersonContainer.getInstance();
    }

    @Test
    public void testCreatePerson() throws SQLException {
        //Arrange
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone",  1, "username", "password", 1303014586);

        //Act
        boolean result = personDB.create(person);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetPerson() throws SQLException {
        //Arrange
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 1, "username", "password", 1303014586);

        //Act
        personDB.create(person);
        Person result = personDB.get(1303014586);

        //Assert
        assertEquals(person.getSsn(), result.getSsn());
    }

    @AfterEach
    public void tearDown() throws SQLException {
        new DBUtils().resetDB();
    }

    @AfterAll
    public static void tearDownAll() throws SQLException {
        DBConnection.getInstance().closeConnection();
    }
}
