package camp.scoreManagement;

import camp.CampManagementApplication;
import camp.model.Student;

import java.util.ArrayList;

import static camp.CampManagementApplication.*;
import static camp.storeManagement.Stores.*;

public class CheckScoreManagement {
    // 수강생 고유 번호 입력값 검증
    public static String checkStudentId() {
        printStudentInfo();

        System.out.print("\n관리할 수강생의 고유 번호를 입력하세요 (ex. ST) : ");
        String studentId = sc.next();
        try {
            if (!scoreStore.containsKey(studentId))
                throw new IllegalArgumentException("해당 학생은 존재하지 않습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return studentId;
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
}
