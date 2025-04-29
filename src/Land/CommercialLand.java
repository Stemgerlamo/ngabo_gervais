package Land;

import java.util.Date;

public class CommercialLand extends Land {
    private static final double VALUE_PER_ACRE = 10000;
    private static final double TAX_RATE = 0.025;
    private String businessType;

    public CommercialLand(String landId, String ownerName, String location,
                          double sizeInAcres, Date registrationDate, String businessType) {
        super(landId, ownerName, location, sizeInAcres, registrationDate);
        this.businessType = businessType;
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        // Commercial land must be in commercial zones
        return location.toLowerCase().contains("commercial zone");
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * VALUE_PER_ACRE * TAX_RATE;
    }

    @Override
    public String generateLandReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Commercial Land Report ===\n");
        report.append("Land ID: ").append(landId).append("\n");
        report.append("Owner: ").append(ownerName).append("\n");
        report.append("Location: ").append(location).append("\n");
        report.append("Size: ").append(sizeInAcres).append(" acres\n");
        report.append("Business Type: ").append(businessType).append("\n");
        report.append("Registered: ").append(registrationDate).append("\n");
        report.append("Status: ").append(landUseStatus).append("\n");
        report.append("Ownership Valid: ").append(validateOwnership() ? "Yes" : "No").append("\n");
        report.append("Zoning Compliant: ").append(checkZoningCompliance() ? "Yes" : "No").append("\n");
        report.append("Annual Tax: $").append(String.format("%.2f", calculateTax())).append("\n");
        return report.toString();
    }

    public String getBusinessType() { return businessType; }
    public void setBusinessType(String type) {
        if (type != null) this.businessType = type;
    }
}
