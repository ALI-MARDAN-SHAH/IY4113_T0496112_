package main;

import data.CityRideDataset;
import service.CityRideService;
import service.RiderProfileService;
import service.ConfigService;

import java.util.Scanner;

public class CityRideLiteApp {

    private Scanner scanner;
    private CityRideService service;
    private RiderProfileService riderProfileService;
    private ConfigService configService;

    public CityRideLiteApp() {
        scanner = new Scanner(System.in);
        configService = new ConfigService();
        service = new CityRideService(configService);
        riderProfileService = new RiderProfileService();
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
                service.showDailySummary();
            } else if (choice == 4) {
                filterJourneys();
            } else if (choice == 5) {
                service.showPassengerTypeTotals();
            } else if (choice == 6) {
                service.showCategoryCounts();
            } else if (choice == 7) {
                removeJourney();
            } else if (choice == 8) {
                resetDay();
            } else if (choice == 9) {
                riderProfileService.createProfile(scanner);
            } else if (choice == 10) {
                riderProfileService.displayProfile();
            } else if (choice == 11) {
                riderProfileService.saveProfile();
            } else if (choice == 12) {
                riderProfileService.loadProfile();
            } else if (choice == 13) {
                configService.displayConfig();
            } else if (choice == 14) {
                configService.saveConfig();
            } else if (choice == 15) {
                editJourney();
            } else if (choice == 16) {
                service.exportJourneysToCsv();
            } else if (choice == 17) {
                service.exportDailySummaryReports();
            } else if (choice == 18) {
                adminMenu();
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
        System.out.println("4. Filter journeys");
        System.out.println("5. Totals by passenger type");
        System.out.println("6. Category counts");
        System.out.println("7. Remove journey");
        System.out.println("8. Reset day");
        System.out.println("9. Create rider profile");
        System.out.println("10. View rider profile");
        System.out.println("11. Save rider profile");
        System.out.println("12. Load rider profile");
        System.out.println("13. View fare config");
        System.out.println("14. Save fare config");
        System.out.println("15. Edit journey");
        System.out.println("16. Export journeys to CSV");
        System.out.println("17. Export daily summary reports");
        System.out.println("18. Admin menu");
        System.out.println("0. Exit");
        System.out.println("------------------------------");
    }

    private void addJourney() {
        String date = getDateInput();

        int fromZone = getValidZone("Enter start zone 1-5: ");
        int toZone = getValidZone("Enter destination zone 1-5: ");

        CityRideDataset.TimeBand timeBand = getTimeBand();
        CityRideDataset.PassengerType passengerType = getPassengerType();

        service.addJourney(date, fromZone, toZone, timeBand, passengerType);
    }

    private String getDateInput() {
        String date = "";

        while (date.isBlank()) {
            System.out.print("Enter journey date (YYYY-MM-DD): ");
            date = scanner.nextLine();

            if (date.isBlank()) {
                System.out.println("Date cannot be blank.");
            }
        }

        return date;
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
        int option = 0;
        CityRideDataset.TimeBand timeBand = CityRideDataset.TimeBand.PEAK;

        while (option != 1 && option != 2) {
            System.out.println("Choose time band:");
            System.out.println("1. Peak");
            System.out.println("2. Off-peak");

            option = getNumberInput("Option: ");

            if (option == 1) {
                timeBand = CityRideDataset.TimeBand.PEAK;
            } else if (option == 2) {
                timeBand = CityRideDataset.TimeBand.OFF_PEAK;
            } else {
                System.out.println("Invalid time band.");
            }
        }

        return timeBand;
    }

    private CityRideDataset.PassengerType getPassengerType() {
        int option = 0;
        CityRideDataset.PassengerType passengerType = CityRideDataset.PassengerType.ADULT;

        while (option < 1 || option > 4) {
            System.out.println("Choose passenger type:");
            System.out.println("1. Adult");
            System.out.println("2. Student");
            System.out.println("3. Child");
            System.out.println("4. Senior Citizen");

            option = getNumberInput("Option: ");

            if (option == 1) {
                passengerType = CityRideDataset.PassengerType.ADULT;
            } else if (option == 2) {
                passengerType = CityRideDataset.PassengerType.STUDENT;
            } else if (option == 3) {
                passengerType = CityRideDataset.PassengerType.CHILD;
            } else if (option == 4) {
                passengerType = CityRideDataset.PassengerType.SENIOR_CITIZEN;
            } else {
                System.out.println("Invalid passenger type.");
            }
        }

        return passengerType;
    }
    private void filterJourneys() {
        System.out.println("Choose filter type:");
        System.out.println("1. Passenger type");
        System.out.println("2. Time band");
        System.out.println("3. Zone");
        System.out.println("4. Date");

        int option = getNumberInput("Option: ");

        if (option == 1) {
            CityRideDataset.PassengerType passengerType = getPassengerType();
            service.filterByPassengerType(passengerType);
        } else if (option == 2) {
            CityRideDataset.TimeBand timeBand = getTimeBand();
            service.filterByTimeBand(timeBand);
        } else if (option == 3) {
            int zone = getValidZone("Enter zone 1-5: ");
            service.filterByZone(zone);
        } else if (option == 4) {
            String date = getDateInput();
            service.filterByDate(date);
        } else {
            System.out.println("Invalid filter option.");
        }
    }

