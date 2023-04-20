package model;

public class CourseMember {
    private Person person;
    private Course course;

    public CourseMember(Person person, Course course) {
        this.person = person;
        this.course = course;
    }

    public Person getMember() {
        return person;
    }

    public void setMember(Person person) {
        this.person = person;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
