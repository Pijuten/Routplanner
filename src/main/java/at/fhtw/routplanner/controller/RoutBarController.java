package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.FXMLDependencyInjection;
import at.fhtw.routplanner.TourNameCell;
import at.fhtw.routplanner.model.Tour;
import at.fhtw.routplanner.viewModel.RoutBarViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class RoutBarController implements Initializable {
    final RoutBarViewModel routBarViewModel;
    public ListView tourListView;
    public Button removeButton;
    public Button addButton;
    public Button editButton;


    public RoutBarController(RoutBarViewModel routBarViewModel) {
        this.routBarViewModel = routBarViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Tour> tours = routBarViewModel.getTours();
        tourListView.setCellFactory(param -> new TourNameCell());
        tourListView.setItems(tours);
        removeButtonListener(removeButton);
        addButtonListener(addButton);
        editButtonListener(editButton);
    }



    private void removeButtonListener(Button removeButton) {
        removeButton.setOnAction(event -> {
            Tour selectedTour = (Tour) tourListView.getSelectionModel().getSelectedItem();
            if (selectedTour != null) {
                String id = selectedTour.getId();
                routBarViewModel.removeTour(id);
            } else {
                System.out.println("No item selected.");
            }
        });
    }

    private void addButtonListener(Button addButton) {

        addButton.setOnAction(event -> {
            setupStage(null);
        });
    }
    private void editButtonListener(Button editButton) {

        editButton.setOnAction(event -> {
            Tour selectedTour = (Tour) tourListView.getSelectionModel().getSelectedItem();
            if (selectedTour == null) {
                System.out.println("No item selected.");
                return;
            }
            setupStage(selectedTour);
        });
    }
    public void saveTour(Tour tour) {
        routBarViewModel.addTour(tour);
    }
    public void editTour(Tour tour){
        tourListView.refresh();
        routBarViewModel.editTour(tour);
    }
    private void setupStage(Tour selectedTour){

        FXMLLoader loader = new FXMLLoader(
                FXMLDependencyInjection.class.getResource("/at/fhtw/routplanner/view/addRoute.fxml"),
                ResourceBundle.getBundle("at.fhtw.routplanner.view." + "gui_strings", Locale.GERMAN)
        );
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        AddRouteController addRouteController = loader.getController();
        addRouteController.setStage(stage);
        addRouteController.setRoutBarController(this);
        addRouteController.setTour(selectedTour);
        addRouteController.setButtonAction();
        addRouteController.setComboBoxElements();

        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }
}
