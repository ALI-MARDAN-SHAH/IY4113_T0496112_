package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.JourneyDayState;

import java.io.FileReader;
import java.io.FileWriter;

public class JourneyStateService {

    private Gson gson;
    private String fileName;

    public JourneyStateService() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        fileName = "journey_day_state.json";
    }

    public void saveState(JourneyDayState state) {
        try {
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(state, writer);
            writer.close();

            System.out.println("Current day state saved to JSON file.");
        } catch (Exception e) {
            System.out.println("Error saving current day state.");
        }
    }

    public JourneyDayState loadState() {
        JourneyDayState state = null;

        try {
            FileReader reader = new FileReader(fileName);
            state = gson.fromJson(reader, JourneyDayState.class);
            reader.close();

            if (state == null) {
                System.out.println("Current day state file was empty.");
            } else {
                System.out.println("Current day state loaded from JSON file.");
            }
        } catch (Exception e) {
            System.out.println("No current day state file found.");
        }

        return state;
    }
}