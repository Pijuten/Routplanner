package at.fhtw.backend.model.OpenRoute.Geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry{
    private List<Double> coordinates;
}

