package at.fhtw.backend.service.Openroute;

import at.fhtw.backend.model.OpenRoute.Direction.Geometry;
import at.fhtw.backend.model.OpenRoute.Geocode.Feature;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OpenRouteServiceTest {
    @Test
    void getOpenRouteTest() {
        OpenRouteService service = new OpenRouteService();
        List<Feature> result= service.getGeocode("Spaungasse");
        for (Feature feature : result) {
            System.out.println(feature.getGeometry().getCoordinates());
        }
    }
    @Test
    void getDirectionTest(){
        OpenRouteService service = new OpenRouteService();
        List<Double> directions = new ArrayList<>();
        directions.add(8.681495);
        directions.add(49.41461);
        directions.add(8.687872);
        directions.add(49.420318);
        Geometry geometry = service.getCoordinate(directions);
    }
}