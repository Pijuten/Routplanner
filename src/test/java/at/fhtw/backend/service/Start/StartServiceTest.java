package at.fhtw.backend.service.Start;

import at.fhtw.backend.enums.TransportType;
import at.fhtw.backend.model.Log;
import at.fhtw.backend.model.Tour;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StartServiceTest {

    @Autowired
    private StartService startService;
    @Test
    void SaveLogTest(){
        Tour tour = new Tour("Test","test","test",23.23,23.3,"test",23.23,5345.3, TransportType.Bike,23.23,23.4f,"test",null);
        Log log = new Log(LocalDate.now(),"test",34,4,34.43,34.3,3);
        List<Log> logs = new ArrayList<>();
        logs.add(log);
        tour.setLog(logs);
        System.out.println(startService.addTour(tour));
    }
}