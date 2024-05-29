package at.fhtw.routplanner.model;


import at.fhtw.routplanner.enums.TransportType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tour {
    private Long tourId;
    private String tourName;
    private String description;
    private String startPoint;
    private Double latStartPoint;
    private Double longStartPoint;
    private String endPoint;
    private Double latEndPoint;
    private Double longEndPoint;
    private TransportType transportType;
    private Double distance;
    private Double time;
    private String information;
    private List<Log> log;
    //tour description, from, to, transport type, tour distance, estimated time, route information
    public Tour(String tourName,String description, String startPoint, Double latStartPoint, Double longStartPoint, String endPoint, Double latEndPoint, Double longEndPoint, TransportType transportType,Double distance, Double time, String information,List<Log> log) {
        this.tourName = tourName;
        this.description = description;
        this.startPoint = startPoint;
        this.latStartPoint = latStartPoint;
        this.longStartPoint = longStartPoint;
        this.endPoint = endPoint;
        this.latEndPoint = latEndPoint;
        this.longEndPoint = longEndPoint;
        this.transportType = transportType;
        this.distance = distance;
        this.time = time;
        this.information = information;
        this.log = log;
    }
    public void addLog(Log log) {
        this.log.add(log);
    }
}