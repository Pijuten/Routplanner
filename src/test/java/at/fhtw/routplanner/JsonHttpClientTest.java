package at.fhtw.routplanner;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

class JsonHttpClientTest {
    @Test
    public void GeocodeTest() throws InterruptedException {
        try(JsonHttpClient client = new JsonHttpClient()){
            CompletableFuture<String> futureResponse = client.sendRequestAsync(null,"http://localhost:8080/route/geocode?locationname=Spaungasse", JsonHttpClient.Method.GET);


        } catch(Exception e){
            e.printStackTrace();
        }
    }
}