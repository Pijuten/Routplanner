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
import java.util.Objects;

public class RoutBarViewModel {

    public RoutBarViewModel(){
        List<Log> logList = new ArrayList<>();
        Log log1 = new Log("1", LocalDate.now(), "test" ,123, 3, 10.5f, 1.5f, 5);


        logList.add(log1);
        Tour tour1 = new Tour("Test","test","start",48.1787951,15.2300914,"end",47.2046171,11.0819367, TransportType.Bike,3.4,2.3f,"test",logList);
        Tour tour2 = new Tour("Test2","tesfwfwt","fwaf",47.8895155,1.4955935,"wad",46.6738053,1.5478237, TransportType.FootWalk,3.4,2.3f,"test",null);
        Tour tour3 = new Tour("Test3","wad","war",-9.4307488,-76.410938,"awdwa",-16.1501735,-73.1948006, TransportType.MountainBike,3.4,2.3f,"test",null);

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
    public void removeTour(Long id){

        for (Iterator<Tour> iterator = tours.iterator(); iterator.hasNext();) {
            Tour item = iterator.next();
            if (Objects.equals(item.getTour(), id)) {
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
