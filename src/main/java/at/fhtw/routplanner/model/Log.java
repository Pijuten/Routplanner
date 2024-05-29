package at.fhtw.routplanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Log {

    private Long logId;
    private Tour tour;
    private LocalDate date;
    private String comment;
    private Integer timeStamp;
    private Integer difficulty;
    private Double distance;
    private Double timeLength;
    private Integer rating;
    public Log(Long logId,LocalDate date,String comment, Integer timeStamp, Integer difficulty, Double distance, Double timeLength, Integer rating) {
        this.logId = logId;
        this.date = date;
        this.comment = comment;
        this.timeStamp = timeStamp;
        this.difficulty = difficulty;
        this.distance = distance;
        this.timeLength = timeLength;
        this.rating = rating;
    }
}


