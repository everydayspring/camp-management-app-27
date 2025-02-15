package camp.Management;

import camp.model.Student;
import camp.model.StudentMap;
import camp.model.SubjectMap;

import java.util.*;

public class StudentManagement {
    public static Scanner sc = new Scanner(System.in);

    // 수강생 등록
    public void create(StudentMap studentMap, SubjectMap subjectMap) {
        System.out.println("\n수강생을 등록합니다...");

        String studentName = getStudentName();
        if (studentName == null) return;

        String studentState = getStudentState();
        if (studentState == null) return;

        ArrayList<String> getSubject = getSubjects(subjectMap);
        if (getSubject == null) return;

        storeStudent(studentMap, studentName, studentState, getSubject);
    }

    // create 기능 세부화 1.
    private String getStudentName() {
        try {
            System.out.print("수강생 이름 입력: ");
            String studentName = sc.next(); // 이름
            if (studentName.length() > 10)
                throw new Exception("이름이 너무 깁니다.");
            return studentName;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // create 기능 세부화 2.
    private String getStudentState() {
        try {
            System.out.print("수강생 상태 입력(Green,Red,Yellow): ");
            String studentState = sc.next();
            if (!cheackStatus(studentState)) throw new IllegalArgumentException("올바른 상태가 아닙니다");
            return studentState;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // create 기능 세부화 3.
    private ArrayList<String> getSubjects(SubjectMap subjectMap) {
        ArrayList<String> getSubject = new ArrayList<>();
        printSubjectInfo(); // 과목 출력
        sc.nextLine(); // 개행문자 날리기

        // 필수 과목 받기
        System.out.println("수강하실 필수 과목의 번호를 입력해 주세요 (필수 : 3개 이상)(띄어쓰기로 구분)");
        String[] mandatorySubjects = sc.nextLine().split(" ");
        Set<String> mandatorySet = new HashSet<>(Arrays.asList(mandatorySubjects));
        try {
            int mandatorySize = mandatorySet.size();
            if (mandatorySize < 3) throw new IllegalArgumentException("필수 과목 개수가 부족합니다.");
            for (String val : mandatorySet) {
                if (Integer.parseInt(val) < 1 || Integer.parseInt(val) > 5) {
                    throw new IllegalArgumentException("필요 과목이 아닙니다.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }

        // 선택 과목 받기
        System.out.println("수강하실 선택 과목의 번호를 입력해 주세요 (선택 : 2개 이상)(띄어쓰기로 구분)");
        String[] optionalSubjects = sc.nextLine().split(" ");
        Set<String> optionalSet = new HashSet<>(Arrays.asList(optionalSubjects));
        try {
            int optionalSize = optionalSet.size();
            if (optionalSize < 2) throw new IllegalArgumentException("선택 과목 개수가 부족합니다.");
            for (String val : optionalSet) {
                if (Integer.parseInt(val) < 1 || Integer.parseInt(val) > 4) {
                    throw new IllegalArgumentException("선택 과목이 아닙니다.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }

        // 필수 과목 저장
        for (String val : mandatorySet) {
            val = subjectMap.makeSequence(val);
            subjectMap.makeManSubject(val, getSubject);
        }

        // 선택 과목 저장
        for (String val : optionalSet) {
            int useVal = Integer.parseInt(val) + 5;
            val = subjectMap.makeSequence(Integer.toString(useVal));
            subjectMap.makeSubSubject(val, getSubject);
        }
        return getSubject;
    }

    // create 기능 세부화 4.
    private void storeStudent(StudentMap studentMap, String studentName, String studentState, ArrayList<String> getSubject) {
        String studentId = studentMap.studentSequence(); // 고유 번호
        studentMap.set_Store(studentId, studentName, studentState, getSubject); // 이름이랑 과목코드 리스트를 담은 객체 생성
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 이름, 상태 수정
    public void update(StudentMap studentMap) {
        if (studentMap.checkEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
            return;
        }
        studentMap.printStudentInfo();

        System.out.print("조회할 수강생의 고유번호를 입력하세요 : ");
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
            System.out.println("등록된 수강생이 없습니다.");
            return;
        }

        studentMap.printStudentInfo();  // 기능 구현

        System.out.print("삭제할 수강생의 고유 번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentMap.getStudent(useKey);

        if (!checkStudentId(student)) return;

        studentMap.deleteKey(useKey);
    }

    // 입력된 상태 검증
    public boolean cheackStatus(String studentState) {
        return studentState.equals("Green") || studentState.equals("Red") || studentState.equals("Yellow");
    }

    // 수강생 검증
    public boolean checkStudentId(Student student) {
        if (student == null) {
            System.out.println("존재하지 않는 수강생입니다.");
            return false;
        }
        return true;
    }

    // 수강중인 과목 검증
    public boolean checkSubjectId(Student student, String subjectId) {
        if (!student.getSubjectList().contains(subjectId)) {
            System.out.println("* 수강중인 과목이 아닙니다");
            return false;
        }
        return true;
    }

    // 전체 과목 리스트 출력
    private void printSubjectInfo() {
        System.out.println("=====   수강 가능한 과목 리스트 입니다.  =====");
        System.out.println("=======================================");
        System.out.println("=====         필수 과목            ======");
        System.out.println("=====         1. Java             =====");
        System.out.println("=====         2. 객체지향           =====");
        System.out.println("=====         3. Spring           =====");
        System.out.println("=====         4. JPA              =====");
        System.out.println("=====         5. MySQL            =====");
        System.out.println("=======================================");
        System.out.println("=====         선택 과목             =====");
        System.out.println("=====         1. 디자인 패턴        =====");
        System.out.println("=====         2. Spring Security  =====");
        System.out.println("=====         3. Redis            =====");
        System.out.println("=====         4. MongoDB          =====");
        System.out.println("=======================================");
    }
}