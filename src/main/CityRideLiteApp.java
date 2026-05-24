package main;

import data.CityRideDataset;
import service.CityRideService;

import java.util.Scanner;

public class CityRideLiteApp {

    private Scanner scanner;
    private CityRideService service;

    public CityRideLiteApp() {
        scanner = new Scanner(System.in);
        service = new CityRideService();
    }

    public static void main(String[] args) {
        CityRideLiteApp app = new CityRideLiteApp();
        app.run();
    }

    private void run() {
        boolean running = true;

        System.out.println("===== CityRide Lite =====");

        while (running) {
            showMenu();

            int choice = getNumberInput("Choose option: ");

            if (choice == 1) {
                addJourney();
            } else if (choice == 2) {
                service.showAllJourneys();
            } else if (choice == 3) {
                System.out.println("Daily summary selected.");
            } else if (choice == 0) {
                System.out.println("Goodbye.");
                running = false;
            } else {
                System.out.println("Invalid option.");
            }

            System.out.println();
        }
    }

    private void showMenu() {
        System.out.println("------------------------------");
        System.out.println("1. Add journey");
        System.out.println("2. List journeys");
        System.out.println("3. Daily summary");
        System.out.println("0. Exit");
        System.out.println("------------------------------");
    }

    private void addJourney() {
        System.out.print("Enter date, for example 2026-05-24: ");
        String date = scanner.nextLine();

        int fromZone = getValidZone("Enter start zone 1-5: ");
        int toZone = getValidZone("Enter destination zone 1-5: ");

        CityRideDataset.TimeBand timeBand = getTimeBand();
        CityRideDataset.PassengerType passengerType = getPassengerType();

        service.addJourney(date, fromZone, toZone, timeBand, passengerType);
    }

    private int getValidZone(String message) {
        int zone = 0;
        boolean validZone = false;

        while (!validZone) {
            zone = getNumberInput(message);

            if (zone >= CityRideDataset.MIN_ZONE && zone <= CityRideDataset.MAX_ZONE) {
                validZone = true;
            } else {
                System.out.println("Zone must be between 1 and 5.");
            }
        }

        return zone;
    }

    private CityRideDataset.TimeBand getTimeBand() {
        CityRideDataset.TimeBand timeBand = CityRideDataset.TimeBand.PEAK;

        System.out.println("Choose time band:");
        System.out.println("1. Peak");
        System.out.println("2. Off-peak");

        int option = getNumberInput("Option: ");

        if (option == 2) {
            timeBand = CityRideDataset.TimeBand.OFF_PEAK;
        }

        return timeBand;
    }

    private CityRideDataset.PassengerType getPassengerType() {
        CityRideDataset.PassengerType passengerType = CityRideDataset.PassengerType.ADULT;

        System.out.println("Choose passenger type:");
        System.out.println("1. Adult");
        System.out.println("2. Student");
        System.out.println("3. Child");
        System.out.println("4. Senior Citizen");

        int option = getNumberInput("Option: ");

        if (option == 2) {
            passengerType = CityRideDataset.PassengerType.STUDENT;
        } else if (option == 3) {
            passengerType = CityRideDataset.PassengerType.CHILD;
        } else if (option == 4) {
            passengerType = CityRideDataset.PassengerType.SENIOR_CITIZEN;
        }

        return passengerType;
    }

    // Adapted from RustyKnights Scanner integer input checking idea (RustyKnight, 2022)
    private int getNumberInput(String message) {
        int number = 0;
        boolean validNumber = false;

        while (!validNumber) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                validNumber = true;
            } else {
                System.out.println("Please enter a number.");
            }

            scanner.nextLine();
        }

        return number;
    }
}