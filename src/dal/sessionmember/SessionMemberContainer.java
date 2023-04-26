package dal.sessionmember;

import model.Person;
import model.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionMemberContainer implements SessionMemberDataAccessIF {
    private static SessionMemberContainer instance;
    private final List<Person> personList = new ArrayList<>();
    private final HashMap<Session, List<Person>> container;

    /**
     * Constructor for SessionMemberContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the SessionMemberContainer.
     */
    private SessionMemberContainer() {
    	container = new HashMap<>();
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
        boolean result = false;
        if (container.containsKey(session)) {
            personList.add(member);
            result = true;
        }
        return result;
    }

    @Override
    public boolean isPersonIn(Session session, Person person) {
        return false;
    }

    @Override
    public List<Person> getAll(Session session) {
        List<Person> members = new ArrayList<>();
        if (container.containsKey(session)) {
            members = container.get(session);
        }
        return members;
    }

    @Override
    public boolean remove(Session session, Person member) {
        boolean result = false;
        if (container.containsKey(session)) {
            personList.remove(member);
            result = true;
        }
        return result;
    }

}
