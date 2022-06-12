package com.company;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {

    // setting up the connection
    DatabaseConnection db = new DatabaseConnection();
    //preparing statement
    PreparedStatement ps = null;
    ResultSet rs;
    Scanner in = new Scanner(System.in);

    public Login() throws SQLException {
    }

    public void LoginTheUser(User user) throws SQLException {
        // check the credentials
        while (true) {
            String checkQuery = "Select * from users where UserId=? and UserPassword= ? ";
            System.out.println("***************  Login Page  **************");
            try {
                System.out.println("Enter the User Id ");
                user.userId = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input....");
            }
            System.out.println("Enter the password ");
            user.userPassword = in.next();
//        System.out.println("Enter the Role Id");
//        UserRole ur= new UserRole();
//        ur.viewUserRole();
//        user.userRole=in.nextInt();
            ps = db.connection.prepareStatement(checkQuery);
            ps.setInt(1, user.userId);
            ps.setString(2, user.userPassword);
            rs = ps.executeQuery();
            if (rs.next()) {
                // credentials match
                if (rs.getInt(7) == 1) {
                    System.out.println("We will redirect to admin page");
                    Admin admin = new Admin();
                    admin.AdminMenu();
                } else if (rs.getInt(7) == 2) {
                    System.out.println("We will redirect to customer page");
                    Customer customer = new Customer();
                    customer.customerMenu(rs.getInt(1), rs.getString(2));

                } else {
                    System.out.println("Invalid roles");
                }
                ps.close();
            } else {
                System.out.println("Invalid credentials try again ");

            }

        }

    }

}