package ems.Controller;
import ems.Models.Customer;
import ems.Models.Organizer;


public class AuthController{
    public static boolean login(String email, String password, String role) {
        // TO DO: implement login logic
        if(role == "User"){
            Customer customer = Customer.fetchByEmail(email);
            System.out.println(customer);
            if(customer != null && customer.getPassword().equals(password)){
                return true;
            }
        }
        else if(role == "Organizer"){
            Organizer organizer = Organizer.fetchByEmail(email);
            if(organizer != null && organizer.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
