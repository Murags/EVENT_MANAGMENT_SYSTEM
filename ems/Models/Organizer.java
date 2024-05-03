package ems.Models;

import java.sql.Connection;
import java.sql.*;
import ems.db.DBConnection; // Import the DBConnection class if it's in a different package

/**
 * Represents an Organizer entity with properties and CRUD operations.
 */
public class Organizer {
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
    public String toString(){
        return "Organizer [" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", gender=" + gender + ", phone=" + phone + "]";
    }

    /**
     * Saves the Organizer object to the database.
     */
    public void save(){
        try{
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "INSERT INTO organizers (First_Name, Last_Name, email, password, gender, phone) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.firstName);
            ps.setString(2, this.lastName);
            ps.setString(3, this.email);
            ps.setString(4, this.password);
            ps.setString(5, this.gender);
            ps.setString(6, this.phone);
            ps.executeUpdate();
            db.closeConnection();
            System.out.println("Organizer added successfully");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Fetches anOrganizer object from the database based on the provided email and password.
     *
     * @param email the email address of the organizer
     * @param password the password of the organizer
     * @return the matched Organizer object from the database or null if not found
     */
    public static Organizer fetchByEmail(String email){
        Organizer organizer = null;
        try{
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "SELECT * FROM organizers WHERE email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                organizer = new Organizer(
                    rs.getString("First_Name"),
                    rs.getString("Last_Name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("gender"),
                    rs.getString("phone")
                );
            }
            db.closeConnection();

        }catch(Exception e){
            e.printStackTrace();
        }
        return organizer;
    }
}
