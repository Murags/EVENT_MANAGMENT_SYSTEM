package ems.Controller;

import java.sql.Timestamp;

import ems.Models.Booking;
import ems.Models.Customer;
import ems.Models.Event;

import ems.Utils.MpesaSTKPush;

public class BookingsController {

    /**
     * Create a new booking.
     *
     * @param customerId the ID of the customer
     * @param eventId the ID of the event
     * @return true if the booking is successful, false otherwise
     */
    public static boolean createBooking(Customer customer, Event event) {
        Timestamp bookingDate = new Timestamp(System.currentTimeMillis());
        try{
            String token = MpesaSTKPush.getAccessToken();
            if (token != null)
            {
                int amount = (int)event.getPrice();
                String phone = "254" + customer.getPhone();
                // System.out.println(phone);
                MpesaSTKPush.triggerSTKPush(token, phone, String.valueOf(amount));

                Booking booking = new Booking(customer.getId(), event.getId(), bookingDate);
                return booking.save();
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
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
