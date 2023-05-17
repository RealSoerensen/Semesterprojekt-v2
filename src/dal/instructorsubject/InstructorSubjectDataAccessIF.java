package dal.instructorsubject;

import dal.CRUD;
import model.Person;
import model.Subject;

public interface InstructorSubjectDataAccessIF {
    boolean create(Person person, Subject subject);
    boolean isPersonIn(Person person, Subject subject);
    boolean remove(Person person, Subject subject);
}
