package ems.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import ems.db.DBConnection;

public class Booking {
    private int id;
    private int customerId;
    private int eventId;
    private Timestamp bookingDate;

    public Booking(int customerId, int eventId, Timestamp bookingDate) {
        this.customerId = customerId;
        this.eventId = eventId;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * Returns a string representation of the Booking object.
     *
     * @return a string representation of the Booking object
     */
    @Override
    public String toString() {
        return "Booking [id=" + id + ", customerId=" + customerId + ", eventId=" + eventId + ", bookingDate=" + bookingDate + "]";
    }

    /**
     * Saves the Booking object to the database.
     */
    public boolean save() {
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "INSERT INTO bookings (customer_id, event_id, booking_date) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, this.customerId);
            ps.setInt(2, this.eventId);
            ps.setTimestamp(3, this.bookingDate);
            ps.executeUpdate();

            // Get the auto-generated ID
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1);
            }

            db.closeConnection();
            System.out.println("Booking added successfully");

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Fetches a Booking object from the database based on the provided ID.
     *
     * @param id the ID of the booking
     * @return the matched Booking object from the database or null if not found
     */
    public static Booking fetchById(int id) {
        Booking booking = null;
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "SELECT * FROM bookings WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                booking = new Booking(
                    rs.getInt("customer_id"),
                    rs.getInt("event_id"),
                    rs.getTimestamp("booking_date")
                );
                booking.setId(rs.getInt("id"));
            }
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }

    /**
     * Fetches the attendee's name based on the customer ID.
     *
     * @return the full name of the attendee
     */
    public String attendeeName() {
        String attendeeName = "";
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "SELECT first_name, last_name FROM customers WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, this.customerId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                attendeeName = firstName + " " + lastName;
            }

            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendeeName;
    }
}
