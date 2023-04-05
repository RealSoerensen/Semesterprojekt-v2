package test;

import dal.DBUtils;
import dal.PersonDB;
import model.Address;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class PersonTest {
    PersonDB personDB;

    public PersonTest() throws SQLException {
        personDB = new PersonDB();
    }

    @Test
    public void testCreatePerson() {
        //Arrange
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 3, "username", "password", 1303014586);

        //Act
        boolean result = personDB.create(person);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetPerson() {
        //Arrange
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 3, "username", "password", 1303014586);

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
}
