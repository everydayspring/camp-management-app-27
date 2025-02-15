package camp.display;

import camp.Management.StudentManagement;
import camp.model.*;

import java.util.*;

public class StudentDisplay {
    StudentManagement studentManagement;
    public Scanner sc;

    public StudentDisplay() {
        this.studentManagement = new StudentManagement();
        this.sc = new Scanner(System.in);
    }

    // 수강생 관리 메뉴
    public void display(StudentMap studentMap, SubjectMap subjectMap) {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 수정");
            System.out.println("4. 상태별 수강생 목록 조회");
            System.out.println("5. 수강생 삭제");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요 : ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> studentManagement.create(studentMap, subjectMap); // 수강생 등록
                case 2 -> inquireAll(studentMap, subjectMap); // 수강생 목록 조회
                case 3 -> studentManagement.update(studentMap); // 수강생 정보 수정
                case 4 -> inquireByCon(studentMap); // 상태별 수강생 목록 조회
                case 5 -> studentManagement.deleteStudent(studentMap); // 수강생 삭제
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 조회
    private void inquireAll(StudentMap studentMap, SubjectMap subjectMap) {
        // 등록된 수강생이 있는지 확인
        if (studentMap.checkEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
            return;
        }

        // 조회할 수강생 고유 번호 확인
        studentMap.printStudentInfo();
        sc.nextLine();
        System.out.print("조회할 수강생의 고유 번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentMap.getStudent(useKey);

        if (!studentManagement.checkStudentId(student)) return;

        ArrayList<String> viewSubject = student.getSubjectList(); // 과목 아이디가 저장되어있음
        ArrayList<String> viewMandatory = new ArrayList<>(); // 필수 과목 리스트
        ArrayList<String> viewOptional = new ArrayList<>(); // 선택 과목 리스트

        // 과목 종류 나눠서 세팅
        for (String val : viewSubject) {
            Subject useSubject = subjectMap.getSubject(val);

            if (subjectMap.check_MANDATORY(useSubject)) {
                viewMandatory.add(useSubject.getSubjectName());
            } else if (subjectMap.check_CHOICE(useSubject)) {
                viewOptional.add(useSubject.getSubjectName());
            }
        }

        System.out.println("==============조회 성공===============");
        System.out.println("[이름]: " + student.getStudentName() + " [번호]: " + useKey + " [상태]: " + student.getStudentState());

        StringBuilder sb = new StringBuilder();
        sb.append("[필수 과목] :");
        for (String vM : viewMandatory) {
            sb.append(vM + ", ");
        }
        sb.deleteCharAt(sb.length() - 1);// 공백 삭제
        sb.deleteCharAt(sb.length() - 1);// , 삭제
        sb.append("\n");
        sb.append("[선택 과목] :");
        for (String vO : viewOptional) {
            sb.append(vO + ", ");
        }
        sb.deleteCharAt(sb.length() - 1);// 공백 삭제
        sb.deleteCharAt(sb.length() - 1);// , 삭제
        System.out.println(sb.toString());
    }

    // 상태별 수강생 조회
    private void inquireByCon(StudentMap studentMap) {
        if (studentMap.checkEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
            return;
        }

        Set<String> keys = studentMap.getKeys();
        List<String> keyList = new ArrayList<>(keys);
        Collections.sort(keyList);
        ArrayList<Student> greenStu = new ArrayList<>();
        ArrayList<Student> redStu = new ArrayList<>();
        ArrayList<Student> yellowStu = new ArrayList<>();

        for (String key : keyList) {
            if (studentMap.checkState(key, "Green")) {
                greenStu.add(studentMap.getStudent(key));
            } else if (studentMap.checkState(key, "Red")) {
                redStu.add(studentMap.getStudent(key));
            } else {
                yellowStu.add(studentMap.getStudent(key));
            }
        }

        System.out.println("====Green 상태 수강생====");
        print(greenStu);
        System.out.println();

        System.out.println("====Red 상태 수강생====");
        print(redStu);
        System.out.println();

        System.out.println("====Yellow 상태 수강생====");
        print(yellowStu);
        System.out.println();
    }

    private void print(ArrayList<Student> stu) {
        for (Student std : stu) {
            System.out.println(std.getStudentId() + " : " + std.getStudentName());
        }
    }
}