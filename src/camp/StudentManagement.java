package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.*;

import static camp.CampManagementApplication.*;

public class StudentManagement {
    // 수강생 관리 메뉴 --> 김창민
    public static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 수정"); //추가 기능 1.
            System.out.println("4. 상태별 수강생 목록 조회"); //추가 기능 2.
            System.out.println("5. 수강생 삭제");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> updateStudent(); // 수강생 정보 수정
                case 4 -> inquireStudentByState(); // 상태별 수강생 목록 조회
                case 5 -> deleteStudent(); // 수강생 삭제
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록 --> 김창민
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = CampManagementApplication.sc.next();                     //이름
        String studentId = CampManagementApplication.sequence(INDEX_TYPE_STUDENT);    //고유번호
        // 기능 구현 (필수 과목, 선택 과목)
        System.out.print("수강생 상태 입력(Green,Red,Yellow): ");
        String studentState = sc.next();
        if (!studentState.equals("Green") && !studentState.equals("Red") && !studentState.equals("Yellow")) {
            throw new IllegalArgumentException("올바른 상태가 아닙니다");
        }

        ArrayList<String> getSubject = new ArrayList<>(); //수강하는 과목코드를 저장할 리스트
        CampManagementApplication.printSubjectInfo();//과목 출력

        /*필수 과목 받기*/
        sc.nextLine(); //개행문자 날리기

        System.out.println("수강하실 필수 과목의 번호를 입력해 주세요 (필수 : 3개 이상)(띄어쓰기로 구분)");
        String[] mandatorySubjects = sc.nextLine().split(" ");
        Set<String> mandatorySet = new HashSet<>(Arrays.asList(mandatorySubjects));
        int mandatorySize = mandatorySet.size();
        if (mandatorySize < 3) throw new IllegalArgumentException("필수 과목 개수가 부족합니다.");

        /*선택 과목 받기*/
        System.out.println("수강하실 선택 과목의 번호를 입력해 주세요 (선택 : 2개 이상)(띄어쓰기로 구분)");
        String[] optionalSubjects = sc.nextLine().split(" ");
        Set<String> optionalSet = new HashSet<>(Arrays.asList(optionalSubjects));
        int optionalSize = optionalSet.size();
        if (optionalSize < 2) throw new IllegalArgumentException("선택 과목 개수가 부족합니다.");

        //필수 과목 저장.
        for (String val : mandatorySet) {
            val = INDEX_TYPE_SUBJECT + val;
            Subject useSubject = subjectStore.get(val);
            if (!useSubject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) //타 과목시 예외처리
                throw new IllegalArgumentException();
            getSubject.add(val);
        }

        //선택 과목 저장
        for (String val : optionalSet) {
            int useVal = Integer.parseInt(val) + 5;
            val = INDEX_TYPE_SUBJECT + useVal;

            Subject useSubject = subjectStore.get(val);
            if (!useSubject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) //타 과목시 예외처리
                throw new IllegalArgumentException();
            getSubject.add(val);
        }

        Student student = new Student(studentId, studentName, studentState, getSubject); //이름이랑 과목코드 리스트를 담은 객체 생성

