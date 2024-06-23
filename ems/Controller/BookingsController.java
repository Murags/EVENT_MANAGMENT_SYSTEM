package ems.Controller;

import java.sql.Timestamp;

import ems.Models.Booking;

public class BookingsController {

    /**
     * Create a new booking.
     *
     * @param customerId the ID of the customer
     * @param eventId the ID of the event
     * @return true if the booking is successful, false otherwise
     */
    public static boolean createBooking(int customerId, int eventId) {
        Timestamp bookingDate = new Timestamp(System.currentTimeMillis());
        Booking booking = new Booking(customerId, eventId, bookingDate);
        return booking.save();
    }

    /**
     * Fetch a booking by ID.
     *
     * @param bookingId the ID of the booking
     * @return the Booking object, or null if not found
     */
    public static Booking getBookingById(int bookingId) {
        return Booking.fetchById(bookingId);
    }

}
