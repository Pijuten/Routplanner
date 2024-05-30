package at.fhtw.backend.service.Openroute;

import at.fhtw.backend.model.OpenRoute.Direction.Direction;
import at.fhtw.backend.model.OpenRoute.Geocode.Geocoding;
import at.fhtw.backend.model.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OpenRouteController {
    private final OpenRouteService openRouteService;

    public OpenRouteController(OpenRouteService openRouteService) {
        this.openRouteService = openRouteService;
    }

    @GetMapping("/route/geocode")
    public ResponseEntity<Geocoding> getGeocode(@RequestParam(name = "locationname") String locationName) {
        Geocoding geocoding = openRouteService.GetGeocode(locationName);
        return new ResponseEntity<>(geocoding, HttpStatus.OK);
    }
    @PostMapping("/route/direction")
    public ResponseEntity <Direction> GetDirection(@RequestBody Route route) {
        return ResponseEntity.ok(openRouteService.GetDirections(route));

    }
}
