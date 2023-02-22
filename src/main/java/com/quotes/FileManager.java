package com.quotes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {


    /**
     * This method used to store the data in a file
     *
     * @param response
     */
    public void storeTheData(String response) {
        try (FileWriter fileWriter = new FileWriter(Data_FILE_PATH)) {
            fileWriter.write(response);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean CheckAndCreateFile() {
        File file = new File(Data_FILE_PATH);
        if (file.exists()) {
            System.out.println("File exists.");
            return true;
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + Data_FILE_PATH);
                    return false;
                } else {
                    System.out.println("File creation failed.");
                    return false;
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
                return false;
            }
        }
    }

    public void ReadJSONFile() {
        Gson gson = new Gson();
        try (BufferedReader reader = new BufferedReader(new FileReader(Data_FILE_PATH))) {
            Type type = new TypeToken<HashMap<String, Object>>() {
            }.getType();
            Map<String, Object> map = gson.fromJson(reader, type);
            //check key validation
            try {
                //display the desired data
                List<Object> contents = (List<Object>) map.get("contents");
                Map<String, Object> contentsMap = (Map<String, Object>) contents.get(0);

                //check if the destination is valid
                try {
                    List<Object> quotes = (List<Object>) contentsMap.get("quote");
                    Map<String, Object> quotesMap = (Map<String, Object>) quotes.get(0);

                    //Map<String, Object> quoteMap = (Map<String, Object>) quotesMap.get("quote");
                    String value = (String) quotesMap.get("quote");

                    System.out.println("The Quote Of Today: " + value);
                } catch (NullPointerException e) {
                    System.out.println();
                    System.out.println("Not Found, please try again");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println();
                System.out.println("Not Found, please check your key");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static final String Data_FILE_PATH = "data/quote.json";
}

