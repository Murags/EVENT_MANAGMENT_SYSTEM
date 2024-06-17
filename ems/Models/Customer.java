package ems.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ems.db.DBConnection;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String phone;

    public Customer(String firstName, String lastName, String email, String password, String gender, String phone){
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
     * Returns a string representation of the Customer object.
     *
     * @return a string representation of the Customer object
     */
    @Override
    public String toString(){
        return "Customer [" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", gender=" + gender + ", phone=" + phone + "]";
    }

    /**
     * Saves the Customer object to the database.
     */
    public void save(){
        try{
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "INSERT INTO customers (First_Name, Last_Name, email, password, gender, phone) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.firstName);
            ps.setString(2, this.lastName);
            ps.setString(3, this.email);
            ps.setString(4, this.password);
            ps.setString(5, this.gender);
            ps.setString(6, this.phone);
            ps.executeUpdate();
            db.closeConnection();
            System.out.println("Customer added successfully");

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Fetches an Customer object from the database based on the provided email and password.
     *
     * @param email the email address of the customer
     * @param password the password of the customer
     * @return the matched Customer object from the database or null if not found
     */
    public static Customer fetchByEmail(String email){
        Customer customer = null;
        try{
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();

            String query = "SELECT * FROM customers WHERE email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                customer = new Customer(
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
        return customer;
    }

}
