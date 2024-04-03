package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.ObserverSelectedTour;
import at.fhtw.routplanner.UpdateTourListener;
import at.fhtw.routplanner.model.Log;
import at.fhtw.routplanner.model.Tour;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;

public class LogBarViewModel implements UpdateTourListener {

    public LogBarViewModel() {
        ObserverSelectedTour.getInstance().register(this);
    }
    @Getter
    private  ObservableList<Log> data=FXCollections.observableArrayList();
    @Getter @Setter
    ObservableList<Log> selectedItems=FXCollections.observableArrayList();

    @Getter
    private Tour tour;


    @Override
    public void updateTour(Tour selectedTour) {
        tour = selectedTour;
        if(selectedTour.getLogs()!=null){
        data.clear();
        data.addAll(selectedTour.getLogs());
        }else{
            data.clear();
        }
    }
    public void removeLog(String id){
        for (Iterator<Log> iterator = data.iterator(); iterator.hasNext();) {
            Log item = iterator.next();
            if (item.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }

    public void editLog(Log log) {

    }

    public void saveLog(Log log) {
        if(tour.getLogs()==null)
            tour.setLogs(
                    new ArrayList<Log>()
            );
        data.add(log);
        tour.addLog(log);
    }
}
