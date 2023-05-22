package dal.sessionmember;

import model.Person;
import model.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionMemberContainer implements SessionMemberDataAccessIF {
    private static SessionMemberContainer instance;
    private final List<SessionMember> container;

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
        boolean result = false;
        SessionMember sessionMember = new SessionMember(session, member);
        if(!container.contains(sessionMember)) {
            result = container.add(sessionMember);
        }
        return result;
    }

    @Override
    public boolean isPersonIn(Session session, Person person) {
        boolean result = false;
        for(int i = 0; i < container.size() && !result; i++) {
            if(container.get(i).session().equals(session) && container.get(i).member().equals(person)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<Person> getAll(Session session) {
        List<Person> members = new ArrayList<>();
        for (SessionMember sessionMember : container) {
            if (sessionMember.session().equals(session)) {
                members.add(sessionMember.member());
            }
        }
        return members;
    }

    @Override
    public boolean remove(Session session, Person member) {
        boolean result = false;
        for(int i = 0; i < container.size() && !result; i++) {
            if(container.get(i).session().equals(session) && container.get(i).member().equals(member)) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}

record SessionMember(Session session, Person member) {
}
