# IY4113 Part 2 Milestone 3

| Assessment Details | Please Complete All Details                                             |
| ------------------ | ----------------------------------------------------------------------- |
| Group              | A                                                                       |
| Module Title       | IY4113 Applied Software Engineering using Object-Orientated Programming |
| Assessment Type    | Java Fundamentals Part 2                                                |
| Module Tutor Name  | Jonathan, Shore                                                         |
| Student ID Number  | T0496112                                                                |
| Date of Submission | 28/6/2026                                                               |
| Word Count         |                                                                         |
| GitHub Link        | https://github.com/ALI-MARDAN-SHAH/IY4113_T0496112_                     |

- [x] *I confirm that this assignment is my own work. Where I have referred to academic sources, I have provided in-text citations and included the sources in
  the final reference list.*

- [x] *Where I have used AI, I have cited and referenced appropriately.

------------------------------------------------------------------------------------------------------------------------------

---

### Research 1

Title of research:  
Google Gson GitHub Repository / Gson User Guide

Reference (link):  
[gson/UserGuide.md at main · google/gson · GitHub](https://github.com/google/gson/blob/main/UserGuide.md)

How does the research help with coding practise?:  
This research helped me understand how Java objects can be converted into JSON file format and then converted back into Java objects and this was useful because my Part 2 program needs persistent storage for rider profiles and fare configuration instead of saving each value separately as plain text and JSON allows related data to be stored in a structured way.

I used this research when creating RiderProfileService and ConfigService and in my program the rider profile is saved to rider_profile.json and the fare configuration is saved to fare_config.json and i also used GsonBuilder().setPrettyPrinting().create() so that the JSON output is easier to read.

Key coding ideas you could reuse in your program:

- Using Gson to convert Java objects into JSON.

- Using toJson() to save object data.

- Using fromJson() to load JSON data back into an object.

- Using GsonBuilder and setPrettyPrinting() for readable JSON output.

- Creating model classes such as RiderProfile and FareConfig so the data can be saved as objects.

Screenshot of research:

---

### Research 2

Title of research:  
callicoder / java-read-write-csv-file

Reference (link):  
[GitHub - callicoder/java-read-write-csv-file: Read and Write CSV files in Java using Apache Commons CSV and OpenCSV · GitHub](https://github.com/callicoder/java-read-write-csv-file)

How does the research help with coding practise?:  
This research helped me understand how Java programs can read and write CSV files and this was useful because my Part 2 program needs to export journey data and daily summary reports and a CSV file is useful because it stores data in rows and columns which makes it suitable for journey records and report summaries.

I used this idea in my CityRideService class by creating exportJourneysToCsv() and exportDailySummaryReports() so my program exports the current journeys to journeys_export.csv and also exports the daily summary as both a text report and a CSV report but the GitHub source uses CSV libraries so i only adapted the idea of using a simpler FileWriter and PrintWriter approach because it fits my current program level and structure better.

Key coding ideas you could reuse in your program:

- Exporting structured data into a CSV file.

- Writing column headings before writing data rows.

- Storing records in a clear row-by-row format.

- Using file handling to create output files from program data.

- Adapting CSV writing ideas for journey exports and summary reports.

Screenshot of research:

---

### Research 3

Title of research:  
TejasKoshti / Student-Management-System

Reference (link):  
[GitHub - TejasKoshti/Student-Management-System: A Java-based application for managing student records, which allows users to add, display, update, and search for student information. The program uses an object-oriented approach and incorporates various classes such as Student, Subject, and BLClass. · GitHub](https://github.com/TejasKoshti/Student-Management-System)

How does the research help with coding practise?:  
This research helped me understand how a simple Java command line management system can be structured using object oriented programming. The project manages student records and includes features such as adding, displaying, removing, updating, and searching records and this is very similar to my CityRide Lite system because my program manages journey records instead of student records.

I used this research to support the structure of my CityRideLiteApp and CityRideService classes. CityRideLiteApp controls the consule menu while CityRideService handles the main journey logic and this is similar to separating menu interaction from business logic and my program also uses features like addJourney(), showAllJourneys(), editJourney(), removeJourney() and filtering/searching by journey details.

Key coding ideas you could reuse in your program:

- Using a command-line menu to let the user choose actions.

- Separating model classes from service/logic classes.

- Adding, displaying, updating, removing and searching records.

- Searching records by ID before editing or deleting.

- Keeping the program organised using an object oriented structure.

Screenshot of research:

------------------------------------------------------------------------------------------------------------------------------

# Milestone 2 Program Code

This section includes the Java files created or changed since Part 1.

## Files included

- User.java
- Rider.java
- Admin.java
- RiderProfile.java
- FareConfig.java
- RiderProfileService.java
- ConfigService.java
- Journey.java
- CityRideService.java
- CityRideLiteApp.java

## User.java

```java
package model;

public class User {

    private int userId;
    private String name;

    public User() {
        userId = 0;
        name = "";
    }

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

## Rider.java

```java
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
```

## Admin.java

```java
package model;

public class Admin extends User {

    private String password;

    public Admin() {
        password = "";
    }

    public Admin(int userId, String name, String password) {
        super(userId, name);
        this.password = password;
    }

    public boolean checkPassword(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```

## RiderProfile.java

```java
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
```

## FareConfig.java

```java
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
```

## RiderProfileService.java

```java
package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.RiderProfile;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class RiderProfileService {

    private RiderProfile riderProfile;
    private Gson gson;
    private String fileName;

    public RiderProfileService() {
        riderProfile = null;
        gson = new GsonBuilder().setPrettyPrinting().create();
        fileName = "rider_profile.json";
    }

    public void createProfile(Scanner scanner) {
        System.out.println("\n--- Create Rider Profile ---");

        System.out.print("Enter rider name: ");
        String riderName = scanner.nextLine();

        System.out.print("Enter passenger type (Adult/Student/Child/Senior Citizen): ");
        String passengerType = scanner.nextLine();

        System.out.print("Enter default payment option (Card/Cash): ");
        String defaultPayment = scanner.nextLine();

        riderProfile = new RiderProfile(riderName, passengerType, defaultPayment);

        System.out.println("Rider profile created successfully.");
    }

    public void displayProfile() {
        if (riderProfile == null) {
            System.out.println("No rider profile found.");
        } else {
            System.out.println("\n--- Rider Profile ---");
            System.out.println("Name: " + riderProfile.getRiderName());
            System.out.println("Passenger Type: " + riderProfile.getPassengerType());
            System.out.println("Default Payment: " + riderProfile.getDefaultPayment());
        }
    }

    public void saveProfile() {
        if (riderProfile == null) {
            System.out.println("No rider profile to save.");
        } else {
            try {
                FileWriter writer = new FileWriter(fileName);
                gson.toJson(riderProfile, writer);
                writer.close();

                System.out.println("Rider profile saved to JSON file.");
            } catch (Exception e) {
                System.out.println("Error saving rider profile.");
            }
        }
    }

    public void loadProfile() {
        try {
            FileReader reader = new FileReader(fileName);
            riderProfile = gson.fromJson(reader, RiderProfile.class);
            reader.close();

            if (riderProfile == null) {
                System.out.println("Profile file was empty.");
            } else {
                System.out.println("Rider profile loaded from JSON file.");
            }
        } catch (Exception e) {
            System.out.println("No rider profile file found.");
        }
    }

    public RiderProfile getRiderProfile() {
        return riderProfile;
    }

    public void setRiderProfile(RiderProfile riderProfile) {
        this.riderProfile = riderProfile;
    }
}
```

## ConfigService.java

```java
package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.CityRideDataset;
import model.FareConfig;

import java.io.FileReader;
import java.io.FileWriter;

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
        System.out.println("\n--- Active Fare Config ---");
        System.out.println("Adult Discount: " + fareConfig.getAdultDiscount());
        System.out.println("Student Discount: " + fareConfig.getStudentDiscount());
        System.out.println("Child Discount: " + fareConfig.getChildDiscount());
        System.out.println("Senior Citizen Discount: " + fareConfig.getSeniorCitizenDiscount());

        System.out.println("Adult Daily Cap: " + fareConfig.getAdultDailyCap());
        System.out.println("Student Daily Cap: " + fareConfig.getStudentDailyCap());
        System.out.println("Child Daily Cap: " + fareConfig.getChildDailyCap());
        System.out.println("Senior Citizen Daily Cap: " + fareConfig.getSeniorCitizenDailyCap());

        System.out.println("Morning Peak: " + fareConfig.getMorningPeakStart() + " - " + fareConfig.getMorningPeakEnd());
        System.out.println("Evening Peak: " + fareConfig.getEveningPeakStart() + " - " + fareConfig.getEveningPeakEnd());
    }

    public boolean updateDiscount(CityRideDataset.PassengerType passengerType, double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
            System.out.println("Discount rate must be between 0.00 and 1.00.");
            return false;
        }

        if (passengerType == CityRideDataset.PassengerType.ADULT) {
            fareConfig.setAdultDiscount(discountRate);
        } else if (passengerType == CityRideDataset.PassengerType.STUDENT) {
            fareConfig.setStudentDiscount(discountRate);
        } else if (passengerType == CityRideDataset.PassengerType.CHILD) {
            fareConfig.setChildDiscount(discountRate);
        } else if (passengerType == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
            fareConfig.setSeniorCitizenDiscount(discountRate);
        }

        System.out.println("Discount updated.");
        return true;
    }

    public boolean updateDailyCap(CityRideDataset.PassengerType passengerType, double dailyCap) {
        if (dailyCap <= 0) {
            System.out.println("Daily cap must be greater than 0.");
            return false;
        }

        if (passengerType == CityRideDataset.PassengerType.ADULT) {
            fareConfig.setAdultDailyCap(dailyCap);
        } else if (passengerType == CityRideDataset.PassengerType.STUDENT) {
            fareConfig.setStudentDailyCap(dailyCap);
        } else if (passengerType == CityRideDataset.PassengerType.CHILD) {
            fareConfig.setChildDailyCap(dailyCap);
        } else if (passengerType == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
            fareConfig.setSeniorCitizenDailyCap(dailyCap);
        }

        System.out.println("Daily cap updated.");
        return true;
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
```

## Journey.java

```java
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
```

## CityRideService.java

```java
package service;

import data.CityRideDataset;
import model.Journey;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CityRideService {

    // This list stores journeys while the program is running.
    private ArrayList<Journey> journeys;
    private int nextJourneyId;

    public CityRideService() {
        journeys = new ArrayList<>();
        nextJourneyId = 1;
    }

    public void addJourney(String date, int fromZone, int toZone,
                           CityRideDataset.TimeBand timeBand,
                           CityRideDataset.PassengerType passengerType) {
        double baseFare = CityRideDataset.getBaseFare(fromZone, toZone, timeBand).doubleValue();
        double discountRate = CityRideDataset.DISCOUNT_RATE.get(passengerType).doubleValue();
        double discountAmount = baseFare * discountRate;
        double discountedFare = baseFare - discountAmount;

        double chargedFare = applyDailyCap(passengerType, discountedFare);

        Journey journey = new Journey(nextJourneyId, date, fromZone, toZone,
                timeBand, passengerType, baseFare, discountAmount, chargedFare);

        journeys.add(journey);
        nextJourneyId++;

        System.out.println("Journey added.");
        System.out.println("Fare charged: £" + String.format("%.2f", chargedFare));
    }

    public void showAllJourneys() {
        if (journeys.size() == 0) {
            System.out.println("No journeys added yet.");
            return;
        }

        for (Journey journey : journeys) {
            System.out.println(journey.showJourneyDetails());
        }
    }

    public void showDailySummary() {
        if (journeys.size() == 0) {
            System.out.println("No journeys added yet.");
            return;
        }

        double totalCost = 0;
        Journey mostExpensiveJourney = journeys.get(0);

        for (Journey journey : journeys) {
            totalCost = totalCost + journey.getChargedFare();

            if (journey.getChargedFare() > mostExpensiveJourney.getChargedFare()) {
                mostExpensiveJourney = journey;
            }
        }

        double averageCost = totalCost / journeys.size();

        System.out.println("===== Daily Summary =====");
        System.out.println("Total journeys: " + journeys.size());
        System.out.println("Total cost: £" + String.format("%.2f", totalCost));
        System.out.println("Average cost: £" + String.format("%.2f", averageCost));
        System.out.println("Most expensive journey ID: " + mostExpensiveJourney.getId());
        System.out.println("Most expensive journey cost: £" + String.format("%.2f", mostExpensiveJourney.getChargedFare()));
    }

    public void exportJourneysToCsv() {
        if (journeys.size() == 0) {
            System.out.println("No journeys to export.");
            return;
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("journeys_export.csv"));

            writer.println("id,date,fromZone,toZone,timeBand,passengerType,zonesCrossed,baseFare,discountAmount,chargedFare");

            for (Journey journey : journeys) {
                writer.println(journey.getId() + ","
                        + journey.getDate() + ","
                        + journey.getFromZone() + ","
                        + journey.getToZone() + ","
                        + journey.getTimeBand() + ","
                        + journey.getPassengerType() + ","
                        + journey.getZonesCrossed() + ","
                        + String.format("%.2f", journey.getBaseFare()) + ","
                        + String.format("%.2f", journey.getDiscountAmount()) + ","
                        + String.format("%.2f", journey.getChargedFare()));
            }

            writer.close();

            System.out.println("Journeys exported to journeys_export.csv");
        } catch (Exception e) {
            System.out.println("Error exporting journeys to CSV.");
        }
    }

    public void exportDailySummaryReports() {
        if (journeys.size() == 0) {
            System.out.println("No journeys to export in report.");
            return;
        }

        double totalCost = 0;
        Journey mostExpensiveJourney = journeys.get(0);

        for (Journey journey : journeys) {
            totalCost = totalCost + journey.getChargedFare();

            if (journey.getChargedFare() > mostExpensiveJourney.getChargedFare()) {
                mostExpensiveJourney = journey;
            }
        }

        double averageCost = totalCost / journeys.size();

        try {
            PrintWriter textWriter = new PrintWriter(new FileWriter("daily_summary_report.txt"));

            textWriter.println("===== CityRide Lite Daily Summary Report =====");
            textWriter.println();
            textWriter.println("Total journeys: " + journeys.size());
            textWriter.println("Total cost: £" + String.format("%.2f", totalCost));
            textWriter.println("Average cost: £" + String.format("%.2f", averageCost));
            textWriter.println("Most expensive journey ID: " + mostExpensiveJourney.getId());
            textWriter.println("Most expensive journey cost: £" + String.format("%.2f", mostExpensiveJourney.getChargedFare()));
            textWriter.println();
            textWriter.println("Passenger type totals:");
            textWriter.println("Adult total: £" + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.ADULT)));
            textWriter.println("Student total: £" + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.STUDENT)));
            textWriter.println("Child total: £" + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.CHILD)));
            textWriter.println("Senior Citizen total: £" + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.SENIOR_CITIZEN)));

            textWriter.close();

            PrintWriter csvWriter = new PrintWriter(new FileWriter("daily_summary_report.csv"));

            csvWriter.println("item,value");
            csvWriter.println("Total journeys," + journeys.size());
            csvWriter.println("Total cost," + String.format("%.2f", totalCost));
            csvWriter.println("Average cost," + String.format("%.2f", averageCost));
            csvWriter.println("Most expensive journey ID," + mostExpensiveJourney.getId());
            csvWriter.println("Most expensive journey cost," + String.format("%.2f", mostExpensiveJourney.getChargedFare()));
            csvWriter.println("Adult total," + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.ADULT)));
            csvWriter.println("Student total," + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.STUDENT)));
            csvWriter.println("Child total," + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.CHILD)));
            csvWriter.println("Senior Citizen total," + String.format("%.2f", getPassengerTypeTotal(CityRideDataset.PassengerType.SENIOR_CITIZEN)));

            csvWriter.close();

            System.out.println("Daily summary reports exported to daily_summary_report.txt and daily_summary_report.csv");
        } catch (Exception e) {
            System.out.println("Error exporting daily summary reports.");
        }
    }

    public void showPassengerTypeTotals() {
        if (journeys.size() == 0) {
            System.out.println("No journeys added yet.");
            return;
        }

        System.out.println("===== Passenger Type Totals =====");
        showTotalForPassengerType(CityRideDataset.PassengerType.ADULT);
        showTotalForPassengerType(CityRideDataset.PassengerType.STUDENT);
        showTotalForPassengerType(CityRideDataset.PassengerType.CHILD);
        showTotalForPassengerType(CityRideDataset.PassengerType.SENIOR_CITIZEN);
    }

    private void showTotalForPassengerType(CityRideDataset.PassengerType passengerType) {
        int count = 0;
        double baseTotal = 0;
        double discountTotal = 0;
        double chargedTotal = 0;

        for (Journey journey : journeys) {
            if (journey.getPassengerType() == passengerType) {
                count++;
                baseTotal = baseTotal + journey.getBaseFare();
                discountTotal = discountTotal + journey.getDiscountAmount();
                chargedTotal = chargedTotal + journey.getChargedFare();
            }
        }

        double dailyCap = CityRideDataset.DAILY_CAP.get(passengerType).doubleValue();
        String capReached = "No";

        if (chargedTotal >= dailyCap) {
            capReached = "Yes";
        }

        System.out.println(passengerType + " journeys: " + count
                + " | Base total: £" + String.format("%.2f", baseTotal)
                + " | Discount total: £" + String.format("%.2f", discountTotal)
                + " | Charged total: £" + String.format("%.2f", chargedTotal)
                + " | Cap reached: " + capReached);
    }

    public void showCategoryCounts() {
        if (journeys.size() == 0) {
            System.out.println("No journeys added yet.");
            return;
        }

        int peakCount = 0;
        int offPeakCount = 0;

        for (Journey journey : journeys) {
            if (journey.getTimeBand() == CityRideDataset.TimeBand.PEAK) {
                peakCount++;
            } else {
                offPeakCount++;
            }
        }

        System.out.println("===== Category Counts =====");
        System.out.println("Peak journeys: " + peakCount);
        System.out.println("Off-peak journeys: " + offPeakCount);

        for (int zone = CityRideDataset.MIN_ZONE; zone <= CityRideDataset.MAX_ZONE; zone++) {
            int zoneCount = countJourneysUsingZone(zone);
            System.out.println("Journeys using zone " + zone + ": " + zoneCount);
        }
    }

    private int countJourneysUsingZone(int zone) {
        int count = 0;

        for (Journey journey : journeys) {
            if (journeyUsesZone(journey, zone)) {
                count++;
            }
        }

        return count;
    }
    // Adapted from Johnson's integer range checking idea and modified for CityRide zone filtering
    // (Johnson, 2008)
    private boolean journeyUsesZone(Journey journey, int zone) {
        int smallerZone = journey.getFromZone();
        int largerZone = journey.getToZone();

        if (smallerZone > largerZone) {
            smallerZone = journey.getToZone();
            largerZone = journey.getFromZone();
        }

        return zone >= smallerZone && zone <= largerZone;
    }
    public void filterByPassengerType(CityRideDataset.PassengerType passengerType) {
        ArrayList<Journey> results = new ArrayList<>();

        for (Journey journey : journeys) {
            if (journey.getPassengerType() == passengerType) {
                results.add(journey);
            }
        }

        showFilterResults(results);
    }

    public void filterByTimeBand(CityRideDataset.TimeBand timeBand) {
        ArrayList<Journey> results = new ArrayList<>();

        for (Journey journey : journeys) {
            if (journey.getTimeBand() == timeBand) {
                results.add(journey);
            }
        }

        showFilterResults(results);
    }

    public void filterByZone(int zone) {
        ArrayList<Journey> results = new ArrayList<>();

        for (Journey journey : journeys) {
            if (journeyUsesZone(journey, zone)) {
                results.add(journey);
            }
        }

        showFilterResults(results);
    }

    public void filterByDate(String date) {
        ArrayList<Journey> results = new ArrayList<>();

        for (Journey journey : journeys) {
            if (journey.getDate().equals(date)) {
                results.add(journey);
            }
        }

        showFilterResults(results);
    }

    private void showFilterResults(ArrayList<Journey> results) {
        if (results.size() == 0) {
            System.out.println("No matching journeys found.");
            return;
        }

        for (Journey journey : results) {
            System.out.println(journey.showJourneyDetails());
        }
    }
    // Adapted from Raghuvorkady's ArrayList removal method and modified to remove a Journey by ID
    // (Raghuvorkady, 2021)
    public boolean removeJourney(int journeyId) {
        for (int i = 0; i < journeys.size(); i++) {
            Journey journey = journeys.get(i);

            if (journey.getId() == journeyId) {
                journeys.remove(i);
                recalculateAllJourneyFares();
                System.out.println("Journey removed and totals recalculated.");
                return true;
            }
        }

        System.out.println("Journey ID not found.");
        return false;
    }

    public boolean editJourney(int journeyId, String date, int fromZone, int toZone,
                               CityRideDataset.TimeBand timeBand,
                               CityRideDataset.PassengerType passengerType) {
        boolean journeyEdited = false;

        for (Journey journey : journeys) {
            if (journey.getId() == journeyId) {
                journey.setDate(date);
                journey.setFromZone(fromZone);
                journey.setToZone(toZone);
                journey.setTimeBand(timeBand);
                journey.setPassengerType(passengerType);

                recalculateAllJourneyFares();

                System.out.println("Journey updated.");
                journeyEdited = true;
            }
        }

        if (!journeyEdited) {
            System.out.println("Journey ID not found.");
        }

        return journeyEdited;
    }

    public void resetDay() {
        journeys.clear();
        nextJourneyId = 1;
        System.out.println("Day reset complete.");
    }

    private void recalculateAllJourneyFares() {
        double adultTotal = 0;
        double studentTotal = 0;
        double childTotal = 0;
        double seniorCitizenTotal = 0;

        for (Journey journey : journeys) {
            double baseFare = CityRideDataset.getBaseFare(
                    journey.getFromZone(),
                    journey.getToZone(),
                    journey.getTimeBand()).doubleValue();

            double discountRate = CityRideDataset.DISCOUNT_RATE
                    .get(journey.getPassengerType()).doubleValue();

            double discountAmount = baseFare * discountRate;
            double discountedFare = baseFare - discountAmount;
            double dailyCap = CityRideDataset.DAILY_CAP
                    .get(journey.getPassengerType()).doubleValue();

            double currentTotal = 0;

            if (journey.getPassengerType() == CityRideDataset.PassengerType.ADULT) {
                currentTotal = adultTotal;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.STUDENT) {
                currentTotal = studentTotal;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.CHILD) {
                currentTotal = childTotal;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
                currentTotal = seniorCitizenTotal;
            }

            double chargedFare = discountedFare;

            if (currentTotal >= dailyCap) {
                chargedFare = 0;
            } else if (currentTotal + discountedFare > dailyCap) {
                chargedFare = dailyCap - currentTotal;
            }

            journey.setBaseFare(baseFare);
            journey.setDiscountAmount(discountAmount);
            journey.setChargedFare(chargedFare);

            if (journey.getPassengerType() == CityRideDataset.PassengerType.ADULT) {
                adultTotal = adultTotal + chargedFare;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.STUDENT) {
                studentTotal = studentTotal + chargedFare;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.CHILD) {
                childTotal = childTotal + chargedFare;
            } else if (journey.getPassengerType() == CityRideDataset.PassengerType.SENIOR_CITIZEN) {
                seniorCitizenTotal = seniorCitizenTotal + chargedFare;
            }
        }
    }

    // AI-assisted method and modified and tested for the CityRide Lite daily cap calculation
    // (OpenAI, 2026)
    private double applyDailyCap(CityRideDataset.PassengerType passengerType, double discountedFare) {
        double currentTotal = getPassengerTypeTotal(passengerType);
        double dailyCap = CityRideDataset.DAILY_CAP.get(passengerType).doubleValue();
        double chargedFare = discountedFare;

        if (currentTotal >= dailyCap) {
            chargedFare = 0;
        } else if (currentTotal + discountedFare > dailyCap) {
            chargedFare = dailyCap - currentTotal;
        }

        return chargedFare;
    }

    private double getPassengerTypeTotal(CityRideDataset.PassengerType passengerType) {
        double total = 0;

        for (Journey journey : journeys) {
            if (journey.getPassengerType() == passengerType) {
                total = total + journey.getChargedFare();
            }
        }

        return total;
    }
}
```

## CityRideLiteApp.java

```java
package main;

