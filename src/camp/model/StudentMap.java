package camp.model;

import java.util.*;

public class StudentMap {

    public Map<String, Student> studentStore;               // 학생번호-학생 쌍 저장 공간
    public int studentIndex;                                // 표기 학생 번호
    public static final String INDEX_TYPE_STUDENT = "ST";   //학생 코드

    // 생성자
    public StudentMap() {
        studentStore = new HashMap<>();
        this.studentIndex = 0;
    }

    //번호 증가시키고 학생코드 생성 - StudentManagement에서 사용
    public String studentSequence() {
        this.studentIndex++;
        return INDEX_TYPE_STUDENT + studentIndex;
    }

    // Map에 저장하는 메소드 - StudentManagement에서 사용
    public void set_Store(String studentId, String studentName, String studentState, ArrayList<String> getSubject) {
        Student student = new Student(studentId, studentName, studentState, getSubject);    //이름이랑 과목코드 리스트를 담은 객체 생성
        this.studentStore.put(studentId, student);                                          //맵에 저장
    }

    //학생 전부를 출력하는 메소드 - StudentManagement, StudentDisplay에서 사용
    public void printStudentInfo() {
        Set<String> keys = getKeys();
        List<String> keyList = new ArrayList<>(keys);

        Collections.sort(keyList);
        System.out.println("=============수강생 리스트============");
        for (String key : keyList) {
            System.out.println(key + " : " + studentStore.get(key).getStudentName());
        }
        System.out.println("===================================");
    }

    //학생 반환 - StudentManagement, StudentDisplay, SubjectMap에서 사용
    public Student getStudent(String studentId) {
        return this.studentStore.get(studentId);
    }

    //학생 삭제 - StudentManagement 에서 사용
    public void deleteKey(String Key) {
        this.studentStore.remove(Key);
        if (!this.studentStore.containsKey(Key))
            System.out.println("삭제 완료되었습니다.");
    }

    //Map 비어있는지 체크 - StudentManagement, StudentDisplay에서 사용
    public boolean checkEmpty() {
        return this.studentStore.isEmpty();
    }

    //키 전부 반환 - StudentDisplay에서 사용
    public Set<String> getKeys() {
        return this.studentStore.keySet();
    }

    //상태 확인 - StudentDisplay에서 사용
    public boolean checkState(String key, String type){
        return this.studentStore.get(key).getStudentState().equals(type);
    }

    public void printSubjectInfo() {
        System.out.println("==========수강중인 과목==========");
        System.out.println("수강생이 수강중이 목록 출력 해야함~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("===============================");
    }
}
