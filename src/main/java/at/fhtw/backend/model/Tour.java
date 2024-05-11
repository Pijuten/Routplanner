package at.fhtw.backend.model;

import at.fhtw.backend.enums.TransportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tour_id;
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
    private Float time;
    private String information;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private List<Log> log;
    //tour description, from, to, transport type, tour distance, estimated time, route information
    public Tour(String tourName,String description, String startPoint, Double latStartPoint, Double longStartPoint, String endPoint, Double latEndPoint, Double longEndPoint, TransportType transportType,Double distance, Float time, String information,List<Log> log) {
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
}