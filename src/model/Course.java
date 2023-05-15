package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Course {
	private long courseID;
	private String name;
	private double price;
	private String description;
	private Date startDate;
	private Date endDate;

	public Course(String name, double price, String description, Date startDate, Date endDate) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Course(long courseID, String name, double price, String description, Date startDate, Date endDate) {
		this.courseID = courseID;
		this.name = name;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
