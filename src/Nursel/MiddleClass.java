package Nursel;

public class MiddleClass extends NurseryClass {
    public MiddleClass(String classId) {
        super(classId, "Middle Class", 20);
    }

    @Override
    public boolean enrollStudent(Student student) {
        if (student == null) {
            System.out.println("Error: Student cannot be null");
            return false;
        }

        if (student.getAge() < 3 || student.getAge() > 4) {
            System.out.println("Error: Middle Class only accepts students aged 3-4");
            return false;
        }

        if (students.size() >= maxCapacity) {
            System.out.println("Error: Middle Class has reached maximum capacity");
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
        return "Focusing on language development and basic counting";
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
}
