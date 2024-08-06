package camp;

import camp.display.MainDisplay;
import camp.model.ScoreMap;
import camp.model.StudentMap;
import camp.model.SubjectMap;

public class CampManagementApplication {

    public static void main(String[] args) throws InterruptedException {
        Init();
    }

    public static void Init() throws InterruptedException {
        StudentMap students = new StudentMap();
        SubjectMap subjects = new SubjectMap();
        ScoreMap scores = new ScoreMap();
        MainDisplay.displayMainView(students,subjects,scores);
    }

}
