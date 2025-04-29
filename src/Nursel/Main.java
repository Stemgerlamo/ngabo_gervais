package Nursel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<NurseryClass> classes = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$"); // Only letters and spaces

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Nursery School Management System ===");
            System.out.println("1. Create Class");
            System.out.println("2. Add Teacher");
            System.out.println("3. Register Student");
            System.out.println("4. Assign Teacher to Class");
            System.out.println("5. Enroll Student in Class");
            System.out.println("6. Conduct Class Activity");
            System.out.println("7. Generate Class Report");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            try {
                int choice = 0;
                while (true) {
                    String input = scanner.nextLine().trim();
                    try {
                        choice = Integer.parseInt(input);
                        if (choice < 1 || choice > 8) {
                            System.out.println("Invalid option. Please select a number between 1 and 8.");
                            System.out.print("Select an option: ");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                        System.out.print("Select an option: ");
                    }
                }

                switch (choice) {
                    case 1:
                        createClass();
                        break;
                    case 2:
                        addTeacher();
                        break;
                    case 3:
                        registerStudent();
                        break;
                    case 4:
                        assignTeacherToClass();
                        break;
                    case 5:
                        enrollStudentInClass();
                        break;
                    case 6:
                        conductActivity();
                        break;
                    case 7:
                        generateReport();
                        break;
                    case 8:
                        running = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        System.out.println("Exiting Nursery School Management System. Goodbye!");
    }

    private static void createClass() {
        System.out.println("\n=== Create New Class ===");
        System.out.println("1. Baby Class");
        System.out.println("2. Middle Class");
        System.out.println("3. Top Class");

        int type = 0;
        while (true) {
            System.out.print("Select class type: ");
            try {
                type = Integer.parseInt(scanner.nextLine().trim());
                if (type < 1 || type > 3) {
                    System.out.println("Invalid class type. Please select 1, 2, or 3.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        int classId = 0;
        while (true) {
            System.out.print("Enter class ID (must be a number): ");
            try {
                classId = Integer.parseInt(scanner.nextLine().trim());
                if (classId <= 0) {
                    System.out.println("Class ID must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        NurseryClass newClass = null;
        switch (type) {
            case 1:
                newClass = new BabyClass(String.valueOf(classId));
                break;
            case 2:
                newClass = new MiddleClass(String.valueOf(classId));
                break;
            case 3:
                newClass = new TopClass(String.valueOf(classId));
                break;
        }

        classes.add(newClass);
        System.out.println(newClass.getClassName() + " created successfully!");
    }

    private static void addTeacher() {
        System.out.println("\n=== Add New Teacher ===");

        int id = 0;
        while (true) {
            System.out.print("Enter teacher ID (must be a number): ");
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                if (id <= 0) {
                    System.out.println("Teacher ID must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        String name;
        while (true) {
            System.out.print("Enter teacher name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Teacher name cannot be empty. Please enter a valid name.");
                continue;
            }
            if (!NAME_PATTERN.matcher(name).matches()) {
                System.out.println("Teacher name can only contain letters and spaces.");
                continue;
            }
            break;
        }

        String role;
        while (true) {
            System.out.print("Enter teacher role: ");
            role = scanner.nextLine().trim();
            if (role.isEmpty()) {
                System.out.println("Teacher role cannot be empty. Please enter a valid role.");
                continue;
            }
            if (!NAME_PATTERN.matcher(role).matches()) {
                System.out.println("Teacher role can only contain letters and spaces.");
                continue;
            }
            break;
        }

        Teacher teacher = new Teacher(String.valueOf(id), name, role);
        teachers.add(teacher);
        System.out.println("Teacher " + name + " added successfully!");
    }

    private static void registerStudent() {
        System.out.println("\n=== Register New Student ===");

        int id = 0;
        while (true) {
            System.out.print("Enter student ID (must be a number): ");
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                if (id <= 0) {
                    System.out.println("Student ID must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        String name;
        while (true) {
            System.out.print("Enter student name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Student name cannot be empty. Please enter a valid name.");
                continue;
            }
            if (!NAME_PATTERN.matcher(name).matches()) {
                System.out.println("Student name can only contain letters and spaces.");
                continue;
            }
            break;
        }

        int age = 0;
        while (true) {
            System.out.print("Enter student age: ");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age <= 0) {
                    System.out.println("Age must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for age.");
            }
        }

        String guardian;
        while (true) {
            System.out.print("Enter guardian name: ");
            guardian = scanner.nextLine().trim();
            if (guardian.isEmpty()) {
                System.out.println("Guardian name cannot be empty. Please enter a valid name.");
                continue;
            }
            if (!NAME_PATTERN.matcher(guardian).matches()) {
                System.out.println("Guardian name can only contain letters and spaces.");
                continue;
            }
            break;
        }

        Student student = new Student(String.valueOf(id), name, age, guardian);
        students.add(student);
        System.out.println("Student " + name + " registered successfully!");
    }

    private static void assignTeacherToClass() {
        System.out.println("\n=== Assign Teacher to Class ===");

        int teacherId = 0;
        while (true) {
            System.out.print("Enter teacher ID (must be a number): ");
            try {
                teacherId = Integer.parseInt(scanner.nextLine().trim());
                if (teacherId <= 0) {
                    System.out.println("Teacher ID must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        int classId = 0;
        while (true) {
            System.out.print("Enter class ID (must be a number): ");
            try {
                classId = Integer.parseInt(scanner.nextLine().trim());
                if (classId <= 0) {
                    System.out.println("Class ID must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Logic to assign teacher to class
        System.out.println("Teacher " + teacherId + " assigned to class " + classId + " successfully!");
    }

    private static void enrollStudentInClass() {
        System.out.println("\n=== Enroll Student in Class ===");

        int studentId = 0;
        while (true) {
            System.out.print("Enter student ID (must be a number): ");
            try {
                studentId = Integer.parseInt(scanner.nextLine().trim());
                if (studentId <= 0) {
                    System.out.println("Student ID must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        int classId = 0;
        while (true) {
            System.out.print("Enter class ID (must be a number): ");
            try {
                classId = Integer.parseInt(scanner.nextLine().trim());
                if (classId <= 0) {
                    System.out.println("Class ID must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Logic to enroll student in class
        System.out.println("Student " + studentId + " enrolled in class " + classId + " successfully!");
    }

    private static void conductActivity() {
        System.out.println("\n=== Conduct Class Activity ===");

        int classId = 0;
        while (true) {
            System.out.print("Enter class ID (must be a number): ");
            try {
                classId = Integer.parseInt(scanner.nextLine().trim());
                if (classId <= 0) {
                    System.out.println("Class ID must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        String activity;
        while (true) {
            System.out.print("Enter activity description: ");
            activity = scanner.nextLine().trim();
            if (activity.isEmpty()) {
                System.out.println("Activity description cannot be empty. Please enter a valid description.");
                continue;
            }
            if (!Pattern.compile("^[a-zA-Z0-9\\s,.!?-]+$").matcher(activity).matches()) {
                System.out.println("Activity description can only contain letters, numbers, spaces, and common punctuation.");
                continue;
            }
            break;
        }

        // Logic to conduct activity
        System.out.println("Activity \"" + activity + "\" conducted for class " + classId + " successfully!");
    }

    private static void generateReport() {
        System.out.println("\n=== Generate Class Report ===");

        int classId = 0;
        while (true) {
            System.out.print("Enter class ID (must be a number): ");
            try {
                classId = Integer.parseInt(scanner.nextLine().trim());
                if (classId <= 0) {
                    System.out.println("Class ID must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Logic to generate report
        System.out.println("Report for class " + classId + " generated successfully!");
    }
}