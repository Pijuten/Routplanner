package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.JsonHttpClient;
import at.fhtw.routplanner.model.OpenRoute.Direction.Direction;
import at.fhtw.routplanner.model.Tour;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            CompletableFuture<String> tourFuture = jsonHttpClient.sendRequestAsync(tour, "http://localhost:8080/tour/add", JsonHttpClient.Method.POST);
            tourFuture.thenAcceptAsync(tourResponse -> {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                try {
                    Tour responseTour = objectMapper.readValue(tourResponse,Tour.class);
                    tour.setTourId(responseTour.getTourId());
                    Platform.runLater(() -> tours.add(tour));
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

    public CompletableFuture<Direction> getDirection(List<Double> coordinates) {
        CompletableFuture<Direction> future = new CompletableFuture<>();

        try (JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            CompletableFuture<String> tourFuture = jsonHttpClient.sendRequestAsync(coordinates, "http://localhost:8080/route/direction", JsonHttpClient.Method.POST);

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
}
