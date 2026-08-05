package com.ugmc.smartops.model;

/**
 * Represents a hospital resource (ambulance/van, rider, equipment, personnel)
 * that can be assigned to service requests.
 * @author UGMC Smart Operations Team
 */
public class Resource {
    private final String resourceId;
    private final String resourceType;
    private final String homeLocationId;
    private final int capacity;
    private String availabilityStatus; // AVAILABLE, ASSIGNED, MAINTENANCE

    public Resource(String resourceId, String resourceType, String homeLocationId,
                    int capacity, String availabilityStatus) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.homeLocationId = homeLocationId;
        this.capacity = capacity;
        this.availabilityStatus = availabilityStatus;
    }

    public String getResourceId() { return resourceId; }
    public String getResourceType() { return resourceType; }
    public String getHomeLocationId() { return homeLocationId; }
    public int getCapacity() { return capacity; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public String toString() {
        return "Resource{" + resourceId + ", " + resourceType + ", home="
                + homeLocationId + ", capacity=" + capacity + ", " + availabilityStatus + "}";
    }
}
