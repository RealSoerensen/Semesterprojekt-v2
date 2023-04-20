package model;

public class SessionMember {
    private Person person;
    private Session session;

    public SessionMember(Person person, Session session) {
        this.person = person;
        this.session = session;
    }

    public Person getPerson() {
        return person;
    }

    public void setMember(Person person) {
        this.person = person;
    }

    public Session getCourseSession() {
        return session;
    }

    public void setCourseSession(Session courseSession) {
        this.session = courseSession;
    }
}
