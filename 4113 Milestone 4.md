# IY4113 Milestone 4

| Assessment Details | Please Complete All Details                                             |
| ------------------ | ----------------------------------------------------------------------- |
| Group              | A                                                                       |
| Module Title       | IY4113 Applied Software Engineering using Object-Orientated Programming |
| Assessment Type    | Java Fundamentals Part 1                                                |
| Module Tutor Name  | Shore, Jonathan                                                         |
| Student ID Number  | T0496112                                                                |
| Date of Submission | 5/24/2026                                                               |
| Word Count         | 1230                                                                    |
| GItHub Link        | https://github.com/ALI-MARDAN-SHAH/new.git                              |

- [x] *I confirm that this assignment is my own work. Where I have referred to academic sources, I have provided in-text citations and included the sources in
  the final reference list.*

- [x] *Where I have used AI, I have cited and referenced appropriately.

------------------------------------------------------------------------------------------------------------------------------

### Program Code

---

#### CityRideLiteApp.java

```java
package main;

import data.CityRideDataset;
import service.CityRideService;

import java.util.Scanner;

public class CityRideLiteApp {

    private Scanner scanner;
    private CityRideService service;

    public CityRideLiteApp() {
        scanner = new Scanner(System.in);
        service = new CityRideService();
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
                System.out.println("Daily summary selected.");
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
        System.out.println("0. Exit");
        System.out.println("------------------------------");
    }

    private void addJourney() {
        System.out.print("Enter date, for example 2026-05-24: ");
        String date = scanner.nextLine();

        int fromZone = getValidZone("Enter start zone 1-5: ");
        int toZone = getValidZone("Enter destination zone 1-5: ");

        CityRideDataset.TimeBand timeBand = getTimeBand();
        CityRideDataset.PassengerType passengerType = getPassengerType();

        service.addJourney(date, fromZone, toZone, timeBand, passengerType);
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
        CityRideDataset.TimeBand timeBand = CityRideDataset.TimeBand.PEAK;

        System.out.println("Choose time band:");
        System.out.println("1. Peak");
        System.out.println("2. Off-peak");

        int option = getNumberInput("Option: ");

        if (option == 2) {
            timeBand = CityRideDataset.TimeBand.OFF_PEAK;
        }

        return timeBand;
    }

    private CityRideDataset.PassengerType getPassengerType() {
        CityRideDataset.PassengerType passengerType = CityRideDataset.PassengerType.ADULT;

        System.out.println("Choose passenger type:");
        System.out.println("1. Adult");
        System.out.println("2. Student");
        System.out.println("3. Child");
        System.out.println("4. Senior Citizen");

        int option = getNumberInput("Option: ");

        if (option == 2) {
            passengerType = CityRideDataset.PassengerType.STUDENT;
        } else if (option == 3) {
            passengerType = CityRideDataset.PassengerType.CHILD;
        } else if (option == 4) {
            passengerType = CityRideDataset.PassengerType.SENIOR_CITIZEN;
        }

        return passengerType;
    }

    // Adapted from RustyKnight's Scanner integer input checking idea (RustyKnight, 2022).
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

---

#### CityRideService.java

```java
package service;

import data.CityRideDataset;
import model.Journey;

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
        Journey journey = new Journey(nextJourneyId, date, fromZone, toZone, timeBand, passengerType);

        journeys.add(journey);
        nextJourneyId++;

        System.out.println("Journey added.");
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
}
```

------------------------------------------------------------------------------------------------------------------------------

### Updated Gantt Chart

------------------------------------------------------------------------------------------------------------------------------

![](M4%20Gantt%20Chart%202.png)

------------------------------------------------------------------------------------------------------------------------------

### Diary Entries : 1 5/22/2026 to 5/23/2026

---

I worked on the milestone 4 coding system and made the first commit for milestone 4 this commit starts the main menu in cityrideliteapp.java and adding the main menu in the program needs to be done before the user chooses what action they wanna do. At this stage the menu doesn’t have the full features yet but it gives a basic structure for this program.  

In this code I also used a scanner so the user can type their option in the menu with this and I have also used a while loop with a boolean variable called running so the menu keeps showing until the user chooses exit. I have used the if and else if just to verify what number the user has entered. For Example, option 1 shows added journey when selected option 2 shows list journeys option 3 shows daily summary and option 0 exits the program.  

The only problem I face is that IntelliJ shows an error for the scanner and some of the print statements but when I check the code I get to know that the important line for the scanner was typed incorrectly which took bit of time. I fixed it by changing it to import java.util.Scanner;. After doing this, I have restarted IntelliJ this time there were no red errors but a few small warnings so I started testing the menu by entering different types of options and at last the menu worked.

---

### Diary Entries : 2 5/24/2026

---

Today I completed second and the third commit for Milestone 4. In the second commit, I connected the menu in CityRideLiteApp.java to the CityRideService.java class. Before connecting the menu the menu only showed messages when the user selected a particular option. so I changed option 1 so that it could add a test journey and option 2 was used for listing the journeys stored in the service class. I have also added the showAllJourneys method in CityRideService.java so that the program can display the journeys saved in the ArrayList.

In the third commit, I replaced the fixed test journey with real user input and I added input for the date and start zone and destination zone and time band and passenger type and I also added simple validation for the zone so that the program only accepts zones from 1 to 5. I also found it difficult to understand how CityRideDataset.TimeBand and CityRideDataset.PassengerType worked from the teacher dataset, so I watched a few Java videos to understand how these types can be used inside another class. The most difficult part of this was adding the number input method because I had to make the program check if the user entered a number before reading it i had to research and found and used Scanner.hasNextInt and Scanner.nextInt and because this was the part that looked harder for me to understand. I used a referenced GitHub example from RustyKnight because it checked integer input and I adapted the idea into my own getNumberInput method for the program.

The main problem I faced was understanding how the input methods are connected. For instance addJourney calls the zone method and the time band method and the passenger type method before sending the final details to CityRideService at the start it was confusing because one method was calling another method but after I tested it from the menu it made more sense to me. I also added the Milestone 4 file to this project. After testing, the program could add a journey from user input and then it could list the journey from the menu.

---

#### Reference

RustyKnight. (2022, March 30). TerminalInput.java. [GitHub Gist]. Retrieved from: [A conceptually terminal parser for Java using Scanner · GitHub](https://gist.github.com/RustyKnight/93f07a59b1b17af7da9d7a5f15aa79a1) [Accessed 24 May 2026].

---
