package camp.model.checkAndUpdate;

import camp.CampManagementApplication;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;


public class UpdateStudentManagement extends UpdateManagement{
    // 수강생 등록
    @Override
    public void create() {
        String studentName;
        String studentId;
        String studentState;
        ArrayList<String> getSubject = new ArrayList<>(); //수강하는 과목코드를 저장할 리스트

        // 수강생 이름 등록, 이름 길이가 10이 넘으면 예외처리
        System.out.println("\n수강생을 등록합니다...");
        try {
            System.out.print("수강생 이름 입력: ");
            studentName = sc.next();                     //이름
            if (studentName.length() > 10)
                throw (new Exception("이름이 너무 깁니다."));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // 상태 저장
        try {
            System.out.print("수강생 상태 입력(Green,Red,Yellow): ");
            studentState = sc.next();
            if (!cheackStatus(studentState)) throw new IllegalArgumentException("올바른 상태가 아닙니다");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // 과목 입력
        CampManagementApplication.printSubjectInfo();//과목 출력
        sc.nextLine(); //개행문자 날리기
        /*필수 과목 받기*/
        System.out.println("수강하실 필수 과목의 번호를 입력해 주세요 (필수 : 3개 이상)(띄어쓰기로 구분)");
        String[] mandatorySubjects = sc.nextLine().split(" ");
        Set<String> mandatorySet = new HashSet<>(Arrays.asList(mandatorySubjects));
        try {
            int mandatorySize = mandatorySet.size();
            if (mandatorySize < 3) throw new IllegalArgumentException("필수 과목 개수가 부족합니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        /*선택 과목 받기*/
        System.out.println("수강하실 선택 과목의 번호를 입력해 주세요 (선택 : 2개 이상)(띄어쓰기로 구분)");
        String[] optionalSubjects = sc.nextLine().split(" ");
        Set<String> optionalSet = new HashSet<>(Arrays.asList(optionalSubjects));
        try {
            int optionalSize = optionalSet.size();
            if (optionalSize < 2) throw new IllegalArgumentException("선택 과목 개수가 부족합니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        //필수 과목 저장
        for (String val : mandatorySet) {
            val = INDEX_TYPE_SUBJECT + val;

            makeSubject(val, getSubject, SUBJECT_TYPE_MANDATORY);
        }

        //선택 과목 저장
        for (String val : optionalSet) {
            int useVal = Integer.parseInt(val) + 5;
            val = INDEX_TYPE_SUBJECT + useVal;

            makeSubject(val, getSubject, SUBJECT_TYPE_CHOICE);
        }

        //studentId = sequence(INDEX_TYPE_STUDENT);    //고유번호
        studentId = studentSequence();    //고유번호
        set_Store(studentId, studentName, studentState, getSubject); //이름이랑 과목코드 리스트를 담은 객체 생성
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 정보 수정
    @Override
    public void update() {

        if (!checkStudentStore()) return;
        printStudentInfo();  // 기능 구현

        sc.nextLine();// 개행문자 날리기
        System.out.print("조회 학생의 고유번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentStore.get(useKey);

        if (!checkStudentId(student)) return;

        System.out.println("====[현재 정보]====");
        System.out.println("[이름]: " + student.getStudentName() + "\n[상태]: " + student.getStudentState());
        System.out.println("====[덮어씌울 정보]====\n이름과 상태 순으로 입력하세요\n(상태: Green,Red,Yellow)(띄어쓰기 구분): ");

        String[] newNameAndState = new String[2];
        try {
            newNameAndState = sc.nextLine().split(" ");
            if (newNameAndState.length != 2) {
                throw new IllegalArgumentException("이름과 상태를 모두 입력해야 합니다.");
            }
            if (!cheackStatus(newNameAndState[1])) {
                throw new IllegalArgumentException("올바른 상태가 아닙니다.");
            }
            if (newNameAndState[0].length() > 10) {
                throw new IllegalArgumentException("이름이 너무 깁니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }


        student.setStudentName(newNameAndState[0]);
        student.setStudentState(newNameAndState[1]);
        System.out.println("====[업데이트 된 정보]====\n");
        System.out.println("[이름]: " + student.getStudentName() + "\n[상태]: " + student.getStudentState());
        System.out.println("====업데이트 완료====");
    }

    // 수강생 삭제
    static void deleteStudent() {
        if (!checkStudentStore()) return;
        printStudentInfo();  // 기능 구현

        sc.nextLine();//개행문자 날리기
        System.out.print("삭제 학생의 고유번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentStore.get(useKey);

        if (!checkStudentId(student)) return;

        studentStore.remove(useKey);
        if (!studentStore.containsKey(useKey))
            System.out.println("삭제 완료되었습니다.");
    }

    // 중복 Subject 저장 기능 메소드화
    private static void makeSubject(String val, ArrayList<String> getSubject, String type) {
        Subject useSubject = subjectStore.get(val);
        if (!useSubject.getSubjectType().equals(type)) //타 과목시 예외처리
            throw new IllegalArgumentException();
        getSubject.add(val);
    }

    // 중복 studentStore 검증 기능 메소드화
    public static boolean checkStudentStore() {
        System.out.println("\n수강생 목록을 조회합니다...");

        if (studentStore.isEmpty()) {
            System.out.println("수강생이 없습니다");
            return false;
        }
        return true;
    }

    // 중복 Status 검증 기능 메소드화
    public static boolean cheackStatus(String studentState) {
        return studentState.equals("Green") || studentState.equals("Red") || studentState.equals("Yellow");
    }
}