import data.CityRideDataset;
import service.CityRideService;
import service.RiderProfileService;
import service.ConfigService;

import java.util.Scanner;

public class CityRideLiteApp {

    private Scanner scanner;
    private CityRideService service;
    private RiderProfileService riderProfileService;
    private ConfigService configService;

    public CityRideLiteApp() {
        scanner = new Scanner(System.in);
        service = new CityRideService();
        riderProfileService = new RiderProfileService();
        configService = new ConfigService();
    }

    public static void main(String[] args) {
        CityRideLiteApp app = new CityRideLiteApp();
        app.run();
    }

    private void run() {
        boolean running = true;

        System.out.println("===== CityRide Lite =====");

        while (running) {
            showMenu();

            int choice = getNumberInput("Choose option: ");

            if (choice == 1) {
                addJourney();
            } else if (choice == 2) {
                service.showAllJourneys();
            } else if (choice == 3) {
                service.showDailySummary();
            } else if (choice == 4) {
                filterJourneys();
            } else if (choice == 5) {
                service.showPassengerTypeTotals();
            } else if (choice == 6) {
                service.showCategoryCounts();
            } else if (choice == 7) {
                removeJourney();
            } else if (choice == 8) {
                resetDay();
            } else if (choice == 9) {
                riderProfileService.createProfile(scanner);
            } else if (choice == 10) {
                riderProfileService.displayProfile();
            } else if (choice == 11) {
                riderProfileService.saveProfile();
            } else if (choice == 12) {
                riderProfileService.loadProfile();
            } else if (choice == 13) {
                configService.displayConfig();
            } else if (choice == 14) {
                configService.saveConfig();
            } else if (choice == 15) {
                editJourney();
            } else if (choice == 16) {
                service.exportJourneysToCsv();
            } else if (choice == 17) {
                service.exportDailySummaryReports();
            } else if (choice == 18) {
                adminMenu();
            } else if (choice == 0) {
                System.out.println("Goodbye.");
                running = false;
            } else {
                System.out.println("Invalid option.");
            }

            System.out.println();
        }
    }

