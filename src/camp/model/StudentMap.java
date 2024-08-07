package camp.model;

import java.util.*;

public class StudentMap {
    public Map<String, Student> studentStore; // < 수강생 고유 번호, 수강생 객체 >
    public int studentIndex; // 고유 번호 인덱스
    public static final String INDEX_TYPE_STUDENT = "ST"; // 수강생 코드

    public StudentMap() {
        studentStore = new HashMap<>();
        this.studentIndex = 0;
    }

    // 수강생 고유 번호 생성
    public String studentSequence() {
        this.studentIndex++;
        return INDEX_TYPE_STUDENT + studentIndex;
    }

    // Map에 저장
    public void set_Store(String studentId, String studentName, String studentState, ArrayList<String> getSubject) {
        Student student = new Student(studentId, studentName, studentState, getSubject); // 이름과 수강중인 과목 리스트를 담은 객체 생성
        this.studentStore.put(studentId, student); // Map에 저장
    }

    // 수강생 전체 출력
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

    // 수강생 반환
    public Student getStudent(String studentId) {
        return this.studentStore.get(studentId);
    }

    // 수강생 삭제
    public void deleteKey(String Key) {
        this.studentStore.remove(Key);
        if (!this.studentStore.containsKey(Key))
            System.out.println("삭제 완료되었습니다.");
    }

    // Map 비어있는지 체크
    public boolean checkEmpty() {
        return this.studentStore.isEmpty();
    }

    // 전체 키 반환
    public Set<String> getKeys() {
        return this.studentStore.keySet();
    }

    // 상태 확인
    public boolean checkState(String key, String type) {
        return this.studentStore.get(key).getStudentState().equals(type);
    }
}