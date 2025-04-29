package MissionManagementSystem;

import java.util.Date;

public class ReconMission extends Mission {
    public ReconMission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        super(missionId, missionName, missionStartDate, missionEndDate);
    }

    @Override
    public boolean assignTask(String taskDescription, Personnel assignedTo) {
        if (taskDescription == null || taskDescription.isEmpty()) {
            System.out.println("Error: Task description cannot be empty.");
            return false;
        }

        if (assignedPersonnel.size() < 2) {
            System.out.println("Error: Recon mission requires at least 2 personnel.");
            System.out.println("Currently assigned: " + assignedPersonnel.size());
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
        System.out.println("Recon task assigned: " + taskWithAssignee);
        return true;
    }

    @Override
    public boolean allocateResources(Resource resource) {
        if (resource == null) {
            System.out.println("Error: Resource cannot be null.");
            return false;
        }

        if (resource.getResourceName().equalsIgnoreCase("drone") && resource.getQuantity() > 0) {
            allocatedResources.add(resource);
            resource.setQuantity(resource.getQuantity() - 1);
            System.out.println("Drone allocated to recon mission.");
            return true;
        } else {
            System.out.println("Error: No drones available for recon mission.");
            System.out.println("Available drones: " + (resource.getResourceName().equalsIgnoreCase("drone") ? resource.getQuantity() : 0));
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
        report.append("=== Recon Mission Report ===\n");
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