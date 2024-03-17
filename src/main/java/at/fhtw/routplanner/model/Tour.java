package at.fhtw.routplanner.model;


import at.fhtw.routplanner.enums.TransportType;
import lombok.Data;

@Data
public class Tour {
    private String tourName;
    private String description;
    private String startPoint;
    private String endPoint;
    private TransportType transportType;
    private Float distance;
    private Float time;
    private String information;
     //tour description, from, to, transport type, tour distance, estimated time, route information
}
