package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.JsonHttpClient;
import at.fhtw.routplanner.ObserverSelectedTour;
import at.fhtw.routplanner.UpdateTourListener;
import at.fhtw.routplanner.model.Log;
import at.fhtw.routplanner.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

@Log4j
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
        if(selectedTour.getLog()!=null){
        data.clear();
        data.addAll(selectedTour.getLog());
        }else{
            data.clear();
        }
    }
    public void removeLog(Log id){
        for (Iterator<Log> iterator = data.iterator(); iterator.hasNext();) {
            Log item = iterator.next();
            if (item.equals(id)) {
                iterator.remove();
                break;
            }
        }
        tour.getLog().remove(id);
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            jsonHttpClient.sendRequestAsync(tour, "http://localhost:8080/tour/add", JsonHttpClient.Method.POST);
        }
    }

    public void editLog(Log log) {
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            jsonHttpClient.sendRequestAsync(tour, "http://localhost:8080/tour/add", JsonHttpClient.Method.POST);
        }
    }

    public void saveLog(Log logItem) {
        if(tour.getLog()==null)
            tour.setLog(
                    new ArrayList<>()
            );
        data.add(logItem);
        tour.addLog(logItem);
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            CompletableFuture<String> jsonCompletableFuture = jsonHttpClient.sendRequestAsync(tour, "http://localhost:8080/tour/add", JsonHttpClient.Method.POST);
            jsonCompletableFuture.handle((result, ex) -> {
                if (ex != null) {
                    log.error(ex.getMessage());
                    return null;
                } else {
                    log.info("Direction Request successful");
                    return result;
                }
            });
        }
    }
}
