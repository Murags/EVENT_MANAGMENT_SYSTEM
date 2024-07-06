package ems.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ems.Models.Booking;
import ems.db.DBConnection;

public class Event {
    private int id;
    private int organizerId; // New field for organizer ID
    private String title;
    private String description;
    private double price;
    private String image;

    public Event(int organizerId, String title, String description, double price, String image) {
        this.organizerId = organizerId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return a string representation of the Event object
     */
    @Override
    public String toString() {
        return "Event [id=" + id + ", organizerId=" + organizerId + ", title=" + title + ", description=" + description + ", price=" + price + ", image=" + image + "]";
    }

    /**
     * Saves the Event object to the database.
     */
    public boolean save() {
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "INSERT INTO events (organizer_id, title, description, price, image) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, this.organizerId);
            ps.setString(2, this.title);
            ps.setString(3, this.description);
            ps.setDouble(4, this.price);
            ps.setString(5, this.image);
            ps.executeUpdate();

            // Get the auto-generated ID
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1);
            }

            db.closeConnection();
            System.out.println("Event added successfully");

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(){
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            String query = "UPDATE events SET organizer_id = ?, title = ?, description = ?, price = ?, image = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, this.organizerId);
            ps.setString(2, this.title);
            ps.setString(3, this.description);
            ps.setDouble(4, this.price);
            ps.setString(5, this.image);
            ps.setInt(6, this.id);
            ps.executeUpdate();
            db.closeConnection();
            System.out.println("Event updated successfully");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete() {
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "DELETE FROM events WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, this.id);
            int affectedRows = ps.executeUpdate();

            db.closeConnection();

            if (affectedRows > 0) {
                System.out.println("Event deleted successfully");
                return true;
            } else {
                System.out.println("No event found with the specified ID");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Fetches an Event object from the database based on the provided ID.
     *
     * @param id the ID of the event
     * @return the matched Event object from the database or null if not found
     */
    public static Event fetchById(int id) {
        Event event = null;
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "SELECT * FROM events WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                event = new Event(
                    rs.getInt("organizer_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("image")
                );
                event.setId(rs.getInt("id"));
            }
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    /**
     * Fetches all events
     *
     * @return a list of Event objects
     */
    public static List<Event> all() {
        List<Event> events = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "SELECT * FROM events";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Event event = new Event(
                    rs.getInt("organizer_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("image")
                );
                event.setId(rs.getInt("id"));
                events.add(event);
            }
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    /**
     * Fetches all bookings
     *
     * @return a list of bookings objects
     */
    public List<Booking> allBookings() {
        List<Booking> bookings = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "SELECT * FROM bookings WHERE event_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, this.id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("customer_id"),
                    rs.getInt("event_id"),
                    rs.getTimestamp("booking_date")
                );
                booking.setId(rs.getInt("id"));
                bookings.add(booking);
            }
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
