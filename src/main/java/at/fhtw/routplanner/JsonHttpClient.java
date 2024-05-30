package at.fhtw.routplanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j
public class JsonHttpClient implements Closeable {
    private final ExecutorService executor;

    public JsonHttpClient( ) {
        this.executor = Executors.newSingleThreadExecutor();
    }

    public <Request> CompletableFuture<String> sendRequestAsync(Request request, String url, Method method) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        executor.submit(() -> {
            try {
                String response = sendRequest(request, url, method);
                completableFuture.complete(response); // Complete CompletableFuture with the result
            } catch (Exception e) {
                completableFuture.completeExceptionally(e); // Complete CompletableFuture exceptionally if an error occurs
            }
        });
        return completableFuture;
    }

    private  <Request> String sendRequest(Request request,String url,Method method){
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod(method.name());
            connection.setRequestProperty("Content-Type", "application/json");

            if (method == Method.POST || method == Method.PUT) {
                connection.setDoOutput(true);
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                String jsonInputString = mapper.writeValueAsString(request);

                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            int responseCode = connection.getResponseCode();
            if(responseCode >= 200 && responseCode < 300){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            }
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void close() {
        executor.shutdown();
    }

    public enum Method {
        GET, POST, PUT, DELETE
    }
}
