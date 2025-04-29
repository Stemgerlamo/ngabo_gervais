package Land;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class LandRegistry {
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static List<Land> landRecords = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Land Management System ===");
            System.out.println("1. Register New Land");
            System.out.println("2. View Land Records");
            System.out.println("3. Search Land Records");
            System.out.println("4. Generate Land Report");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            try {
                String choiceStr = scanner.nextLine();
                while (!choiceStr.matches("[1-5]")) {
                    System.out.println("Please enter a number between 1 and 5.");
                    System.out.print("Select an option: ");
                    choiceStr = scanner.nextLine();
                }
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        registerNewLand();
                        break;
                    case 2:
                        viewLandRecords();
                        break;
                    case 3:
                        searchLandRecords();
                        break;
                    case 4:
                        generateLandReport();
                        break;
                    case 5:
                        running = false;
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        System.out.println("Exiting Land Management System. Goodbye!");
    }

    private static void registerNewLand() {
        System.out.println("\n=== Register New Land ===");
        System.out.println("Select land type:");
        System.out.println("1. Agricultural");
        System.out.println("2. Residential");
        System.out.println("3. Commercial");
        System.out.println("4. Industrial");
        System.out.print("Enter choice: ");

        try {
            String typeStr = scanner.nextLine();
            while (!typeStr.matches("[1-4]")) {
                System.out.println("Please enter a number between 1 and 4.");
                System.out.print("Enter choice: ");
                typeStr = scanner.nextLine();
            }
            int typeChoice = Integer.parseInt(typeStr);

            String landId = getInput("Enter land ID: ", false);
            while (!landId.matches("\\d+")) {
                System.out.println("ID must be numeric.");
                landId = getInput("Enter land ID: ", false);
            }
            String ownerName = getInput("Enter owner name: ", true);
            String location = getInput("Enter location: ", false);
            while (!location.matches("[A-Za-z0-9 ]+")) {
                System.out.println("Location must be alphanumeric or spaces.");
                location = getInput("Enter location: ", false);
            }

            double size = 0;
            while (size <= 0) {
                try {
                    String sizeStr = getInput("Enter size in acres: ", false);
                    while (!sizeStr.matches("\\d*\\.?\\d+")) {
                        System.out.println("Size must be a positive number.");
                        sizeStr = getInput("Enter size in acres: ", false);
                    }
                    size = Double.parseDouble(sizeStr);
                    if (size <= 0) System.out.println("Size must be positive.");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            Date regDate = null;
            while (regDate == null) {
                try {
                    String dateStr = getInput("Enter registration date (yyyy-MM-dd): ", false);
                    while (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        System.out.println("Date must be in yyyy-MM-dd format.");
                        dateStr = getInput("Enter registration date (yyyy-MM-dd): ", false);
                    }
                    regDate = dateFormat.parse(dateStr);
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Use yyyy-MM-dd.");
                }
            }

            Land land = null;
            switch (typeChoice) {
                case 1: // Agricultural
                    land = new AgriculturalLand(landId, ownerName, location, size, regDate);
                    break;
                case 2: // Residential
                    int units = 0;
                    while (units <= 0) {
                        try {
                            String unitsStr = getInput("Enter number of residential units: ", false);
                            while (!unitsStr.matches("\\d+")) {
                                System.out.println("Units must be a positive integer.");
                                unitsStr = getInput("Enter number of residential units: ", false);
                            }
                            units = Integer.parseInt(unitsStr);
                            if (units <= 0) System.out.println("Must have at least 1 unit.");
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }
                    land = new ResidentialLand(landId, ownerName, location, size, regDate, units);
                    break;
                case 3: // Commercial
                    String businessType = getInput("Enter business type: ", false);
                    while (!businessType.matches("[A-Za-z ]+")) {
                        System.out.println("Business type must contain only letters and spaces.");
                        businessType = getInput("Enter business type: ", false);
                    }
                    land = new CommercialLand(landId, ownerName, location, size, regDate, businessType);
                    break;
                case 4: // Industrial
                    boolean clearance = getYesNoInput("Does this land have environmental clearance? (y/n): ");
                    land = new IndustrialLand(landId, ownerName, location, size, regDate, clearance);
                    break;
            }

            if (land != null) {
                landRecords.add(land);
                System.out.println("Land registered successfully!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void viewLandRecords() {
        if (landRecords.isEmpty()) {
            System.out.println("No land records available.");
            return;
        }

        System.out.println("\n=== Land Records ===");
        for (int i = 0; i < landRecords.size(); i++) {
            Land land = landRecords.get(i);
            System.out.println((i+1) + ". " + land.getLandId() + " - " +
                    land.getClass().getSimpleName() + " - " +
                    land.getOwnerName() + " - " + land.getLocation());
        }
    }

    private static void searchLandRecords() {
        System.out.println("\n=== Search Land Records ===");
        System.out.println("Search by:");
        System.out.println("1. Owner Name");
        System.out.println("2. Location");
        System.out.println("3. Land Type");
        System.out.print("Enter choice: ");

        try {
            String choiceStr = scanner.nextLine();
            while (!choiceStr.matches("[1-3]")) {
                System.out.println("Please enter a number between 1 and 3.");
                System.out.print("Enter choice: ");
                choiceStr = scanner.nextLine();
            }
            int choice = Integer.parseInt(choiceStr);
            String searchTerm = "";

            if (choice == 3) {
                System.out.println("Select land type:");
                System.out.println("1. Agricultural");
                System.out.println("2. Residential");
                System.out.println("3. Commercial");
                System.out.println("4. Industrial");
                System.out.print("Enter choice: ");
                String typeStr = scanner.nextLine();
                while (!typeStr.matches("[1-4]")) {
                    System.out.println("Please enter a number between 1 and 4.");
                    System.out.print("Enter choice: ");
                    typeStr = scanner.nextLine();
                }
                int typeChoice = Integer.parseInt(typeStr);
                String[] types = {"Agricultural", "Residential", "Commercial", "Industrial"};
                searchTerm = types[typeChoice-1];
            } else {
                searchTerm = getInput("Enter search term: ", false).toLowerCase();
                while (searchTerm.isEmpty()) {
                    System.out.println("Search term cannot be empty.");
                    searchTerm = getInput("Enter search term: ", false).toLowerCase();
                }
            }

            List<Land> results = new ArrayList<>();
            for (Land land : landRecords) {
                boolean match = false;
                switch (choice) {
                    case 1:
                        match = land.getOwnerName().toLowerCase().contains(searchTerm);
                        break;
                    case 2:
                        match = land.getLocation().toLowerCase().contains(searchTerm);
                        break;
                    case 3:
                        match = land.getClass().getSimpleName().equalsIgnoreCase(searchTerm);
                        break;
                }
                if (match) results.add(land);
            }

            if (results.isEmpty()) {
                System.out.println("No matching records found.");
            } else {
                System.out.println("\nSearch Results (" + results.size() + "):");
                for (Land land : results) {
                    System.out.println("ID: " + land.getLandId() + " | Type: " +
                            land.getClass().getSimpleName() + " | Owner: " +
                            land.getOwnerName() + " | Location: " + land.getLocation());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void generateLandReport() {
        if (landRecords.isEmpty()) {
            System.out.println("No land records available.");
            return;
        }

        System.out.println("\n=== Generate Land Report ===");
        for (int i = 0; i < landRecords.size(); i++) {
            System.out.println((i+1) + ". " + landRecords.get(i).getLandId());
        }
        System.out.print("Select land to generate report: ");

        try {
            String indexStr = scanner.nextLine();
            while (!indexStr.matches("\\d+")) {
                System.out.println("Please enter a valid number.");
                System.out.print("Select land to generate report: ");
                indexStr = scanner.nextLine();
            }
            int index = Integer.parseInt(indexStr) - 1;
            if (index >= 0 && index < landRecords.size()) {
                System.out.println("\n" + landRecords.get(index).generateLandReport());
            } else {
                System.out.println("Invalid selection.");
            }
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

    private static boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            while (!input.matches("[yn]")) {
                System.out.println("Please enter 'y' or 'n'.");
                System.out.print(prompt);
                input = scanner.nextLine().trim().toLowerCase();
            }
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
        }
    }
}