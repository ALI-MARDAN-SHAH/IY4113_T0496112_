package service;

import data.CityRideDataset;
import model.Journey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CityRideService {

    // This list stores journeys while the program is running.
    private ArrayList<Journey> journeys;
    private int nextJourneyId;
    private ConfigService configService;

    public CityRideService() {
        this(new ConfigService());
    }

    public CityRideService(ConfigService configService) {
        this.configService = configService;
        journeys = new ArrayList<>();
        nextJourneyId = 1;
    }

    public void addJourney(String date, int fromZone, int toZone,
                           CityRideDataset.TimeBand timeBand,
                           CityRideDataset.PassengerType passengerType) {
        BigDecimal baseFare = configService.getBaseFare(fromZone, toZone, timeBand);
        BigDecimal discountRate = configService.getDiscountRate(passengerType);
        BigDecimal discountAmount = baseFare.multiply(discountRate);
        BigDecimal discountedFare = baseFare.subtract(discountAmount);

        BigDecimal chargedFare = applyDailyCap(passengerType, discountedFare);

        Journey journey = new Journey(nextJourneyId, date, fromZone, toZone,
                timeBand, passengerType, baseFare, discountAmount, chargedFare);

        journeys.add(journey);
        nextJourneyId++;

        System.out.println("Journey added.");
        System.out.println("Fare charged: £" + String.format("%.2f", chargedFare));
    }

    public void showAllJourneys() {
        if (journeys.size() == 0) {
            System.out.println("No journeys added yet.");
            return;
        }

        for (Journey journey : journeys) {
            System.out.println(journey.showJourneyDetails());
        }
    }

    public void showDailySummary() {
        if (journeys.size() == 0) {
            System.out.println("No journeys added yet.");
            return;
        }

        BigDecimal totalCost = new BigDecimal("0.00");
        Journey mostExpensiveJourney = journeys.get(0);

        for (Journey journey : journeys) {
            totalCost = totalCost.add(journey.getChargedFare());

            if (journey.getChargedFare().compareTo(mostExpensiveJourney.getChargedFare()) > 0) {
                mostExpensiveJourney = journey;
            }
        }

        BigDecimal averageCost = totalCost.divide(
                BigDecimal.valueOf(journeys.size()), 2, RoundingMode.HALF_UP);

        System.out.println("===== Daily Summary =====");
        System.out.println("Total journeys: " + journeys.size());
        System.out.println("Total cost: £" + String.format("%.2f", totalCost));
        System.out.println("Average cost: £" + String.format("%.2f", averageCost));
        System.out.println("Most expensive journey ID: " + mostExpensiveJourney.getId());
        System.out.println("Most expensive journey cost: £" + String.format("%.2f", mostExpensiveJourney.getChargedFare()));
    }

    public void exportJourneysToCsv() {
        if (journeys.size() == 0) {
            System.out.println("No journeys to export.");
            return;
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("journeys_export.csv"));

            writer.println("id,date,fromZone,toZone,timeBand,passengerType,zonesCrossed,baseFare,discountAmount,chargedFare");

            for (Journey journey : journeys) {
                writer.println(journey.getId() + ","
                        + journey.getDate() + ","
                        + journey.getFromZone() + ","
                        + journey.getToZone() + ","
                        + journey.getTimeBand() + ","
                        + journey.getPassengerType() + ","
                        + journey.getZonesCrossed() + ","
                        + String.format("%.2f", journey.getBaseFare()) + ","
                        + String.format("%.2f", journey.getDiscountAmount()) + ","
                        + String.format("%.2f", journey.getChargedFare()));
            }

            writer.close();

            System.out.println("Journeys exported to journeys_export.csv");
        } catch (Exception e) {
            System.out.println("Error exporting journeys to CSV.");
        }
    }

    private boolean isValidZone(int zone) {
        return zone >= CityRideDataset.MIN_ZONE && zone <= CityRideDataset.MAX_ZONE;
    }

    private CityRideDataset.TimeBand parseTimeBand(String value) {
        CityRideDataset.TimeBand timeBand = null;

        if (value.equals("PEAK")) {
            timeBand = CityRideDataset.TimeBand.PEAK;
        } else if (value.equals("OFF_PEAK")) {
            timeBand = CityRideDataset.TimeBand.OFF_PEAK;
        }

        return timeBand;
    }

    private CityRideDataset.PassengerType parsePassengerType(String value) {
        CityRideDataset.PassengerType passengerType = null;

        if (value.equals("ADULT")) {
            passengerType = CityRideDataset.PassengerType.ADULT;
        } else if (value.equals("STUDENT")) {
            passengerType = CityRideDataset.PassengerType.STUDENT;
        } else if (value.equals("CHILD")) {
            passengerType = CityRideDataset.PassengerType.CHILD;
        } else if (value.equals("SENIOR_CITIZEN")) {
            passengerType = CityRideDataset.PassengerType.SENIOR_CITIZEN;
        }

        return passengerType;
    }

    public void importJourneysFromCsv(String fileName) {
        int importedCount = 0;
        int failedCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");

                if (columns.length < 6) {
                    failedCount++;
                } else {
                    try {
                        String date = columns[1];
                        int fromZone = Integer.parseInt(columns[2]);
                        int toZone = Integer.parseInt(columns[3]);
                        CityRideDataset.TimeBand timeBand = parseTimeBand(columns[4]);
                        CityRideDataset.PassengerType passengerType = parsePassengerType(columns[5]);

                        if (isValidZone(fromZone) && isValidZone(toZone)
                                && timeBand != null && passengerType != null
                                && !date.isBlank()) {
                            addJourney(date, fromZone, toZone, timeBand, passengerType);
                            importedCount++;
                        } else {
                            failedCount++;
                        }
                    } catch (Exception e) {
                        failedCount++;
                    }
                }
            }

            reader.close();

            System.out.println("CSV import complete.");
            System.out.println("Imported journeys: " + importedCount);
            System.out.println("Failed rows: " + failedCount);
        } catch (Exception e) {
            System.out.println("Error importing journeys from CSV file.");
        }
    }

    public void exportDailySummaryReports() {
        if (journeys.size() == 0) {
            System.out.println("No journeys to export in report.");
            return;
        }

        BigDecimal totalCost = new BigDecimal("0.00");
        Journey mostExpensiveJourney = journeys.get(0);

        for (Journey journey : journeys) {
            totalCost = totalCost.add(journey.getChargedFare());

            if (journey.getChargedFare().compareTo(mostExpensiveJourney.getChargedFare()) > 0) {
                mostExpensiveJourney = journey;
            }
        }

        BigDecimal averageCost = totalCost.divide(
                BigDecimal.valueOf(journeys.size()), 2, RoundingMode.HALF_UP);


        try {
            PrintWriter textWriter = new PrintWriter(new FileWriter("daily_summary_report.txt"));

            textWriter.println("===== CityRide Lite Daily Summary Report =====");
            textWriter.println();
            textWriter.println("Total journeys: " + journeys.size());
            textWriter.println("Total cost: £" + String.format("%.2f", totalCost));
            textWriter.println("Average cost: £" + String.format("%.2f", averageCost));
            textWriter.println("Most expensive journey ID: " + mostExpensiveJourney.getId());
            textWriter.println("Most expensive journey cost: £" + String.format("%.2f", mostExpensiveJourney.getChargedFare()));
            textWriter.println();
            textWriter.println("Passenger type totals:");
            textWriter.println("Adult total: £" + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.ADULT)));
            textWriter.println("Student total: £" + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.STUDENT)));
            textWriter.println("Child total: £" + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.CHILD)));
            textWriter.println("Senior Citizen total: £" + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.SENIOR_CITIZEN)));

            textWriter.close();

            PrintWriter csvWriter = new PrintWriter(new FileWriter("daily_summary_report.csv"));

            csvWriter.println("item,value");
            csvWriter.println("Total journeys," + journeys.size());
            csvWriter.println("Total cost," + String.format("%.2f", totalCost));
            csvWriter.println("Average cost," + String.format("%.2f", averageCost));
            csvWriter.println("Most expensive journey ID," + mostExpensiveJourney.getId());
            csvWriter.println("Most expensive journey cost," + String.format("%.2f", mostExpensiveJourney.getChargedFare()));
            csvWriter.println("Adult total," + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.ADULT)));
            csvWriter.println("Student total," + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.STUDENT)));
            csvWriter.println("Child total," + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.CHILD)));
            csvWriter.println("Senior Citizen total," + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.SENIOR_CITIZEN)));

            csvWriter.close();

            System.out.println("Daily summary reports exported to daily_summary_report.txt and daily_summary_report.csv");
        } catch (Exception e) {
            System.out.println("Error exporting daily summary reports.");
        }
    }

    public void showPassengerTypeTotals() {
        if (journeys.size() == 0) {
            System.out.println("No journeys added yet.");
            return;
        }

        System.out.println("===== Passenger Type Totals =====");
        showTotalForPassengerType(CityRideDataset.PassengerType.ADULT);
        showTotalForPassengerType(CityRideDataset.PassengerType.STUDENT);
        showTotalForPassengerType(CityRideDataset.PassengerType.CHILD);
        showTotalForPassengerType(CityRideDataset.PassengerType.SENIOR_CITIZEN);
    }

    private void showTotalForPassengerType(CityRideDataset.PassengerType passengerType) {
        int count = 0;
        BigDecimal baseTotal = new BigDecimal("0.00");
        BigDecimal discountTotal = new BigDecimal("0.00");
        BigDecimal chargedTotal = new BigDecimal("0.00");

        for (Journey journey : journeys) {
            if (journey.getPassengerType() == passengerType) {
                count++;
                baseTotal = baseTotal.add(journey.getBaseFare());
                discountTotal = discountTotal.add(journey.getDiscountAmount());
                chargedTotal = chargedTotal.add(journey.getChargedFare());
            }
        }

        BigDecimal dailyCap = configService.getDailyCap(passengerType);
        String capReached = "No";

        if (chargedTotal.compareTo(dailyCap) >= 0) {
            capReached = "Yes";
        }

        System.out.println(passengerType + " journeys: " + count
                + " | Base total: £" + String.format("%.2f", baseTotal)
                + " | Discount total: £" + String.format("%.2f", discountTotal)
                + " | Charged total: £" + String.format("%.2f", chargedTotal)
                + " | Cap reached: " + capReached);
    }

    public void showCategoryCounts() {
        if (journeys.size() == 0) {
            System.out.println("No journeys added yet.");
            return;
        }

        int peakCount = 0;
        int offPeakCount = 0;

        for (Journey journey : journeys) {
            if (journey.getTimeBand() == CityRideDataset.TimeBand.PEAK) {
                peakCount++;
            } else {
                offPeakCount++;
            }
        }

        System.out.println("===== Category Counts =====");
        System.out.println("Peak journeys: " + peakCount);
        System.out.println("Off-peak journeys: " + offPeakCount);

        for (int zone = CityRideDataset.MIN_ZONE; zone <= CityRideDataset.MAX_ZONE; zone++) {
            int zoneCount = countJourneysUsingZone(zone);
            System.out.println("Journeys using zone " + zone + ": " + zoneCount);
        }
    }

    private int countJourneysUsingZone(int zone) {
        int count = 0;

        for (Journey journey : journeys) {
            if (journeyUsesZone(journey, zone)) {
                count++;
            }
        }

        return count;
    }
    // Adapted from Johnson's integer range checking idea and modified for CityRide zone filtering
    // (Johnson, 2008)
    private boolean journeyUsesZone(Journey journey, int zone) {
        int smallerZone = journey.getFromZone();
        int largerZone = journey.getToZone();

        if (smallerZone > largerZone) {
            smallerZone = journey.getToZone();
            largerZone = journey.getFromZone();
        }

        return zone >= smallerZone && zone <= largerZone;
    }
    public void filterByPassengerType(CityRideDataset.PassengerType passengerType) {
        ArrayList<Journey> results = new ArrayList<>();

        for (Journey journey : journeys) {
            if (journey.getPassengerType() == passengerType) {
                results.add(journey);
            }
        }

        showFilterResults(results);
    }

    public void filterByTimeBand(CityRideDataset.TimeBand timeBand) {
        ArrayList<Journey> results = new ArrayList<>();

        for (Journey journey : journeys) {
            if (journey.getTimeBand() == timeBand) {
                results.add(journey);
            }
        }

        showFilterResults(results);
    }

    public void filterByZone(int zone) {
        ArrayList<Journey> results = new ArrayList<>();

        for (Journey journey : journeys) {
            if (journeyUsesZone(journey, zone)) {
                results.add(journey);
            }
        }

        showFilterResults(results);
    }

    public void filterByDate(String date) {
        ArrayList<Journey> results = new ArrayList<>();

        for (Journey journey : journeys) {
            if (journey.getDate().equals(date)) {
                results.add(journey);
            }
        }

        showFilterResults(results);
    }

    private void showFilterResults(ArrayList<Journey> results) {
        if (results.size() == 0) {
            System.out.println("No matching journeys found.");
            return;
        }

        for (Journey journey : results) {
            System.out.println(journey.showJourneyDetails());
        }
    }
    // Adapted from Raghuvorkady's ArrayList removal method and modified to remove a Journey by ID
    // (Raghuvorkady, 2021)
    public boolean removeJourney(int journeyId) {
        for (int i = 0; i < journeys.size(); i++) {
            Journey journey = journeys.get(i);

            if (journey.getId() == journeyId) {
                journeys.remove(i);
                recalculateAllJourneyFares();
                System.out.println("Journey removed and totals recalculated.");
                return true;
            }
        }

        System.out.println("Journey ID not found.");
        return false;
    }

    public boolean editJourney(int journeyId, String date, int fromZone, int toZone,
                               CityRideDataset.TimeBand timeBand,
                               CityRideDataset.PassengerType passengerType) {
        boolean journeyEdited = false;

        for (Journey journey : journeys) {
            if (journey.getId() == journeyId) {
                journey.setDate(date);
                journey.setFromZone(fromZone);
                journey.setToZone(toZone);
                journey.setTimeBand(timeBand);
                journey.setPassengerType(passengerType);

                recalculateAllJourneyFares();

                System.out.println("Journey updated.");
                journeyEdited = true;
            }
        }

        if (!journeyEdited) {
            System.out.println("Journey ID not found.");
        }

        return journeyEdited;
    }

    public void resetDay() {
        journeys.clear();
        nextJourneyId = 1;
        System.out.println("Day reset complete.");
    }

    public ArrayList<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(ArrayList<Journey> journeys) {
        if (journeys == null) {
            this.journeys = new ArrayList<>();
        } else {
            this.journeys = journeys;
        }

        recalculateAllJourneyFares();
    }

    public int getNextJourneyId() {
        return nextJourneyId;
    }

    public void setNextJourneyId(int nextJourneyId) {
        if (nextJourneyId < 1) {
            this.nextJourneyId = 1;
        } else {
            this.nextJourneyId = nextJourneyId;
        }
    }

    public void recalculateAllJourneyFares() {
        BigDecimal adultTotal = new BigDecimal("0.00");
        BigDecimal studentTotal = new BigDecimal("0.00");
        BigDecimal childTotal = new BigDecimal("0.00");
        BigDecimal seniorCitizenTotal = new BigDecimal("0.00");

        for (Journey journey : journeys) {
            BigDecimal baseFare = configService.getBaseFare(
                    journey.getFromZone(),
                    journey.getToZone(),
                    journey.getTimeBand());

            BigDecimal discountRate = configService.getDiscountRate(journey.getPassengerType());
            BigDecimal discountAmount = baseFare.multiply(discountRate);
            BigDecimal discountedFare = baseFare.subtract(discountAmount);
            BigDecimal dailyCap = configService.getDailyCap(journey.getPassengerType());

            BigDecimal currentTotal = new BigDecimal("0.00");

            if (journey.getPassengerType() == CityRideDataset.PassengerType.ADULT) {
                currentTotal = adultTotal;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.STUDENT) {
                currentTotal = studentTotal;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.CHILD) {
                currentTotal = childTotal;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
                currentTotal = seniorCitizenTotal;
            }

            BigDecimal chargedFare = discountedFare;

            if (currentTotal.compareTo(dailyCap) >= 0) {
                chargedFare = new BigDecimal("0.00");
            } else if (currentTotal.add(discountedFare).compareTo(dailyCap) > 0) {
                chargedFare = dailyCap.subtract(currentTotal);
            }

            journey.setBaseFare(baseFare);
            journey.setDiscountAmount(discountAmount);
            journey.setChargedFare(chargedFare);

            if (journey.getPassengerType() == CityRideDataset.PassengerType.ADULT) {
                adultTotal = adultTotal.add(chargedFare);
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.STUDENT) {
                studentTotal = studentTotal.add(chargedFare);
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.CHILD) {
                childTotal = childTotal.add(chargedFare);
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
                seniorCitizenTotal = seniorCitizenTotal.add(chargedFare);
            }
        }
    }

    // AI-assisted daily cap calculation, then modified and tested for CityRide Lite
    // Updated to use BigDecimal and active fare configuration
    // (OpenAI, 2026)
    private BigDecimal applyDailyCap(CityRideDataset.PassengerType passengerType,
                                     BigDecimal discountedFare) {
        BigDecimal currentTotal = getPassengerTypeTotal(passengerType);
        BigDecimal dailyCap = configService.getDailyCap(passengerType);
        BigDecimal chargedFare = discountedFare;

        if (currentTotal.compareTo(dailyCap) >= 0) {
            chargedFare = new BigDecimal("0.00");
        } else if (currentTotal.add(discountedFare).compareTo(dailyCap) > 0) {
            chargedFare = dailyCap.subtract(currentTotal);
        }

        return chargedFare;
    }


    private BigDecimal getPassengerTypeTotal(CityRideDataset.PassengerType passengerType) {
        BigDecimal total = new BigDecimal("0.00");

        for (Journey journey : journeys) {
            if (journey.getPassengerType() == passengerType) {
                total = total.add(journey.getChargedFare());
            }
        }

        return total;
    }
}