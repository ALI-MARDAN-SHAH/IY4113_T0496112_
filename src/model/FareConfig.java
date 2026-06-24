package model;

public class FareConfig {

    private double adultDiscount;
    private double studentDiscount;
    private double childDiscount;
    private double seniorCitizenDiscount;

    private double adultDailyCap;
    private double studentDailyCap;
    private double childDailyCap;
    private double seniorCitizenDailyCap;

    private String morningPeakStart;
    private String morningPeakEnd;
    private String eveningPeakStart;
    private String eveningPeakEnd;

    public FareConfig() {
        adultDiscount = 0.00;
        studentDiscount = 0.25;
        childDiscount = 0.50;
        seniorCitizenDiscount = 0.30;

        adultDailyCap = 8.00;
        studentDailyCap = 6.00;
        childDailyCap = 4.00;
        seniorCitizenDailyCap = 7.00;

        morningPeakStart = "07:00";
        morningPeakEnd = "09:30";
        eveningPeakStart = "16:30";
        eveningPeakEnd = "19:00";
    }

    public double getAdultDiscount() {
        return adultDiscount;
    }

    public void setAdultDiscount(double adultDiscount) {
        this.adultDiscount = adultDiscount;
    }

    public double getStudentDiscount() {
        return studentDiscount;
    }

    public void setStudentDiscount(double studentDiscount) {
        this.studentDiscount = studentDiscount;
    }

    public double getChildDiscount() {
        return childDiscount;
    }

    public void setChildDiscount(double childDiscount) {
        this.childDiscount = childDiscount;
    }

    public double getSeniorCitizenDiscount() {
        return seniorCitizenDiscount;
    }

    public void setSeniorCitizenDiscount(double seniorCitizenDiscount) {
        this.seniorCitizenDiscount = seniorCitizenDiscount;
    }

    public double getAdultDailyCap() {
        return adultDailyCap;
    }

    public void setAdultDailyCap(double adultDailyCap) {
        this.adultDailyCap = adultDailyCap;
    }

    public double getStudentDailyCap() {
        return studentDailyCap;
    }

    public void setStudentDailyCap(double studentDailyCap) {
        this.studentDailyCap = studentDailyCap;
    }

    public double getChildDailyCap() {
        return childDailyCap;
    }

    public void setChildDailyCap(double childDailyCap) {
        this.childDailyCap = childDailyCap;
    }

    public double getSeniorCitizenDailyCap() {
        return seniorCitizenDailyCap;
    }

    public void setSeniorCitizenDailyCap(double seniorCitizenDailyCap) {
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
}