package model;

import java.util.ArrayList;

public class Member extends Person{
    private ArrayList<Course> courses;
    public Member(String name, String address, String email, String phoneNumber, int role, String username, String password, int ssn) {
        super(name, address, email, phoneNumber, role, username, password, ssn);
        courses = new ArrayList<>();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
