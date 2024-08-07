package camp.Management;

import camp.model.*;

import java.util.*;


public class ScoreManagement {
    Scanner sc = new Scanner(System.in);

    // 점수 등록
    public void create(StudentManagement studentManagement, StudentMap studentMap, SubjectMap subjectMap, ScoreMap scoreMap) {
        System.out.println("==============점수 등록==============");

        // 등록된 수강생이 있는지 확인
        if (studentMap.checkEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
            return;
        }

        // 수강생 고유 번호 확인
        studentMap.printStudentInfo();
        System.out.print("점수를 등록할 수강생의 고유 번호를 입력하세요 : ");
        String studentId = sc.next();
        Student student = studentMap.getStudent(studentId);
        if (!studentManagement.checkStudentId(student)) {
            System.out.println("============점수 등록 실패============");
            return;
        }

        // 과목 고유 번호 확인
        subjectMap.printSubjectInfoByStudentId(studentMap, studentId);
        System.out.print("점수를 등록할 과목의 고유 번호를 입력하세요 : ");
        String subjectId = sc.next();
        if (!studentManagement.checkSubjectId(student, subjectId)) {
            System.out.println("============점수 등록 실패============");
            return;
        }

        // 등록할 시험 회차 확인
        System.out.print("등록할 시험 회차를 입력하세요 : ");
        String index = sc.next();
        if (!checkTestIndex(index)) {
            System.out.println("============점수 등록 실패============");
            return;
        }

        // 등록할 시험 점수 확인
        System.out.print("점수를 입력하세요 : ");
        String score = sc.next();
        if (!checkTestScore(score)) {
            System.out.println("============점수 등록 실패============");
            return;
        }

        // 점수 등록
        scoreMap.setScoreStore(studentId, subjectId, index, score);
    }

    // 점수 수정
    public void update(StudentMap studentMap, SubjectMap subjectMap, ScoreMap scoreMap) {
        System.out.println("==============점수 수정==============");

        // 등록된 수강생이 있는지 확인
        if (studentMap.checkEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
            return;
        }

        // 수강생 고유 번호 확인
        studentMap.printStudentInfo();
        System.out.print("점수를 수정할 수강생의 고유 번호를 입력하세요 : ");
        String studentId = sc.next();
        Map<String, Score> innerMap = scoreMap.getStudent(studentId);
        if (!checkStudentId(innerMap)) {
            System.out.println("============점수 수정 실패============");
            return;
        }

        // 과목 고유 번호 확인
        subjectMap.printSubjectInfoByStudentId(studentMap, studentId);
        System.out.print("점수를 수정할 과목의 고유 번호를 입력하세요 : ");
        String subjectId = sc.next();
        if (innerMap.get(subjectId) == null) {
            System.out.println("등록된 점수가 없는 과목입니다");
            System.out.println("============점수 수정 실패============");
            return;
        }

        // 수정할 시험 회차 확인
        System.out.print("수정할 시험 회차를 입력하세요 : ");
        String index = sc.next();
        if (!checkTestIndex(index)) {
            System.out.println("============점수 수정 실패============");
            return;
        }

        // 수정할 시험 점수 확인
        System.out.print("점수를 입력하세요 : ");
        String score = sc.next();
        if (!checkTestScore(score)) {
            System.out.println("============점수 수정 실패============");
            return;
        }

        // 점수 수정
        scoreMap.updateScoreStore(studentId, subjectId, index, score);
    }

    // 시험 회차 입력값 검증
    public boolean checkTestIndex(String index) {
        try {
            if (Integer.parseInt(index) < 1 || Integer.parseInt(index) > 10) {
                System.out.println("* 유효하지 않은 시험 회차 *");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("* 유효하지 않은 시험 회차 *");
            return false;
        }
        return true;
    }

    // 시험 점수 입력값 검증
    public boolean checkTestScore(String score) {
        try {
            if (Integer.parseInt(score) < 1 || Integer.parseInt(score) > 100) {
                System.out.println("* 유효하지 않은 시험 점수 *");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("* 유효하지 않은 시험 점수 *");
            return false;
        }
        return true;
    }

    // 수강생 점수 등록 여부 검증
    public boolean checkStudentId(Map<String, Score> innerMap) {
        if (innerMap == null) {
            System.out.println("등록된 점수가 없는 수강생입니다");
            return false;
        }
        return true;
    }
}