package Nursel;

public class Teacher {
    private String teacherId;
    private String teacherName;
    private String teacherRole;
    private NurseryClass assignedClass;

    public Teacher(String teacherId, String teacherName, String teacherRole) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherRole = teacherRole;
    }

    // Getters
    public String getTeacherId() { return teacherId; }
    public String getTeacherName() { return teacherName; }
    public String getTeacherRole() { return teacherRole; }
    public NurseryClass getAssignedClass() { return assignedClass; }

    // Setters
    public void setAssignedClass(NurseryClass nurseryClass) {
        this.assignedClass = nurseryClass;
    }

    @Override
    public String toString() {
        return teacherName + " (" + teacherRole + ")";
    }
}
