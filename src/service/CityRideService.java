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
        double chargedFare = baseFare - discountAmount;

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
}