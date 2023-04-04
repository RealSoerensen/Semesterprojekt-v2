package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private long courseID;
	private String name;
	private double price;
	private String description;
	private String period;
	private List<Member> members;

	public Course(String name, double price, String description, String period) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.period = period;
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

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
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

	public long getCourseID() {
		return courseID;
	}

	public void setCourseID(long courseID) {
		this.courseID = courseID;
	}
}
