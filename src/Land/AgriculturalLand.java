package Land;

import java.util.Date;

public class AgriculturalLand extends Land {
    private static final double VALUE_PER_ACRE = 5000;
    private static final double TAX_RATE = 0.01;

    public AgriculturalLand(String landId, String ownerName, String location,
                            double sizeInAcres, Date registrationDate) {
        super(landId, ownerName, location, sizeInAcres, registrationDate);
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        // Agricultural land must be at least 1 acre and in farming zone
        return sizeInAcres >= 1 && location.toLowerCase().contains("farming zone");
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * VALUE_PER_ACRE * TAX_RATE;
    }

    @Override
    public String generateLandReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Agricultural Land Report ===\n");
        report.append("Land ID: ").append(landId).append("\n");
        report.append("Owner: ").append(ownerName).append("\n");
        report.append("Location: ").append(location).append("\n");
        report.append("Size: ").append(sizeInAcres).append(" acres\n");
        report.append("Registered: ").append(registrationDate).append("\n");
        report.append("Status: ").append(landUseStatus).append("\n");
        report.append("Ownership Valid: ").append(validateOwnership() ? "Yes" : "No").append("\n");
        report.append("Zoning Compliant: ").append(checkZoningCompliance() ? "Yes" : "No").append("\n");
        report.append("Annual Tax: $").append(String.format("%.2f", calculateTax())).append("\n");
        return report.toString();
    }
}
