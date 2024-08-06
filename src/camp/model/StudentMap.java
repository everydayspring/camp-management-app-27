package camp.model;

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

    private String studentSequence(){
        studentIndex++;
        return INDEX_TYPE_STUDENT + studentIndex;
    }



}
