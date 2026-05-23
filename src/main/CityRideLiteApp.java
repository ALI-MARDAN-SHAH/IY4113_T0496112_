package main;

import java.util.Scanner;

public class CityRideLiteApp {
    private Scanner scanner;

    public CityRideLiteApp() {
        scanner = new Scanner(System.in);
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
                System.out.println("Add journey selected.");
            } else if (choice == 2) {
                System.out.println("List journeys selected.");
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
}