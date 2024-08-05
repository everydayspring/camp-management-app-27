package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

public class CampManagementApplication {
    // 데이터 저장소
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

    // 스캐너
    protecullted static Scanner sc = new Scanner(System.in);

    // main
    public static void main(String[] args) throws InterruptedException {
        setInitData();
        displayMainView();
    }
    /*
    public static void main(String[] args) {
        setInitData();

        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }
    */
    // Constructor
    private static void setInitData() {
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

    // index 자동 증가
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

    // 메인 메뉴
    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> StudentManagement.displayStudentView(); // 수강생 관리
                case 2 -> ScoreManagement.displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    // 전체 과목 리스트 출력 --> 김창민
    public static void printSubjectInfo() {
        System.out.println("=====   수강 가능한 과목 리스트 입니다.  =====");
        System.out.println("=======================================");
        System.out.println("=====         필수 과목             =====");
        System.out.println("=====         1. Java             =====");
        System.out.println("=====         2. 객체지향           =====");
        System.out.println("=====         3. Spring           =====");
        System.out.println("=====         4. JPA              =====");
        System.out.println("=====         5. MySQL            =====");
        System.out.println("=======================================");
        System.out.println("=====         선택 과목             =====");
        System.out.println("=====         1. 디자인 패턴         =====");
        System.out.println("=====         2. Spring Security  =====");
        System.out.println("=====         3. Redis            =====");
        System.out.println("=====         4. MongoDB          =====");
        System.out.println("=======================================");
    }

    // 수강생 리스트 출력 --> 김창민
    public static void printStudentInfo() {
        Set<String> keys = studentStore.keySet();
        List<String> keyList = new ArrayList<>(keys);

        Collections.sort(keyList);
        System.out.println("=============수강생 리스트============");
        for (String key : keyList) {
            System.out.println(key + " : " + studentStore.get(key).getStudentName());
        }
        System.out.println("===================================");
    }

    // 수강생이 수강중인 과목 리스트 출력 --> 이봄
    public static void printSubjectInfoByStudentId(String studentId) {
        Student student = studentStore.get(studentId);
        ArrayList<String> keys = student.getSubjectList();

        System.out.println("==============수강중인 과목==============");
        for (String key : keys) {
            System.out.println(key + " : " + subjectStore.get(key).getSubjectName());
        }
        System.out.println("=======================================");
    }
}

