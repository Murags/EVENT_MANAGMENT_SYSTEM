package ems.Controller;

import java.sql.SQLException;
import java.util.HashMap;

import ems.Models.Customer;

public class CustomerController {
    public static boolean createCustomer(HashMap<String, String> data){
        String firstName = data.get("FirstName");
        String lastName = data.get("LastName");
        String email = data.get("Email");
        String password = data.get("Password");
        String gender = data.get("Gender");
        String phone = data.get("Phone");

        Customer customer = new Customer(firstName, lastName, email, password, gender, phone);

        boolean success = customer.save();
        return success;
    }

    public static Customer getCustomer(String email){
        Customer customer = Customer.fetchByEmail(email);
        return customer;
    }

}
