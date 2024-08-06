package camp.Management;

import camp.CampManagementApplication;
import camp.model.*;
import camp.display.ScoreDisplay;

import java.util.*;


public class ScoreManagement  {

    Scanner sc = new Scanner(System.in);

    public void create(StudentManagement studentManagement, StudentMap studentMap, SubjectMap subjectMap, ScoreMap scoreMap){
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


/*
    public void create() {
        try {
            String studentId = CheckScoreManagement.checkStudentId(); // 관리할 수강생 고유 번호
            String subjectId = CheckScoreManagement.checkSubjectId(studentId); // 등록할 과목 고유 번호

            System.out.println("등록할 시험 회차를 입력하시오...");
            String index = sc.next();
            System.out.println("점수를 입력하시오...");
            String score = sc.next();

            Map<String, Score> inner = new HashMap<>();

            if (!scoreStore.containsKey(studentId) || !scoreStore.get(studentId).containsKey(subjectId)) {
                inner.put(subjectId, new Score(index, score));
                scoreStore.put(studentId, inner);
            } else {
                scoreStore.get(studentId).get(subjectId).setScores(index, score);
            }
            System.out.println("\n점수 등록 성공!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 수강생의 과목별 회차 점수 수정 --> 김민주

    public void update() {
        String studentId = CheckScoreManagement.checkStudentId(); // 관리할 수강생 고유 번호
        if (!scoreStore.containsKey(studentId)) {
            System.out.println("등록된 점수가 없는 학생입니다");
            return;
        }

        String subjectId = CheckScoreManagement.checkSubjectId(studentId); // 등록할 과목 고유 번호
        if (!scoreStore.get(studentId).containsKey(subjectId)) {
            System.out.println("등록된 점수가 없는 학생입니다");
            return;
        }

        ScoreDisplay.printsScoreInfoByStudentId(studentId);
        System.out.println("등록할 시험 회차를 입력하시오...");
        String index = sc.next();
        System.out.println("점수를 입력하시오...");
        String score = sc.next();

        int indexInt; //번호
        int ScoreInt; //점수

        // 인덱스 예외처리
        try {
            indexInt = Integer.parseInt(index) - 1;
            ScoreInt = Integer.parseInt(score);

            if (indexInt < 0 || indexInt > 9 || ScoreInt < 0 || ScoreInt > 100) {
                throw new NumberFormatException();
            }

            int[] scores = scoreStore.get(studentId).get(subjectId).getScores();

            if (scores[indexInt] != -1) {
                scores[indexInt] = ScoreInt;
            } else {
                System.out.println(index + "회차의 점수는 이미 등록되어 있습니다");
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닌 값이 입력됨");
        }
    }

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
