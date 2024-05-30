package at.fhtw.routplanner;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class JsonHttpClientTest {
    @Test
    void JsonHttpClientGetTest() throws ExecutionException, InterruptedException {
        try(JsonHttpClient client = new JsonHttpClient()) {
            CompletableFuture<String> completableFuture = client.sendRequestAsync(null, "https://jsonplaceholder.typicode.com/todos/1", JsonHttpClient.Method.GET);
            String result = completableFuture.get();
            assertEquals(result, "{  \"userId\": 1,  \"id\": 1,  \"title\": \"delectus aut autem\",  \"completed\": false}");
        }
    }
    @Test
    void JsonHttpClientPostTest() throws ExecutionException, InterruptedException {
        try(JsonHttpClient client = new JsonHttpClient()) {
            Post post = new Post("foo", "bar", 1);
            CompletableFuture<String> completableFuture = client.sendRequestAsync(post, "https://jsonplaceholder.typicode.com/posts", JsonHttpClient.Method.POST);
            String result = completableFuture.get();
            assertEquals("{  \"title\": \"foo\",  \"body\": \"bar\",  \"userId\": 1,  \"id\": 101}", result);
        }
    }
    @Test
    void testJsonHttpClientInvalidUrl()  {
        try(JsonHttpClient client = new JsonHttpClient()) {
            CompletableFuture<String> completableFuture = client.sendRequestAsync(null, "jsonplaceholder.typicode.com/todos/1", JsonHttpClient.Method.GET);
            assertThrows(ExecutionException.class, completableFuture::get);
        }
    }
}