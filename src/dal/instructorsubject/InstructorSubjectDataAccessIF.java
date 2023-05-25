package dal.instructorsubject;

import model.Person;
import model.Subject;

public interface InstructorSubjectDataAccessIF {
    void create(Person person, Subject subject);
    boolean isPersonIn(Person person, Subject subject);
    boolean remove(Person person, Subject subject);
}
