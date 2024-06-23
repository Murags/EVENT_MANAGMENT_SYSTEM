package ems.Controller;

import ems.Models.Event;

public class EventsController {
    public static boolean createEvent(int organizer_id, String title, String description, double price, String imageUrl) {
        Event event = new Event(organizer_id, title, description, price, imageUrl);
        return event.save();
    }
}
