package at.fhtw.routplanner;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
    private String title;
    private String body;
    private int userId;
}
