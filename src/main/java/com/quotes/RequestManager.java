package com.quotes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class RequestManager {
    String json;

    public RequestManager() {

    }

    public String userRequest() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String responseBody;
        Request request = new Request.Builder()
                .url("https://quotes.rest/qod.json")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            responseBody = response.body().string(); // Save the response body to a variable
            // System.out.println(responseBody);
            if (responseBody.contains("error")) {
                System.out.println("Too many requests, please wait for 31 minutes and try again");
            } else {
//                Gson gson = new Gson();
//                Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
//                HashMap<String, Object> quoteResponse = gson.fromJson(responseBody, type);
//
//                // Do something with the deserialized response
//                //System.out.println("Quote: " + quoteResponse.get("quote"));
                return responseBody;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

