package test;

import controller.CourseController;
import controller.PersonController;
import dal.DBUtils;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {
    private final CourseController courseController;
    private final PersonController personController;

    public SessionTest() throws SQLException {
        courseController = new CourseController();
        personController = new PersonController();
    }

    @BeforeEach
    public void init() throws SQLException {
        new DBUtils().resetDB();
    }

    @Test
    public void testCreateSession() throws Exception {
        //Arrange
        Person person = new Person("John", "Doe", new Address("1234", "Aalborg", "Testvej", "1"), "email", "phone", 1, "password", 1303014586);
        Course course = new Course("TestCourse", 20.0, "TestDescription",  LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2023, Month.MAY, 5));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Subject subject = new Subject("TestSubject", "TestDescription");

        //Act
        personController.createPerson(person);
        address = personController.createAddress(address);
        course = courseController.createCourse(course);
        subject = courseController.createSubject(subject);
        Session session = new Session(LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));
        session = courseController.createSession(session);

        //Assert
        assertEquals(1, session.getSessionID());
    }

    @Test
    public void testGetSession() throws Exception {
        //Arrange
        Person person = new Person("John", "Doe", new Address("1234", "Aalborg", "Testvej", "1"), "email", "phone", 1, "password", 1303014586);
        Course course = new Course("TestCourse", 20.0, "TestDescription",  LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2023, Month.MAY, 5));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Subject subject = new Subject("TestSubject", "TestDescription");
        personController.createPerson(person);
        address = personController.createAddress(address);
        course = courseController.createCourse(course);
        subject = courseController.createSubject(subject);

        //Act
        Session session = new Session(LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));
        session = courseController.createSession(session);
        Session result = courseController.getSession(course, session.getSessionID());

        //Assert
        assertEquals(session.getSessionID(), result.getSessionID());
    }

    @Test
    public void testUpdateSession() throws Exception {
        //Arrange
        Person person = new Person("John", "Doe", new Address("1234", "Aalborg", "Testvej", "1"), "email", "phone", 1, "password", 1303014586);
        Course course = new Course("TestCourse", 20.0, "TestDescription",  LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2023, Month.MAY, 5));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Subject subject = new Subject("TestSubject", "TestDescription");
        personController.createPerson(person);
        address = personController.createAddress(address);
        course = courseController.createCourse(course);
        subject = courseController.createSubject(subject);

        //Act
        courseController.createCourse(course);
        Session session = new Session(LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));
        session = courseController.createSession(session);
        Session updatedSession = new Session(session.getSessionID(), LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));
        boolean result = courseController.updateSession(updatedSession);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testDeleteSession() throws Exception {
        //Arrange
        Person person = new Person("John", "Doe", new Address("1234", "Aalborg", "Testvej", "1"), "email", "phone", 1, "password", 1303014586);
        Course course = new Course("TestCourse", 20.0, "TestDescription",  LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2023, Month.MAY, 5));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Subject subject = new Subject("TestSubject", "TestDescription");
        personController.createPerson(person);
        address = personController.createAddress(address);
        course = courseController.createCourse(course);
        subject = courseController.createSubject(subject);

        //Act
        Session session = new Session(1, LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));
        session = courseController.createSession(session);
        boolean result = courseController.removeSession(session);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetAllSessions() throws Exception {
        //Arrange
        Person person = new Person("John", "Doe", new Address("1234", "Aalborg", "Testvej", "1"), "email", "phone", 1, "password", 1303014586);
        Course course = new Course("TestCourse", 20.0, "TestDescription",  LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2023, Month.MAY, 5));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Subject subject = new Subject("TestSubject", "TestDescription");
        personController.createPerson(person);
        address = personController.createAddress(address);
        course = courseController.createCourse(course);
        subject = courseController.createSubject(subject);

        //Act
        Session session1 = new Session(1, LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));
        Session session2 = new Session(2, LocalDate.of(2023, Month.MAY, 6), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));
        Session session3 = new Session(3, LocalDate.of(2023, Month.MAY, 7), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));

        courseController.createSession(session1);
        courseController.createSession(session2);
        courseController.createSession(session3);
        int result = courseController.getAllSessions().size();

        //Assert
        assertEquals(3, result);
    }

    @Test
    public void testGetAllSessionFromCourse() throws Exception {
        // Arrange
        Person person = new Person("John", "Doe", new Address("1234", "Aalborg", "Testvej", "1"), "email", "phone", 1, "password", 1303014586);
        Course course = new Course("TestCourse", 20.0, "TestDescription",  LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2023, Month.MAY, 5));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Subject subject = new Subject("TestSubject", "TestDescription");
        personController.createPerson(person);
        address = personController.createAddress(address);
        course = courseController.createCourse(course);
        subject = courseController.createSubject(subject);

        // Act
        Session session1 = new Session(1, LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));
        Session session2 = new Session(2, LocalDate.of(2023, Month.MAY, 6), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));
        Session session3 = new Session(3, LocalDate.of(2023, Month.MAY, 7), person, course, address, subject, LocalTime.of(14, 30), LocalTime.of(16, 30));

        courseController.createSession(session1);
        courseController.createSession(session2);
        courseController.createSession(session3);
        int result = courseController.getAllSessionsFromCourse(course).size();

        // Assert
        assertEquals(3, result);
    }

}
