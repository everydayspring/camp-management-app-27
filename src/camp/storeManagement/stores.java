package camp.storeManagement;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.HashMap;
import java.util.Map;

public class stores {
    public static Map<String, Student> studentStore;
    public static Map<String, Subject> subjectStore;
    public static Map<String, Map<String, Score>> scoreStore;

    // 과목 타입
    public static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    public static int studentIndex;
    public static final String INDEX_TYPE_STUDENT = "ST";
    public static int subjectIndex;
    public static final String INDEX_TYPE_SUBJECT = "SU";
    public static int scoreIndex;
    public static final String INDEX_TYPE_SCORE = "SC";

    public static void setInitData() {
        studentStore = new HashMap<>();
        scoreStore = new HashMap<>();
        subjectStore = new HashMap<>();

        String subjectId = sequence(INDEX_TYPE_SUBJECT);
        subjectStore.put(subjectId, new Subject(subjectId, "Java", SUBJECT_TYPE_MANDATORY));
        subjectId = sequence(INDEX_TYPE_SUBJECT);
        subjectStore.put(subjectId, new Subject(subjectId, "객체지향", SUBJECT_TYPE_MANDATORY));
        subjectId = sequence(INDEX_TYPE_SUBJECT);
        subjectStore.put(subjectId, new Subject(subjectId, "Spring", SUBJECT_TYPE_MANDATORY));
        subjectId = sequence(INDEX_TYPE_SUBJECT);
        subjectStore.put(subjectId, new Subject(subjectId, "JPA", SUBJECT_TYPE_MANDATORY));
        subjectId = sequence(INDEX_TYPE_SUBJECT);
        subjectStore.put(subjectId, new Subject(subjectId, "MySQL", SUBJECT_TYPE_MANDATORY));
        subjectId = sequence(INDEX_TYPE_SUBJECT);
        subjectStore.put(subjectId, new Subject(subjectId, "디자인 패턴", SUBJECT_TYPE_CHOICE));
        subjectId = sequence(INDEX_TYPE_SUBJECT);
        subjectStore.put(subjectId, new Subject(subjectId, "Spring Security", SUBJECT_TYPE_CHOICE));
        subjectId = sequence(INDEX_TYPE_SUBJECT);
        subjectStore.put(subjectId, new Subject(subjectId, "Redis", SUBJECT_TYPE_CHOICE));
        subjectId = sequence(INDEX_TYPE_SUBJECT);
        subjectStore.put(subjectId, new Subject(subjectId, "MongoDB", SUBJECT_TYPE_CHOICE));
    }

    public static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }



}

