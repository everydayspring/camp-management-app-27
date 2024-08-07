package camp;

import camp.display.MainDisplay;
import camp.model.*;

public class CampManagementApplication {
    public static void main(String[] args) throws InterruptedException {
        Init();
    }

    public static void Init() throws InterruptedException {
        // 수강생, 과목, 점수 관리 객체 생성
        StudentMap studentMap = new StudentMap();
        SubjectMap subjectMap = new SubjectMap();
        ScoreMap scoreMap = new ScoreMap();

        // 메인 메뉴 호출
        MainDisplay.displayMainView(studentMap,subjectMap,scoreMap);
    }
}