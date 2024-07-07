package ems.Models;

import javax.swing.*;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ems.db.DBConnection; // Import the DBConnection class
import ems.views.Login;



/**
 * Represents an Organizer entity with properties and CRUD operations.
 */
public class Organizer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String phone;

    /**
     * Constructor to initialize an Organizer object with provided details.
     *
     * @param firstName  the first name of the organizer
     * @param lastName   the last name of the organizer
     * @param email      the email address of the organizer
     * @param password   the password of the organizer
     * @param gender     the gender of the organizer
     * @param phone      the phone number of the organizer
     */
    public Organizer(String firstName, String lastName, String email, String password, String gender, String phone) {
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
     * Returns a string representation of the Organizer object.
     *
     * @return a string representation of the Organizer object
     */
    @Override
    public String toString() {
        return "Organizer [" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", gender=" + gender + ", phone=" + phone + "]";
    }

    /**
     * Saves the Organizer object to the database and sets the ID.
     */
    public void save() {
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "INSERT INTO organizers (First_Name, Last_Name, email, password, gender, phone) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.firstName);
            ps.setString(2, this.lastName);
            ps.setString(3, this.email);
            ps.setString(4, this.password);
            ps.setString(5, this.gender);
            ps.setString(6, this.phone);
            ps.executeUpdate();

            // Get the generated ID
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }

            db.closeConnection();
            System.out.println("Organizer added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches an Organizer object from the database based on the provided email.
     *
     * @param email the email address of the organizer
     * @return the matched Organizer object from the database or null if not found
     */
    public static Organizer fetchByEmail(String email) {
        Organizer organizer = null;
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "SELECT * FROM organizers WHERE email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                organizer = new Organizer(
                    rs.getString("First_Name"),
                    rs.getString("Last_Name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("gender"),
                    rs.getString("phone")
                );
                organizer.setId(rs.getInt("id")); // Set the ID
            }
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return organizer;
    }
    
 //Updating the Organizer details 
 public void updateOrganizer(String newName, String newEmail, String newPassword, JFrame organizerViewFrame) {
    try {
        DBConnection db = new DBConnection();
        Connection con = db.getConnection();

        String query = "UPDATE organizers SET First_Name = ?, email = ?, password = ? WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, newName);
        ps.setString(2, newEmail);
        ps.setString(3, newPassword);
        ps.setInt(4, getId());

        int rowsAffected = ps.executeUpdate();
        JPanel panel = new JPanel();
        if (rowsAffected > 0) {
            setFirstName(newName);
            setEmail(newEmail);
            setPassword(newPassword);
            JOptionPane.showMessageDialog(panel, "Organizer details updated successfully!");
            organizerViewFrame.dispose();  // Close the OrganizerView frame
            new Login().setVisible(true);  // Show the login frame
        } else {
            JOptionPane.showMessageDialog(panel, "Organizer details update failed. Please try again!");
        }
    } catch (SQLException | ClassNotFoundException em) {
        em.printStackTrace();
        JPanel panel = new JPanel();
        JOptionPane.showMessageDialog(panel, "Database Connection error: " + em.getMessage());
    }
}

//Deleting Method  for deleting organizer data
public void deleteOrganizer(JFrame organizerViewFrame){
    JPanel panel = new JPanel();
    int confirmation = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete this organizer?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if(confirmation == JOptionPane.YES_OPTION){
                      try{
                            DBConnection db = new DBConnection();
                            Connection con = db.getConnection();

                            String query = "DELETE FROM organizers WHERE id = ?";
                            PreparedStatement pmt = con.prepareStatement(query);
                            pmt.setInt(1, getId());
                            int rowsAffectected = pmt.executeUpdate();

                            if(rowsAffectected > 0){
                                JOptionPane.showMessageDialog(panel, "Organizer deleted successfully!");
                                organizerViewFrame.dispose();
                                new Login().setVisible(true);
                            }
                            else{
                                JOptionPane.showMessageDialog(panel, "Organizer deletetion failed. Please Try Again!");

                            }
                      }catch(ClassNotFoundException | SQLException emp){
                          emp.printStackTrace();
                      }
                }
}

    /**
     * Fetches all events associated with this organizer.
     *
     * @return a list of Event objects associated with this organizer
     */
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "SELECT * FROM events WHERE organizer_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, this.id);

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
    public void update(){
        
    }
}
