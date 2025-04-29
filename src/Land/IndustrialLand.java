package Land;

import java.util.Date;

public class IndustrialLand extends Land {
    private static final double VALUE_PER_ACRE = 12000;
    private static final double TAX_RATE = 0.03;
    private boolean hasEnvironmentalClearance;

    public IndustrialLand(String landId, String ownerName, String location,
                          double sizeInAcres, Date registrationDate, boolean hasClearance) {
        super(landId, ownerName, location, sizeInAcres, registrationDate);
        this.hasEnvironmentalClearance = hasClearance;
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        // Industrial land must be in industrial zones and have clearance
        return location.toLowerCase().contains("industrial zone") && hasEnvironmentalClearance;
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * VALUE_PER_ACRE * TAX_RATE;
    }

    @Override
    public String generateLandReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Industrial Land Report ===\n");
        report.append("Land ID: ").append(landId).append("\n");
        report.append("Owner: ").append(ownerName).append("\n");
        report.append("Location: ").append(location).append("\n");
        report.append("Size: ").append(sizeInAcres).append(" acres\n");
        report.append("Environmental Clearance: ").append(hasEnvironmentalClearance ? "Yes" : "No").append("\n");
        report.append("Registered: ").append(registrationDate).append("\n");
        report.append("Status: ").append(landUseStatus).append("\n");
        report.append("Ownership Valid: ").append(validateOwnership() ? "Yes" : "No").append("\n");
        report.append("Zoning Compliant: ").append(checkZoningCompliance() ? "Yes" : "No").append("\n");
        report.append("Annual Tax: $").append(String.format("%.2f", calculateTax())).append("\n");
        return report.toString();
    }

    public boolean hasEnvironmentalClearance() { return hasEnvironmentalClearance; }
    public void setEnvironmentalClearance(boolean clearance) {
        this.hasEnvironmentalClearance = clearance;
    }
}
