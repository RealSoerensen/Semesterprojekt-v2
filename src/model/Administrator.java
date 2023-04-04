package model;

public class Administrator extends Person {
    private long snn;

    public Administrator(String firstName, String lastName, Address address, String email, String phoneNumber, int role, String username, String password, long ssn) {
        super(firstName, lastName, address, email, phoneNumber, role, username, password, ssn);
        this.snn = ssn;
    }

    public long getSnn() {
        return snn;
    }

    public void setSnn(long snn) {
        this.snn = snn;
    }
}