    private void showMenu() {
        System.out.println("------------------------------");
        System.out.println("1. Add journey");
        System.out.println("2. List journeys");
        System.out.println("3. Daily summary");
        System.out.println("4. Filter journeys");
        System.out.println("5. Totals by passenger type");
        System.out.println("6. Category counts");
        System.out.println("7. Remove journey");
        System.out.println("8. Reset day");
        System.out.println("9. Create rider profile");
        System.out.println("10. View rider profile");
        System.out.println("11. Save rider profile");
        System.out.println("12. Load rider profile");
        System.out.println("13. View fare config");
        System.out.println("14. Save fare config");
        System.out.println("15. Edit journey");
        System.out.println("16. Export journeys to CSV");
        System.out.println("17. Export daily summary reports");
        System.out.println("18. Admin menu");
        System.out.println("0. Exit");
        System.out.println("------------------------------");
    }

    private void addJourney() {
        String date = getDateInput();

        int fromZone = getValidZone("Enter start zone 1-5: ");
        int toZone = getValidZone("Enter destination zone 1-5: ");

        CityRideDataset.TimeBand timeBand = getTimeBand();
        CityRideDataset.PassengerType passengerType = getPassengerType();

        service.addJourney(date, fromZone, toZone, timeBand, passengerType);
    }

