package model;

public class RiderProfile {

    private String riderName;
    private String passengerType;
    private String defaultPayment;

    public RiderProfile() {
        riderName = "";
        passengerType = "";
        defaultPayment = "";
    }

    public RiderProfile(String riderName, String passengerType, String defaultPayment) {
        this.riderName = riderName;
        this.passengerType = passengerType;
        this.defaultPayment = defaultPayment;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public String getDefaultPayment() {
        return defaultPayment;
    }

    public void setDefaultPayment(String defaultPayment) {
        this.defaultPayment = defaultPayment;
    }
}