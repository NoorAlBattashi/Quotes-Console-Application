package com.quotes;

import com.google.gson.Gson;

import java.io.IOException;


public class Main {
    public static FileManager fileManager = new FileManager();
    public static RequestManager requestManager = new RequestManager();

    public static void main(String[] args) throws IOException {
        requestManager.userRequest();
        if (requestManager.getResponse() != null) {
            //check file existence and display the results
            boolean fileExist = fileManager.CheckAndCreateFile();
            if (fileExist) {
                fileManager.ReadJSONFile();
            } else if (!fileExist) {
                requestManager.userRequest();
                fileManager.storeTheData(requestManager.getResponse());
                fileManager.ReadJSONFile();
            }
        }
    }
}
