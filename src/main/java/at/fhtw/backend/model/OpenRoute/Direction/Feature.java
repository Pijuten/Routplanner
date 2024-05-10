package at.fhtw.backend.model.OpenRoute.Direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class Feature {
    private Geometry geometry;
}
