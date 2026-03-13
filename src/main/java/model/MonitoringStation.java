package model;
import java.time.LocalDate;

/**
 * Parent entity – a radiation monitoring station.
 * Extends Entity<Integer> so it can be used with the generic repository.
 */
public class MonitoringStation extends Entity<Integer> {

    private String location;
    private String type;            // Fixed /Mobile /Emergency
    private String status;          // Active /Inactive /Maintenance
    private LocalDate establishedDate;
    private String operator;

    public MonitoringStation() {}

    public MonitoringStation(Integer id, String location, String type, String status, LocalDate establishedDate, String operator) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.status = status;
        this.establishedDate = establishedDate;
        this.operator = operator;
    }

    public String getLocation() { return location; }
    public void setLocation(String l) { this.location = l; }
    public String getType() { return type; }
    public void setType(String t) { this.type = t; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public LocalDate getEstablishedDate() { return establishedDate; }
    public void setEstablishedDate(LocalDate d) { this.establishedDate = d; }
    public String getOperator() { return operator; }
    public void setOperator(String o) { this.operator = o; }

}
