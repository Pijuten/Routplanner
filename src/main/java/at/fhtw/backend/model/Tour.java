package at.fhtw.backend.model;

import at.fhtw.backend.enums.TransportType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long tourId;
    private int tourPosition;
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
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Log> log;

    public Tour(String tourName, String description, String startPoint, Double latStartPoint, Double longStartPoint, String endPoint, Double latEndPoint, Double longEndPoint, TransportType transportType, Double distance, Double time, String information, List<Log> log) {
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

    @Override
    public String toString() {
        return "{" +
                "tourId=" + tourId +
                ", tourName='" + tourName + '\'' +
                ", tourPosition=" + tourPosition +
                ", description='" + description + '\'' +
                ", startPoint='" + startPoint + '\'' +
                ", latStartPoint=" + latStartPoint +
                ", longStartPoint=" + longStartPoint +
                ", endPoint='" + endPoint + '\'' +
                ", latEndPoint=" + latEndPoint +
                ", longEndPoint=" + longEndPoint +
                ", transportType=" + transportType +
                ", distance=" + distance +
                ", time=" + time +
                ", information='" + information + '\'' +
                '}';
    }
    public void addLog(Log log) {
        this.log.add(log);
    }
}