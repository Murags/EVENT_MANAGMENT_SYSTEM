package ems.Controller;

import java.util.ArrayList;
import java.util.List;
import ems.Models.Event;

public class EventsController {
    public static boolean createEvent(int organizer_id, String title, String description, double price, String imageUrl) {
        Event event = new Event(organizer_id, title, description, price, imageUrl);
        return event.save();
    }

    public static boolean deleteEvent(int eventId){
        Event event = Event.fetchById(eventId);
        if(event != null){
           return event.delete();
        }
        return false;
    }

    public static List<Event> allEvents(){
        return Event.all();
    }
}
