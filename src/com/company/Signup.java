package com.company;

import java.sql.*;
import java.util.Scanner;

public class Signup {


    Scanner in = new Scanner(System.in);
    // setting up the connection
    DatabaseConnection db=new DatabaseConnection();
    //preparing statement
    PreparedStatement ps=null;

    public Signup() throws SQLException {
    }
    public void RegisterUser(User user) throws SQLException {
        System.out.println("Enter userId  : ");
        user.userId=in.nextInt();
        System.out.println("Enter the User Name : ");
        user.username=in.next();
        System.out.println("Enter your password");
        user.userPassword=in.next();
            System.out.println("Enter your email");
            user.userEmail=in.next();
        System.out.println("Enter your Gender (M/F)");
        user.userGender=in.next().charAt(0);
        System.out.println("Enter your address");
        in.nextLine();
        user.userAddress=in.nextLine();
        System.out.println("Enter your Role Id ");
        UserRole ur=new UserRole();
        ur.viewUserRole();
        user.userRole= in.nextInt();
        if (user.userRole==1){
            System.out.println("Kindly wait for the master admin to give approval of your admin registeration");
            try
            {
                System.out.printf("Kindly wait ");
                int i = 0;
                for (;i<3; i++) {
                    System.out.printf(".");
                    Thread.sleep(3000);
                }

            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }

        String insertQuery="insert into " +
                "users (UserId,UserName,UserPassword,UserEmail,UserGender,UserAddress,UserRole)"
                +"values (?,?,?,?,?,?,?)";
        ps=db.connection.prepareStatement(insertQuery);
        ps.setInt(1,user.userId);
        ps.setString(2,user.username);
        ps.setString(3,user.userPassword);
        ps.setString(4,user.userEmail);
        ps.setString(5, String.valueOf(user.userGender));
        ps.setString(6,user.userAddress);
        ps.setInt(7,user.userRole);
        ps.executeUpdate();

        if (user.userRole==2){
            UserPayment userPayment = new UserPayment();
            userPayment.choosePaymentMethod(user.userId);
            System.out.println("Payment Method is Selected");
        }
        System.out.println("Congratulation your account is Created Successfully");
        Customer customer= new Customer();
        customer.customerMenu(user.userId,user.username);
    }
}
