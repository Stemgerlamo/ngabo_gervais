package MissionManagementSystem;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static List<Personnel> allPersonnel = new ArrayList<>();
    private static List<Resource> allResources = new ArrayList<>();
    private static List<Mission> allMissions = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Mission Management System ===");
            System.out.println("1. Create Personnel");
            System.out.println("2. Create Resource");
            System.out.println("3. Create Mission");
            System.out.println("4. Assign Personnel to Mission");
            System.out.println("5. Allocate Resources to Mission");
            System.out.println("6. Assign Task to Mission");
            System.out.println("7. Complete Task");
            System.out.println("8. Generate Mission Report");
            System.out.println("9. Exit");
            System.out.print("Select an option: ");

            try {
                String choiceStr = scanner.nextLine();
                while (!choiceStr.matches("[1-9]")) {
                    System.out.println("Please enter a number between 1 and 9.");
                    System.out.print("Select an option: ");
                    choiceStr = scanner.nextLine();
                }
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        createPersonnel();
                        break;
                    case 2:
                        createResource();
                        break;
                    case 3:
                        createMission();
                        break;
                    case 4:
                        assignPersonnelToMission();
                        break;
                    case 5:
                        allocateResourcesToMission();
                        break;
                    case 6:
                        assignTaskToMission();
                        break;
                    case 7:
                        completeTask();
                        break;
                    case 8:
                        generateMissionReport();
                        break;
                    case 9:
                        running = false;
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        System.out.println("Exiting Mission Management System. Goodbye!");
    }

    private static void createPersonnel() {
        System.out.println("\n=== Create New Personnel ===");

        String id = getInput("Enter personnel ID: ", false);
        while (!id.matches("\\d+")) {
            System.out.println("ID must be numeric.");
            id = getInput("Enter personnel ID: ", false);
        }
        String name = getInput("Enter personnel name (letters only): ", true);
        String role = getInput("Enter personnel role: ", false);
        while (!role.matches("[A-Za-z ]+")) {
            System.out.println("Role must contain only letters and spaces.");
            role = getInput("Enter personnel role: ", false);
        }

        try {
            Personnel personnel = new Personnel(id, name, role);
            allPersonnel.add(personnel);
            System.out.println("Personnel created successfully: " + personnel.getPersonnelName());
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating personnel: " + e.getMessage());
        }
    }

    private static void createResource() {
        System.out.println("\n=== Create New Resource ===");

        String id = getInput("Enter resource ID: ", false);
        while (!id.matches("\\d+")) {
            System.out.println("ID must be numeric.");
            id = getInput("Enter resource ID: ", false);
        }
        String name = getInput("Enter resource name: ", false);
        while (!name.matches("[A-Za-z0-9 ]+")) {
            System.out.println("Name must be alphanumeric or spaces.");
            name = getInput("Enter resource name: ", false);
        }

        int quantity = 0;
        boolean validQuantity = false;
        while (!validQuantity) {
            try {
                String qtyStr = getInput("Enter resource quantity: ", false);
                while (!qtyStr.matches("\\d+")) {
                    System.out.println("Quantity must be a positive number.");
                    qtyStr = getInput("Enter resource quantity: ", false);
                }
                quantity = Integer.parseInt(qtyStr);
                validQuantity = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for quantity.");
            }
        }

        String type = getInput("Enter resource type: ", false);
        while (!type.matches("[A-Za-z ]+")) {
            System.out.println("Type must contain only letters and spaces.");
            type = getInput("Enter resource type: ", false);
        }

        try {
            Resource resource = new Resource(id, name, quantity, type);
            allResources.add(resource);
            System.out.println("Resource created successfully: " + resource.getResourceName());
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating resource: " + e.getMessage());
        }
    }

    private static void createMission() {
        System.out.println("\n=== Create New Mission ===");
        System.out.println("Select mission type:");
        System.out.println("1. Recon Mission");
        System.out.println("2. Rescue Mission");
        System.out.println("3. Combat Mission");
        System.out.println("4. Humanitarian Mission");
        System.out.print("Enter choice: ");

        try {
            String typeStr = scanner.nextLine();
            while (!typeStr.matches("[1-4]")) {
                System.out.println("Please enter a number between 1 and 4.");
                System.out.print("Enter choice: ");
                typeStr = scanner.nextLine();
            }
            int typeChoice = Integer.parseInt(typeStr);

            String id = getInput("Enter mission ID: ", false);
            while (!id.matches("\\d+")) {
                System.out.println("ID must be numeric.");
                id = getInput("Enter mission ID: ", false);
            }
            String name = getInput("Enter mission name: ", false);
            while (!name.matches("[A-Za-z0-9 ]+")) {
                System.out.println("Name must be alphanumeric or spaces.");
                name = getInput("Enter mission name: ", false);
            }

            Date startDate = null;
            Date endDate = null;
            boolean validDates = false;

            while (!validDates) {
                try {
                    String startStr = getInput("Enter start date (yyyy-MM-dd): ", false);
                    while (!startStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        System.out.println("Date must be in yyyy-MM-dd format.");
                        startStr = getInput("Enter start date (yyyy-MM-dd): ", false);
                    }
                    String endStr = getInput("Enter end date (yyyy-MM-dd): ", false);
                    while (!endStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        System.out.println("Date must be in yyyy-MM-dd format.");
                        endStr = getInput("Enter end date (yyyy-MM-dd): ", false);
                    }

                    startDate = dateFormat.parse(startStr);
                    endDate = dateFormat.parse(endStr);

                    if (startDate.before(endDate)) {
                        validDates = true;
                    } else {
                        System.out.println("Error: Start date must be before end date.");
                    }
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                }
            }

            Mission mission = null;
            switch (typeChoice) {
                case 1:
                    mission = new ReconMission(id, name, startDate, endDate);
                    break;
                case 2:
                    mission = new RescueMission(id, name, startDate, endDate);
                    break;
                case 3:
                    mission = new CombatMission(id, name, startDate, endDate);
                    break;
                case 4:
                    mission = new HumanitarianMission(id, name, startDate, endDate);
                    break;
            }

            if (mission != null) {
                allMissions.add(mission);
                System.out.println("Mission created successfully: " + mission.getMissionName());
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number for mission type.");
        }
    }

    private static void assignPersonnelToMission() {
        if (allMissions.isEmpty() || allPersonnel.isEmpty()) {
            System.out.println("No missions or personnel available.");
            return;
        }

        System.out.println("\n=== Assign Personnel to Mission ===");
        listMissions();
        System.out.print("Select mission number: ");

        try {
            String missionStr = scanner.nextLine();
            while (!missionStr.matches("\\d+")) {
                System.out.println("Please enter a valid number.");
                System.out.print("Select mission number: ");
                missionStr = scanner.nextLine();
            }
            int missionIndex = Integer.parseInt(missionStr) - 1;
            if (missionIndex < 0 || missionIndex >= allMissions.size()) {
                System.out.println("Invalid mission selection.");
                return;
            }

            Mission mission = allMissions.get(missionIndex);
            listPersonnel();
            System.out.print("Select personnel number: ");

            String personnelStr = scanner.nextLine();
            while (!personnelStr.matches("\\d+")) {
                System.out.println("Please enter a valid number.");
                System.out.print("Select personnel number: ");
                personnelStr = scanner.nextLine();
            }
            int personnelIndex = Integer.parseInt(personnelStr) - 1;
            if (personnelIndex < 0 || personnelIndex >= allPersonnel.size()) {
                System.out.println("Invalid personnel selection.");
                return;
            }

            Personnel personnel = allPersonnel.get(personnelIndex);
            if (mission.assignPersonnel(personnel)) {
                System.out.println("Personnel assigned successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void allocateResourcesToMission() {
        if (allMissions.isEmpty() || allResources.isEmpty()) {
            System.out.println("No missions or resources available.");
            return;
        }

        System.out.println("\n=== Allocate Resources to Mission ===");
        listMissions();
        System.out.print("Select mission number: ");

        try {
            String missionStr = scanner.nextLine();
            while (!missionStr.matches("\\d+")) {
                System.out.println("Please enter a valid number.");
                System.out.print("Select mission number: ");
                missionStr = scanner.nextLine();
            }
            int missionIndex = Integer.parseInt(missionStr) - 1;
            if (missionIndex < 0 || missionIndex >= allMissions.size()) {
                System.out.println("Invalid mission selection.");
                return;
            }

            Mission mission = allMissions.get(missionIndex);
            listResources();
            System.out.print("Select resource number: ");

            String resourceStr = scanner.nextLine();
            while (!resourceStr.matches("\\d+")) {
                System.out.println("Please enter a valid number.");
                System.out.print("Select resource number: ");
                resourceStr = scanner.nextLine();
            }
            int resourceIndex = Integer.parseInt(resourceStr) - 1;
            if (resourceIndex < 0 || resourceIndex >= allResources.size()) {
                System.out.println("Invalid resource selection.");
                return;
            }

            Resource resource = allResources.get(resourceIndex);
            if (mission.allocateResources(resource)) {
                System.out.println("Resource allocated successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void assignTaskToMission() {
        if (allMissions.isEmpty()) {
            System.out.println("No missions available.");
            return;
        }

        System.out.println("\n=== Assign Task to Mission ===");
        listMissions();
        System.out.print("Select mission number: ");

        try {
            String missionStr = scanner.nextLine();
            while (!missionStr.matches("\\d+")) {
                System.out.println("Please enter a valid number.");
                System.out.print("Select mission number: ");
                missionStr = scanner.nextLine();
            }
            int missionIndex = Integer.parseInt(missionStr) - 1;
            if (missionIndex < 0 || missionIndex >= allMissions.size()) {
                System.out.println("Invalid mission selection.");
                return;
            }

            Mission mission = allMissions.get(missionIndex);
            String taskDescription = getInput("Enter task description: ", false);
            while (taskDescription.isEmpty()) {
                System.out.println("Task description cannot be empty.");
                taskDescription = getInput("Enter task description: ", false);
            }

            Personnel assignedTo = null;
            if (!mission.getAssignedPersonnel().isEmpty()) {
                System.out.println("Available personnel for this mission:");
                List<Personnel> missionPersonnel = mission.getAssignedPersonnel();
                for (int i = 0; i < missionPersonnel.size(); i++) {
                    System.out.println((i+1) + ". " + missionPersonnel.get(i).getPersonnelName() +
                            " (" + missionPersonnel.get(i).getPersonnelRole() + ")");
                }

                System.out.print("Select personnel to assign this task (0 to leave unassigned): ");
                String personnelStr = scanner.nextLine();
                while (!personnelStr.matches("\\d+")) {
                    System.out.println("Please enter a valid number.");
                    System.out.print("Select personnel to assign this task (0 to leave unassigned): ");
                    personnelStr = scanner.nextLine();
                }
                int personnelChoice = Integer.parseInt(personnelStr);

                if (personnelChoice > 0 && personnelChoice <= missionPersonnel.size()) {
                    assignedTo = missionPersonnel.get(personnelChoice-1);
                }
            }

            if (mission.assignTask(taskDescription, assignedTo)) {
                System.out.println("Task assigned successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void completeTask() {
        if (allMissions.isEmpty()) {
            System.out.println("No missions available.");
            return;
        }

        System.out.println("\n=== Complete Task ===");
        listMissions();
        System.out.print("Select mission number: ");

        try {
            String missionStr = scanner.nextLine();
            while (!missionStr.matches("\\d+")) {
                System.out.println("Please enter a valid number.");
                System.out.print("Select mission number: ");
                missionStr = scanner.nextLine();
            }
            int missionIndex = Integer.parseInt(missionStr) - 1;
            if (missionIndex < 0 || missionIndex >= allMissions.size()) {
                System.out.println("Invalid mission selection.");
                return;
            }

            Mission mission = allMissions.get(missionIndex);

            if (mission.getTasks().isEmpty()) {
                System.out.println("This mission has no pending tasks.");
                return;
            }

            System.out.println("\nPending Tasks:");
            for (int i = 0; i < mission.getTasks().size(); i++) {
                System.out.println((i+1) + ". " + mission.getTasks().get(i));
            }

            System.out.print("Select task to complete: ");
            String taskStr = scanner.nextLine();
            while (!taskStr.matches("\\d+")) {
                System.out.println("Please enter a valid number.");
                System.out.print("Select task to complete: ");
                taskStr = scanner.nextLine();
            }
            int taskIndex = Integer.parseInt(taskStr) - 1;

            if (mission.completeTask(taskIndex)) {
                System.out.println("Task marked as completed.");
                mission.trackMissionProgress();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void generateMissionReport() {
        if (allMissions.isEmpty()) {
            System.out.println("No missions available.");
            return;
        }

        System.out.println("\n=== Generate Mission Report ===");
        listMissions();
        System.out.print("Select mission number: ");

        try {
            String missionStr = scanner.nextLine();
            while (!missionStr.matches("\\d+")) {
                System.out.println("Please enter a valid number.");
                System.out.print("Select mission number: ");
                missionStr = scanner.nextLine();
            }
            int missionIndex = Integer.parseInt(missionStr) - 1;
            if (missionIndex < 0 || missionIndex >= allMissions.size()) {
                System.out.println("Invalid mission selection.");
                return;
            }

            Mission mission = allMissions.get(missionIndex);
            mission.trackMissionProgress();
            System.out.println("\n" + mission.generateMissionReport());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static String getInput(String prompt, boolean lettersOnly) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            if (lettersOnly && !input.matches("[a-zA-Z ]+")) {
                System.out.println("Input must contain only letters and spaces.");
                continue;
            }

            return input;
        }
    }

    private static void listMissions() {
        if (allMissions.isEmpty()) {
            System.out.println("No missions available.");
            return;
        }

        System.out.println("\nAvailable Missions:");
        for (int i = 0; i < allMissions.size(); i++) {
            Mission m = allMissions.get(i);
            System.out.println((i+1) + ". " + m.getMissionName() + " (" + m.getMissionId() + ") - " + m.getStatus());
        }
    }

    private static void listPersonnel() {
        if (allPersonnel.isEmpty()) {
            System.out.println("No personnel available.");
            return;
        }

        System.out.println("\nAvailable Personnel:");
        for (int i = 0; i < allPersonnel.size(); i++) {
            Personnel p = allPersonnel.get(i);
            System.out.println((i+1) + ". " + p.getPersonnelName() + " (" + p.getPersonnelRole() + ")");
        }
    }

    private static void listResources() {
        if (allResources.isEmpty()) {
            System.out.println("No resources available.");
            return;
        }

        System.out.println("\nAvailable Resources:");
        for (int i = 0; i < allResources.size(); i++) {
            Resource r = allResources.get(i);
            System.out.println((i+1) + ". " + r.getResourceName() + " (" + r.getResourceType() + ") - Qty: " + r.getQuantity());
        }
    }
}