package test;

import controller.PersonController;
import dal.DBUtils;
import model.Address;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

public class PersonTest {
    private final PersonController personController;

    public PersonTest() throws SQLException {
        personController = new PersonController();
    }

    @BeforeEach
    public void setUp() throws SQLException {
        new DBUtils().resetDB();
    }

    @Test
    public void testCreatePerson() throws Exception {
        //Arrange
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone",  1, "password", 1303014586);

        //Act
        Person result = personController.createPerson(person);

        //Assert
        assertNotNull(result);
    }

    @Test
    public void testGetPerson() throws Exception {
        //Arrange
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 1, "password", 1303014586);

        //Act
        personController.createPerson(person);
        Person result = personController.getPerson(1303014586);

        //Assert
        assertEquals(person.getSsn(), result.getSsn());
    }

    @Test
    public void testGetAllPersons() throws Exception {
        //Arrange
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 1, "password", 1303014586);
        personController.createPerson(person);

        List<Person> persons = null;
        //Act
        try {
            persons = personController.getAllPersons();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert persons != null;
        assertEquals(1, persons.size());
    }
}
