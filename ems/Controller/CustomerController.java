    package ems.Controller;

    import java.sql.SQLException;
    import java.util.HashMap;

    import ems.Models.Customer;

    public class CustomerController {
        public static void createCustomer(HashMap<String, String> data){

            String name = data.get("CustomerName");
            String telephone = data.get("TelephoneNumber");
            String email = data.get("EmailAddress");
            String password = data.get("Password");

            Customer customer = new Customer(name,telephone,email,password);
            try {
            customer.save(); 
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error while saving customer: ");
            e.getMessage();
            e.printStackTrace();
        }
    }
        public static Customer getCustomer(String email){
            Customer customer = Customer.fetchByEmail(email);
            return customer;
        }
    
}
