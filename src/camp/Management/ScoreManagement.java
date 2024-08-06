package camp.Management;

import camp.CampManagementApplication;
import camp.model.*;
import camp.display.ScoreDisplay;

import java.util.*;


public class ScoreManagement  {

    Scanner sc = new Scanner(System.in);

    public void create(StudentManagement studentManagement, StudentMap studentMap, ScoreMap scoreMap){
        System.out.println("==============점수 등록==============");

        if (studentMap.checkEmpty()) {
            System.out.println("학생이 없습니다.");
            return;
        }

        studentMap.printStudentInfo();
        System.out.print("점수를 등록할 수강생의 고유 번호를 입력하세요 : ");
        String studentId = sc.next();
        Student student = studentMap.getStudent(studentId);
        if (!studentManagement.checkStudentId(student)) {
            System.out.println("============점수 등록 실패============");
            return;
        }

        studentMap.printSubjectInfo();
        System.out.print("점수를 등록할 과목의 고유 번호를 입력하세요 : ");
        String subjectId = sc.next();
        if(!studentManagement.checkSubjectId(student, subjectId)) {
            System.out.println("============점수 등록 실패============");
            return;
        }

        System.out.print("등록할 시험 회차를 입력하세요 : ");
        String index = sc.next();
        if(!checkTestIndex(index)) {
            System.out.println("============점수 등록 실패============");
            return;
        }

        System.out.print("점수를 입력하세요 : ");
        String score = sc.next();
        if(!checkTestScore(score)) {
            System.out.println("============점수 등록 실패============");
            return;
        }

        scoreMap.setScoreStore(studentId, subjectId, index, score);
    }

    public void update(StudentMap studentMap, ScoreMap scoreMap){
        System.out.println("==============점수 수정==============");

        if (studentMap.checkEmpty()) {
            System.out.println("학생이 없습니다.");
            return;
        }

        studentMap.printStudentInfo();
        System.out.print("점수를 수정할 수강생의 고유 번호를 입력하세요 : ");
        String studentId = sc.next();
        Map<String, Score> innerMap = scoreMap.getStudent(studentId);
        if (!checkStudentId(innerMap)) {
            System.out.println("============점수 수정 실패============");
            return;
        }

        studentMap.printSubjectInfo();
        System.out.print("점수를 수정할 과목의 고유 번호를 입력하세요 : ");
        String subjectId = sc.next();
        if(innerMap.get(subjectId) == null) {
            System.out.println("등록된 점수가 없는 과목입니다");
            System.out.println("============점수 수정 실패============");
            return;
        }

        System.out.print("수정할 시험 회차를 입력하세요 : ");
        String index = sc.next();
        if(!checkTestIndex(index)) {
            System.out.println("============점수 수정 실패============");
            return;
        }

        System.out.print("점수를 입력하세요 : ");
        String score = sc.next();
        if(!checkTestScore(score)) {
            System.out.println("============점수 수정 실패============");
            return;
        }

        scoreMap.updateScoreStore(studentId, subjectId, index, score);
    }

    public boolean checkTestIndex(String index){
        try{
            if(Integer.parseInt(index) < 1 || Integer.parseInt(index) > 10){
                System.out.println("* 유효하지 않은 시험 회차 *");
                return false;
            }
        } catch (NumberFormatException e){
            System.out.println("* 유효하지 않은 시험 회차 *");
            return false;
        }
        return true;
    }

    public boolean checkTestScore(String index){
        try{
            if(Integer.parseInt(index) < 1 || Integer.parseInt(index) > 100){
                System.out.println("* 유효하지 않은 시험 점수 *");
                return false;
            }
        } catch (NumberFormatException e){
            System.out.println("* 유효하지 않은 시험 점수 *");
            return false;
        }
        return true;
    }

    public boolean checkStudentId(Map<String, Score> innerMap) {
        if (innerMap == null) {
            System.out.println("등록된 점수가 없는 학생입니다");
            return false;
        }
        return true;
    }


/*
    // 수강중인 과목 고유 번호 입력값 검증
    public static String checkSubjectId(String studentId) {
        CampManagementApplication.printSubjectInfoByStudentId(studentId);
        Student student = studentStore.get(studentId);
        ArrayList<String> scores = student.getSubjectList();

        System.out.print("\n관리할 과목의 고유 번호를 입력하세요 (ex. SU1) : ");
        String subjectId = sc.next();  //SU1
        try {
            if (!scores.contains(subjectId)) {
                throw new IllegalArgumentException("선택한 수강생이 수강중인 과목이 아닙니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return subjectId;
    }
 */
}
