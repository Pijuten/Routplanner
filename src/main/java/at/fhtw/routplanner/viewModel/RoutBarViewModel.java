package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.JsonHttpClient;
import at.fhtw.routplanner.Main;
import at.fhtw.routplanner.model.OpenRoute.Direction.Direction;
import at.fhtw.routplanner.model.OpenRoute.Geocode.Geocoding;
import at.fhtw.routplanner.model.Tour;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class RoutBarViewModel {

    public RoutBarViewModel(){
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            CompletableFuture<String> listCompletableFuture = jsonHttpClient.sendRequestAsync(null, "http://localhost:8080/tour/get", JsonHttpClient.Method.GET);
            listCompletableFuture.thenAcceptAsync(list -> {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                try {
                    List<Tour> tours1 = objectMapper.readValue(list, new TypeReference<List<Tour>>() {
                    });

                    tours.addAll(tours1);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }).exceptionally(ex -> {
                // Handle exceptions if CompletableFuture completes exceptionally
                ex.printStackTrace();
                return null; // Return a default value or handle the exception as needed
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    private ObservableList<Tour> tours = FXCollections.observableArrayList();

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
                ex.printStackTrace();
                return null;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            jsonHttpClient.sendRequestAsync(null, "http://localhost:8080/tour/remove?logId="+id, JsonHttpClient.Method.DELETE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void selectTour(){
    }
    private Direction direction;
    public CompletableFuture<Direction> getDirection(List<Double> coordinates) {
        CompletableFuture<Direction> future = new CompletableFuture<>();

        try (JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            CompletableFuture<String> tourFuture = jsonHttpClient.sendRequestAsync(coordinates, "http://localhost:8080/route/direction", JsonHttpClient.Method.POST);

            tourFuture.thenAcceptAsync(tourResponse -> {
                System.out.println(tourResponse);
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
                ex.printStackTrace();
                future.completeExceptionally(ex);
                return null;
            });
        } catch (IOException e) {
            future.completeExceptionally(e);
        }

        return future;
    }
    public void editTour(Tour tour) {
        try(JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            jsonHttpClient.sendRequestAsync(tour, "http://localhost:8080/tour/add", JsonHttpClient.Method.POST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
