package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.EventListener;
import at.fhtw.routplanner.UpdateTourListener;
import at.fhtw.routplanner.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class RoutBarViewModel {

    public RoutBarViewModel(){
    }
    private List<Tour> tours = new ArrayList<>();

    public void addTour(Tour tour){
        tours.add(tour);
    }
    public void selectTour(){
    }
}
