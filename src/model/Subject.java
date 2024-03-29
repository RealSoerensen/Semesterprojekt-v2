package model;

public class Subject {
    private String name;
    private String description;
    private long subjectID;

    public Subject(long subjectID, String name, String description) {
        this.name = name;
        this.subjectID = subjectID;
        this.description = description;
    }

    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
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

	public long getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(long subjectID) {
		this.subjectID = subjectID;
	}
	@Override
	public String toString() {
		return getName();
		
	}
}
