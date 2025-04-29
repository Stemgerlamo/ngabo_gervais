package Nursel;

import java.util.ArrayList;
import java.util.List;

public abstract class NurseryClass {
    protected String classId;
    protected String className;
    protected int maxCapacity;
    protected Teacher assignedTeacher;
    protected List<Student> students;
    protected List<String> activities;

    public NurseryClass(String classId, String className, int maxCapacity) {
        this.classId = classId;
        this.className = className;
        this.maxCapacity = maxCapacity;
        this.students = new ArrayList<>();
        this.activities = new ArrayList<>();
    }

    // Abstract methods
    public abstract boolean enrollStudent(Student student);
    public abstract String trackProgress();
    public abstract boolean assignTeacher(Teacher teacher);

    // Common methods
    public boolean conductActivity(String activityName) {
        if (activityName != null && !activityName.isEmpty()) {
            activities.add(activityName);
            return true;
        }
        return false;
    }

    public String generateClassReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== ").append(className).append(" Report ===\n");
        report.append("Class ID: ").append(classId).append("\n");
        report.append("Teacher: ").append(assignedTeacher != null ? assignedTeacher.getTeacherName() : "Not assigned").append("\n");
        report.append("Students Enrolled: ").append(students.size()).append("/").append(maxCapacity).append("\n");
        report.append("Progress: ").append(trackProgress()).append("\n");

        report.append("\nRecent Activities:\n");
        if (activities.isEmpty()) {
            report.append("No activities recorded yet\n");
        } else {
            for (String activity : activities) {
                report.append("- ").append(activity).append("\n");
            }
        }

        report.append("\nStudent List:\n");
        if (students.isEmpty()) {
            report.append("No students enrolled\n");
        } else {
            for (Student student : students) {
                report.append("- ").append(student.getStudentName())
                        .append(" (Age: ").append(student.getAge()).append(")\n");
            }
        }

        return report.toString();
    }

    // Getters
    public String getClassId() { return classId; }
    public String getClassName() { return className; }
    public int getMaxCapacity() { return maxCapacity; }
    public Teacher getAssignedTeacher() { return assignedTeacher; }
    public List<Student> getStudents() { return students; }
    public List<String> getActivities() { return activities; }
}
