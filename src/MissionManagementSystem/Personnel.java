package MissionManagementSystem;

public class Personnel {
    private String personnelId;
    private String personnelName;
    private String personnelRole;
    private Mission assignedMission;

    public Personnel(String personnelId, String personnelName, String personnelRole) {
        if (personnelId == null || personnelId.isEmpty()) {
            throw new IllegalArgumentException("Personnel ID cannot be null or empty");
        }
        if (personnelName == null || personnelName.isEmpty() || !personnelName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Personnel name must contain only letters and spaces");
        }
        if (personnelRole == null || personnelRole.isEmpty()) {
            throw new IllegalArgumentException("Personnel role cannot be null or empty");
        }

        this.personnelId = personnelId;
        this.personnelName = personnelName;
        this.personnelRole = personnelRole;
    }

    // Getters
    public String getPersonnelId() { return personnelId; }
    public String getPersonnelName() { return personnelName; }
    public String getPersonnelRole() { return personnelRole; }
    public Mission getAssignedMission() { return assignedMission; }

    // Setters
    public void setAssignedMission(Mission mission) {
        this.assignedMission = mission;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personnel personnel = (Personnel) obj;
        return personnelId.equals(personnel.personnelId);
    }

    @Override
    public int hashCode() {
        return personnelId.hashCode();
    }
}

