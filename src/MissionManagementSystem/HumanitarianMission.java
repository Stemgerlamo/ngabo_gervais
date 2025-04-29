package MissionManagementSystem;
import java.util.Date;

public class HumanitarianMission extends Mission {
    public HumanitarianMission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        super(missionId, missionName, missionStartDate, missionEndDate);
    }

    @Override
    public boolean assignTask(String taskDescription, Personnel assignedTo) {
        if (taskDescription == null || taskDescription.isEmpty()) {
            System.out.println("Error: Task description cannot be empty.");
            return false;
        }

        boolean hasRequiredRoles = assignedPersonnel.stream()
                .anyMatch(p -> p.getPersonnelRole().equalsIgnoreCase("logistics") ||
                        p.getPersonnelRole().equalsIgnoreCase("medic"));

        if (!hasRequiredRoles) {
            System.out.println("Error: Humanitarian mission requires logistics or medical personnel.");
            return false;
        }

        if (assignedTo != null && !assignedPersonnel.contains(assignedTo)) {
            System.out.println("Error: Assigned personnel must be part of the mission.");
            return false;
        }

        String taskWithAssignee = taskDescription;
        if (assignedTo != null) {
            taskWithAssignee += " (Assigned to: " + assignedTo.getPersonnelName() + ")";
        }

        tasks.add(taskWithAssignee);
        System.out.println("Humanitarian task assigned: " + taskWithAssignee);
        return true;
    }

    @Override
    public boolean allocateResources(Resource resource) {
        if (resource == null) {
            System.out.println("Error: Resource cannot be null.");
            return false;
        }

        if ((resource.getResourceType().equalsIgnoreCase("food") ||
                resource.getResourceType().equalsIgnoreCase("medical")) &&
                resource.getQuantity() > 0) {
            allocatedResources.add(resource);
            resource.setQuantity(resource.getQuantity() - 1);
            System.out.println("Humanitarian resource allocated: " + resource.getResourceName());
            return true;
        } else {
            System.out.println("Error: Humanitarian mission requires food or medical supplies.");
            System.out.println("Available food/medical: " +
                    (resource.getResourceType().equalsIgnoreCase("food") ||
                            resource.getResourceType().equalsIgnoreCase("medical") ? resource.getQuantity() : 0));
            return false;
        }
    }

    @Override
    public void trackMissionProgress() {
        if (tasks.isEmpty() && completedTasks.isEmpty()) {
            status = "PLANNED";
            return;
        }

        double progress = (double) completedTasks.size() / (completedTasks.size() + tasks.size()) * 100;

        if (progress >= 100) {
            status = "COMPLETED";
        } else if (progress > 0) {
            status = "IN_PROGRESS";
        } else {
            status = "PLANNED";
        }
    }

    @Override
    public String generateMissionReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Humanitarian Mission Report ===\n");
        report.append("Mission ID: ").append(missionId).append("\n");
        report.append("Mission Name: ").append(missionName).append("\n");
        report.append("Status: ").append(status).append("\n");
        report.append("Start Date: ").append(missionStartDate).append("\n");
        report.append("End Date: ").append(missionEndDate).append("\n");

        report.append("\nAssigned Personnel (").append(assignedPersonnel.size()).append("):\n");
        for (Personnel p : assignedPersonnel) {
            report.append("- ").append(p.getPersonnelName()).append(" (").append(p.getPersonnelRole()).append(")\n");
        }

        report.append("\nAllocated Resources (").append(allocatedResources.size()).append("):\n");
        for (Resource r : allocatedResources) {
            report.append("- ").append(r.getResourceName()).append(" (").append(r.getResourceType()).append(")\n");
        }

        report.append("\nPending Tasks (").append(tasks.size()).append("):\n");
        for (int i = 0; i < tasks.size(); i++) {
            report.append(i+1).append(". ").append(tasks.get(i)).append("\n");
        }

        report.append("\nCompleted Tasks (").append(completedTasks.size()).append("):\n");
        for (int i = 0; i < completedTasks.size(); i++) {
            report.append(i+1).append(". ").append(completedTasks.get(i)).append("\n");
        }

        return report.toString();
    }
}