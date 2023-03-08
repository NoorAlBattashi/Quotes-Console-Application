package com.quotes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * The FileManager class is responsible for managing the storage, retrieval and creation of quote data
 */
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

    /**
     * This method checks for the existence of the data file and creates it if it does not exist
     *
     * @return boolean true if file exists, false otherwise
     */
    public boolean CheckAndCreateFile() {
        File file = new File(Data_FILE_PATH);
        if (file.exists()) {
            System.out.println("File exists.");
            System.out.println();
            return true;
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + Data_FILE_PATH);
                    System.out.println();
                    return false;
                } else {
                    System.out.println("File creation failed.");
                    System.out.println();
                    return false;
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                System.out.println();
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * This method reads data from the file and retrieves a quote
     */
    public void ReadJSONFile() {
        Gson gson = new Gson();
        try (BufferedReader reader = new BufferedReader(new FileReader(Data_FILE_PATH))) {
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Map<String, Object> quoteResponse = gson.fromJson(reader, type);
            //check key validation
            try {
                Map<String, Object> contents = (Map<String, Object>) quoteResponse.get("contents");
                ArrayList<Map<String, Object>> quotes = (ArrayList<Map<String, Object>>) contents.get("quotes");
                Map<String, Object> quote = quotes.get(0);
                String quoteText = (String) quote.get("quote");

                System.out.println("Quote: " + quoteText);
            } catch (NullPointerException e) {
                System.out.println();
                System.out.println("Not Found, please try again");
            }
        } catch (IndexOutOfBoundsException | FileNotFoundException e) {
            System.out.println();
            System.out.println("Not Found, please check your key");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * The file path for the quote data file
     */
    public static final String Data_FILE_PATH = "data/quote.json";
}

