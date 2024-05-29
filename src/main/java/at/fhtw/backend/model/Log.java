package at.fhtw.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    @JsonBackReference
    private Tour tour;
    private LocalDate date;
    private String comment;
    private Integer timeStamp;
    private Integer difficulty;
    private Double distance;
    private Double timeLength;
    private Integer rating;

    public Log(LocalDate date, String comment, Integer timeStamp, Integer difficulty, Double distance, Double timeLength, Integer rating) {
        this.date = date;
        this.comment = comment;
        this.timeStamp = timeStamp;
        this.difficulty = difficulty;
        this.distance = distance;
        this.timeLength = timeLength;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                ", timeStamp=" + timeStamp +
                ", difficulty=" + difficulty +
                ", distance=" + distance +
                ", timeLength=" + timeLength +
                ", rating=" + rating +
                '}';
    }
}