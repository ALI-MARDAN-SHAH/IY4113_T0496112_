package model;

import data.CityRideDataset;

public class Journey {

    private int id;
    private String date;
    private int fromZone;
    private int toZone;
    private CityRideDataset.TimeBand timeBand;
    private CityRideDataset.PassengerType passengerType;

    public Journey(int id, String date, int fromZone, int toZone,
                   CityRideDataset.TimeBand timeBand,
                   CityRideDataset.PassengerType passengerType) {
        this.id = id;
        this.date = date;
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.timeBand = timeBand;
        this.passengerType = passengerType;
    }

    public int getId() {
        return id;
    }

    public int getFromZone() {
        return fromZone;
    }

    public int getToZone() {
        return toZone;
    }

    public int getZonesCrossed() {
        return Math.abs(toZone - fromZone) + 1;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Date: " + date
                + " | From zone: " + fromZone
                + " | To zone: " + toZone
                + " | Time: " + timeBand
                + " | Passenger: " + passengerType
                + " | Zones crossed: " + getZonesCrossed();
    }
}