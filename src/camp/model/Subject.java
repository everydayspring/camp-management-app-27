package camp.model;

public class Subject {
    private final String subjectId;
    private final String subjectName;
    private final String subjectType;

    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    public String getSubjectName() { return subjectName; }

    public String getSubjectType() { return subjectType; }
}
