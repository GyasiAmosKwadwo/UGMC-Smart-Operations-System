package com.ugmc.smartops.model;

/**
 * Represents a hospital service request that will be queued, prioritised,
 * searched and sorted across the system.
 * @author UGMC Smart Operations Team
 */
public class ServiceRequest {
    private final String requestId;
    private final String sourceLocationId;
    private final String destinationLocationId;
    private final String category;
    private final int urgency;          // 1 (low) .. 5 (critical)
    private final String timeSubmitted; // ISO-8601 local time string
    private final String deadline;      // ISO-8601 local time string
    private String status;

    public ServiceRequest(String requestId, String sourceLocationId,
                          String destinationLocationId, String category,
                          int urgency, String timeSubmitted, String deadline,
                          String status) {
        this.requestId = requestId;
        this.sourceLocationId = sourceLocationId;
        this.destinationLocationId = destinationLocationId;
        this.category = category;
        this.urgency = urgency;
        this.timeSubmitted = timeSubmitted;
        this.deadline = deadline;
        this.status = status;
    }

    public String getRequestId() { return requestId; }
    public String getSourceLocationId() { return sourceLocationId; }
    public String getDestinationLocationId() { return destinationLocationId; }
    public String getCategory() { return category; }
    public int getUrgency() { return urgency; }
    public String getTimeSubmitted() { return timeSubmitted; }
    public String getDeadline() { return deadline; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ServiceRequest{" + requestId + ", " + sourceLocationId + "->"
                + destinationLocationId + ", " + category + ", urgency=" + urgency + "}";
    }
}
