package model;

import java.util.ArrayList;
import java.util.List;

public class Member extends Person {
    public Member(Person person) {
        super(person.getFirstName(), person.getLastName(), person.getAddress(), person.getEmail(), person.getPhoneNumber(), person.getRole(), person.getUsername(), person.getPassword(), person.getSsn());
    }
}
