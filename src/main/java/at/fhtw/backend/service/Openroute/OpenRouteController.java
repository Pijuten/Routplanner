package at.fhtw.backend.service.Openroute;

import at.fhtw.backend.model.OpenRoute.Direction.Direction;
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
    public List<Feature> GetGeocode(@RequestBody String locationName) {
        return openRouteService.GetGeocode(locationName);
    }
    @GetMapping("/route/direction")
    public Direction GetDirection(@RequestBody List<Double> directions) {
        return openRouteService.GetDirections(directions);

    }
}
