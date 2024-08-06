package camp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentMap {

    public Map<String, Student> studentStore;
    public int studentIndex;
    public final String INDEX_TYPE_STUDENT = "ST";

    public StudentMap(){
        studentStore = new HashMap<>();
        this.studentIndex=0;
    }

    public String studentSequence(){
        studentIndex++;
        return INDEX_TYPE_STUDENT + studentIndex;
    }

    public

    private set_Store(String studentName, String studentId, String studentState, ArrayList<String> getSubject) {
        Student student = new Student(studentId, studentName, studentState, getSubject); //이름이랑 과목코드 리스트를 담은 객체 생성
        this.studentStore.put(studentId, student); //맵에 저장
    }




}
