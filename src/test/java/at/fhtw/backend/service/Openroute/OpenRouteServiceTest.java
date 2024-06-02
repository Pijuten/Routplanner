package at.fhtw.backend.service.Openroute;

import at.fhtw.backend.enums.TransportType;
import at.fhtw.backend.model.OpenRoute.Direction.Direction;
import at.fhtw.backend.model.OpenRoute.Geocode.Geocoding;
import at.fhtw.backend.model.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class OpenRouteServiceTest {
    @Test
    void getOpenRouteTest() {
        OpenRouteService service = new OpenRouteService();
        Geocoding result= service.GetGeocode("Spaungasse");
        Assertions.assertEquals(result.getFeatures().get(0).getProperties().getName(), "Spaungasse");
        Assertions.assertEquals(result.getFeatures().get(0).getGeometry().getCoordinates().get(0), 16.365736);
        Assertions.assertEquals(result.getFeatures().get(0).getGeometry().getCoordinates().get(1), 48.234628);
    }
    @Test
    void getDirectionTest(){
        OpenRouteService service = new OpenRouteService();
        List<Double> directions = new ArrayList<>();
        directions.add(8.681495);
        directions.add(49.41461);
        directions.add(8.687872);
        directions.add(49.420318);
        Route route = new Route(directions, TransportType.Car);
        Direction direction = service.GetDirections(route);
        assertEquals(direction.getFeatures().get(0).getProperties().getSummary().getDistance(), 1408.8);
    }
}