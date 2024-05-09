package at.fhtw.backend.start;

import at.fhtw.backend.model.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StartController {
    private final StartService startService;

    public StartController(StartService startService) {
        this.startService = startService;
    }

    @PostMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public String start() {
        List<Log> logList = startService.fetchTours();
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(logList);
        } catch (Exception e) {
            // Handle exception if necessary
            e.printStackTrace();
        }
        return jsonString;
    }
}
