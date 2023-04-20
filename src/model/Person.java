package model;

public class Person {
    private String firstName;
    private String lastName;
    private Address address;
    private String email;
    private String phoneNumber;
    private int role;
    private String username;
    private String password;
    private long ssn;

    public Person(String firstName, String lastName, Address address, String email, String phoneNumber, int role, String username, String password, long ssn) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.address = address;
    	this.email = email;
        this.role = role;
    	this.phoneNumber = phoneNumber;
    	this.username = username;
    	this.password = password;
    	this.ssn = ssn;
    }

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
    	return role;
    }

    public void setRole(int role) {
    	this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public long getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }
}