    private String getDateInput() {
        String date = "";

        while (date.isBlank()) {
            System.out.print("Enter journey date (YYYY-MM-DD): ");
            date = scanner.nextLine();

            if (date.isBlank()) {
                System.out.println("Date cannot be blank.");
            }
        }

        return date;
    }

    private int getValidZone(String message) {
        int zone = 0;
        boolean validZone = false;

        while (!validZone) {
            zone = getNumberInput(message);

            if (zone >= CityRideDataset.MIN_ZONE && zone <= CityRideDataset.MAX_ZONE) {
                validZone = true;
            } else {
                System.out.println("Zone must be between 1 and 5.");
            }
        }

        return zone;
    }

    private CityRideDataset.TimeBand getTimeBand() {
        int option = 0;
        CityRideDataset.TimeBand timeBand = CityRideDataset.TimeBand.PEAK;

        while (option != 1 && option != 2) {
            System.out.println("Choose time band:");
            System.out.println("1. Peak");
            System.out.println("2. Off-peak");

            option = getNumberInput("Option: ");

            if (option == 1) {
                timeBand = CityRideDataset.TimeBand.PEAK;
            } else if (option == 2) {
                timeBand = CityRideDataset.TimeBand.OFF_PEAK;
            } else {
                System.out.println("Invalid time band.");
            }
        }

        return timeBand;
    }

