package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.TourNameCell;
import at.fhtw.routplanner.model.Log;
import at.fhtw.routplanner.model.Tour;
import at.fhtw.routplanner.viewModel.RoutBarViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class RoutBarController implements Initializable {
    final RoutBarViewModel routBarViewModel;
    public ListView tourListView;
    public Button removeButton;

    public RoutBarController(RoutBarViewModel routBarViewModel){
        this.routBarViewModel=routBarViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Tour> tours = routBarViewModel.getTours();
        tourListView.setCellFactory(param -> new TourNameCell());
        tourListView.setItems(tours);
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Tour selectedLog = (Tour) tourListView.getSelectionModel().getSelectedItem();
                if (selectedLog != null) {
                    String id = selectedLog.getId();
                    routBarViewModel.removeTour(id);
                } else {
                    System.out.println("No item selected.");
                }
            }
        });
    }
}
