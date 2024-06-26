package at.fhtw.routplanner;

import at.fhtw.routplanner.model.Tour;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;


@Log4j
public class ObserverSelectedTour {
    private List<UpdateTourListener> updateTourListeners = new ArrayList<>();
    private static ObserverSelectedTour INSTANCE;
    public static ObserverSelectedTour getInstance(){
        if(INSTANCE==null)
            INSTANCE =  new ObserverSelectedTour();
        return INSTANCE;
    }
    public void updateSelectedTour(Tour selectedTour){
        for(var listener: updateTourListeners)
            listener.updateTour(selectedTour);
        log.info("updateSelectedTour");
        log.info(selectedTour.getTourPosition());
    }
    public void register(UpdateTourListener updateTourListener){
        updateTourListeners.add(updateTourListener);
    }
}
