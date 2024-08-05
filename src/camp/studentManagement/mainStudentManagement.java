package camp.studentManagement;

import camp.model.Student;
import camp.model.Subject;

import java.util.*;

import static camp.CampManagementApplication.*;
import static camp.storeManagement.stores.*;
import static camp.studentManagement.checkStudentManagement.*;
import static camp.studentManagement.updateStudentManagement.*;


// Student 관련 UI, 비즈니스 로직 분리

public class mainStudentManagement {
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

    // 수강생 목록 조회 --> 김창민
    private static void inquireStudent() {
        if (!checkStudentStore()) return;

        printStudentInfo();
        sc.nextLine();//개행문자 날리기
        System.out.print("조회 학생의 고유번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentStore.get(useKey);

        if(!checkStudentId(student)) return;


        ArrayList<String> viewSubject = studentStore.get(useKey).getSubjectList();//과목 아이디가 저장되어있음
        ArrayList<String> viewMandatory = new ArrayList<>(); // 필수 과목 리스트
        ArrayList<String> viewOptional = new ArrayList<>(); // 선택 과목 리스트

        // 과목 종류 나눠서 세팅
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

    // 상태별 수강생 조회 --> 김창민
    private static void inquireStudentByState() {
        if (!checkStudentStore()) return;

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
        printInquireStudentByState(greenStu);
        System.out.println();

        System.out.println("====Red 상태 학생====");
        printInquireStudentByState(redStu);
        System.out.println();

        System.out.println("====Yellow 상태 학생====");
        printInquireStudentByState(yellowStu);
        System.out.println();
    }

    // 중복 출력 기능 메소드화
    private static void printInquireStudentByState(ArrayList<Student> stu) {
        for (Student std : stu) {
            System.out.println(std.getStudentId() + " : " + std.getStudentName());
        }
    }
}