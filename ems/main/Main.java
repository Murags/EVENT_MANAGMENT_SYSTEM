package ems.main;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.SwingUtilities;

import java.sql.Connection;
import java.sql.SQLException;

import ems.Controller.CustomerController;
import ems.db.DBConnection; // Import the DBConnection class if it's in a different package
import ems.Models.Customer;

import ems.views.SignUp;
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
        SwingUtilities.invokeLater(() ->{
            SignUp signUp = new SignUp();
            signUp.setVisible(true);
        });
    }
}
