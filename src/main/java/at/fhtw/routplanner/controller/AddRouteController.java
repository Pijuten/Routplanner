package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.enums.TransportType;
import at.fhtw.routplanner.model.Tour;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.Objects;
import java.util.ResourceBundle;


public class AddRouteController implements Initializable {
    public Button saveButton;
    public Button cancleButton;
    public ComboBox<String> vehicleComboBox;
    public TextField endpointTextField;
    public TextField tourNameTextField;
    public TextField startPointTextField;
    public TextField descriptionTextField;
    public Label tourNameErrorLabel;
    public Label descriptionErrorLabel;
    public Label startPointErrorLabel;
    public Label endPointErrorLabel;
    public Label vehicleSelectionErrorLabel;
    @Setter
    private Tour tour;
    @Setter
    private RoutBarController routBarController;
    @Setter
    private Stage stage;


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
        if(tour!=null){
            tourNameTextField.textProperty().bindBidirectional(new SimpleStringProperty(tour.getTourName()));
            descriptionTextField.textProperty().bindBidirectional(new SimpleStringProperty(tour.getDescription()));
            startPointTextField.textProperty().bindBidirectional(new SimpleStringProperty(tour.getStartPoint()));
            endpointTextField.textProperty().bindBidirectional(new SimpleStringProperty(tour.getEndPoint()));
            vehicleComboBox.valueProperty().bindBidirectional(new SimpleObjectProperty<>(tour.getTransportType().toString()));
        }
    }

    public void setButtonAction() {
        if (tour == null) {
            saveButton.setOnAction(actionEvent -> saveTour());
        } else {
            saveButton.setOnAction(actionEvent -> editTour());
        }
    }

    public void saveTour(){
        setErrorTextInvis();
        if(!checkInput()){
            return;
        }
        Tour tour = new Tour(tourNameTextField.getText(),descriptionTextField.getText(),startPointTextField.getText(),48.1787951,15.2300914,endpointTextField.getText(),47.2046171,11.0819367,TransportType.fromDisplayName(vehicleComboBox.getValue()),43.3,23.f,"test",null);
        routBarController.saveTour(tour);
        closeStage();
    }

    public void editTour() {
        setErrorTextInvis();
        if(!checkInput()){
            return;
        }
        tour.setTourName(tourNameTextField.getText());
        tour.setDescription(descriptionTextField.getText());
        tour.setStartPoint(startPointTextField.getText());
        tour.setEndPoint(endpointTextField.getText());
        tour.setTransportType(TransportType.fromDisplayName(vehicleComboBox.getValue()));
        routBarController.editTour(tour);
        closeStage();
    }

    private boolean checkInput(){
        if(Objects.equals(tourNameTextField.getText(), "") ){
            tourNameErrorLabel.setVisible(true);
            return false;
        } else if(Objects.equals(descriptionTextField.getText(), "") ){
            descriptionErrorLabel.setVisible(true);
            return false;
        }else if(Objects.equals(startPointTextField.getText(), "") ){
            startPointErrorLabel.setVisible(true);
            return false;
        }else if(Objects.equals(endpointTextField.getText(), "") ){
            endPointErrorLabel.setVisible(true);
            return false;
        }else if(Objects.equals(vehicleComboBox.getValue(), null) ){
            vehicleSelectionErrorLabel.setVisible(true);
            return false;
        }
        return true;
    }

    public void setErrorTextInvis(){
        tourNameErrorLabel.setVisible(false);
        descriptionErrorLabel.setVisible(false);
        startPointErrorLabel.setVisible(false);
        endPointErrorLabel.setVisible(false);
        vehicleSelectionErrorLabel.setVisible(false);
    }

    private void closeStage() {
        stage.close();
    }
}
