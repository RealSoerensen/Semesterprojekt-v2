package model;

public class CourseSessionMember {
    private Person person;
    private CourseSession courseSession;

    public CourseSessionMember(Person person, CourseSession courseSession) {
        this.person = person;
        this.courseSession = courseSession;
    }

    public Person getPerson() {
        return person;
    }

    public void setMember(Person person) {
        this.person = person;
    }

    public CourseSession getCourseSession() {
        return courseSession;
    }

    public void setCourseSession(CourseSession courseSession) {
        this.courseSession = courseSession;
    }
}
