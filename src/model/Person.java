package model;

public class Person {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private int role; 		//1: member, 2: instructor, 3: administrator
    private String username;
    private String password;
    private int ssn;

    public Person(String name, String address, String email, String phoneNumber, int role, String username, String password, int ssn) {
    	this.name = name;
    	this.address = address;
    	this.email = email;
    	this.phoneNumber = phoneNumber;
    	this.role = role;
    	this.username = username;
    	this.password = password;
    	this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }
}
