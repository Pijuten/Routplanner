package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.enums.TransportType;
import at.fhtw.routplanner.model.Tour;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class RoutBarViewModelTest {

    @Test
    void getDirections() throws ExecutionException, InterruptedException {
        RoutBarViewModel viewModel = new RoutBarViewModel();
        List<Double> list = new ArrayList<>();
        list.add(8.681495);
        list.add(49.41461);
        list.add(8.687872);
        list.add(49.420318);
        viewModel.getDirection(list).get();
    }
}