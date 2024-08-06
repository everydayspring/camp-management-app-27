package camp.model;

import java.util.*;

public class SubjectMap {
    public Map<String, Subject> subjectStore;

    // 과목 타입
    public String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public String SUBJECT_TYPE_CHOICE = "CHOICE";

    public int subjectIndex;
    public final String INDEX_TYPE_SUBJECT = "SU";


    public SubjectMap(){
        subjectStore = new HashMap<>();
        this.subjectIndex=0;

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

    public String subjectSequence(){
        subjectIndex++;
        return INDEX_TYPE_SUBJECT + subjectIndex;
    }

    public String makeSequence(String val){
        return INDEX_TYPE_SUBJECT + val;
    }


    // 중복 Subject 저장 기능 메소드화
    public void makeManSubject(String val, ArrayList<String> getSubject) {
        Subject useSubject = this.subjectStore.get(val);
        if (!useSubject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) //타 과목시 예외처리
            throw new IllegalArgumentException();
        getSubject.add(val);
    }

    public void makeSubSubject(String val, ArrayList<String> getSubject) {
        Subject useSubject = this.subjectStore.get(val);
        if (!useSubject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) //타 과목시 예외처리
            throw new IllegalArgumentException();
        getSubject.add(val);
    }

    public void printSubjectInfoByStudentId(ArrayList<String> keys){
        System.out.println("==============수강중인 과목==============");
        for (String key : keys) {
            System.out.println(key + " : " + subjectStore.get(key).getSubjectName());
        }
        System.out.println("=======================================");
    }

}
