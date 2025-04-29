package Nursel;

public class Student {
    private String studentId;
    private String studentName;
    private int age;
    private String guardianName;
    private NurseryClass registeredClass;

    public Student(String studentId, String studentName, int age, String guardianName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.guardianName = guardianName;
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public int getAge() { return age; }
    public String getGuardianName() { return guardianName; }
    public NurseryClass getRegisteredClass() { return registeredClass; }

    // Setters
    public void setRegisteredClass(NurseryClass nurseryClass) {
        this.registeredClass = nurseryClass;
    }

    @Override
    public String toString() {
        return studentName + " (Age: " + age + ")";
    }
}
