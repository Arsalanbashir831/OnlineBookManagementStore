package com.company;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Customer extends User {

    Scanner in = new Scanner(System.in);
    // setting up the connection
    // DatabaseConnection db=new DatabaseConnection();
    //preparing statement
    PreparedStatement ps = null;
    //result set for reterival of data
    //ResultSet rs;

    public Customer() throws SQLException {
    }
    // actions that customer will perform

    Book book = new Book();
    // Order order= new Order();
    CharityBook charityBook = new CharityBook();
    Eclub eclub = new Eclub();
    //Review review = new Review();
    UserPayment userPayment = new UserPayment();

    public void customerMenu(int userId, String username) throws SQLException {

        System.out.println("Welcome " + username + " Please Select the Following Options you want to use");
        while (true) {
            System.out.println("1- Books");//books menu
            System.out.println("2- E-club");// e-club menu
            System.out.println("3- Charity");//charity menu
            System.out.println("4- Payment Method ");//payment menu
            System.out.println("5- Logout");
            int opt;
            opt = in.nextInt();
            if (opt == 1) {
                book.BookMenu(userId);
            }
            if (opt == 2) {
                eclub.EclubMenu(userId);
            }
            if (opt == 3) {
                charityBook.BookMenu(userId);
            }
            if (opt == 4) {
                userPayment.PaymentMenu(userId);
            }
            if (opt == 5) {
                break;
            }
        }
    }
}

