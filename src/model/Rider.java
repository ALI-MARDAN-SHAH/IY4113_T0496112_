package model;

public class Rider extends User {

    private String passengerType;
    private String defaultPayment;

    public Rider() {
        passengerType = "";
        defaultPayment = "";
    }

    public Rider(int userId, String name, String passengerType, String defaultPayment) {
        super(userId, name);
        this.passengerType = passengerType;
        this.defaultPayment = defaultPayment;
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