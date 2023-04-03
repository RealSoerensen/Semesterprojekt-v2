package model;

public class Administrator extends Person {
    private int snn;

    public Administrator(String firstName, String lastName, Address address, String email, String phoneNumber, int role, String username, String password, int ssn) {
        super(firstName, lastName, address, email, phoneNumber, role, username, password, ssn);
        this.snn = ssn;
    }

    public int getSnn() {
        return snn;
    }

    public void setSnn(int snn) {
        this.snn = snn;
    }
}
