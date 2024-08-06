package camp.Management;


import camp.display.MainDisplay;
import camp.model.Student;
import camp.model.StudentMap;
import camp.model.SubjectMap;

import java.util.*;

public class StudentManagement {

    public static Scanner sc = new Scanner(System.in);

    // 수강생 등록
    public void create(StudentMap students, SubjectMap subjects) {
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
        MainDisplay.printSubjectInfo();//과목 출력
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
        for (String val : mandatorySet) { //1 2 3 입력하면 makeSequence로 SU1로 바궈서 makeManSubject로 넣는거임
            val = subjects.makeSequence(val);
            subjects.makeManSubject(val, getSubject);
        }

        //선택 과목 저장
        for (String val : optionalSet) {
            int useVal = Integer.parseInt(val) + 5;
            val = subjects.makeSequence(Integer.toString(useVal));
            subjects.makeSubSubject(val, getSubject);
        }

        //studentId = sequence(INDEX_TYPE_STUDENT);    //고유번호
        studentId = ((StudentMap)students).studentSequence();    //고유번호
        ((StudentMap)students).set_Store(studentId, studentName, studentState, getSubject); //이름이랑 과목코드 리스트를 담은 객체 생성
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 정보 수정
    public void update(StudentMap studentMap) {

        if (studentMap.checkEmpty()) {
            System.out.println("학생이 없습니다.");
            return;
        }
        studentMap.printStudentInfo();  // 기능 구현

        sc.nextLine();// 개행문자 날리기
        System.out.print("조회 학생의 고유번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentMap.getStudent(useKey);

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
    public void deleteStudent(StudentMap studentMap) {
        if (studentMap.checkEmpty()) {
            System.out.println("학생이 없습니다.");
            return;
        }

        studentMap.printStudentInfo();  // 기능 구현

        sc.nextLine();//개행문자 날리기
        System.out.print("삭제 학생의 고유번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentMap.getStudent(useKey);

        if (!checkStudentId(student)) return;

        studentMap.deleteKey(useKey);
    }

    // 입력된 상태 검증
    public  boolean cheackStatus(String studentState) {
        return studentState.equals("Green") || studentState.equals("Red") || studentState.equals("Yellow");
    }

    // 학생 존재 체크
    public boolean checkStudentId(Student student) {
        if (student == null) {
            System.out.println("존재하지 않는 학생입니다.");
            return false;
        }
        return true;
    }


}
