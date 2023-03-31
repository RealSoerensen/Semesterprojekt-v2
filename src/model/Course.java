package model;

import java.util.ArrayList;

public class Course {
	private double price;
	private String period;
	private ArrayList<Member> members;
	private String name;
	private String description;
	private String address;
	private long CourseID;

	public Course(double price, String period, String name, String description, String address) {
		this.price = price;
		this.period = period;
		this.name = name;
		this.description = description;
		this.address = address;
		members = new ArrayList<>();
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getCourseID() {
		return CourseID;
	}

	public void setCourseID(long courseID) {
		CourseID = courseID;
	}
}
