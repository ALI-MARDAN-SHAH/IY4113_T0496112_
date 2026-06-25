package service;

import data.CityRideDataset;
import model.Journey;

import java.util.ArrayList;

public class CityRideService {

    // This list stores journeys while the program is running.
    private ArrayList<Journey> journeys;
    private int nextJourneyId;

    public CityRideService() {
        journeys = new ArrayList<>();
        nextJourneyId = 1;
    }

    public void addJourney(String date, int fromZone, int toZone,
                           CityRideDataset.TimeBand timeBand,
                           CityRideDataset.PassengerType passengerType) {
        double baseFare = CityRideDataset.getBaseFare(fromZone, toZone, timeBand).doubleValue();
        double discountRate = CityRideDataset.DISCOUNT_RATE.get(passengerType).doubleValue();
        double discountAmount = baseFare * discountRate;
        double discountedFare = baseFare - discountAmount;

        double chargedFare = applyDailyCap(passengerType, discountedFare);

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

        double totalCost = 0;
        Journey mostExpensiveJourney = journeys.get(0);

        for (Journey journey : journeys) {
            totalCost = totalCost + journey.getChargedFare();

            if (journey.getChargedFare() > mostExpensiveJourney.getChargedFare()) {
                mostExpensiveJourney = journey;
            }
        }

        double averageCost = totalCost / journeys.size();

        System.out.println("===== Daily Summary =====");
        System.out.println("Total journeys: " + journeys.size());
        System.out.println("Total cost: £" + String.format("%.2f", totalCost));
        System.out.println("Average cost: £" + String.format("%.2f", averageCost));
        System.out.println("Most expensive journey ID: " + mostExpensiveJourney.getId());
        System.out.println("Most expensive journey cost: £" + String.format("%.2f", mostExpensiveJourney.getChargedFare()));
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
        double baseTotal = 0;
        double discountTotal = 0;
        double chargedTotal = 0;

        for (Journey journey : journeys) {
            if (journey.getPassengerType() == passengerType) {
                count++;
                baseTotal = baseTotal + journey.getBaseFare();
                discountTotal = discountTotal + journey.getDiscountAmount();
                chargedTotal = chargedTotal + journey.getChargedFare();
            }
        }

        double dailyCap = CityRideDataset.DAILY_CAP.get(passengerType).doubleValue();
        String capReached = "No";

        if (chargedTotal >= dailyCap) {
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

    private void recalculateAllJourneyFares() {
        double adultTotal = 0;
        double studentTotal = 0;
        double childTotal = 0;
        double seniorCitizenTotal = 0;

        for (Journey journey : journeys) {
            double baseFare = CityRideDataset.getBaseFare(
                    journey.getFromZone(),
                    journey.getToZone(),
                    journey.getTimeBand()).doubleValue();

            double discountRate = CityRideDataset.DISCOUNT_RATE
                    .get(journey.getPassengerType()).doubleValue();

            double discountAmount = baseFare * discountRate;
            double discountedFare = baseFare - discountAmount;
            double dailyCap = CityRideDataset.DAILY_CAP
                    .get(journey.getPassengerType()).doubleValue();

            double currentTotal = 0;

            if (journey.getPassengerType() == CityRideDataset.PassengerType.ADULT) {
                currentTotal = adultTotal;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.STUDENT) {
                currentTotal = studentTotal;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.CHILD) {
                currentTotal = childTotal;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
                currentTotal = seniorCitizenTotal;
            }

            double chargedFare = discountedFare;

            if (currentTotal >= dailyCap) {
                chargedFare = 0;
            } else if (currentTotal + discountedFare > dailyCap) {
                chargedFare = dailyCap - currentTotal;
            }

            journey.setBaseFare(baseFare);
            journey.setDiscountAmount(discountAmount);
            journey.setChargedFare(chargedFare);

            if (journey.getPassengerType() == CityRideDataset.PassengerType.ADULT) {
                adultTotal = adultTotal + chargedFare;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.STUDENT) {
                studentTotal = studentTotal + chargedFare;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.CHILD) {
                childTotal = childTotal + chargedFare;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
                seniorCitizenTotal = seniorCitizenTotal + chargedFare;
            }
        }
    }

    // AI-assisted method and modified and tested for the CityRide Lite daily cap calculation
    // (OpenAI, 2026)
    private double applyDailyCap(CityRideDataset.PassengerType passengerType, double discountedFare) {
        double currentTotal = getPassengerTypeTotal(passengerType);
        double dailyCap = CityRideDataset.DAILY_CAP.get(passengerType).doubleValue();
        double chargedFare = discountedFare;

        if (currentTotal >= dailyCap) {
            chargedFare = 0;
        } else if (currentTotal + discountedFare > dailyCap) {
            chargedFare = dailyCap - currentTotal;
        }

        return chargedFare;
    }

    private double getPassengerTypeTotal(CityRideDataset.PassengerType passengerType) {
        double total = 0;

        for (Journey journey : journeys) {
            if (journey.getPassengerType() == passengerType) {
                total = total + journey.getChargedFare();
            }
        }

        return total;
    }
}