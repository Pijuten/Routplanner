package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.UpdateTourListener;
import at.fhtw.routplanner.model.Tour;
import lombok.Getter;

public class OverviewViewModel implements UpdateTourListener {
    @Getter
    private Tour tour;
    @Override
    public void updateTour(Tour selectedTour) {
        this.tour = selectedTour;
    }
}
