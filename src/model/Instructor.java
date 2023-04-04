package model;

import java.util.List;

public class Instructor extends Person{
    private List<Subject> subject;

    public Instructor(String firstName, String lastName, Address address, String email, String phoneNumber, int role, String username, String password, long ssn, List<Subject> subject) {
        super(firstName, lastName, address, email, phoneNumber, role, username, password, ssn);
        this.subject = subject;
    }

    public List<Subject> getSubjects() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }
}
