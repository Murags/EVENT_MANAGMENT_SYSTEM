package ems.Models;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ems.db.DBConnection;
import java.util.ArrayList;
import java.util.List;


import ems.views.CustomerView;
import ems.views.Login;


/**
 * Represents a Customer entity with properties and CRUD operations.
 */
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String phone;

    /**
     * Constructor to initialize a Customer object with provided details.
     *
     * @param firstName  the first name of the customer
     * @param lastName   the last name of the customer
     * @param email      the email address of the customer
     * @param password   the password of the customer
     * @param gender     the gender of the customer
     * @param phone      the phone number of the customer
     */
    public Customer(String firstName, String lastName, String email, String password, String gender, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the Customer object.
     *
     * @return a string representation of the Customer object
     */
    @Override
    public String toString() {
        return "Customer [" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", gender=" + gender + ", phone=" + phone + "]";
    }

    /**
     * Saves the Customer object to the database and sets the ID.
     */
    public boolean save() {
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "INSERT INTO customers (First_Name, Last_Name, email, password, gender, phone) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.firstName);
            ps.setString(2, this.lastName);
            ps.setString(3, this.email);
            ps.setString(4, this.password);
            ps.setString(5, this.gender);
            ps.setString(6, this.phone.substring(1));
            ps.executeUpdate();

            // Get the generated ID
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }

            db.closeConnection();
            System.out.println("Customer added successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Fetches a Customer object from the database based on the provided email.
     *
     * @param email the email address of the customer
     * @return the matched Customer object from the database or null if not found
     */
    public static Customer fetchByEmail(String email) {
        Customer customer = null;
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "SELECT * FROM customers WHERE email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(
                    rs.getString("First_Name"),
                    rs.getString("Last_Name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("gender"),
                    rs.getString("phone")
                );
                customer.setId(rs.getInt("id")); // Set the ID
            }
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    /**
     * Fetches all events booked by this customer.
     *
     * @return a list of Event objects booked by this customer
     */
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            String query = "SELECT e.id, e.organizer_id, e.title, e.description, e.price, e.image " +
                        "FROM bookings b " +
                        "JOIN events e ON b.event_id = e.id " +
                        "WHERE b.customer_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, this.id);

            rs = ps.executeQuery();
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return events;
    }
    //The below method is called in the CustomerView when the update button is clicked to update customer details
    public void updateCustomer(String newName, String newEmail, String newPassword, JFrame cusomterViewFrame){

        try{
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "UPDATE customers SET First_Name = ? , email = ? , password = ? WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, newName);
            ps.setString(2, newEmail);
            ps.setString(3, newPassword);
            ps.setInt(4, getId());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                setFirstName(newName);
                setEmail(newEmail);
                setPassword(newPassword);
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "User details updated successfully!");
                cusomterViewFrame.dispose();
                new Login().setVisible(true);
            } else {
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "User details update failed. Please try again!");
            }
         }catch(SQLException | ClassNotFoundException em){
            em.printStackTrace();
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Database Connection error: " + em.getMessage());
        }

    }

    //The below method is called in the CustomerView when the delete button is clicked to delete cusomter details
    public void deleteCustomer(JFrame customerViewFrame) {

    JPanel panel = new JPanel();
    int confirmation = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete this user?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if(confirmation == JOptionPane.YES_OPTION){
                try{
                        DBConnection db = new DBConnection();
                        Connection con = db.getConnection();

                        String query = "DELETE FROM customers WHERE id = ?";
                        PreparedStatement pmt = con.prepareStatement(query);
                        pmt.setInt(1, getId());
                        int rowsAffectected = pmt.executeUpdate();

                        if(rowsAffectected > 0){
                            JOptionPane.showMessageDialog(panel, "Userr deleted successfully!");
                            customerViewFrame.dispose();
                            new Login().setVisible(true);
                        }
                        else{
                            JOptionPane.showMessageDialog(panel, "User deletetion failed. Please Try Again!");

                        }
                }catch(ClassNotFoundException | SQLException emp){
                    emp.printStackTrace();
                }
            }
    }
}
