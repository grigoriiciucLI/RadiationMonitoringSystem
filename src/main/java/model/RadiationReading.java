package model;

import java.time.LocalDateTime;

/**
 * Child entity – a radiation reading belonging to a MonitoringStation.
 * Extends Entity<Integer> so it can be used with the generic repository.
 */
public class RadiationReading extends Entity<Integer> {

    private int stationId;
    private LocalDateTime timestamp;
    private double radiationLevel;  // mSv
    private String radiationType;   // Alpha /Beta /Gamma
    private String alertStatus;     // Normal /Warning /Critical
    private String notes;

    public RadiationReading() {}

    public RadiationReading(Integer id, int stationId, LocalDateTime timestamp, double radiationLevel, String radiationType,
                             String alertStatus, String notes) {
        this.id = id;
        this.stationId = stationId;
        this.timestamp = timestamp;
        this.radiationLevel = radiationLevel;
        this.radiationType = radiationType;
        this.alertStatus = alertStatus;
        this.notes = notes;
    }

    public int getStationId() { return stationId; }
    public void setStationId(int s) { this.stationId = s; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime t){ this.timestamp = t; }
    public double getRadiationLevel() { return radiationLevel; }
    public void setRadiationLevel(double l) { this.radiationLevel = l; }
    public String getRadiationType() { return radiationType; }
    public void setRadiationType(String t) { this.radiationType = t; }
    public String getAlertStatus() { return alertStatus; }
    public void setAlertStatus(String a) { this.alertStatus = a; }
    public String getNotes() { return notes; }
    public void setNotes(String n) { this.notes = n; }
}
