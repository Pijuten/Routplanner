package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.CustomCombobox;
import at.fhtw.routplanner.JsonHttpClient;
import at.fhtw.routplanner.enums.TransportType;
import at.fhtw.routplanner.model.OpenRoute.Direction.Direction;
import at.fhtw.routplanner.model.OpenRoute.Geocode.Feature;
import at.fhtw.routplanner.model.OpenRoute.Geocode.Geocoding;
import at.fhtw.routplanner.model.Route;
import at.fhtw.routplanner.model.Tour;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
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
import lombok.extern.log4j.Log4j;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j
public class AddRouteController implements Initializable {
    public Button saveButton;
    public Button cancleButton;
    public ComboBox<String> vehicleComboBox;
    public CustomCombobox endpointTextField;
    public TextField tourNameTextField;
    public CustomCombobox startPointTextField;
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

    private ObservableList<String> startpointItems = FXCollections.observableArrayList();
    private ObservableList<String> endpointItems = FXCollections.observableArrayList();

    private Map<String, List<Double>> coordinatesMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancleButton.setOnAction(actionEvent -> stage.close());
        startPointTextField.setItems(startpointItems);
        startPointTextField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the new value is not already in the list
            boolean isNewItem = startPointTextField.getItems().stream().noneMatch(item -> item.equals(newValue));

