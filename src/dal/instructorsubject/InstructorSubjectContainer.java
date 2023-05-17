package dal.instructorsubject;

import model.Course;
import model.Person;
import model.Subject;

import java.util.ArrayList;
import java.util.List;

public class InstructorSubjectContainer implements InstructorSubjectDataAccessIF{
    private static InstructorSubjectContainer instance;
    private List<InstructorSubject> container;

    private InstructorSubjectContainer() {
        	container = new ArrayList<>();
    }

    public static InstructorSubjectContainer getInstance() {
        if (instance == null) {
            instance = new InstructorSubjectContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Person person, Subject subject) {
        boolean result = false;
        InstructorSubject newInstructorSubject = new InstructorSubject(person, subject);
        if(!isPersonIn(person, subject)) {
            container.add(newInstructorSubject);
            result = true;
        }
        return result;
    }

    @Override
    public boolean isPersonIn(Person person, Subject subject) {
        Person result = null;
        InstructorSubject newInstructorSubject = new InstructorSubject(person, subject);
        for(int i = 0; i < container.size() && result == null; i++) {
            InstructorSubject instructorSubject = container.get(i);
            if(newInstructorSubject.equals(instructorSubject)) {
                result = instructorSubject.instructor();
            }
        }
        return result != null;
    }

    @Override
    public boolean remove(Person person, Subject subject) {
        boolean result = false;
        for(int i = 0; i < container.size() && !result; i++) {
            InstructorSubject instructorSubject = container.get(i);
            if(instructorSubject.instructor().equals(person) && instructorSubject.subject().equals(subject)) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}

record InstructorSubject(Person instructor, Subject subject) {
}
