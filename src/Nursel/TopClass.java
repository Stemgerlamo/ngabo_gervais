package Nursel;

import java.time.LocalDate;

public class TopClass extends NurseryClass {
    private LocalDate lastAssessmentDate;

    public TopClass(String classId) {
        super(classId, "Top Class", 25);
    }

    @Override
    public boolean enrollStudent(Student student) {
        if (student == null) {
            System.out.println("Error: Student cannot be null");
            return false;
        }

        if (student.getAge() < 4 || student.getAge() > 5) {
            System.out.println("Error: Top Class only accepts students aged 4-5");
            return false;
        }

        if (students.size() >= maxCapacity) {
            System.out.println("Error: Top Class has reached maximum capacity");
            return false;
        }

        if (student.getRegisteredClass() != null) {
            System.out.println("Error: Student is already registered in another class");
            return false;
        }

        students.add(student);
        student.setRegisteredClass(this);
        return true;
    }

    @Override
    public String trackProgress() {
        return "Preparing for primary school with reading, writing, and arithmetic";
    }

    @Override
    public boolean assignTeacher(Teacher teacher) {
        if (teacher == null) {
            System.out.println("Error: Teacher cannot be null");
            return false;
        }

        this.assignedTeacher = teacher;
        teacher.setAssignedClass(this);
        return true;
    }

    public boolean conductAssessment() {
        lastAssessmentDate = LocalDate.now();
        activities.add("Assessment conducted on " + lastAssessmentDate);
        return true;
    }

    public LocalDate getLastAssessmentDate() {
        return lastAssessmentDate;
    }
}
