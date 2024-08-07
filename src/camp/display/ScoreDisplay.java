package camp.display;

import camp.Management.ScoreManagement;
import camp.Management.StudentManagement;
import camp.model.*;

import java.util.*;

public class ScoreDisplay {
    ScoreManagement scoreManagement;
    StudentManagement studentManagement;
    public Scanner sc;

    public ScoreDisplay() {
        this.studentManagement = new StudentManagement();
        this.scoreManagement = new ScoreManagement();
        this.sc = new Scanner(System.in);
    }

    // 점수 관리 메뉴
    public void display(StudentMap studentMap, SubjectMap subjectMap, ScoreMap scoreMap) {
        boolean flag = true;
        while (flag) {
            System.out.println("===================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 특정 과목 평균 등급 조회");
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요 : ");
            int input = sc.nextInt();

            switch (input) {
                case 1 ->
                        scoreManagement.create(studentManagement, studentMap, subjectMap, scoreMap); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> scoreManagement.update(studentMap, subjectMap, scoreMap); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireAll(studentMap, subjectMap, scoreMap); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireByCon(studentMap, subjectMap, scoreMap); // 수강생의 특정 과목 평균 등급 조회
                case 5 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 회차별 등급 조회
    private void inquireAll(StudentMap studentMap, SubjectMap subjectMap, ScoreMap scoreMap) {
        System.out.println("===========회차별 등급 조회===========");

        // 등록된 수강생이 있는지 확인
        if (studentMap.checkEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
            return;
        }

        // 조회할 수강생 고유 번호 확인
        studentMap.printStudentInfo();
        System.out.print("점수를 조회할 수강생의 고유 번호를 입력하세요 : ");
        String studentId = sc.next();
        Map<String, Score> innerMap = scoreMap.getStudent(studentId);
        if (!scoreManagement.checkStudentId(innerMap)) {
            System.out.println("============점수 조회 실패============");
            return;
        }

        // 조회할 과목 고유 번호 확인
        subjectMap.printSubjectInfoByStudentId(studentMap, studentId);
        System.out.print("점수를 조회할 과목의 고유 번호를 입력하세요 : ");
        String subjectId = sc.next();
        if (innerMap.get(subjectId) == null) {
            System.out.println("조회할 점수가 없는 과목입니다");
            System.out.println("============점수 조회 실패============");
            return;
        }

        // 회차별 등급 출력
        int[] scores = innerMap.get(subjectId).getScores();
        System.out.println("============ " + studentId + " - " + subjectId + " ============");
        for (int i = 0; i < scores.length; i++) {
            String grade = "";
            if (scores[i] != -1) {
                if (subjectMap.checkSubjectType(subjectId)) {
                    grade = subjectMap.gradeMandatory(scores[i]);
                } else {
                    grade = subjectMap.gradeChoice(scores[i]);
                }
                System.out.println(i + 1 + "회차 : " + grade);
            }
        }
    }

    // 과목 평균 등급 조회
    private void inquireByCon(StudentMap studentMap, SubjectMap subjectMap, ScoreMap scoreMap) {
        System.out.println("============평균 등급 조회============");

        // 등록된 수강생이 있는지 확인
        if (studentMap.checkEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
            return;
        }

        // 조회할 수강생 고유 번호 확인
        studentMap.printStudentInfo();
        System.out.print("평균 등급을 조회할 수강생의 고유 번호를 입력하세요 : ");
        String studentId = sc.next();
        Map<String, Score> innerMap = scoreMap.getStudent(studentId);
        if (!scoreManagement.checkStudentId(innerMap)) {
            System.out.println("============등급 조회 실패============");
            return;
        }

        // 조회할 과목 고유 번호 확인
        subjectMap.printSubjectInfoByStudentId(studentMap, studentId);
        System.out.print("평균 등급을 과목의 고유 번호를 입력하세요 : ");
        String subjectId = sc.next();
        if (innerMap.get(subjectId) == null) {
            System.out.println("등록된 점수가 없는 과목입니다");
            System.out.println("============등급 조회 실패============");
            return;
        }

        // 과목 평균값 연산
        int[] scores = innerMap.get(subjectId).getScores();
        System.out.println("============ " + studentId + " - " + subjectId + " ============");
        String grade = "";
        int scoreCount = 0;
        int totalScore = 0;

        for (int score : scores) {
            if (score != -1) {
                totalScore += score;
                scoreCount++;
            }
        }

        // 과목 평균 등급 출력
        if (subjectMap.checkSubjectType(subjectId)) {
            grade = subjectMap.gradeMandatory(totalScore / scoreCount);
        } else {
            grade = subjectMap.gradeChoice(totalScore / scoreCount);
        }
        System.out.println("평균등급 : " + grade);
    }
}