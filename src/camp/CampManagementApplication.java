package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

public class CampManagementApplication {
    // 데이터 저장소
    private static Map<String, Student> studentStore;
    private static Map<String, Subject> subjectStore;
    private static Map<String, Map<String, Score>> scoreStore;


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


    public static void main(String[] args) throws InterruptedException {
        setInitData();
        displayMainView();
    }

//    public static void main(String[] args) {
//        setInitData();
//
//        try {
//            displayMainView();
//        } catch (Exception e) {
//            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
//        }
//    }

    //생성자임 보니까.
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
        /*
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "Java", SUBJECT_TYPE_MANDATORY));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "객체지향", SUBJECT_TYPE_MANDATORY));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring", SUBJECT_TYPE_MANDATORY));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "JPA", SUBJECT_TYPE_MANDATORY));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "MySQL", SUBJECT_TYPE_MANDATORY));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "디자인 패턴", SUBJECT_TYPE_CHOICE));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring Security", SUBJECT_TYPE_CHOICE));
        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "Redis", SUBJECT_TYPE_CHOICE));

        addSubject(new Subject(sequence(INDEX_TYPE_SUBJECT), "MongoDB", SUBJECT_TYPE_CHOICE));

        */
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

        //필수 과목 저장.
        for(String val : mandatorySet){
            val =INDEX_TYPE_SUBJECT+val;
            Subject useSubject = subjectStore.get(val);
            if(!useSubject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) //타 과목시 예외처리
                throw new IllegalArgumentException();
            getSubject.add(val);
        }

        //선택 과목 저장
        for(String val : optionalSet){
            int useVal = Integer.parseInt(val)+5;
            val =INDEX_TYPE_SUBJECT+useVal;

            Subject useSubject = subjectStore.get(val);
            if(!useSubject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) //타 과목시 예외처리
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
        System.out.println(scoreStore.size());
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

    //고유 번호 입력 검증 - 김창민
    private static String getStudentId() {
        printStudentInfo();

        System.out.print("\n관리할 수강생의 고유 번호를 입력하세요 (ex. ST) : ");
        String useName = sc.next();
        if(!studentStore.containsKey(useName))
            throw new IllegalArgumentException("해당 학생은 존재하지 않습니다.");

        return useName;
    }

    // 수강생이 등록한 과목중 선택 -> 이봄
    private static String getSubjectIdByStudent(String studentId) {
        printSubjectInfoByStudentId(studentId);

        Student stu = studentStore.get(studentId);
        ArrayList<String> arr = stu.getSubjectList();

        System.out.print("\n관리할 과목의 고유 번호를 입력하세요 (ex. SU1) : ");
        String subName =  sc.next();  //SU1
        if(!arr.contains(subName)){
            throw new IllegalArgumentException("선택한 수강생이 수강중인 과목이 아닙니다.");
        }
        return subName;
    }

    // 수강생의 과목별 시험 회차 및 점수 등록 -> 이봄

    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        String subjectId = getSubjectIdByStudent(studentId); // 등록할 과목 고유 번호


        System.out.println("등록할 시험 회차를 입력하시오...");
        String index = sc.next();
        System.out.println("점수를 입력하시오...");
        String score = sc.next();

        Map<String, Score> inner = new HashMap<>();


        if(!scoreStore.containsKey(studentId)){
            inner.put(subjectId, new Score(index, score));

            scoreStore.put(studentId, inner);

        } else {

            scoreStore.get(studentId).get(subjectId).setScores(index, score);
        }

        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    public static void updateRoundScoreBySubject() {

        /*
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("원하는 수강생의 고유번호 입력: ");
        String fixstudentId = sc.nextLine();


        while (true) {
            // 메뉴 출력
            System.out.println("수정할 항목을 선택하세요:");
            System.out.println("press1: 과목만 수정");
            System.out.println("press2: 점수만 수정");
            System.out.println("press3: 회차만 수정");
            System.out.println("press4: 회차,과목과 점수 모두 수정");
            System.out.println("press0: 종료");

            int fix = sc.nextInt();
            sc.nextLine();  // 버퍼를 비우기 위해 호출

            try {
                switch (fix) {
                    case 1:
                        System.out.print("수정할 과목 입력: ");
                        String newSubject = sc.nextLine();
                        if (newSubject.equals(subjectStore)) {
                            System.out.println("과목이 수정되었습니다.");
                        } else {
                            System.out.println("변경된 과목이 없습니다.");
                        }
                        break;
                    case 2:
                        System.out.print("수정할 점수 입력: ");
                        int newScore = sc.nextInt();
                        sc.nextLine();  // 버퍼를 비우기 위해 호출
                        if (newScore >= 0 && newScore <= 100) {
                            System.out.println("점수가 수정되었습니다.");
                        } else {
                            System.out.println("잘못된 입력값입니다. 점수는 0에서 100 사이여야 합니다.");
                        }
                        break;
                    case 3:
                        System.out.print("새 회차 입력 (1~10): ");
                        int newRound = sc.nextInt();
                        sc.nextLine();  // 버퍼를 비우기 위해 호출
                        if (newRound >= 1 && newRound <= 10) {
                            System.out.println("회차가 수정되었습니다.");
                        } else {
                            System.out.println("잘못된 입력값입니다. 회차는 1에서 10 사이여야 합니다.");
                        }
                        break;
                    case 4:
                        System.out.print("수정할 과목 입력: ");
                        String updatedSubject = sc.nextLine();
                        if (newSubject.equals(subjectStore)) {
                            System.out.println("과목이 수정되었습니다.");
                        } else {
                            System.out.println("변경된 과목이 없습니다.");
                        }
                        System.out.print("새 점수 입력: ");
                        int updatedScore = sc.nextInt();

                        sc.nextLine();  // 버퍼를 비우기 위해 호출
                        System.out.print("새 회차 입력 (1~10): ");
                        int updatedRound = sc.nextInt();
                        sc.nextLine();  // 버퍼를 비우기 위해 호출

                        if (updatedScore >= 0 && updatedScore <= 100 && updatedRound >= 1 && updatedRound <= 10) {
                            System.out.println("과목, 점수 및 회차가 모두 수정되었습니다.");
                        } else {
                            System.out.println("잘못된 입력값입니다.");
                        }
                        break;
                    case 0:
                        System.out.println("프로그램을 종료합니다.");
                        sc.close();
                        return;
                    default:
                        System.out.println("잘못된 선택입니다. 다시 시도하세요.");
                }
            } catch (Exception e) {
                System.out.println("다시 입력하세요. " + e.getMessage());
            }


        }

         */

    }


    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        Scanner sc = new Scanner(System.in);
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)

        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현

