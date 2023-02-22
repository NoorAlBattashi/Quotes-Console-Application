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

    public void userRequest() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://quotes.rest/qod.json")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected code " + response);
//            }
            String responseBody = response.body().string();
            if (responseBody.contains("error")) {
                // Handle the error response
//                System.out.println("Error response: " + responseBody);
                System.out.println("To many requests, please wait for 31 min then make new request");
            } else {
                Gson gson = new Gson();
                Type type = new TypeToken<HashMap<String, Object>>() {
                }.getType();
                System.out.println(response.body().string());
                this.json = response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getResponse() {
        return this.json;
    }
}
