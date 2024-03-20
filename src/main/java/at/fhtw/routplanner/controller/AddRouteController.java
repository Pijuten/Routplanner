package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.enums.TransportType;
import at.fhtw.routplanner.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddRouteController implements Initializable {
    public Button saveButton;
    public Button cancleButton;
    public ComboBox vehicleComboBox;
    public Label errorText;
    public TextField endpointTextField;
    public TextField tourNameTextField;
    public TextField startPointTextField;
    public TextField descriptionTextField;
    @Setter
    private Tour tour;
    @Setter
    private RoutBarController routBarController;
    @Setter
    private Stage stage;
    @Setter
    private int flagAddOrEdit=0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancleButton.setOnAction(actionEvent -> stage.close());
    }

    public void setComboBoxElements() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (TransportType transportType: TransportType.values()) {
            items.add(transportType.toString());
        }
        vehicleComboBox.setItems(items);
        if(tour==null){
            System.out.println("No tour");
            return;
        }
        tourNameTextField.setText(tour.getTourName());
        descriptionTextField.setText(tour.getDescription());
        startPointTextField.setText(tour.getStartPoint());
        endpointTextField.setText(tour.getEndPoint());
        vehicleComboBox.setValue(tour.getTransportType().toString());
    }

    public void saveTour(){
        Tour tour = new Tour("we221e",tourNameTextField.getText(),descriptionTextField.getText(),startPointTextField.getText(),endpointTextField.getText(),TransportType.fromDisplayName(vehicleComboBox.getValue().toString()),43.3f,23.f,"test",null);
        routBarController.saveTour(tour);
        errorText.setVisible(true);
        stage.close();
    }

    public void editTour() {
        //Tour tour = new Tour("we221e",tourNameTextField.getText(),descriptionTextField.getText(),startPointTextField.getText(),endpointTextField.getText(),TransportType.fromDisplayName(vehicleComboBox.getValue().toString()),43.3f,23.f,"test",null);
        //routBarController.editTour(tour);
        tour.setTourName(tourNameTextField.getText());
        tour.setDescription(descriptionTextField.getText());
        tour.setStartPoint(startPointTextField.getText());
        tour.setEndPoint(endpointTextField.getText());
        tour.setTransportType(TransportType.fromDisplayName(vehicleComboBox.getValue().toString()));
        stage.close();
    }

    public void setEditButtonAction() {
        saveButton.setOnAction(actionEvent -> editTour());
    }
    public void setSaveButtonAction(){
        saveButton.setOnAction(actionEvent -> saveTour());
    }
}
