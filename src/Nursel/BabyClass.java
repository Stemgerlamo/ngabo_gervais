package Nursel;

public class BabyClass extends NurseryClass {
    public BabyClass(String classId) {
        super(classId, "Baby Class", 15);
    }

    @Override
    public boolean enrollStudent(Student student) {
        if (student == null) {
            System.out.println("Error: Student cannot be null");
            return false;
        }

        if (student.getAge() < 2 || student.getAge() > 3) {
            System.out.println("Error: Baby Class only accepts students aged 2-3");
            return false;
        }

        if (students.size() >= maxCapacity) {
            System.out.println("Error: Baby Class has reached maximum capacity");
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
        return "Focusing on motor skills and play-based learning";
    }

    @Override
    public boolean assignTeacher(Teacher teacher) {
        if (teacher == null) {
            System.out.println("Error: Teacher cannot be null");
            return false;
        }

        if (!"Early Childhood Educator".equalsIgnoreCase(teacher.getTeacherRole())) {
            System.out.println("Error: Baby Class requires Early Childhood Educators");
            return false;
        }

        this.assignedTeacher = teacher;
        teacher.setAssignedClass(this);
        return true;
    }
}
