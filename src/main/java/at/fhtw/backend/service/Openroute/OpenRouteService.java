package at.fhtw.backend.service.Openroute;

import at.fhtw.backend.model.OpenRoute.Direction.Direction;
import at.fhtw.backend.model.OpenRoute.Geocode.Geocoding;
import at.fhtw.backend.model.Route;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OpenRouteService {
    public OpenRouteService() {

    }
    public Geocoding GetGeocode(String locationName){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf624828d13c2fba8d4a7bbd0e73e720a65b4b&text="+locationName;
        String jsonString = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Geocoding geocoding;
        try {
            geocoding = mapper.readValue(jsonString, Geocoding.class);
        } catch (JsonProcessingException e) {
            System.out.println(jsonString);
            throw new RuntimeException(e);
        }
        return geocoding;
    }
    public Direction GetDirections(Route route){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openrouteservice.org/v2/directions/"+route.getTransportType().toString()+"?api_key=5b3ce3597851110001cf624828d13c2fba8d4a7bbd0e73e720a65b4b&start="+route.getCoordinates().get(0)+","+route.getCoordinates().get(1)+"&end="+route.getCoordinates().get(2)+","+route.getCoordinates().get(3);
        String jsonString = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Direction direction;
        try {
            direction = mapper.readValue(jsonString, Direction.class);
        } catch (JsonProcessingException e) {
            System.out.println(jsonString);
            throw new RuntimeException(e);
        }
        return direction;
    }
}
