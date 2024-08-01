package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static Map<String,Student> studentStore;
    private static Map<String,Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    //생성자임 보니까.
    private static void setInitData() {
        studentStore = new HashMap<>();

        subjectStore = new HashMap<>();
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "Java", SUBJECT_TYPE_MANDATORY));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "객체지향", SUBJECT_TYPE_MANDATORY));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring", SUBJECT_TYPE_MANDATORY));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "JPA", SUBJECT_TYPE_MANDATORY));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "MySQL", SUBJECT_TYPE_MANDATORY));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "디자인 패턴", SUBJECT_TYPE_CHOICE));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring Security", SUBJECT_TYPE_CHOICE));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "Redis", SUBJECT_TYPE_CHOICE));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "MongoDB", SUBJECT_TYPE_CHOICE));

        scoreStore = new ArrayList<>();
    }
    private static void addSubject(Subject subject) {
        subjectStore.put(subject.getSubjectId(), subject);
    }

    // index 자동 증가
    private static String sequence(String type) {
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
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록 - 김창민
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();                     //이름
        String studentId = sequence(INDEX_TYPE_STUDENT);    //고유번호
        // 기능 구현 (필수 과목, 선택 과목)

        ArrayList<String> getSubject = new ArrayList<>(); //수강하는 과목코드를 저장할 리스트
        printSubjectInfo();//과목 출력

        /*필수 과목 받기*/
        sc.nextLine(); //개행문자 날리기

        System.out.println("수강하실 필수 과목의 번호를 입력해 주세요 (필수 : 3개 이상)(띄어쓰기로 구분)");
        String[] mandatorySubjects = sc.nextLine().split(" ");
        Set<String> mandatorySet = new HashSet<>(Arrays.asList(mandatorySubjects));
        int mandatorySize = mandatorySet.size();
        if(mandatorySize<3) throw new IllegalArgumentException("필수 과목 개수가 부족합니다.");

        /*선택 과목 받기*/
        System.out.println("수강하실 선택 과목의 번호를 입력해 주세요 (선택 : 2개 이상)(띄어쓰기로 구분)");
        String[] optionalSubjects = sc.nextLine().split(" ");
        Set<String> optionalSet = new HashSet<>(Arrays.asList(optionalSubjects));
        int optionalSize = optionalSet.size();
        if(optionalSize<2) throw new IllegalArgumentException("선택 과목 개수가 부족합니다.");

        for(String val : mandatorySet){
            val =INDEX_TYPE_SUBJECT+val;
            Subject useSubject = subjectStore.get(val);
            if(!useSubject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY))
                throw new IllegalArgumentException();
            getSubject.add(val);
        }

        for(String val : optionalSet){
            int useVal = Integer.parseInt(val)+5;
            val =INDEX_TYPE_SUBJECT+useVal;

            Subject useSubject = subjectStore.get(val);
            if(!useSubject.getSubjectType().equals(SUBJECT_TYPE_CHOICE))
                throw new IllegalArgumentException();
            getSubject.add(val);
        }

        Student student = new Student(studentName, getSubject); //이름이랑 과목코드 리스트를 담은 객체 생성
        // 기능 구현
        studentStore.put(studentId, student); //맵에 저장

        System.out.println("수강생 등록 성공!\n");
    }


    // 수강생 목록 조회 - 김창민
    private static void inquireStudent() {

        System.out.println("\n수강생 목록을 조회합니다...");
        printStudentInfo();
        // 기능 구현
        System.out.println("\n수강생 목록 조회 성공!");

        sc.nextLine();//개행문자 날리기
        System.out.print("조회 학생의 고유번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentStore.get(useKey);

        if(student==null) throw new NullPointerException("존재하지않는 학생입니다.");

        ArrayList<String> viewSubject = studentStore.get(useKey).getSubjectList();//과목 아이디가 저장되어있음
        ArrayList<String> viewMandatory = new ArrayList<>();
        ArrayList<String> viewOptional = new ArrayList<>();


        for(String val : viewSubject){
            Subject useSubject = subjectStore.get(val);

            if(useSubject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)){
                viewMandatory.add(useSubject.getSubjectName());
            }
            else if(useSubject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)){
                viewOptional.add(useSubject.getSubjectName());
            }
        }

        System.out.print("필수 과목 :");
        for(String vM : viewMandatory){
            System.out.print(vM+" ");
        }
        System.out.print("선택 과목 :");
        for(String vO : viewOptional){
            System.out.print(vO+" ");
        }
        System.out.println();

    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

    private static void printStudentInfo(){
        Set<String> keys = studentStore.keySet();
        for(String key : keys){
            System.out.println(studentStore.get(key).getStudentName()+":"+key);
        }
    }

    private static void printSubjectInfo(){
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
}
