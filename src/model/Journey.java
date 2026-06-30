package model;

import data.CityRideDataset;

import java.math.BigDecimal;

public class Journey {

    private int id;
    private String date;
    private int fromZone;
    private int toZone;
    private CityRideDataset.TimeBand timeBand;
    private CityRideDataset.PassengerType passengerType;
    private BigDecimal baseFare;
    private BigDecimal discountAmount;
    private BigDecimal chargedFare;

    public Journey() {
        id = 0;
        date = "";
        fromZone = 0;
        toZone = 0;
        timeBand = CityRideDataset.TimeBand.OFF_PEAK;
        passengerType = CityRideDataset.PassengerType.ADULT;
        baseFare = new BigDecimal("0.00");
        discountAmount = new BigDecimal("0.00");
        chargedFare = new BigDecimal("0.00");
    }

    public Journey(int id, String date, int fromZone, int toZone,
                   CityRideDataset.TimeBand timeBand,
                   CityRideDataset.PassengerType passengerType,
                   BigDecimal baseFare, BigDecimal discountAmount, BigDecimal chargedFare) {
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

    public BigDecimal getBaseFare() {
        return baseFare;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getChargedFare() {
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

    public void setBaseFare(BigDecimal baseFare) {
        this.baseFare = baseFare;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setChargedFare(BigDecimal chargedFare) {
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
                + " | Base fare: £" + formatMoney(baseFare)
                + " | Discount: £" + formatMoney(discountAmount)
                + " | Charged fare: £" + formatMoney(chargedFare);
    }

    private String formatMoney(BigDecimal amount) {
        return String.format("%.2f", amount);
    }
}