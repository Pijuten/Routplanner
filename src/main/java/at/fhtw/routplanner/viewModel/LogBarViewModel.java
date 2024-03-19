package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.EventListener;
import at.fhtw.routplanner.UpdateTourListener;
import at.fhtw.routplanner.model.Log;
import at.fhtw.routplanner.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class LogBarViewModel implements UpdateTourListener {

    public LogBarViewModel() {
        EventListener.getInstance().register(this);
    }
    private final ObservableList<Log> data = FXCollections.observableArrayList();

    public ObservableList<Log> getData() {
        return data;
    }


    @Override
    public void updateTour(Tour selectedTour) {
        List<Log> logList = selectedTour.getLogs();
        for(var log : logList)
            data.addAll(logList);
    }
}
