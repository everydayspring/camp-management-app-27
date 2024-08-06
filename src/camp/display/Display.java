package camp.display;

import camp.model.ScoreMap;
import camp.model.StudentMap;
import camp.model.SubjectMap;

public interface Display {

    void display(StudentMap studentMap, SubjectMap subjectMap);
    void display(ScoreMap scoreMap, StudentMap studentMap, SubjectMap subjectMap);
    void inquireAll();
    void inquireByCon();
    void print();


}
