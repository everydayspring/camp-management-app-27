package camp.model;

import java.util.*;

public class SubjectMap {
    public Map<String, Subject> subjectStore; // < 과목 고유번호, 과목 객체 >
    public int subjectIndex; // 고유 번호 인덱스

    // 과목 타입
    public final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public final String SUBJECT_TYPE_CHOICE = "CHOICE";
    public final String INDEX_TYPE_SUBJECT = "SU";

    // 초기화 할 때 모든 과목을 subjectStore에 저장
    public SubjectMap() {
        subjectStore = new HashMap<>();
        this.subjectIndex = 0;

        String subjectId = subjectSequence();
        subjectStore.put(subjectId, new Subject(subjectId, "Java", SUBJECT_TYPE_MANDATORY));
        subjectId = subjectSequence();
        subjectStore.put(subjectId, new Subject(subjectId, "객체지향", SUBJECT_TYPE_MANDATORY));
        subjectId = subjectSequence();
        subjectStore.put(subjectId, new Subject(subjectId, "Spring", SUBJECT_TYPE_MANDATORY));
        subjectId = subjectSequence();
        subjectStore.put(subjectId, new Subject(subjectId, "JPA", SUBJECT_TYPE_MANDATORY));
        subjectId = subjectSequence();
        subjectStore.put(subjectId, new Subject(subjectId, "MySQL", SUBJECT_TYPE_MANDATORY));
        subjectId = subjectSequence();
        subjectStore.put(subjectId, new Subject(subjectId, "디자인 패턴", SUBJECT_TYPE_CHOICE));
        subjectId = subjectSequence();
        subjectStore.put(subjectId, new Subject(subjectId, "Spring Security", SUBJECT_TYPE_CHOICE));
        subjectId = subjectSequence();
        subjectStore.put(subjectId, new Subject(subjectId, "Redis", SUBJECT_TYPE_CHOICE));
        subjectId = subjectSequence();
        subjectStore.put(subjectId, new Subject(subjectId, "MongoDB", SUBJECT_TYPE_CHOICE));
    }

    // 과목 고유 번호 생성
    public String subjectSequence() {
        subjectIndex++;
        return INDEX_TYPE_SUBJECT + subjectIndex;
    }

    // 지정된 번호로 과목 고유 번호 생성
    public String makeSequence(String val) {
        return INDEX_TYPE_SUBJECT + val;
    }

    // 필수 과목 리스트 생성
    public void makeManSubject(String val, ArrayList<String> getSubject) {
        Subject useSubject = this.subjectStore.get(val);
        if (!useSubject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) //타 과목시 예외처리
            throw new IllegalArgumentException();
        getSubject.add(val);
    }

    // 선택 과목 리스트 생성
    public void makeSubSubject(String val, ArrayList<String> getSubject) {
        Subject useSubject = this.subjectStore.get(val);
        if (!useSubject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) //타 과목시 예외처리
            throw new IllegalArgumentException();
        getSubject.add(val);
    }

    // 수강중인 과목 전체 출력
    public void printSubjectInfoByStudentId(StudentMap studentMap, String studentId) {
        ArrayList<String> list = studentMap.studentStore.get(studentId).getSubjectList();
        System.out.println("==========수강중인 과목 리스트=========");
        for (String s : list) {
            System.out.println(s + " : " + subjectStore.get(s).getSubjectName());
        }
        System.out.println("====================================");
    }

    // 고유 번호에 매칭된 과목 반환
    public Subject getSubject(String code) {
        return this.subjectStore.get(code);
    }

    // 필수 과목 체크
    public boolean check_MANDATORY(Subject useSubject) {
        return useSubject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY);
    }

    // 선택 과목 체크
    public boolean check_CHOICE(Subject useSubject) {
        return useSubject.getSubjectType().equals(SUBJECT_TYPE_CHOICE);
    }

    // 필수 과목 등급 계산
    public String gradeMandatory(int score) {
        if (score > 94) {
            return "A";
        } else if (score > 89) {
            return "B";
        } else if (score > 79) {
            return "C";
        } else if (score > 69) {
            return "D";
        } else if (score > 59) {
            return "F";
        } else {
            return "N";
        }
    }

    // 선택 과목 등급 계산
    public String gradeChoice(int score) {
        if (score > 89) {
            return "A";
        } else if (score > 79) {
            return "B";
        } else if (score > 69) {
            return "C";
        } else if (score > 59) {
            return "D";
        } else if (score > 49) {
            return "F";
        } else {
            return "N";
        }
    }

    // 과목 분류 확인
    public boolean checkSubjectType(String subjectId) {
        Subject subject = subjectStore.get(subjectId);
        if (subject.getSubjectType().equals("MANDATORY")) return true;
        return false;
    }
}