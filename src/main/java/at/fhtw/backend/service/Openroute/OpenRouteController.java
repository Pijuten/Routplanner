package at.fhtw.backend.service.Openroute;

import at.fhtw.backend.model.OpenRoute.Direction.Geometry;
import at.fhtw.backend.model.OpenRoute.Geocode.Feature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OpenRouteController {
    private final OpenRouteService openRouteService;

    public OpenRouteController(OpenRouteService openRouteService) {
        this.openRouteService = openRouteService;
    }

    @GetMapping("/route/geocode")
    public List<Feature> getGeocode(@RequestBody String locationName) {
        return openRouteService.getGeocode(locationName);
    }
    @GetMapping("/route/direction")
    public Geometry getDirection(@RequestBody List<Double> directions) {
        return openRouteService.getCoordinate(directions);
    }
}
