package test;

import controller.PersonController;
import dal.DBConnection;
import dal.DBUtils;
import dal.address.AddressContainer;
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
    private final PersonController personController;

    public PersonTest() {
        personController = new PersonController(PersonContainer.getInstance(), AddressContainer.getInstance());
    }

    @Test
    public void testCreatePerson() throws SQLException {
        //Arrange
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone",  1, "username", "password", 1303014586);

        //Act
        boolean result = personController.createPerson(person);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetPerson() throws SQLException {
        //Arrange
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 1, "username", "password", 1303014586);

        //Act
        personController.createPerson(person);
        Person result = personController.getPerson(1303014586);

        //Assert
        assertEquals(person.getSsn(), result.getSsn());
    }

    @AfterEach
    public void tearDown() throws SQLException {
        personController.removeAllPersons();
    }
}