            if (isNewItem) {
                updateEndpointTextField(newValue, startPointTextField, startpointItems);
                if (!startPointTextField.isShowing()) {
                    startPointTextField.show();
                }
            }
        });
        endpointTextField.setItems(endpointItems);
        endpointTextField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {

            updateEndpointTextField(newValue, endpointTextField, endpointItems);
            if (!endpointTextField.isShowing()) {
                endpointTextField.show();
            }
        });
    }

    private void updateEndpointTextField(String newValue, ComboBox<String> comboBox, ObservableList<String> observableList) {
        System.out.println("api calls");
        if (newValue.length() > 3) {
            try (JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
                CompletableFuture<String> jsonCompletableFuture = jsonHttpClient.sendRequestAsync(
                        null, "http://localhost:8080/route/geocode?locationname=" + newValue.replace(" ", "+"), JsonHttpClient.Method.GET
                );

                jsonCompletableFuture.handle((result, ex) -> {
                    if (ex != null) {
                        log.error("Request failed: " + ex.getMessage());
                        return "Error occurred";
                    } else {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            Geocoding geocoding = objectMapper.readValue(result, Geocoding.class);

                            // Collecting the results in a local list
                            List<String> names = new ArrayList<>();
                            for (Feature feature : geocoding.getFeatures()) {
                                String name = feature.getProperties().getName() + " " + feature.getProperties().getCountry_a();
                                names.add(name);
                                coordinatesMap.put(name, feature.getGeometry().getCoordinates());
                            }

                            // Update the observable list on the JavaFX Application Thread
                            Platform.runLater(() -> {
                                observableList.setAll(names);
                                if (!names.isEmpty()) {
                                    comboBox.show(); // Ensure the ComboBox dropdown is shown
                                }
                            });

                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        return result;
                    }
                });
            }
        } else {
            // Clear the list if the input is not valid
            Platform.runLater(observableList::clear);
        }
    }

    public void setComboBoxElements() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (TransportType transportType : TransportType.values()) {
            items.add(transportType.toString());
        }
        vehicleComboBox.setItems(items);
        if (tour != null) {
            tourNameTextField.textProperty().bindBidirectional(new SimpleStringProperty(tour.getTourName()));
            descriptionTextField.textProperty().bindBidirectional(new SimpleStringProperty(tour.getDescription()));
            startPointTextField.valueProperty().bindBidirectional(new SimpleStringProperty(tour.getStartPoint()));
            endpointTextField.valueProperty().bindBidirectional(new SimpleStringProperty(tour.getEndPoint()));
            vehicleComboBox.valueProperty().bindBidirectional(new SimpleObjectProperty<>(tour.getTransportType().toString()));
        }
    }

    public void setButtonAction() {
        if (tour == null) {
            saveButton.setOnAction(actionEvent -> {
                try {
                    saveTour();
                } catch (ExecutionException | InterruptedException e) {
                    log.error("No route found");
                }
            });
        } else {
            saveButton.setOnAction(actionEvent -> {
                try {
                    editTour();
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public void saveTour() throws ExecutionException, InterruptedException {
        setErrorTextInvis();
        if (!checkInput()) {
            return;
        }

        List<Double> startCoordinates = coordinatesMap.get(startPointTextField.getValue());
        List<Double> endCoordinates = coordinatesMap.get(endpointTextField.getValue());
        List<Double> coordinates = coordinatesMap.get(startPointTextField.getValue());
        coordinates.addAll(endCoordinates);
        Route route = new Route(coordinates, TransportType.fromDisplayName(vehicleComboBox.getValue()));
        Direction direction = routBarController.routBarViewModel.getDirection(route).get();

        Tour tour = new Tour(
                tourNameTextField.getText(),

                descriptionTextField.getText(),
                startPointTextField.getValue(),
                startCoordinates.get(0), startCoordinates.get(1),
                endpointTextField.getValue(),
                endCoordinates.get(0), endCoordinates.get(1),
                TransportType.fromDisplayName(vehicleComboBox.getValue()),
                direction.getFeatures().get(0).getProperties().getSummary().getDistance(),
                direction.getFeatures().get(0).getProperties().getSummary().getDuration(),
                "test",
                null
        );

        routBarController.saveTour(tour);
        closeStage();
    }

    public void editTour() throws ExecutionException, InterruptedException {
        setErrorTextInvis();
        if (!checkInput()) {
            return;
        }

        List<Double> startCoordinates = coordinatesMap.get(startPointTextField.getValue());
        List<Double> endCoordinates = coordinatesMap.get(endpointTextField.getValue());
        List<Double> coordinates = coordinatesMap.get(startPointTextField.getValue());
        coordinates.addAll(endCoordinates);
        Route route = new Route(coordinates, TransportType.fromDisplayName(vehicleComboBox.getValue()));
        Direction direction = routBarController.routBarViewModel.getDirection(route).get();

        tour.setTourName(tourNameTextField.getText());
        tour.setDescription(descriptionTextField.getText());
        tour.setStartPoint(startPointTextField.getValue());
        tour.setLatStartPoint(startCoordinates.get(0));
        tour.setLongStartPoint(startCoordinates.get(1));
        tour.setEndPoint(endpointTextField.getValue());
        tour.setLatEndPoint(endCoordinates.get(0));
        tour.setLongEndPoint(endCoordinates.get(1));
        tour.setTime(direction.getFeatures().get(0).getProperties().getSummary().getDuration());
        tour.setDistance(direction.getFeatures().get(0).getProperties().getSummary().getDistance());

                tour.setTransportType(TransportType.fromDisplayName(vehicleComboBox.getValue()));

        routBarController.editTour(tour);
        closeStage();
    }

    private boolean checkInput() {
        if (Objects.equals(tourNameTextField.getText(), "")) {
            tourNameErrorLabel.setVisible(true);
            return false;
        } else if (Objects.equals(descriptionTextField.getText(), "")) {
            descriptionErrorLabel.setVisible(true);
            return false;
        } else if (Objects.equals(startPointTextField.valueProperty().toString(), "")) {
            startPointErrorLabel.setVisible(true);
            return false;
        } else if (Objects.equals(endpointTextField.valueProperty().toString(), "")) {
            endPointErrorLabel.setVisible(true);
            return false;
        } else if (Objects.equals(vehicleComboBox.getValue(), null)) {
            vehicleSelectionErrorLabel.setVisible(true);
            return false;
        }
        return true;
    }

    public void setErrorTextInvis() {
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