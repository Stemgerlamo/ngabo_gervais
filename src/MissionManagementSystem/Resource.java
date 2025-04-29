package MissionManagementSystem;
public class Resource {
    private String resourceId;
    private String resourceName;
    private int quantity;
    private String resourceType;

    public Resource(String resourceId, String resourceName, int quantity, String resourceType) {
        if (resourceId == null || resourceId.isEmpty()) {
            throw new IllegalArgumentException("Resource ID cannot be null or empty");
        }
        if (resourceName == null || resourceName.isEmpty()) {
            throw new IllegalArgumentException("Resource name cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Resource quantity cannot be negative");
        }
        if (resourceType == null || resourceType.isEmpty()) {
            throw new IllegalArgumentException("Resource type cannot be null or empty");
        }

        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.resourceType = resourceType;
    }

    // Getters
    public String getResourceId() { return resourceId; }
    public String getResourceName() { return resourceName; }
    public int getQuantity() { return quantity; }
    public String getResourceType() { return resourceType; }

    // Setters
    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }
}
