package model;

import java.util.ArrayList;

public class JourneyDayState {

    private RiderProfile riderProfile;
    private ArrayList<Journey> journeys;
    private int nextJourneyId;

    public JourneyDayState() {
        riderProfile = null;
        journeys = new ArrayList<>();
        nextJourneyId = 1;
    }

    public JourneyDayState(RiderProfile riderProfile, ArrayList<Journey> journeys, int nextJourneyId) {
        this.riderProfile = riderProfile;
        this.journeys = journeys;
        this.nextJourneyId = nextJourneyId;
    }

    public RiderProfile getRiderProfile() {
        return riderProfile;
    }

    public void setRiderProfile(RiderProfile riderProfile) {
        this.riderProfile = riderProfile;
    }

    public ArrayList<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(ArrayList<Journey> journeys) {
        this.journeys = journeys;
    }

    public int getNextJourneyId() {
        return nextJourneyId;
    }

    public void setNextJourneyId(int nextJourneyId) {
        this.nextJourneyId = nextJourneyId;
    }
}