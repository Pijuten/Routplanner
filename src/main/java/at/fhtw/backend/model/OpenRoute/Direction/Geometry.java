package at.fhtw.backend.model.OpenRoute.Direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class Geometry {
    private List<List<Double>> coordinates;}