    private CityRideDataset.PassengerType getPassengerType() {
        int option = 0;
        CityRideDataset.PassengerType passengerType = CityRideDataset.PassengerType.ADULT;

        while (option < 1 || option > 4) {
            System.out.println("Choose passenger type:");
            System.out.println("1. Adult");
            System.out.println("2. Student");
            System.out.println("3. Child");
            System.out.println("4. Senior Citizen");

            option = getNumberInput("Option: ");

            if (option == 1) {
                passengerType = CityRideDataset.PassengerType.ADULT;
            } else if (option == 2) {
                passengerType = CityRideDataset.PassengerType.STUDENT;
            } else if (option == 3) {
                passengerType = CityRideDataset.PassengerType.CHILD;
            } else if (option == 4) {
                passengerType = CityRideDataset.PassengerType.SENIOR_CITIZEN;
            } else {
                System.out.println("Invalid passenger type.");
            }
        }

        return passengerType;
    }
    private void filterJourneys() {
        System.out.println("Choose filter type:");
        System.out.println("1. Passenger type");
        System.out.println("2. Time band");
        System.out.println("3. Zone");
        System.out.println("4. Date");

        int option = getNumberInput("Option: ");

        if (option == 1) {
            CityRideDataset.PassengerType passengerType = getPassengerType();
            service.filterByPassengerType(passengerType);
        } else if (option == 2) {
            CityRideDataset.TimeBand timeBand = getTimeBand();
            service.filterByTimeBand(timeBand);
        } else if (option == 3) {
            int zone = getValidZone("Enter zone 1-5: ");
            service.filterByZone(zone);
        } else if (option == 4) {
            String date = getDateInput();
            service.filterByDate(date);
        } else {
            System.out.println("Invalid filter option.");
        }
    }

