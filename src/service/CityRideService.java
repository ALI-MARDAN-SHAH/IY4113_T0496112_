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
        Journey journey = new Journey(nextJourneyId, date, fromZone, toZone, timeBand, passengerType);

        journeys.add(journey);
        nextJourneyId++;

        System.out.println("Journey added.");
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