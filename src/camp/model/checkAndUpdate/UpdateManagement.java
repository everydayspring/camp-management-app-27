package camp.model.checkAndUpdate;

import camp.model.Student;

public abstract class UpdateManagement {

    public boolean checkStudentId(Student student) {
        if (student == null) {
            System.out.println("존재하지 않는 학생입니다.");
            return false;
        }
        return true;
    }

    abstract void create();
    abstract void update();

}
