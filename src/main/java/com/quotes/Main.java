package com.quotes;

import java.io.IOException;


public class Main {
    public static FileManager fileManager = new FileManager();
    public static RequestManager requestManager = new RequestManager();

    public static void main(String[] args) throws IOException {

            boolean fileExist = fileManager.CheckAndCreateFile();
            if (fileExist) {
                fileManager.ReadJSONFile();
            } else if (!fileExist) {
                fileManager.storeTheData(requestManager.userRequest());
                fileManager.ReadJSONFile();
            }
        }
    }

