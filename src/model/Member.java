package model;

import java.util.ArrayList;
import java.util.List;

public class Member extends Person {
    private List<Course> courses;
    public Member(String firstName, String lastName, Address address, String email, String phoneNumber, int role, String username, String password, int ssn) {
        super(firstName, lastName, address, email, phoneNumber, role, username, password, ssn);
        courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
    	courses.add(course);
    }

    public void removeCourse(Course course) {
    	courses.remove(course);
    }
}
