package model;

public class Member extends Person {
    public Member(Person person) {
        super(person.getFirstName(), person.getLastName(), person.getAddress(), person.getEmail(), person.getPhoneNumber(), person.getUsername(), person.getPassword(), person.getSsn());

    }

    public int getRole() {
        return 1;
    }
}
