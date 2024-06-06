package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.JsonHttpClient;
import at.fhtw.routplanner.model.Tour;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Log4j
public class OptionBarViewModel {
    private final RoutBarViewModel routBarViewModel;
    public OptionBarViewModel() {
        routBarViewModel = new RoutBarViewModel();
    }
    public void importTour(File selectedFile) {
        List<Tour> tours = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String content = Files.readString(Paths.get(selectedFile.getAbsolutePath()), StandardCharsets.UTF_8);
            System.out.println(content);
            tours = objectMapper.readValue(content, new TypeReference<>() {
            });
            for (Tour tour : tours) {
                routBarViewModel.addTour(tour);
            }
        } catch (IOException e) {
            log.error("Error occurred while importing tour: " + e.getMessage());
        }
    }

    public void exportTour(File selectedDirectory) {
        try {
            // Construct the file path
            String filePath = selectedDirectory.getAbsolutePath() + ".json";

            // Create a FileWriter object
            FileWriter writer = new FileWriter(filePath);

            // Wrap the FileWriter in a BufferedWriter for efficient writing
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            String tourData = fetchTours();
            if(tourData == null) {
                return;
            }
            // Write data to the file
            bufferedWriter.write(tourData);

            // Close the writer to release resources
            bufferedWriter.close();

            System.out.println("Tour data has been exported to: " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while exporting tour data: " + e.getMessage());
        }
    }

    private String fetchTours(){

        try (JsonHttpClient jsonHttpClient = new JsonHttpClient()) {
            CompletableFuture<String> listCompletableFuture = jsonHttpClient.sendRequestAsync(null, "http://localhost:8080/tour/get", JsonHttpClient.Method.GET);
            // Use thenApplyAsync to process the result when the CompletableFuture completes
            return listCompletableFuture.thenApplyAsync(response -> {
                // Process the response here
                return response;
            }).exceptionally(ex -> {
                // Handle exceptions if CompletableFuture completes exceptionally
                log.error("Error occurred while fetching tours: " + ex.getMessage());
                return null; // Return a default value or handle the exception as needed
            }).join(); // Wait for the CompletableFuture to complete and return the result
        } catch (Exception e) {
            log.error("Error occurred while fetching tours: " + e.getMessage());
            return null; // Return a default value or handle the exception as needed
        }
    }
}
