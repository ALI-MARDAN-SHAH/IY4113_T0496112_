package model;

import data.CityRideDataset;

import java.math.BigDecimal;

public class FareRule {

    private int fromZone;
    private int toZone;
    private CityRideDataset.TimeBand timeBand;
    private BigDecimal baseFare;

    public FareRule() {
        fromZone = 0;
        toZone = 0;
        timeBand = CityRideDataset.TimeBand.OFF_PEAK;
        baseFare = new BigDecimal("0.00");
    }

    public FareRule(int fromZone, int toZone,
                    CityRideDataset.TimeBand timeBand,
                    BigDecimal baseFare) {
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.timeBand = timeBand;
        this.baseFare = baseFare;
    }

    public int getFromZone() {
        return fromZone;
    }

    public void setFromZone(int fromZone) {
        this.fromZone = fromZone;
    }

    public int getToZone() {
        return toZone;
    }

    public void setToZone(int toZone) {
        this.toZone = toZone;
    }

    public CityRideDataset.TimeBand getTimeBand() {
        return timeBand;
    }

    public void setTimeBand(CityRideDataset.TimeBand timeBand) {
        this.timeBand = timeBand;
    }

    public BigDecimal getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(BigDecimal baseFare) {
        this.baseFare = baseFare;
    }
}