    private void removeJourney() {
        int journeyId = getNumberInput("Enter journey ID to remove: ");

        System.out.print("Are you sure you want to remove this journey? yes/no: ");
        String confirm = scanner.nextLine();

        if (confirm.equals("yes")) {
            service.removeJourney(journeyId);
        } else {
            System.out.println("Remove cancelled.");
        }
    }

    private void editJourney() {
        int journeyId = getNumberInput("Enter journey ID to edit: ");

        System.out.println("Enter the new journey details.");

        String date = getDateInput();

        int fromZone = getValidZone("Enter new start zone 1-5: ");
        int toZone = getValidZone("Enter new destination zone 1-5: ");

        CityRideDataset.TimeBand timeBand = getTimeBand();
        CityRideDataset.PassengerType passengerType = getPassengerType();

        service.editJourney(journeyId, date, fromZone, toZone, timeBand, passengerType);
    }

    private void resetDay() {
        System.out.print("Are you sure you want to reset the day? yes/no: ");
        String confirm = scanner.nextLine();

        if (confirm.equals("yes")) {
            service.resetDay();
        } else {
            System.out.println("Reset cancelled.");
        }
    }

    private void adminMenu() {
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (!password.equals("admin123")) {
            System.out.println("Incorrect admin password.");
            return;
        }

        boolean adminRunning = true;

        while (adminRunning) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. View fare config");
            System.out.println("2. Update discount");
            System.out.println("3. Update daily cap");
            System.out.println("4. Update peak times");
            System.out.println("5. Save fare config");
            System.out.println("0. Return to main menu");

            int option = getNumberInput("Choose admin option: ");

            if (option == 1) {
                configService.displayConfig();
            } else if (option == 2) {
                updateDiscount();
            } else if (option == 3) {
                updateDailyCap();
            } else if (option == 4) {
                updatePeakTimes();
            } else if (option == 5) {
                configService.saveConfig();
            } else if (option == 0) {
                adminRunning = false;
            } else {
                System.out.println("Invalid admin option.");
            }
        }
    }

    private void updateDiscount() {
        CityRideDataset.PassengerType passengerType = getPassengerType();

        System.out.println("Enter discount as a decimal.");
        System.out.println("Example: 0.25 means 25% discount.");

        double discountRate = getDoubleInput("Enter new discount rate: ");

        configService.updateDiscount(passengerType, discountRate);
    }

    private void updateDailyCap() {
        CityRideDataset.PassengerType passengerType = getPassengerType();

        double dailyCap = getDoubleInput("Enter new daily cap: £");

        configService.updateDailyCap(passengerType, dailyCap);
    }

    private void updatePeakTimes() {
        System.out.print("Enter morning peak start time: ");
        String morningStart = scanner.nextLine();

        System.out.print("Enter morning peak end time: ");
        String morningEnd = scanner.nextLine();

        System.out.print("Enter evening peak start time: ");
        String eveningStart = scanner.nextLine();

        System.out.print("Enter evening peak end time: ");
        String eveningEnd = scanner.nextLine();

        configService.updatePeakTimes(morningStart, morningEnd, eveningStart, eveningEnd);
    }

    private double getDoubleInput(String message) {
        double number = 0;
        boolean validNumber = false;

        while (!validNumber) {
            System.out.print(message);

            if (scanner.hasNextDouble()) {
                number = scanner.nextDouble();
                validNumber = true;
            } else {
                System.out.println("Please enter a valid number.");
            }

            scanner.nextLine();
        }

        return number;
    }

    // Adapted from RustyKnight's Scanner integer input checking idea
    // (RustyKnight, 2022)
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