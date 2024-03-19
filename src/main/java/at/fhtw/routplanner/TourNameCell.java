package at.fhtw.routplanner;

import at.fhtw.routplanner.model.Tour;
import javafx.scene.control.ListCell;

public class TourNameCell extends ListCell<Tour> {
    @Override
    protected void updateItem(Tour tour, boolean empty){
        super.updateItem(tour, empty);
        if(empty || tour == null){
            setText(null);
        }else{
            setText(tour.getTourName());
            setOnMouseClicked(event ->{
                EventListener.getInstance().updateSelectedTour(tour);
            });
        }
    }
}