        // 기능 구현
        studentStore.put(studentId, student); //맵에 저장


        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회 --> 김창민
    private static void inquireStudent() {

        System.out.println("\n수강생 목록을 조회합니다...");
        if (studentStore.isEmpty()) {
            System.out.println("수강생이 없습니다");
            return;
        }

        printStudentInfo();  // 기능 구현


        sc.nextLine();//개행문자 날리기
        System.out.print("조회 학생의 고유번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentStore.get(useKey);

        if (student == null) throw new NullPointerException("존재하지않는 학생입니다.");

        ArrayList<String> viewSubject = studentStore.get(useKey).getSubjectList();//과목 아이디가 저장되어있음
        ArrayList<String> viewMandatory = new ArrayList<>(); //필수 과목 리스트
        ArrayList<String> viewOptional = new ArrayList<>(); //선택 과목 리스트

        //과목 종류 나눠서 세팅
        for (String val : viewSubject) {
            Subject useSubject = subjectStore.get(val);

            if (useSubject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                viewMandatory.add(useSubject.getSubjectName());
            } else if (useSubject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                viewOptional.add(useSubject.getSubjectName());
            }
        }
        System.out.println("==============검색완료===============");
        System.out.println("[이름]: " + student.getStudentName() + " [번호]: " + useKey + " [상태]: " + student.getStudentState());

        StringBuilder sb = new StringBuilder();
        sb.append("[필수 과목] :");
        for (String vM : viewMandatory) {
            sb.append(vM + ", ");
        }
        sb.deleteCharAt(sb.length() - 1);//공백 삭제
        sb.deleteCharAt(sb.length() - 1);//, 삭제
        sb.append("\n");
        sb.append("[선택 과목] :");
        for (String vO : viewOptional) {
            sb.append(vO + ", ");
        }
        sb.deleteCharAt(sb.length() - 1);//공백 삭제
        sb.deleteCharAt(sb.length() - 1);//, 삭제
        System.out.println(sb.toString());
    }

    // 수강생 정보 수정 --> 김창민
    private static void updateStudent() {
        // 번호 받아와서
        // set 시리즈로 수정
        System.out.println("\n수강생 목록을 조회합니다...");
        if (studentStore.isEmpty()) {
            System.out.println("수강생이 없습니다");
            return;
        }
        printStudentInfo();  // 기능 구현


        sc.nextLine();//개행문자 날리기
        System.out.print("조회 학생의 고유번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentStore.get(useKey);

        if (student == null) throw new NullPointerException("존재하지않는 학생입니다.");

        System.out.println("====[현재 정보]====");
        System.out.println("[이름]: " + student.getStudentName() + "\n[상태]: " + student.getStudentState());
        System.out.println("====[덮어씌울 정보]====\n이름과 상태 순으로 입력하세요\n(상태: Green,Red,Yellow)(띄어쓰기 구분): ");
        String[] newNameAndState = sc.nextLine().split(" ");
        if (!newNameAndState[1].equals("Green") && !newNameAndState[1].equals("Red") && !newNameAndState[1].equals("Yellow")) {
            throw new IllegalArgumentException("올바른 상태가 아닙니다");
        }
        student.setStudentName(newNameAndState[0]);
        student.setStudentState(newNameAndState[1]);
        System.out.println("====[업데이트 된 정보]====\n");
        System.out.println("[이름]: " + student.getStudentName() + "\n[상태]: " + student.getStudentState());
        System.out.println("====업데이트 완료====");
    }

    // 상태별 수강생 조회 --> 김창민
    private static void inquireStudentByState() {
        if (studentStore.isEmpty()) {
            System.out.println("수강생이 없습니다");
            return;
        }
        Set<String> keys = studentStore.keySet();
        List<String> keyList = new ArrayList<>(keys);
        Collections.sort(keyList);
        ArrayList<Student> greenStu = new ArrayList<>();
        ArrayList<Student> redStu = new ArrayList<>();
        ArrayList<Student> yellowStu = new ArrayList<>();
        for (String key : keyList) {
            if (studentStore.get(key).getStudentState().equals("Green")) {
                greenStu.add(studentStore.get(key));
            } else if (studentStore.get(key).getStudentState().equals("Red")) {
                redStu.add(studentStore.get(key));
            } else {
                yellowStu.add(studentStore.get(key));
            }
        }

        System.out.println("====Green 상태 학생====");
        for (Student std : greenStu) {
            System.out.print("[이름]: " + std.getStudentName() + " [번호]" + std.getStudentId());
        }
        System.out.println();
        System.out.println("====Red 상태 학생====");
        for (Student std : redStu) {
            System.out.print("[이름]: " + std.getStudentName() + " [번호]" + std.getStudentId());
        }
        System.out.println();
        System.out.println("====Yellow 상태 학생====");
        for (Student std : yellowStu) {
            System.out.print("[이름]: " + std.getStudentName() + " [번호]" + std.getStudentId());
        }
    }

    // 수강생 삭제 --> 김창민
    private static void deleteStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        if (studentStore.isEmpty()) {
            System.out.println("수강생이 없습니다");
            return;
        }
        printStudentInfo();  // 기능 구현

        sc.nextLine();//개행문자 날리기
        System.out.print("삭제 학생의 고유번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentStore.get(useKey);

        if (student == null) throw new NullPointerException("존재하지않는 학생입니다.");

        studentStore.remove(useKey);
        if (!studentStore.containsKey(useKey))
            System.out.println("삭제 완료되었습니다.");
    }
}
