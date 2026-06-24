package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.FareConfig;

import java.io.FileReader;
import java.io.FileWriter;

public class ConfigService {

    private FareConfig fareConfig;
    private final Gson gson;
    private final String fileName;

    public ConfigService() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        fileName = "fare_config.json";
        fareConfig = loadConfig();
    }

    public FareConfig loadConfig() {
        FareConfig loadedConfig = new FareConfig();

        try {
            FileReader reader = new FileReader(fileName);
            loadedConfig = gson.fromJson(reader, FareConfig.class);
            reader.close();

            if (loadedConfig == null) {
                loadedConfig = new FareConfig();
                System.out.println("Config file was empty. Safe default config loaded.");
            } else {
                System.out.println("Config loaded from JSON file.");
            }

        } catch (Exception e) {
            System.out.println("No config file found. Safe default config loaded.");
        }

        return loadedConfig;
    }

    public void saveConfig() {
        try {
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(fareConfig, writer);
            writer.close();

            System.out.println("Config saved to JSON file.");
        } catch (Exception e) {
            System.out.println("Error saving config file.");
        }
    }

    public void displayConfig() {
        System.out.println("\n--- Active Fare Config ---");
        System.out.println("Adult Discount: " + fareConfig.getAdultDiscount());
        System.out.println("Student Discount: " + fareConfig.getStudentDiscount());
        System.out.println("Child Discount: " + fareConfig.getChildDiscount());
        System.out.println("Senior Citizen Discount: " + fareConfig.getSeniorCitizenDiscount());

        System.out.println("Adult Daily Cap: " + fareConfig.getAdultDailyCap());
        System.out.println("Student Daily Cap: " + fareConfig.getStudentDailyCap());
        System.out.println("Child Daily Cap: " + fareConfig.getChildDailyCap());
        System.out.println("Senior Citizen Daily Cap: " + fareConfig.getSeniorCitizenDailyCap());

        System.out.println("Morning Peak: " + fareConfig.getMorningPeakStart() + " - " + fareConfig.getMorningPeakEnd());
        System.out.println("Evening Peak: " + fareConfig.getEveningPeakStart() + " - " + fareConfig.getEveningPeakEnd());
    }

    public FareConfig getFareConfig() {
        return fareConfig;
    }

    public void setFareConfig(FareConfig fareConfig) {
        this.fareConfig = fareConfig;
    }
}