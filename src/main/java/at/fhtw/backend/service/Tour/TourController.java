package at.fhtw.backend.service.Tour;

import at.fhtw.backend.model.Tour;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TourController {
    private final TourService startService;

    public TourController(TourService startService) {
        this.startService = startService;
    }

    @GetMapping(value = "/tour/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tour>> fetchLogs() {
        List<Tour> logList = startService.fetchTours();
        return ResponseEntity.ok(logList);
    }
    @PostMapping(value = "/tour/add")
    public ResponseEntity<Tour> addLog(@RequestBody Tour tour) {
       return ResponseEntity.ok(startService.addTour(tour));
    }
    @DeleteMapping(value = "/tour/remove")
    public void removeLog(@RequestParam Long logId) {
        startService.removeTour(logId);
    }
}
