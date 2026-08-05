package com.ugmc.smartops.model;

/**
 * Represents a weighted edge (road/pathway) between two locations.
 * @author UGMC Smart Operations Team
 */
public class Road {
    private final String roadId;
    private final String fromLocationId;
    private final String toLocationId;
    private final double distanceKm;
    private final double travelTimeMin;
    private final double conditionWeight;

    public Road(String roadId, String fromLocationId, String toLocationId,
                double distanceKm, double travelTimeMin, double conditionWeight) {
        this.roadId = roadId;
        this.fromLocationId = fromLocationId;
        this.toLocationId = toLocationId;
        this.distanceKm = distanceKm;
        this.travelTimeMin = travelTimeMin;
        this.conditionWeight = conditionWeight;
    }

    public String getRoadId() { return roadId; }
    public String getFromLocationId() { return fromLocationId; }
    public String getToLocationId() { return toLocationId; }
    public double getDistanceKm() { return distanceKm; }
    public double getTravelTimeMin() { return travelTimeMin; }
    public double getConditionWeight() { return conditionWeight; }

    /** Effective cost used by weighting algorithms (travelTime * conditionWeight). */
    public double getEffectiveCost() {
        return travelTimeMin * conditionWeight;
    }

    @Override
    public String toString() {
        return "Road{" + roadId + ", " + fromLocationId + "->" + toLocationId
                + ", " + distanceKm + "km, " + travelTimeMin + "min, w=" + conditionWeight + "}";
    }
}
