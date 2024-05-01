package ems.main;

import java.util.HashMap;
import java.util.Map;

import java.sql.Connection;
import java.sql.SQLException;

import ems.Controller.OrganizerController;
import ems.db.DBConnection; // Import the DBConnection class if it's in a different package
import ems.Models.Organizer;

/**
 * The Main class is the entry point of the application.
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Create a map to store the employee data
        Map<String, String> organizerData = new HashMap<>();

        // Add the organizer data to the map
        organizerData.put("FirstName", "John");
        organizerData.put("LastName", "John");
        organizerData.put("Email", "John@example.com");
        organizerData.put("Password", "root");
        organizerData.put("gender", "M");
        organizerData.put("phone", "0708626805");

        // Create a new Organizer object with the provided data
        OrganizerController.createOrganizer(organizerData);

        // Retrieve the Organizer object from the database based on the email
        Organizer organizer = OrganizerController.getOrganizer("John@example.com");
        System.out.println(organizer);
    }
}
