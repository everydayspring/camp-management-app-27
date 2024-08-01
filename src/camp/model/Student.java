package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String studentName;
    private ArrayList<String> subjectList;

    public Student(String studentName, ArrayList<String> subjectList) {
        this.subjectList = subjectList;
        this.studentName = studentName;
    }

    // Getter


    public String getStudentName() {
        return studentName;
    }

}
