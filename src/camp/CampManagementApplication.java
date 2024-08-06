package camp;




import camp.Management.ScoreManagement;
import camp.Management.StudentManagement;
import camp.model.ScoreMap;
import camp.model.StudentMap;
import camp.model.SubjectMap;
import camp.display.ScoreDisplay;
import camp.display.StudentDisplay;

import static camp.display.MainDisplay.displayMainView;


// UI관련 Field & Method
public class CampManagementApplication {

    public static void main(String[] args) throws InterruptedException {
        Init();
    }

    public static void Init() throws InterruptedException {
        StudentMap students = new StudentMap();
        SubjectMap subjects = new SubjectMap();
        ScoreMap scores = new ScoreMap();
        displayMainView(students,subjects,scores);
    }

}
