package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.JsonHttpClient;
import at.fhtw.routplanner.model.OpenRoute.Direction.Direction;
import at.fhtw.routplanner.model.OpenRoute.Geocode.Feature;
import at.fhtw.routplanner.model.OpenRoute.Geocode.Geocoding;
import at.fhtw.routplanner.model.Route;
import at.fhtw.routplanner.model.Tour;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import java.util.*;
import java.util.concurrent.CompletableFuture;


@Log4j
public class RoutBarViewModel {

    public RoutBarViewModel(){
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            CompletableFuture<String> listCompletableFuture = jsonHttpClient.sendRequestAsync(null, "http://localhost:8080/tour/get", JsonHttpClient.Method.GET);
            listCompletableFuture.thenAcceptAsync(list -> {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                try {
                    List<Tour> tours1 = objectMapper.readValue(list, new TypeReference<>() {
                    });

                    tours.addAll(tours1);
                    FXCollections.sort(tours, Comparator.comparingInt(Tour::getTourPosition));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }).exceptionally(ex -> {
                // Handle exceptions if CompletableFuture completes exceptionally
                log.error(ex);
                return null; // Return a default value or handle the exception as needed
            });
        }catch(Exception e){
            log.error(e);
        }

    }
    private final ObservableList<Tour> tours = FXCollections.observableArrayList();

    public ObservableList<Tour> getTours(){
        return tours;
    }
    public void addTour(Tour tour){
        tour.setTourPosition(tours.size());
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            CompletableFuture<String> tourFuture = jsonHttpClient.sendRequestAsync(tour, "http://localhost:8080/tour/add", JsonHttpClient.Method.POST);
            tourFuture.thenAcceptAsync(tourResponse -> {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                try {
                    Tour responseTour = objectMapper.readValue(tourResponse,Tour.class);
                    tour.setTourId(responseTour.getTourId());
                    Platform.runLater(() -> {
                        tours.add(tour);
                    });
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }).exceptionally(ex -> {
                // Handle exceptions if CompletableFuture completes exceptionally
                log.error(ex);
                return null;
            });
        }
    }
    public void removeTour(Long id){
        for (Iterator<Tour> iterator = tours.iterator(); iterator.hasNext();) {
            Tour item = iterator.next();
            if (Objects.equals(item.getTourId(), id)) {
                iterator.remove();
                break;
            }
        }

        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            jsonHttpClient.sendRequestAsync(null, "http://localhost:8080/tour/remove?tourId="+id, JsonHttpClient.Method.DELETE);
        }
        log.info("Tour removed id: "+id);
    }

    public CompletableFuture<Direction> getDirection(Route route) {
        CompletableFuture<Direction> future = new CompletableFuture<>();

        try (JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            CompletableFuture<String> tourFuture = jsonHttpClient.sendRequestAsync(route, "http://localhost:8080/route/direction", JsonHttpClient.Method.POST);

            tourFuture.thenAcceptAsync(tourResponse -> {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                try {
                    Direction direction = objectMapper.readValue(tourResponse, Direction.class);
                    future.complete(direction);
                } catch (JsonProcessingException e) {
                    future.completeExceptionally(e);
                }
            }).exceptionally(ex -> {
                // Handle exceptions if CompletableFuture completes exceptionally
                log.error(ex);
                future.completeExceptionally(ex);
                return null;
            });
        }
        log.info("direction received");
        return future;
    }
    public void editTour(Tour tour) {
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            jsonHttpClient.sendRequestAsync(tour, "http://localhost:8080/tour/add", JsonHttpClient.Method.POST);
        }
        log.info("Tour edited");
    }

    public void changeTourPosition( int tourPosition, boolean up) {
        // Ensure the position is valid
        if (tourPosition < 0 || tourPosition >= tours.size()) {
            throw new IndexOutOfBoundsException("Invalid tour position");
        }

        int swapPosition = up ? tourPosition - 1 : tourPosition + 1;

        // Ensure the swap position is valid
        if (swapPosition < 0 || swapPosition >= tours.size()) {
            return; // No swap if the swap position is out of bounds
        }

        // Perform the swap
        Tour temp = tours.get(tourPosition);
        tours.get(tourPosition).setTourPosition(swapPosition);
        tours.get(swapPosition).setTourPosition(tourPosition);
        tours.set(tourPosition, tours.get(swapPosition));
        tours.set(swapPosition, temp);
        for(Tour tour: tours){
            editTour(tour);
        }
    }

    @Getter
    private ObservableList<String> startpointItems = FXCollections.observableArrayList();
    @Getter
    private ObservableList<String> endpointItems = FXCollections.observableArrayList();

    @Getter
    private Map<String, List<Double>> coordinatesMap = new HashMap<>();
    public void updateEndpointTextField(String newValue, ComboBox<String> comboBox, ObservableList<String> observableList) {
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
}
