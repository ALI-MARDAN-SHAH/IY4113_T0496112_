package model;

import data.CityRideDataset;

import java.math.BigDecimal;
import java.util.ArrayList;

public class FareConfig {

    private BigDecimal adultDiscount;
    private BigDecimal studentDiscount;
    private BigDecimal childDiscount;
    private BigDecimal seniorCitizenDiscount;

    private BigDecimal adultDailyCap;
    private BigDecimal studentDailyCap;
    private BigDecimal childDailyCap;
    private BigDecimal seniorCitizenDailyCap;

    private String morningPeakStart;
    private String morningPeakEnd;
    private String eveningPeakStart;
    private String eveningPeakEnd;

    private ArrayList<FareRule> fareRules;

    public FareConfig() {
        adultDiscount = new BigDecimal("0.00");
        studentDiscount = new BigDecimal("0.25");
        childDiscount = new BigDecimal("0.50");
        seniorCitizenDiscount = new BigDecimal("0.30");

        adultDailyCap = new BigDecimal("8.00");
        studentDailyCap = new BigDecimal("6.00");
        childDailyCap = new BigDecimal("4.00");
        seniorCitizenDailyCap = new BigDecimal("7.00");

        morningPeakStart = "07:00";
        morningPeakEnd = "09:30";
        eveningPeakStart = "16:30";
        eveningPeakEnd = "19:00";

        fareRules = new ArrayList<>();
        addDefaultFareRules();
    }

    private void addDefaultFareRules() {
        for (int fromZone = CityRideDataset.MIN_ZONE; fromZone <= CityRideDataset.MAX_ZONE; fromZone++) {
            for (int toZone = CityRideDataset.MIN_ZONE; toZone <= CityRideDataset.MAX_ZONE; toZone++) {
                addDefaultFareRule(fromZone, toZone, CityRideDataset.TimeBand.PEAK);
                addDefaultFareRule(fromZone, toZone, CityRideDataset.TimeBand.OFF_PEAK);
            }
        }
    }

    private void addDefaultFareRule(int fromZone, int toZone, CityRideDataset.TimeBand timeBand) {
        BigDecimal baseFare = CityRideDataset.getBaseFare(fromZone, toZone, timeBand);
        FareRule fareRule = new FareRule(fromZone, toZone, timeBand, baseFare);
        fareRules.add(fareRule);
    }

    public void ensureFareRules() {
        if (fareRules == null || fareRules.size() == 0) {
            fareRules = new ArrayList<>();
            addDefaultFareRules();
        }
    }

    public BigDecimal getAdultDiscount() {
        return adultDiscount;
    }

    public void setAdultDiscount(BigDecimal adultDiscount) {
        this.adultDiscount = adultDiscount;
    }

    public BigDecimal getStudentDiscount() {
        return studentDiscount;
    }

    public void setStudentDiscount(BigDecimal studentDiscount) {
        this.studentDiscount = studentDiscount;
    }

    public BigDecimal getChildDiscount() {
        return childDiscount;
    }

    public void setChildDiscount(BigDecimal childDiscount) {
        this.childDiscount = childDiscount;
    }

    public BigDecimal getSeniorCitizenDiscount() {
        return seniorCitizenDiscount;
    }

    public void setSeniorCitizenDiscount(BigDecimal seniorCitizenDiscount) {
        this.seniorCitizenDiscount = seniorCitizenDiscount;
    }

    public BigDecimal getAdultDailyCap() {
        return adultDailyCap;
    }

    public void setAdultDailyCap(BigDecimal adultDailyCap) {
        this.adultDailyCap = adultDailyCap;
    }

    public BigDecimal getStudentDailyCap() {
        return studentDailyCap;
    }

    public void setStudentDailyCap(BigDecimal studentDailyCap) {
        this.studentDailyCap = studentDailyCap;
    }

    public BigDecimal getChildDailyCap() {
        return childDailyCap;
    }

    public void setChildDailyCap(BigDecimal childDailyCap) {
        this.childDailyCap = childDailyCap;
    }

    public BigDecimal getSeniorCitizenDailyCap() {
        return seniorCitizenDailyCap;
    }

    public void setSeniorCitizenDailyCap(BigDecimal seniorCitizenDailyCap) {
        this.seniorCitizenDailyCap = seniorCitizenDailyCap;
    }

    public String getMorningPeakStart() {
        return morningPeakStart;
    }

    public void setMorningPeakStart(String morningPeakStart) {
        this.morningPeakStart = morningPeakStart;
    }

    public String getMorningPeakEnd() {
        return morningPeakEnd;
    }

    public void setMorningPeakEnd(String morningPeakEnd) {
        this.morningPeakEnd = morningPeakEnd;
    }

    public String getEveningPeakStart() {
        return eveningPeakStart;
    }

    public void setEveningPeakStart(String eveningPeakStart) {
        this.eveningPeakStart = eveningPeakStart;
    }

    public String getEveningPeakEnd() {
        return eveningPeakEnd;
    }

    public void setEveningPeakEnd(String eveningPeakEnd) {
        this.eveningPeakEnd = eveningPeakEnd;
    }

    public ArrayList<FareRule> getFareRules() {
        return fareRules;
    }

    public void setFareRules(ArrayList<FareRule> fareRules) {
        this.fareRules = fareRules;
    }
}