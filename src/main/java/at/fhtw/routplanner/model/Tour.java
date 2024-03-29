package at.fhtw.routplanner.model;


import at.fhtw.routplanner.enums.TransportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tour {
    private String id;
    private String tourName;
    private String description;
    private String startPoint;
    private Double latStartPoint;
    private Double longStartPoint;
    private String endPoint;
    private Double latEndPoint;
    private Double longEndPoint;
    private TransportType transportType;
    private Float distance;
    private Float time;
    private String information;
    private List<Log> logs;
    //tour description, from, to, transport type, tour distance, estimated time, route information

    public void addLog(Log log) {
        this.logs.add(log);
    }

}