    private void removeJourney() {
        int journeyId = getNumberInput("Enter journey ID to remove: ");

        System.out.print("Are you sure you want to remove this journey? yes/no: ");
        String confirm = scanner.nextLine();

        if (confirm.equals("yes")) {
            service.removeJourney(journeyId);
        } else {
            System.out.println("Remove cancelled.");
        }
    }

    private void editJourney() {
        int journeyId = getNumberInput("Enter journey ID to edit: ");

        System.out.println("Enter the new journey details.");

        String date = getDateInput();

        int fromZone = getValidZone("Enter new start zone 1-5: ");
        int toZone = getValidZone("Enter new destination zone 1-5: ");

        CityRideDataset.TimeBand timeBand = getTimeBand();
        CityRideDataset.PassengerType passengerType = getPassengerType();

        service.editJourney(journeyId, date, fromZone, toZone, timeBand, passengerType);
    }

    private void resetDay() {
        System.out.print("Are you sure you want to reset the day? yes/no: ");
        String confirm = scanner.nextLine();

        if (confirm.equals("yes")) {
            service.resetDay();
        } else {
            System.out.println("Reset cancelled.");
        }
    }

    private void adminMenu() {
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (!password.equals("admin123")) {
            System.out.println("Incorrect admin password.");
            return;
        }

        boolean adminRunning = true;

        while (adminRunning) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. View fare config");
            System.out.println("2. Update discount");
            System.out.println("3. Update daily cap");
            System.out.println("4. Update peak times");
            System.out.println("5. Save fare config");
            System.out.println("0. Return to main menu");

            int option = getNumberInput("Choose admin option: ");

            if (option == 1) {
                configService.displayConfig();
            } else if (option == 2) {
                updateDiscount();
            } else if (option == 3) {
                updateDailyCap();
            } else if (option == 4) {
                updatePeakTimes();
            } else if (option == 5) {
                configService.saveConfig();
            } else if (option == 0) {
                adminRunning = false;
            } else {
                System.out.println("Invalid admin option.");
            }
        }
    }

    private void updateDiscount() {
        CityRideDataset.PassengerType passengerType = getPassengerType();

        System.out.println("Enter discount as a decimal.");
        System.out.println("Example: 0.25 means 25% discount.");

        double discountRate = getDoubleInput("Enter new discount rate: ");

        configService.updateDiscount(passengerType, discountRate);
    }

    private void updateDailyCap() {
        CityRideDataset.PassengerType passengerType = getPassengerType();

        double dailyCap = getDoubleInput("Enter new daily cap: £");

        configService.updateDailyCap(passengerType, dailyCap);
    }

    private void updatePeakTimes() {
        System.out.print("Enter morning peak start time: ");
        String morningStart = scanner.nextLine();

        System.out.print("Enter morning peak end time: ");
        String morningEnd = scanner.nextLine();

        System.out.print("Enter evening peak start time: ");
        String eveningStart = scanner.nextLine();

        System.out.print("Enter evening peak end time: ");
        String eveningEnd = scanner.nextLine();

        configService.updatePeakTimes(morningStart, morningEnd, eveningStart, eveningEnd);
    }

    private double getDoubleInput(String message) {
        double number = 0;
        boolean validNumber = false;

        while (!validNumber) {
            System.out.print(message);

            if (scanner.hasNextDouble()) {
                number = scanner.nextDouble();
                validNumber = true;
            } else {
                System.out.println("Please enter a valid number.");
            }

            scanner.nextLine();
        }

        return number;
    }

    // Adapted from RustyKnight's Scanner integer input checking idea
    // (RustyKnight, 2022)
    private int getNumberInput(String message) {
        int number = 0;
        boolean validNumber = false;

        while (!validNumber) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                validNumber = true;
            } else {
                System.out.println("Please enter a number.");
            }

            scanner.nextLine();
        }

        return number;
    }
}
```

------------------------------------------------------------------------------------------------------------------------------

### Updated Gantt Chart

------------------------------------------------------------------------------------------------------------------------------

![](C:\Users\HP\OneDrive\Desktop\MILESTONE%202%20CHART%20f.png)

------------------------------------------------------------------------------------------------------------------------------

### Diary Entry 1: User Roles and Rider Profile Foundation

In this stage i started extending my original CityRide Lite Part 1 project for the Part 2 requirements so i created new model classes called User, `Rider`, `Admin` and `RiderProfile` and the reason for doing this was to separate user related data properly instead of placing everything inside the main program so the `User` class stores shared details such as `userId` and `name`, while `Rider` and `Admin` represent different user roles and the `RiderProfile` class stores the rider’s name, passenger type and default payment option.

I also created `RiderProfileService` to manage the rider profile and this means that profile logic is not placed directly inside `CityRideLiteApp` and the main application only controls the menu,while the service class handles the profile actions and i also connected the profile features to the console menu so the user can create and view a rider profile.

One challenge I faced was making sure the new classes were placed in the correct package so some files needded to be moved into the model package and needed the correct package model line and i solved this by checking the existing Part 1 structure and making the new files follow the same style and this helped remove import errors and kept my project organised.

### Diary Entry 2: JSON Profile and Configuration File Handling

In this stage i added JSON file handling so that data can be saved and loaded instead of only staying in memory and i used the Gson library because it can convert Java objects into JSON and then write them to a file and i also updated RiderProfileService so the rider profile can be saved to rider_profile.json and can be loaded again later.

I also created a FareConfig class to store configurable values such as discounts, daily caps and peak time settings and then i created ConfigService which uses Gson, FileReader and FileWriter to load and save the config in fare_config.json and i also added safe default values so the program can still run if the JSON file does not exist.

The main challenge was adding Gson correctly in IntelliJ and making sure the imports worked and i solved this by adding the Gson library through IntelliJ and following the same structure as the teacher’s JSON example and i also used try/catch so the program does not crash if the file is missing or empty and i tested this by saving and loading the rider profile and by viewing and saving the fare config.

### Diary Entry 3: Journey Editing, Deleting and Fare Recalculation

In this stage i improved the journey management features and i added setter methods to the Journey class so that journey details can be changed after the journey has been created and i also added setters for date, zones, time band, passenger type, base fare, discount amount and charged fare but i did not add a setter for the journey ID because the ID should stay fixed.

I then added an editJourney method in CityRideService and this method searches the ArrayList<Journey> using the journey ID and if the journey is found then it updates the journey details and recalculates the fare and i also updated removeJourney so that after a journey is removed all fares are recalculated and this is important because daily caps depend on the journeys in the list so editing or deleting one journey can change the totals.

One challenge was working out how to recalculate fares properly after editing or deleting and i solved this by creating recalculateAllJourneyFares which loops through the journey list again and updates the base fare, discount amount and charged fare and i also had a small red error in IntelliJ caused by an accidental extra character after return true which was headache and i fixed it by checking the Problems tab and removing the extra character and first this error was not showing up first but restarting again again it was fixed.

### Diary Entry 4: CSV Export, Report Export, and Admin Configuration

In this stage i added CSV and report export features so i created exportJourneysToCsv in CityRideService so the current journeys can be written to journeys_export.csv and the CSV includes journey ID, date, zones, time band, passenger type, zones crossed, base fare, discount amount and charged fare and i also added a check so if there are no journeys the program shows a message instead of exporting empty data.

I also created exportDailySummaryReports which exports the daily summary as both a text report and a CSV report and the report includes total journeys, total cost, average cost, most expensive journey ID, most expensive journey cost and passenger type totals. I used FileWriter and PrintWriter because they are simple ways to write lines into files and i connected these features to the menu and tested them by adding journeys, exporting the files and checking that the data was correct.

After this i prepared the admin configuration features and i added update methods in ConfigService for discounts, daily caps and peak times and i also added a password protected admin menu in CityRideLiteApp where the admin can view config, update values and save the config back to JSON and i tested the wrong password, correct password, view config, update discount, update daily cap, update peak times and save config options successfully.

------------------------------------------------------------------------------------------------------------------------------
