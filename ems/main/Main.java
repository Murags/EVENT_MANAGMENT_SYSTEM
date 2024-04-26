package ems.main;

import java.sql.Connection;
import java.sql.SQLException;

import ems.db.DBConnection; // Import the DBConnection class if it's in a different package

public class Main {
    public static void main(String[] args) {
        Connection con = null;

        try {
            // Create an instance of DBConnection
            DBConnection dbConnection = new DBConnection();

            // Retrieve the Connection object
            con = dbConnection.getConnection();

        } catch (ClassNotFoundException | SQLException e) {
            // Handle ClassNotFoundException and SQLException separately
            System.out.println("Error connecting to the database:");
            e.printStackTrace();
        } finally {
            // Close the connection in the finally block to ensure it's always closed
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                // Log or handle the SQLException
                System.out.println("Error closing connection:");
                e.printStackTrace();
            }
        }
    }
}
