package com.quotes;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * The RequestManager class is responsible for sending HTTP requests to the quotes API
 * and returning the response body as a string.
 * It uses the OkHttp library for making HTTP requests and the Gson library for parsing JSON data.
 */
public class RequestManager {
    /**
     * Creates a new RequestManager object.
     */
    public RequestManager() {

    }

    /**
     * Sends a GET request to the quotes API and returns the response body as a string.
     *
     * @return the response body as a string
     * @throws IOException if an error occurs while sending the request or receiving the response
     */
    public String userRequest() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String responseBody;
        Request request = new Request.Builder()
                .url("https://quotes.rest/qod.json")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            responseBody = response.body().string(); // Save the response body to a variable
            if (responseBody.contains("error")) {
                System.out.println("Too many requests, please wait for 31 minutes and try again");
            } else {
                return responseBody;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

