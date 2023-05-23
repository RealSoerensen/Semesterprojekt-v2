package dal.session;

import dal.CRUD;
import model.Address;
import model.Course;
import model.Person;
import model.Session;

import java.sql.SQLException;
import java.util.List;

public interface SessionDataAccessIF extends CRUD<Session> {
    void deleteAll();
    List<Session> getEnrolledSessions(Person person, Course course) throws SQLException;

    long createAddressAndGetID(Address address);
}
