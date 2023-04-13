package model;

public class Administrator extends Person {
    private long snn;

    public Administrator(Person person, long ssn) {
        super(person.getFirstName(), person.getLastName(), person.getAddress(), person.getEmail(), person.getPhoneNumber(), person.getRole(), person.getUsername(), person.getPassword(), ssn);
        this.snn = ssn;
    }

    public long getSnn() {
        return snn;
    }

    public void setSnn(long snn) {
        this.snn = snn;
    }
}
