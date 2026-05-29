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