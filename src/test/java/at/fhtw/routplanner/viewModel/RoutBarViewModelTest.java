package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.enums.TransportType;
import at.fhtw.routplanner.model.Tour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoutBarViewModelTest {

    @Test
    void saveDataToRoutList_shouldAddData(){
        RoutBarViewModel routBarViewModel = new RoutBarViewModel();
        var lastDataCount = routBarViewModel.getTours().size();
        Tour tour = new Tour("23","23","","2e",23.2,23.2,"",233.42,233., TransportType.Bike,23.3f,232.3f,"32",null);
        routBarViewModel.addTour(tour);
        assertEquals(++lastDataCount,routBarViewModel.getTours().size());
    }
}