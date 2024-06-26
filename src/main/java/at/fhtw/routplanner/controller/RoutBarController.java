package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.FXMLDependencyInjection;
import at.fhtw.routplanner.TourNameCell;
import at.fhtw.routplanner.model.Tour;
import at.fhtw.routplanner.viewModel.RoutBarViewModel;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Log4j
public class RoutBarController implements Initializable {
    final RoutBarViewModel routBarViewModel;
    public ListView<Tour> tourListView;
    public Button removeButton;
    public Button addButton;
    public Button editButton;
    public Button downButton;
    public Button upButton;


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
        upButtonListener(upButton);
        downButtonListener(downButton);
        editButton.setDisable(true);
        removeButton.setDisable(true);
        downButton.setDisable(true);
        upButton.setDisable(true);
        tourListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Tour>) change -> {
            editButton.setDisable(tourListView.getSelectionModel().getSelectedItems().isEmpty());
            removeButton.setDisable(tourListView.getSelectionModel().getSelectedItems().isEmpty());
            downButton.setDisable(tourListView.getSelectionModel().getSelectedItems().isEmpty());
            upButton.setDisable(tourListView.getSelectionModel().getSelectedItems().isEmpty());
        } );
    }

    private void downButtonListener(Button downButton) {
        downButton.setOnAction(event -> {
            int selectedTourPosition = tourListView.getSelectionModel().getSelectedItem().getTourPosition();
            routBarViewModel.changeTourPosition(selectedTourPosition, false);
        });
    }

    private void upButtonListener(Button upButton) {
        upButton.setOnAction(event -> {
            int selectedTourPosition = tourListView.getSelectionModel().getSelectedItem().getTourPosition();
            routBarViewModel.changeTourPosition(selectedTourPosition, true);
        });
    }


    private void removeButtonListener(Button removeButton) {
        removeButton.setOnAction(event -> {
            Tour selectedTour = tourListView.getSelectionModel().getSelectedItem();
            if (selectedTour != null) {
                Long id = selectedTour.getTourId();
                routBarViewModel.removeTour(id);
            } else {
                log.info("No item selected.");
            }
        });
    }

    private void addButtonListener(Button addButton) {

        addButton.setOnAction(event -> setupStage(null));
    }
    private void editButtonListener(Button editButton) {

        editButton.setOnAction(event -> {
            Tour selectedTour = tourListView.getSelectionModel().getSelectedItem();
            if (selectedTour == null) {
                log.info("No item selected.");
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
    private void setupStage(Tour selectedTour) {
        FXMLLoader loader = new FXMLLoader(
                FXMLDependencyInjection.class.getResource("/at/fhtw/routplanner/view/addRoute.fxml"),
                ResourceBundle.getBundle("at.fhtw.routplanner.view." + "gui_strings", Locale.GERMAN)
        );
        Parent root = null;
        AddRouteController addRouteController = new AddRouteController();
        addRouteController.setRoutBarController(this); // Set the controller here
        loader.setController(addRouteController);
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        // Ensure the controller is set after the FXMLLoader has loaded the FXML file
        addRouteController.setStage(stage);
        addRouteController.setTour(selectedTour);
        addRouteController.setButtonAction();
        addRouteController.setComboBoxElements();

        stage.setScene(scene);
        stage.setResizable(false);

        stage.initModality(Modality.WINDOW_MODAL);
        Window primaryStage = addButton.getScene().getWindow();
        stage.initOwner(primaryStage);

        stage.show();
    }
}
