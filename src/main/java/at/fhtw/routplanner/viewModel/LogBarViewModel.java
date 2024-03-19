package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.EventListener;
import at.fhtw.routplanner.UpdateTourListener;
import at.fhtw.routplanner.model.Log;
import at.fhtw.routplanner.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogBarViewModel implements UpdateTourListener {

    public LogBarViewModel() {
        EventListener.getInstance().register(this);
    }
    private  ObservableList<Log> data=FXCollections.observableArrayList();
    private Tour currentSelectedTour;

    public ObservableList<Log> getData() {
        return data;
    }


    @Override
    public void updateTour(Tour selectedTour) {
        currentSelectedTour = selectedTour;
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
}
