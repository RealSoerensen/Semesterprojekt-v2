package dal.sessionmember;

import model.Session;
import model.Person;

import java.util.List;

public interface SessionMemberDataAccessIF {
    boolean create(Session session, Person member);
    Person getMemberFromSession(long ssn, Session session);
    List<Person> getMembersInSession(Session session);
    boolean removeMemberFromSession(Session session, Person member);
}
