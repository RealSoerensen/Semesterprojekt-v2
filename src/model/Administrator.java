package model;

public class Administrator extends Person {
    public Administrator(Person person) {
        super(person.getFirstName(), person.getLastName(), person.getAddress(), person.getEmail(), person.getPhoneNumber(), person.getUsername(), person.getPassword(), person.getSsn());
    }

    public int getRole() {
        return 3;
    }
}
