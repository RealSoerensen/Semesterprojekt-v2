package dal.sessionmember;

import model.Person;
import model.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionMemberContainer implements SessionMemberDataAccessIF {
    private static SessionMemberContainer instance;
    private final List<Person> container;

    /**
     * Constructor for SessionMemberContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the SessionMemberContainer.
     */
    private SessionMemberContainer() {
    	container = new ArrayList<>();
    }

    /**
     * Gets the instance of the SessionMemberContainer.
     *
     * @return The instance of the SessionMemberContainer.
     */
    public static SessionMemberContainer getInstance() {
        if (instance == null) {
            instance = new SessionMemberContainer();
        }
        return instance;
    }


    @Override
    public boolean create(Session session, Person member) {
        return false;
    }

    @Override
    public Person getMemberFromSession(long ssn, Session session) {
        return null;
    }

    @Override
    public List<Person> getMembersInSession(Session session) {
        return null;
    }

    @Override
    public boolean removeMemberFromSession(Session session, Person member) {
        return false;
    }
}
