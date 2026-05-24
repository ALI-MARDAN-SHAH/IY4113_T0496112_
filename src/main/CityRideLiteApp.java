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

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                addTestJourney();
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

    private void addTestJourney() {
        String date = "2026-05-24";
        int fromZone = 1;
        int toZone = 3;
        CityRideDataset.TimeBand timeBand = CityRideDataset.TimeBand.PEAK;
        CityRideDataset.PassengerType passengerType = CityRideDataset.PassengerType.ADULT;

        service.addJourney(date, fromZone, toZone, timeBand, passengerType);
    }
}