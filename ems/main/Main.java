package ems.main;

import java.util.HashMap;
import java.util.Map;

import java.sql.Connection;
import java.sql.SQLException;

import ems.Controller.CustomerController;
import ems.db.DBConnection; // Import the DBConnection class if it's in a different package
import ems.Models.Customer;

/**
 * The Main class is the entry point of the application.
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Create a map to store the employee data
        Connection con = null;
        HashMap<String, String> customerData = new HashMap<>();
        try{

            DBConnection dbConnection = new DBConnection();
            //Add customer to the database
            customerData.put("FirstName","Jhon");
            customerData.put("LastName","Doe");
            customerData.put("gender", "M");
            customerData.put("phone","797354544");
            customerData.put("Email","jhondoes@gmail.com");
            customerData.put("Password", "jhondoe123");

            con = dbConnection.getConnection();
            CustomerController.createCustomer(customerData);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(con != null && !con.isClosed()){
                    con.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        // Retrieve the Customer object from the database based on the email
        Customer customer = CustomerController.getCustomer("jhondoes@gmail.com");
        System.out.println(customer);
    }
}
