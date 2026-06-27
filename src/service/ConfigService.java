package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.CityRideDataset;
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

    public boolean updateDiscount(CityRideDataset.PassengerType passengerType, double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
            System.out.println("Discount rate must be between 0.00 and 1.00.");
            return false;
        }

        if (passengerType == CityRideDataset.PassengerType.ADULT) {
            fareConfig.setAdultDiscount(discountRate);
        } else if (passengerType == CityRideDataset.PassengerType.STUDENT) {
            fareConfig.setStudentDiscount(discountRate);
        } else if (passengerType == CityRideDataset.PassengerType.CHILD) {
            fareConfig.setChildDiscount(discountRate);
        } else if (passengerType == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
            fareConfig.setSeniorCitizenDiscount(discountRate);
        }

        System.out.println("Discount updated.");
        return true;
    }

    public boolean updateDailyCap(CityRideDataset.PassengerType passengerType, double dailyCap) {
        if (dailyCap <= 0) {
            System.out.println("Daily cap must be greater than 0.");
            return false;
        }

        if (passengerType == CityRideDataset.PassengerType.ADULT) {
            fareConfig.setAdultDailyCap(dailyCap);
        } else if (passengerType == CityRideDataset.PassengerType.STUDENT) {
            fareConfig.setStudentDailyCap(dailyCap);
        } else if (passengerType == CityRideDataset.PassengerType.CHILD) {
            fareConfig.setChildDailyCap(dailyCap);
        } else if (passengerType == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
            fareConfig.setSeniorCitizenDailyCap(dailyCap);
        }

        System.out.println("Daily cap updated.");
        return true;
    }

    public boolean updatePeakTimes(String morningStart, String morningEnd,
                                   String eveningStart, String eveningEnd) {
        if (morningStart.isBlank() || morningEnd.isBlank()
                || eveningStart.isBlank() || eveningEnd.isBlank()) {
            System.out.println("Peak time values cannot be blank.");
            return false;
        }

        fareConfig.setMorningPeakStart(morningStart);
        fareConfig.setMorningPeakEnd(morningEnd);
        fareConfig.setEveningPeakStart(eveningStart);
        fareConfig.setEveningPeakEnd(eveningEnd);

        System.out.println("Peak times updated.");
        return true;
    }

    public FareConfig getFareConfig() {
        return fareConfig;
    }

    public void setFareConfig(FareConfig fareConfig) {
        this.fareConfig = fareConfig;
    }
}