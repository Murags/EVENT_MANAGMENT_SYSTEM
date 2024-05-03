package ems.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ems.db.DBConnection;

public class Customer {
    private String name;
    private String telephone;
    private String email;
    private String password;

    public Customer(String name, String telephone, String email, String password){

        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
    }
    
    //Getters and Setters

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTelephone(){
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
   }

   public String getEmail(){
    return  email;
   }
   public void setEmail(String email){
        this.email = email;
   }
   

   public String  getPassword() {
       return password;
   }
   public void setPassword(String password){
        this.password = password;
   }

   public void save()throws ClassNotFoundException,SQLException{
    try{
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
            
            PreparedStatement stmt= con.prepareStatement("INSERT INTO customers (name,telephoneNumber,email,password) VALUES(?,?,?,?)");
            
            stmt.setString(1, name);
            stmt.setString(2, telephone);
            stmt.setString(3, email);
            stmt.setString(4, password);
            
           stmt.executeUpdate();
           db.closeConnection();

    }catch(ClassNotFoundException | SQLException e){
       
        System.out.println("Error with database");
        e.printStackTrace();
    }
   }

   public static Customer fetchByEmail(String email){

        Customer customer = null;
        

        try{
            DBConnection db = new DBConnection();
            Connection con = db.getConnection();
 
            PreparedStatement pst = con.prepareStatement("SELECT * FROM customers WHERE email=?");
            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                customer = new Customer(rs.getString("name"), 
                rs.getString("telephoneNumber"), 
                rs.getString("email"),
                rs.getString("password"));
            }

            db.closeConnection();

        }catch(ClassNotFoundException | SQLException e){
           System.out.println("Database Error");
           e.printStackTrace();
   }
   return customer;
}

}
