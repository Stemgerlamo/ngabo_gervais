package MissionManagementSystem;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class Mission {
    protected String missionId;
    protected String missionName;
    protected Date missionStartDate;
    protected Date missionEndDate;
    protected String status;
    protected List<Personnel> assignedPersonnel;
    protected List<Resource> allocatedResources;
    protected List<String> tasks; // Now using simple String for tasks
    protected List<String> completedTasks;

    public Mission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.missionStartDate = missionStartDate;
        this.missionEndDate = missionEndDate;
        this.status = "PLANNED";
        this.assignedPersonnel = new ArrayList<>();
        this.allocatedResources = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.completedTasks = new ArrayList<>();
    }

    // Abstract methods
    public abstract boolean assignTask(String taskDescription, Personnel assignedTo);
    public abstract boolean allocateResources(Resource resource);
    public abstract void trackMissionProgress();
    public abstract String generateMissionReport();

    // Common methods
    public boolean assignPersonnel(Personnel personnel) {
        if (personnel == null) {
            System.out.println("Error: Personnel cannot be null.");
            return false;
        }

        if (personnel.getAssignedMission() != null && !personnel.getAssignedMission().equals(this)) {
            System.out.println("Error: Personnel " + personnel.getPersonnelName() + " is already assigned to another mission.");
            return false;
        }

        if (assignedPersonnel.contains(personnel)) {
            System.out.println("Error: Personnel " + personnel.getPersonnelName() + " is already assigned to this mission.");
            return false;
        }

        assignedPersonnel.add(personnel);
        personnel.setAssignedMission(this);
        return true;
    }

    public boolean validateMissionDates() {
        if (missionStartDate == null || missionEndDate == null) {
            System.out.println("Error: Mission dates cannot be null.");
            return false;
        }
        return missionStartDate.before(missionEndDate);
    }

    public boolean completeTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            System.out.println("Invalid task index.");
            return false;
        }
        String task = tasks.remove(taskIndex);
        completedTasks.add(task);
        return true;
    }

    // Getters
    public String getMissionId() { return missionId; }
    public String getMissionName() { return missionName; }
    public Date getMissionStartDate() { return missionStartDate; }
    public Date getMissionEndDate() { return missionEndDate; }
    public String getStatus() { return status; }
    public List<Personnel> getAssignedPersonnel() { return assignedPersonnel; }
    public List<Resource> getAllocatedResources() { return allocatedResources; }
    public List<String> getTasks() { return tasks; }
    public List<String> getCompletedTasks() { return completedTasks; }

    // Setters
    public void setStatus(String status) {
        if (status != null && !status.isEmpty()) {
            this.status = status;
        }
    }
}