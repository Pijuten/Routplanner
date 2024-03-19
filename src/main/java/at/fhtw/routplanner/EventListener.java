package at.fhtw.routplanner;

import at.fhtw.routplanner.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class EventListener {
    private List<UpdateTourListener> updateTourListeners = new ArrayList<>();
    private static EventListener INSTANCE;
    public static EventListener getInstance(){
        if(INSTANCE==null)
            INSTANCE =  new EventListener();
        return INSTANCE;
    }
    public void updateSelectedTour(Tour selectedTour){
        for(var listener: updateTourListeners)
            listener.updateTour(selectedTour);
    }
    public void register(UpdateTourListener updateTourListener){
        updateTourListeners.add(updateTourListener);
    }
}
