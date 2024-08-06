package camp.model;

import java.util.*;

public class StudentMap {

    public Map<String, Student> studentStore;
    public int studentIndex;
    public static final String INDEX_TYPE_STUDENT = "ST";

    public StudentMap(){
        studentStore = new HashMap<>();
        this.studentIndex=0;
    }

    public String studentSequence(){
        this.studentIndex++;
        return INDEX_TYPE_STUDENT + studentIndex;
    }

    public void set_Store(String studentName, String studentId, String studentState, ArrayList<String> getSubject) {
        Student student = new Student(studentId, studentName, studentState, getSubject); //이름이랑 과목코드 리스트를 담은 객체 생성
        this.studentStore.put(studentId, student); //맵에 저장
    }


    public void printStudentInfo(){
        Set<String> keys = studentStore.keySet();
        List<String> keyList = new ArrayList<>(keys);

        Collections.sort(keyList);
        System.out.println("=============수강생 리스트============");
        for (String key : keyList) {
            System.out.println(key + " : " + studentStore.get(key).getStudentName());
        }
        System.out.println("===================================");
    }

    public Student getStudent(String studentId){
        return studentStore.get(studentId);
    }


    public void deleteKey(String Key){
        this.studentStore.remove(Key);
        if (!this.studentStore.containsKey(Key))
            System.out.println("삭제 완료되었습니다.");
    }

    public boolean checkEmpty(){
        return this.studentStore.isEmpty();
    }

}
