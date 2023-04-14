package model;

public class CourseMember {
    private Member member;
    private Course course;

    public CourseMember(Member member, Course course) {
        this.member = member;
        this.course = course;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
