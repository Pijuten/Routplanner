package at.fhtw.routplanner.model;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class Log {
    private Date date;
    private LocalTime timeStamp;
    private Integer difficulty;
    private Float distance;
    private Float timeLength;
    private Integer rating;
}
