package service;

import model.RiderProfile;

import java.util.Scanner;

public class RiderProfileService {

    private RiderProfile riderProfile;

    public RiderProfileService() {
        riderProfile = null;
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

    public RiderProfile getRiderProfile() {
        return riderProfile;
    }

    public void setRiderProfile(RiderProfile riderProfile) {
        this.riderProfile = riderProfile;
    }
}