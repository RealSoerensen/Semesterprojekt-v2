package test;

import controller.CourseController;
import controller.PersonController;
import dal.DBConnection;
import dal.DBUtils;
import model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.function.BooleanSupplier;

public class CourseTest {
    private static CourseController courseController = null;
    private final PersonController personController;

    public CourseTest() throws SQLException {
        courseController = new CourseController();
        personController = new PersonController();
    }

    @BeforeEach
    public void setUp() throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        if(conn.isClosed()) {
            fail("Connection closed");
        }
        new DBUtils().resetDB();
    }

    @Test
    public void testCreateCourse() throws Exception {
        //Arrange
        Course course = new Course("Test Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));

        //Act
        Course result = courseController.createCourse(course);

        //Assert
        assertNotNull(result);
    }

    @Test
    public void testGetCourse() throws Exception {
        //Arrange
        Course course = new Course("Test Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));

        //Act
        course = courseController.createCourse(course);
        Course result = courseController.getCourse(1);

        //Assert
        assertEquals(course.getCourseID(), result.getCourseID());
    }

    @Test
    public void testUpdateCourse() throws Exception {
        //Arrange
        Course course = new Course("Test Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
        Course updatedCourse = new Course("Updated Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));

        //Act
        course = courseController.createCourse(course);
        updatedCourse.setCourseID(course.getCourseID());
        boolean result = courseController.updateCourse(updatedCourse);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testDeleteCourse() throws Exception {
        //Arrange
        Course course = new Course("Test Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));

        //Act
        course = courseController.createCourse(course);
        boolean result = courseController.removeCourse(course);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetAllCourses() throws Exception {
        //Arrange
        Course course1 = new Course("Test Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
        Course course2 = new Course("Test Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
        Course course3 = new Course("Test Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));

        //Act
        course1 = courseController.createCourse(course1);
        course2 = courseController.createCourse(course2);
        course3 = courseController.createCourse(course3);
        int result = courseController.getAllCourses().size();

        //Assert
        assertEquals(3, result);
    }

    @Test
    public void testEnrollMember() throws Exception {
        //Arrange
        Course course = new Course("Test Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 1,  "password", 1303014586);

        //Act
        courseController.createCourse(course);
        personController.createPerson(person);
        boolean result = courseController.createCourseMember(course, person);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testUnenrollMember() throws Exception {
        //Arrange
        Course course = new Course("Test Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 1, "password", 1303014586);

        //Act
        courseController.createCourse(course);
        personController.createPerson(person);
        courseController.createCourseMember(course, person);
        boolean result = courseController.removeCourseMember(course, person);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetAllCourseMembers() throws Exception {
        //Arrange
        Course course = new Course("Test Name", 20.00, "Test Description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Address address1 = new Address("1234", "Aalborg", "Testvej", "1");
        Address address2 = new Address("1234", "Aalborg", "Testvej", "1");
        Person person1 = new Person("John", "Doe", address, "email", "phone", 1, "password", 1303014586);
        Person person2 = new Person("John", "Doe", address1, "email", "phone", 1, "password", 123123);
        Person person3 = new Person("John", "Doe", address2, "email", "phone", 1, "password", 412312);

        //Act
        courseController.createCourse(course);
        personController.createPerson(person1);
        personController.createPerson(person2);
        personController.createPerson(person3);
        courseController.createCourseMember(course, person1);
        courseController.createCourseMember(course, person2);
        courseController.createCourseMember(course, person3);
        int result = courseController.getAllCourseMembers(course).size();

        //Assert
        assertEquals(3, result);
    }

}
