package Land;

import java.util.Date;

public abstract class Land {
    protected String landId;
    protected String ownerName;
    protected String location;
    protected double sizeInAcres;
    protected Date registrationDate;
    protected String landUseStatus;

    public Land(String landId, String ownerName, String location,
                double sizeInAcres, Date registrationDate) {
        this.landId = landId;
        this.ownerName = ownerName;
        this.location = location;
        this.sizeInAcres = sizeInAcres;
        this.registrationDate = registrationDate;
        this.landUseStatus = "Vacant";
    }

    // Abstract methods
    public abstract boolean validateOwnership();
    public abstract boolean checkZoningCompliance();
    public abstract double calculateTax();
    public abstract String generateLandReport();

    // Common methods
    public boolean changeUseStatus(String newStatus) {
        if (newStatus != null && !newStatus.isEmpty()) {
            this.landUseStatus = newStatus;
            return true;
        }
        return false;
    }

    // Getters
    public String getLandId() { return landId; }
    public String getOwnerName() { return ownerName; }
    public String getLocation() { return location; }
    public double getSizeInAcres() { return sizeInAcres; }
    public Date getRegistrationDate() { return registrationDate; }
    public String getLandUseStatus() { return landUseStatus; }

    // Setters with validation
    public void setOwnerName(String ownerName) {
        if (ownerName != null && !ownerName.isEmpty()) {
            this.ownerName = ownerName;
        }
    }

    public boolean setSizeInAcres(double sizeInAcres) {
        if (sizeInAcres > 0) {
            this.sizeInAcres = sizeInAcres;
            return true;
        }
        return false;
    }
}
