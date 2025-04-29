package Land;

import java.util.Date;

public class ResidentialLand extends Land {
    private static final double VALUE_PER_ACRE = 8000;
    private static final double TAX_RATE = 0.015;
    private int residentialUnits;

    public ResidentialLand(String landId, String ownerName, String location,
                           double sizeInAcres, Date registrationDate, int residentialUnits) {
        super(landId, ownerName, location, sizeInAcres, registrationDate);
        this.residentialUnits = residentialUnits;
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        // Residential land must comply with max 2 units per acre
        return (residentialUnits / sizeInAcres) <= 2;
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * VALUE_PER_ACRE * TAX_RATE;
    }

    @Override
    public String generateLandReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Residential Land Report ===\n");
        report.append("Land ID: ").append(landId).append("\n");
        report.append("Owner: ").append(ownerName).append("\n");
        report.append("Location: ").append(location).append("\n");
        report.append("Size: ").append(sizeInAcres).append(" acres\n");
        report.append("Residential Units: ").append(residentialUnits).append("\n");
        report.append("Registered: ").append(registrationDate).append("\n");
        report.append("Status: ").append(landUseStatus).append("\n");
        report.append("Ownership Valid: ").append(validateOwnership() ? "Yes" : "No").append("\n");
        report.append("Zoning Compliant: ").append(checkZoningCompliance() ? "Yes" : "No").append("\n");
        report.append("Annual Tax: $").append(String.format("%.2f", calculateTax())).append("\n");
        return report.toString();
    }

    public int getResidentialUnits() { return residentialUnits; }
    public void setResidentialUnits(int units) {
        if (units >= 0) this.residentialUnits = units;
    }
}
