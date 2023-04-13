package model;

public class Subject {
    private String name;
    private String description;
    private long subjectID;

    public Subject(String name, long subjectID) {
        this.name = name;
        this.subjectID = subjectID;
        description = "";
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
    
}
