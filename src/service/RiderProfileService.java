package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.RiderProfile;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class RiderProfileService {

    private RiderProfile riderProfile;
    private Gson gson;
    private String fileName;

    public RiderProfileService() {
        riderProfile = null;
        gson = new GsonBuilder().setPrettyPrinting().create();
        fileName = "rider_profile.json";
    }

    public void createProfile(Scanner scanner) {
        System.out.println("\n--- Create Rider Profile ---");

        System.out.print("Enter rider name: ");
        String riderName = scanner.nextLine();

        System.out.print("Enter passenger type (Adult/Student/Child/Senior Citizen): ");
        String passengerType = scanner.nextLine();

        System.out.print("Enter default payment option (Card/Cash): ");
        String defaultPayment = scanner.nextLine();

        riderProfile = new RiderProfile(riderName, passengerType, defaultPayment);

        System.out.println("Rider profile created successfully.");
    }

    public void displayProfile() {
        if (riderProfile == null) {
            System.out.println("No rider profile found.");
        } else {
            System.out.println("\n--- Rider Profile ---");
            System.out.println("Name: " + riderProfile.getRiderName());
            System.out.println("Passenger Type: " + riderProfile.getPassengerType());
            System.out.println("Default Payment: " + riderProfile.getDefaultPayment());
        }
    }

    public void saveProfile() {
        if (riderProfile == null) {
            System.out.println("No rider profile to save.");
        } else {
            try {
                FileWriter writer = new FileWriter(fileName);
                gson.toJson(riderProfile, writer);
                writer.close();

                System.out.println("Rider profile saved to JSON file.");
            } catch (Exception e) {
                System.out.println("Error saving rider profile.");
            }
        }
    }

    public void loadProfile() {
        try {
            FileReader reader = new FileReader(fileName);
            riderProfile = gson.fromJson(reader, RiderProfile.class);
            reader.close();

            if (riderProfile == null) {
                System.out.println("Profile file was empty.");
            } else {
                System.out.println("Rider profile loaded from JSON file.");
            }
        } catch (Exception e) {
            System.out.println("No rider profile file found.");
        }
    }

    public RiderProfile getRiderProfile() {
        return riderProfile;
    }

    public void setRiderProfile(RiderProfile riderProfile) {
        this.riderProfile = riderProfile;
    }
}