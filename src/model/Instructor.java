package model;

public class Instructor extends Person{
    private String subject;

    public Instructor(String firstName, String lastName, Address address, String email, String phoneNumber, int role, String username, String password, long ssn, String subject) {
        super(firstName, lastName, address, email, phoneNumber, role, username, password, ssn);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
