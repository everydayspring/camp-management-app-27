package camp.model;

import java.util.ArrayList;

public class Student {
    private final String studentId;
    private String studentName;
    private String studentState; //수강생 상태
    private final ArrayList<String> subjectList; // 수강 과목

    public Student(String studentId,String studentName, String studentState,ArrayList<String> subjectList) {
        this.studentId=studentId;
        this.subjectList = subjectList;
        this.studentState = studentState;
        this.studentName = studentName;
    }

    // Getter
    public String getStudentId() { return studentId; }
    public String getStudentName() {
        return studentName;
    }

    public String getStudentState() { return studentState; }

    public ArrayList<String> getSubjectList() {
        return subjectList;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentState(String studentState) {
        this.studentState = studentState;
    }
}
