package at.fhtw.backend.model;


import at.fhtw.backend.enums.TransportType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Route {
    private List<Double> coordinates;
    private TransportType transportType;
}
