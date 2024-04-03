package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.ObserverSelectedTour;
import at.fhtw.routplanner.model.Log;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LogBarViewModelTest {


    @Test
    void saveDataToRoutList_shouldAddData() {
        RoutBarViewModel routBarViewModel = new RoutBarViewModel();
        LogBarViewModel logBarViewModel = new LogBarViewModel();
        ObserverSelectedTour.getInstance().updateSelectedTour(routBarViewModel.getTours().get(0));
        var lastDataCount = logBarViewModel.getData().size();
        Log log = new Log("23", LocalDate.now(), "dwadwa", 32, 4, 2f, 23f, 2);
        logBarViewModel.saveLog(log);
        assertEquals(++lastDataCount,logBarViewModel.getData().size());
    }
}