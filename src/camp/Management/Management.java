package camp.Management;

import camp.model.Student;
import camp.model.StudentMap;
import camp.model.SubjectMap;

public abstract class Management {

    public boolean checkStudentId(Student student) {
        if (student == null) {
            System.out.println("존재하지 않는 학생입니다.");
            return false;
        }
        return true;
    }

    abstract void create(StudentMap student, SubjectMap subjectMap);
    abstract void update();

}
