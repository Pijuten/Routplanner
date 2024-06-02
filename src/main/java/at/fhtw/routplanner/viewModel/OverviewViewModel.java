package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.JsonHttpClient;
import at.fhtw.routplanner.ObserverSelectedTour;
import at.fhtw.routplanner.Service.DirectionMapFileWriter;
import at.fhtw.routplanner.UpdateTourListener;
import at.fhtw.routplanner.model.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class OverviewViewModel implements UpdateTourListener {
    private Tour tour;
    private final StringProperty currentTourName = new SimpleStringProperty("");
    private final StringProperty currentDescription = new SimpleStringProperty("");
    private final StringProperty currentStartPoint = new SimpleStringProperty("");
    private final StringProperty currentEndpoint = new SimpleStringProperty("");
    private final StringProperty currentTime = new SimpleStringProperty("");
    private final StringProperty currentTransportType = new SimpleStringProperty("");
    private final StringProperty currentDistance = new SimpleStringProperty("");
    public  OverviewViewModel(){
        ObserverSelectedTour.getInstance().register(this);
    }
    @Override
    public void updateTour(Tour selectedTour) {
        this.tour = selectedTour;
        currentTourName.set(tour.getTourName());
        currentDescription.set(tour.getDescription());
        currentStartPoint.set(tour.getStartPoint());
        currentEndpoint.set(tour.getEndPoint());
        currentTransportType.set(tour.getTransportType().toString());
        currentDistance.set(tour.getDistance().toString()+" m");
        currentTime.set(Math.round((tour.getTime()/60)) +" min");
        DirectionMapFileWriter.writeToFile(tour);
    }

    public void createReport() {
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            jsonHttpClient.sendRequestAsync(tour, "http://localhost:8080/tour/report", JsonHttpClient.Method.POST);
        }
    }
}
