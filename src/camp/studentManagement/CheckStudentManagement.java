package camp.studentManagement;

import camp.model.Student;

import static camp.storeManagement.Stores.studentStore;

public class CheckStudentManagement {
    // 수강생 존재 검증
    public static boolean checkStudentId(Student student) {
        if (student == null) {
            System.out.println("존재하지 않는 학생입니다.");
            return false;
        }
        return true;
    }

    // 중복 studentStore 검증 기능 메소드화
    public static boolean checkStudentStore() {
        System.out.println("\n수강생 목록을 조회합니다...");

        if (studentStore.isEmpty()) {
            System.out.println("수강생이 없습니다");
            return false;
        }
        return true;
    }

    // 중복 Status 검증 기능 메소드화
    public static boolean cheackStatus(String studentState) {
        return studentState.equals("Green") || studentState.equals("Red") || studentState.equals("Yellow");
    }
}
