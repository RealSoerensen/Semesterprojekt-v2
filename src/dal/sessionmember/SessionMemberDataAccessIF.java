package dal.sessionmember;

import model.Session;
import model.Person;

import java.util.List;

public interface SessionMemberDataAccessIF {
    boolean create(Session session, Person member);
    boolean isPersonIn(Session session, Person person);
    List<Person> getAll(Session session);
    boolean remove(Session session, Person member);
    void removeAll(Session session);
}
