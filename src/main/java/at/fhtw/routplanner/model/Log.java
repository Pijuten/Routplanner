package at.fhtw.routplanner.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@AllArgsConstructor
@Data
public class Log {

    private String id;
    private LocalDate date;
    private String comment;
    private Integer timeStamp;
    private Integer difficulty;
    private Float distance;
    private Float timeLength;
    private Integer rating;
}