        try{
            //scoreStore 맵에 입력한 학생고유번호가 존재할경우
            if(scoreStore.containsKey(studentId)){
                System.out.println("점수 조회를 원하는 과목의 고유번호를 입력하세요");
                //전체보유중인 subject 의 고유번호 전체를 보여줌
                printSubjectInfoByStudentId(studentId);
                String inputSubjectId = sc.nextLine();
                int indicator = Character.getNumericValue(inputSubjectId.charAt(2));
                System.out.println(indicator);
                //scoreStore안에있는 Map 을 지정해줌
                Map<String,Score> innerScoreStore = scoreStore.get(studentId);

                //만약 2중 맵안에 고유학생번호키가 가진 Value의 Map안에 고유 과목 키값이 존재할경우
                if(innerScoreStore.containsKey(inputSubjectId) && indicator < 6){
                    int[] d = innerScoreStore.get(inputSubjectId).getScores();

                    for(int i = 0; i< d.length; i++){
                        int num = d[i];

                        if( num> 94){
                            System.out.println((i+1)+"회차 등급: A");

                        }else if(num > 89){
                            System.out.println((i+1)+"회차 등급: B");

                        }else if(num > 80){
                            System.out.println((i+1)+"회차 등급 : C");
                        }else if(num >70){
                            System.out.println((i+1)+"회차 등급 : D");
                        }else if(num >60){
                            System.out.println((i+1)+"회차 등급 : F");
                        }else if(num > 0){
                            System.out.println((i+1)+"회차 등급 : N");
                        }else{
                            continue;
                        }

                    }
                    System.out.println("\n등급 조회 성공!");

                }else if(innerScoreStore.containsKey(inputSubjectId) && indicator > 5) {
                    int[] d = innerScoreStore.get(inputSubjectId).getScores();

                    for (int i = 0; i < d.length; i++) {
                        int num = d[i];

                        if (num > 89) {
                            System.out.println((i + 1) + "회차 등급: A");

                        } else if (num > 79) {
                            System.out.println((i + 1) + "회차 등급: B");

                        } else if (num > 69) {
                            System.out.println((i + 1) + "회차 등급 : C");
                        } else if (num > 59) {
                            System.out.println((i + 1) + "회차 등급 : D");
                        } else if (num > 49) {
                            System.out.println((i + 1) + "회차 등급 : F");
                        }else if (num > 0){
                            System.out.println((i+1)+"회차 등급 : N");
                        }else {
                            continue;
                        }

                    }
                    System.out.println("\n등급 조회 성공!");
                }else{
                    throw new Exception("잘못된 과목 고유번호를 입력하였습니다.");
                }
            }//해당 고유번호를지닌 학생이 없을시
            else {
                throw new Exception("해당 고유번호를 가진 수강생이 존재하지않습니다");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }


    private static void printStudentInfo(){
        Set<String> keys = studentStore.keySet();
        List<String> keyList = new ArrayList<>(keys);
        Collections.sort(keyList);
        for(String key : keyList){
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


    // 수강생이 등록한 과목만 출력 --> 이봄
    private static void printSubjectInfoByStudentId(String studentId) {
        Student stu = studentStore.get(studentId);
        ArrayList<String> arr = stu.getSubjectList();

        System.out.println("==============수강중인 과목==============");
        for(String str : arr){
            System.out.println(str + " : " + subjectStore.get(str).getSubjectName());
        }
        System.out.println("=======================================");
    }

}
