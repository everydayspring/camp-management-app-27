package camp.display;

//import camp.Management.ScoreManagement;
import camp.Management.StudentManagement;
import camp.model.ScoreMap;
import camp.model.Student;
import camp.model.StudentMap;
import camp.model.SubjectMap;

import java.util.*;


public class MainDisplay {
    //여기서 ScoreDislpay 두개 만들고
    public static Scanner sc = new Scanner(System.in);
    // 메인 메뉴
    public static void displayMainView(StudentMap students, SubjectMap subjects, ScoreMap scores) throws InterruptedException { // 메인에 둬야함
        StudentDisplay studentDisplay = new StudentDisplay();
        ScoreDisplay scoreDisplay = new ScoreDisplay();
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> studentDisplay.display(students,subjects); // 수강생 관리
                case 2 -> scoreDisplay.display(scores,students,subjects); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    // 전체 과목 리스트 출력 --> 김창민
    public static void printSubjectInfo() {
        System.out.println("=====   수강 가능한 과목 리스트 입니다.  =====");
        System.out.println("=======================================");
        System.out.println("=====         필수 과목             =====");
        System.out.println("=====         1. Java             =====");
        System.out.println("=====         2. 객체지향           =====");
        System.out.println("=====         3. Spring           =====");
        System.out.println("=====         4. JPA              =====");
        System.out.println("=====         5. MySQL            =====");
        System.out.println("=======================================");
        System.out.println("=====         선택 과목             =====");
        System.out.println("=====         1. 디자인 패턴         =====");
        System.out.println("=====         2. Spring Security  =====");
        System.out.println("=====         3. Redis            =====");
        System.out.println("=====         4. MongoDB          =====");
        System.out.println("=======================================");
    }


}
