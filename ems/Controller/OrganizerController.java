package ems.Controller;

import java.util.Map;

import ems.Models.Organizer;
import ems.views.OrganizerView;

/**
 * The OrganizerController class handles the creation and retrieval of Organizer objects.
 */
public class OrganizerController {

    /**
     * Creates a new Organizer object with the provided data and saves it to the database.
     *
     * @param data A Map containing the Organizer's properties.
     */
    public static void createOrganizer(Map<String, String> data) {
        String firstName = data.get("FirstName");
        String lastName = data.get("LastName");
        String email = data.get("Email");
        String password = data.get("Password");
        String gender = data.get("gender");
        String phone = data.get("phone");

        Organizer organizer = new Organizer(firstName, lastName, email, password, gender, phone);
        organizer.save();
    }

    /**
     * Retrieves an Organizer object from the database based on the provided email.
     *
     * @param email The email address of the Organizer.
     * @return The Organizer object if found, otherwise null.
     */
    public static Organizer getOrganizer(String email) {
        Organizer organizer = Organizer.fetchByEmail(email);
        return organizer;
    }
}
