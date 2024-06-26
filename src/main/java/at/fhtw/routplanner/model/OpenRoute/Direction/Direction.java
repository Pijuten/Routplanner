package at.fhtw.routplanner.model.OpenRoute.Direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class Direction {
    private List<Feature> features;
}
