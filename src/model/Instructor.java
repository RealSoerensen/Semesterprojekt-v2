package model;

public class Instructor extends Person{
    public Instructor(Person person) {
        super(person.getFirstName(), person.getLastName(), person.getAddress(), person.getEmail(), person.getPhoneNumber(), person.getUsername(), person.getPassword(), person.getSsn());
    }

    public int getRole() {
        return 2;
    }
}
