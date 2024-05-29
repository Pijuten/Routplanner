package at.fhtw.routplanner.model.OpenRoute.Direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Summary {
    public double distance;
    public double duration;
}
