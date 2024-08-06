package camp;




import camp.model.ScoreMap;
import camp.model.StudentMap;
import camp.model.SubjectMap;
import camp.view.MainDisplay;

import static camp.view.MainDisplay.displayMainView;


// UI관련 Field & Method
public class CampManagementApplication {

    public static void main(String[] args) throws InterruptedException {
        Init();
    }

    public static void Init() throws InterruptedException {
        StudentMap students = new StudentMap();
        SubjectMap subjects = new SubjectMap();
        ScoreMap scores = new ScoreMap();
        MainDisplay maindisplay = new MainDisplay();

        displayMainView(students,subjects,scores);
    }

}
