package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.EventListener;
import at.fhtw.routplanner.UpdateTourListener;
import at.fhtw.routplanner.model.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

public class OverviewViewModel implements UpdateTourListener {
    @Getter
    private Tour tour;
    @Getter
    private final StringProperty currentTourName = new SimpleStringProperty("");
    @Getter
    private final StringProperty currentDescription = new SimpleStringProperty("");
    @Getter
    private final StringProperty currentStartPoint = new SimpleStringProperty("");
    @Getter
    private final StringProperty currentEndpoint = new SimpleStringProperty("");
    @Getter
    private final StringProperty currentTransportType = new SimpleStringProperty("");
    @Getter
    private final StringProperty currentDistance = new SimpleStringProperty("");
    @Getter
    private final StringProperty currentTime = new SimpleStringProperty("");
    public  OverviewViewModel(){
        EventListener.getInstance().register(this);
    }
    @Override
    public void updateTour(Tour selectedTour) {
        this.tour = selectedTour;
        currentTourName.set(tour.getTourName());
        currentDescription.set(tour.getDescription());
        currentStartPoint.set(tour.getStartPoint());
        currentEndpoint.set(tour.getEndPoint());
        currentTransportType.set(tour.getTransportType().toString());
        currentDistance.set(tour.getDistance().toString()+" km");
        currentTime.set(tour.getTime().toString()+" h");
    }
}
