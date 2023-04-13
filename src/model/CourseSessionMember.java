package model;

public class CourseSessionMember {
    private Member member;
    private CourseSession courseSession;

    public CourseSessionMember(Member member, CourseSession courseSession) {
        this.member = member;
        this.courseSession = courseSession;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public CourseSession getCourseSession() {
        return courseSession;
    }

    public void setCourseSession(CourseSession courseSession) {
        this.courseSession = courseSession;
    }
}
