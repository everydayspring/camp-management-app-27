package camp.display;

import camp.Management.StudentManagement;
import camp.model.*;

import java.util.*;

import static camp.Management.StudentManagement.*;


public class StudentDisplay  {
    StudentManagement studentManagement = new StudentManagement();

    public void display(StudentMap students, SubjectMap subjects) {

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
                case 1 -> studentManagement.create(students,subjects); // 수강생 등록
                case 2 -> inquireAll(students,subjects); // 수강생 목록 조회
                case 3 -> studentManagement.update(students); // 수강생 정보 수정
                case 4 -> inquireByCon(students); // 상태별 수강생 목록 조회
                case 5 -> studentManagement.deleteStudent(students); // 수강생 삭제
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }


    }


    public void display(ScoreMap scoreMap, StudentMap studentMap, SubjectMap subjectMap) {
    }

    //displayStudentView
    //inquireStudent 전체 수강생
    public void inquireAll(StudentMap studentMap,SubjectMap subjectMap) {

        if (!studentManagement.checkStudentStore(studentMap)) return;

        studentMap.printStudentInfo();
        sc.nextLine();//개행문자 날리기
        System.out.print("조회 학생의 고유번호를 입력하세요 : ");
        String useKey = sc.nextLine();
        Student student = studentMap.getStudent(useKey);

        if (!studentManagement.checkStudentId(student)) return;


        ArrayList<String> viewSubject = student.getSubjectList();//과목 아이디가 저장되어있음
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

    //inquireStudentByState    상태별 수강생 조회

    public void inquireByCon(StudentMap students) {

        if (!studentManagement.checkStudentStore(students)) return;

        Set<String> keys = students.getKeys();
        List<String> keyList = new ArrayList<>(keys);
        Collections.sort(keyList);
        ArrayList<Student> greenStu = new ArrayList<>();
        ArrayList<Student> redStu = new ArrayList<>();
        ArrayList<Student> yellowStu = new ArrayList<>();
        for (String key : keyList) {
            if (students.getterStudensStore().get(key).getStudentState().equals("Green")) {
                greenStu.add(students.getterStudensStore().get(key));
            } else if (students.getterStudensStore().get(key).getStudentState().equals("Red")) {
                redStu.add(students.getterStudensStore().get(key));
            } else {
                yellowStu.add(students.getterStudensStore().get(key));
            }
        }

        System.out.println("====Green 상태 학생====");
        students.print(greenStu);
        System.out.println();

        System.out.println("====Red 상태 학생====");
        students.print(redStu);
        System.out.println();

        System.out.println("====Yellow 상태 학생====");
        students.print(yellowStu);
        System.out.println();

    }

    //printInquireStudentByState 중복 출력 기능 메소드화


}