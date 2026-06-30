package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.CityRideDataset;
import model.FareConfig;
import model.FareRule;

import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;

public class ConfigService {

    private FareConfig fareConfig;
    private final Gson gson;
    private final String fileName;

    public ConfigService() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        fileName = "fare_config.json";
        fareConfig = loadConfig();
    }

    public FareConfig loadConfig() {
        FareConfig loadedConfig = new FareConfig();

        try {
            FileReader reader = new FileReader(fileName);
            loadedConfig = gson.fromJson(reader, FareConfig.class);
            reader.close();

            if (loadedConfig == null) {
                loadedConfig = new FareConfig();
                System.out.println("Config file was empty. Safe default config loaded.");
            } else {
                loadedConfig.ensureFareRules();
                System.out.println("Config loaded from JSON file.");
            }
        } catch (Exception e) {
            System.out.println("No config file found. Safe default config loaded.");
        }

        return loadedConfig;
    }

    public void saveConfig() {
        try {
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(fareConfig, writer);
            writer.close();

            System.out.println("Config saved to JSON file.");
        } catch (Exception e) {
            System.out.println("Error saving config file.");
        }
    }

    public void displayConfig() {
        fareConfig.ensureFareRules();

        System.out.println("\n--- Active Fare Config ---");
        System.out.println("Adult Discount: " + fareConfig.getAdultDiscount());
        System.out.println("Student Discount: " + fareConfig.getStudentDiscount());
        System.out.println("Child Discount: " + fareConfig.getChildDiscount());
        System.out.println("Senior Citizen Discount: " + fareConfig.getSeniorCitizenDiscount());

        System.out.println("Adult Daily Cap: £" + fareConfig.getAdultDailyCap());
        System.out.println("Student Daily Cap: £" + fareConfig.getStudentDailyCap());
        System.out.println("Child Daily Cap: £" + fareConfig.getChildDailyCap());
        System.out.println("Senior Citizen Daily Cap: £" + fareConfig.getSeniorCitizenDailyCap());

        System.out.println("Morning Peak: " + fareConfig.getMorningPeakStart()
                + " - " + fareConfig.getMorningPeakEnd());
        System.out.println("Evening Peak: " + fareConfig.getEveningPeakStart()
                + " - " + fareConfig.getEveningPeakEnd());

        System.out.println("\nBase fare rules:");
        for (FareRule fareRule : fareConfig.getFareRules()) {
            System.out.println("From zone " + fareRule.getFromZone()
                    + " to zone " + fareRule.getToZone()
                    + " | " + fareRule.getTimeBand()
                    + " | £" + fareRule.getBaseFare());
        }
    }

    public BigDecimal getDiscountRate(CityRideDataset.PassengerType passengerType) {
        BigDecimal discountRate = new BigDecimal("0.00");

        if (passengerType == CityRideDataset.PassengerType.ADULT) {
            discountRate = fareConfig.getAdultDiscount();
        } else if (passengerType == CityRideDataset.PassengerType.STUDENT) {
            discountRate = fareConfig.getStudentDiscount();
        } else if (passengerType == CityRideDataset.PassengerType.CHILD) {
            discountRate = fareConfig.getChildDiscount();
        } else if (passengerType == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
            discountRate = fareConfig.getSeniorCitizenDiscount();
        }

        return discountRate;
    }

    public BigDecimal getDailyCap(CityRideDataset.PassengerType passengerType) {
        BigDecimal dailyCap = new BigDecimal("0.00");

        if (passengerType == CityRideDataset.PassengerType.ADULT) {
            dailyCap = fareConfig.getAdultDailyCap();
        } else if (passengerType == CityRideDataset.PassengerType.STUDENT) {
            dailyCap = fareConfig.getStudentDailyCap();
        } else if (passengerType == CityRideDataset.PassengerType.CHILD) {
            dailyCap = fareConfig.getChildDailyCap();
        } else if (passengerType == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
            dailyCap = fareConfig.getSeniorCitizenDailyCap();
        }

        return dailyCap;
    }

    public BigDecimal getBaseFare(int fromZone, int toZone, CityRideDataset.TimeBand timeBand) {
        fareConfig.ensureFareRules();

        BigDecimal baseFare = new BigDecimal("0.00");
        boolean fareFound = false;

        for (FareRule fareRule : fareConfig.getFareRules()) {
            if (fareRule.getFromZone() == fromZone
                    && fareRule.getToZone() == toZone
                    && fareRule.getTimeBand() == timeBand) {
                baseFare = fareRule.getBaseFare();
                fareFound = true;
            }
        }

        if (!fareFound) {
            System.out.println("Fare rule not found. Default fare used.");
            baseFare = CityRideDataset.getBaseFare(fromZone, toZone, timeBand);
        }

        return baseFare;
    }

    public boolean updateDiscount(CityRideDataset.PassengerType passengerType, double discountRate) {
        BigDecimal newDiscountRate = BigDecimal.valueOf(discountRate);

        if (newDiscountRate.compareTo(new BigDecimal("0.00")) < 0
                || newDiscountRate.compareTo(new BigDecimal("1.00")) > 0) {
            System.out.println("Discount rate must be between 0.00 and 1.00.");
            return false;
        }

        if (passengerType == CityRideDataset.PassengerType.ADULT) {
            fareConfig.setAdultDiscount(newDiscountRate);
        } else if (passengerType == CityRideDataset.PassengerType.STUDENT) {
            fareConfig.setStudentDiscount(newDiscountRate);
        } else if (passengerType == CityRideDataset.PassengerType.CHILD) {
            fareConfig.setChildDiscount(newDiscountRate);
        } else if (passengerType == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
            fareConfig.setSeniorCitizenDiscount(newDiscountRate);
        }

        System.out.println("Discount updated.");
        return true;
    }

    public boolean updateDailyCap(CityRideDataset.PassengerType passengerType, double dailyCap) {
        BigDecimal newDailyCap = BigDecimal.valueOf(dailyCap);

        if (newDailyCap.compareTo(new BigDecimal("0.00")) <= 0) {
            System.out.println("Daily cap must be greater than 0.");
            return false;
        }

        if (passengerType == CityRideDataset.PassengerType.ADULT) {
            fareConfig.setAdultDailyCap(newDailyCap);
        } else if (passengerType == CityRideDataset.PassengerType.STUDENT) {
            fareConfig.setStudentDailyCap(newDailyCap);
        } else if (passengerType == CityRideDataset.PassengerType.CHILD) {
            fareConfig.setChildDailyCap(newDailyCap);
        } else if (passengerType == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
            fareConfig.setSeniorCitizenDailyCap(newDailyCap);
        }

        System.out.println("Daily cap updated.");
        return true;
    }

    public boolean updateBaseFare(int fromZone, int toZone,
                                  CityRideDataset.TimeBand timeBand,
                                  double newFare) {
        fareConfig.ensureFareRules();

        BigDecimal newBaseFare = BigDecimal.valueOf(newFare);
        boolean updated = false;

        if (newBaseFare.compareTo(new BigDecimal("0.00")) <= 0) {
            System.out.println("Base fare must be greater than 0.");
            return false;
        }

        for (FareRule fareRule : fareConfig.getFareRules()) {
            if (fareRule.getFromZone() == fromZone
                    && fareRule.getToZone() == toZone
                    && fareRule.getTimeBand() == timeBand) {
                fareRule.setBaseFare(newBaseFare);
                updated = true;
            }
        }

        if (updated) {
            System.out.println("Base fare updated.");
        } else {
            System.out.println("Fare rule not found.");
        }

        return updated;
    }

    public boolean updatePeakTimes(String morningStart, String morningEnd,
                                   String eveningStart, String eveningEnd) {
        if (morningStart.isBlank() || morningEnd.isBlank()
                || eveningStart.isBlank() || eveningEnd.isBlank()) {
            System.out.println("Peak time values cannot be blank.");
            return false;
        }

        fareConfig.setMorningPeakStart(morningStart);
        fareConfig.setMorningPeakEnd(morningEnd);
        fareConfig.setEveningPeakStart(eveningStart);
        fareConfig.setEveningPeakEnd(eveningEnd);

        System.out.println("Peak times updated.");
        return true;
    }

    public FareConfig getFareConfig() {
        return fareConfig;
    }

    public void setFareConfig(FareConfig fareConfig) {
        this.fareConfig = fareConfig;
    }
}