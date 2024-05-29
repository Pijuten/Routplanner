package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.JsonHttpClient;
import at.fhtw.routplanner.ObserverSelectedTour;
import at.fhtw.routplanner.UpdateTourListener;
import at.fhtw.routplanner.model.Tour;
import at.fhtw.routplanner.openstreet.CalcTile;
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
    @Getter
    private final  StringProperty mapUrl = new SimpleStringProperty("");
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
        Integer zoomLevel = CalcTile.getZoom(tour.getLatStartPoint(),tour.getLongStartPoint(),tour.getLatEndPoint(),tour.getLongEndPoint());
    }

    public void createReport() {
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            jsonHttpClient.sendRequestAsync(tour, "http://localhost:8080/tour/report", JsonHttpClient.Method.POST);
        }
    }
}
