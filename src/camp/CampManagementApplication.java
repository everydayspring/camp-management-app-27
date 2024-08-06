package camp;




import camp.model.ScoreMap;
import camp.model.StudentMap;
import camp.model.SubjectMap;
import camp.view.MainDisplay;


// UI관련 Field & Method
public class CampManagementApplication {

    public static void main(String[] args) throws InterruptedException {
        MainDisplay maindisplay = new MainDisplay();

        setInitData();
        maindisplay.displayMainView();
    }

    public static void setInitData(){
        StudentMap students = new StudentMap();
        SubjectMap subjects = new SubjectMap();
        ScoreMap scores = new ScoreMap();
    }

}
