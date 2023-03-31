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
}
