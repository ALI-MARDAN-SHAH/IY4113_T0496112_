package model;

import data.CityRideDataset;

public class Journey {

    private int id;
    private String date;
    private int fromZone;
    private int toZone;
    private CityRideDataset.TimeBand timeBand;
    private CityRideDataset.PassengerType passengerType;
    private double baseFare;
    private double discountAmount;
    private double chargedFare;

    public Journey(int id, String date, int fromZone, int toZone,
                   CityRideDataset.TimeBand timeBand,
                   CityRideDataset.PassengerType passengerType,
                   double baseFare, double discountAmount, double chargedFare) {
        this.id = id;
        this.date = date;
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.timeBand = timeBand;
        this.passengerType = passengerType;
        this.baseFare = baseFare;
        this.discountAmount = discountAmount;
        this.chargedFare = chargedFare;
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

    public CityRideDataset.PassengerType getPassengerType() {
        return passengerType;
    }

    public CityRideDataset.TimeBand getTimeBand() {
        return timeBand;
    }

    public String getDate() {
        return date;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getChargedFare() {
        return chargedFare;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFromZone(int fromZone) {
        this.fromZone = fromZone;
    }

    public void setToZone(int toZone) {
        this.toZone = toZone;
    }

    public void setTimeBand(CityRideDataset.TimeBand timeBand) {
        this.timeBand = timeBand;
    }

    public void setPassengerType(CityRideDataset.PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setChargedFare(double chargedFare) {
        this.chargedFare = chargedFare;
    }

    public int getZonesCrossed() {
        return Math.abs(toZone - fromZone) + 1;
    }

    public String showJourneyDetails() {
        return "ID: " + id
                + " | Date: " + date
                + " | From zone: " + fromZone
                + " | To zone: " + toZone
                + " | Time: " + timeBand
                + " | Passenger: " + passengerType
                + " | Zones crossed: " + getZonesCrossed()
                + " | Base fare: £" + String.format("%.2f", baseFare)
                + " | Discount: £" + String.format("%.2f", discountAmount)
                + " | Charged fare: £" + String.format("%.2f", chargedFare);
    }
}