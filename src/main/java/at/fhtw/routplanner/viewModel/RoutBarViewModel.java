package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.enums.TransportType;
import at.fhtw.routplanner.model.Log;
import at.fhtw.routplanner.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoutBarViewModel {

    public RoutBarViewModel(){
        List<Log> logList = new ArrayList<>();
        Log log1 = new Log("1", LocalDate.now(), "test" ,123, 3, 10.5f, 1.5f, 5);


        logList.add(log1);
        Tour tour1 = new Tour("2323231","Test","test","start","end", TransportType.Bike,3.4f,2.3f,"test",logList);
        Tour tour2 = new Tour("6776","Test2","test","start","end", TransportType.Bike,3.4f,2.3f,"test",null);
        Tour tour3 = new Tour("wadwad","Test3","test","start","end", TransportType.Bike,3.4f,2.3f,"test",null);

        tours.add(tour1);
        tours.add(tour2);
        tours.add(tour3);
    }
    private ObservableList<Tour> tours = FXCollections.observableArrayList();

    public ObservableList<Tour> getTours(){
        return tours;
    }
    public void addTour(Tour tour){
        tours.add(tour);
    }
    public void removeTour(String id){

        for (Iterator<Tour> iterator = tours.iterator(); iterator.hasNext();) {
            Tour item = iterator.next();
            if (item.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }
    public void selectTour(){
    }

    public void editTour(Tour tour) {
    }
}
