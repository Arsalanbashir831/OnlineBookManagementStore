package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserPayment {
Scanner in=new Scanner(System.in);
DatabaseConnection db= new DatabaseConnection();
PreparedStatement ps;
ResultSet rs;

    public UserPayment() throws SQLException {
    }
    public void choosePaymentMethod(int userId) throws SQLException {
        //User user = new User();
        Payment payment = new Payment();
//        System.out.println("Enter your id  ");
//        userId=in.nextInt();
        payment.viewPaymentMethod();
        int pid=0;
        System.out.println("Select the id of the payment ");
        pid = in.nextInt();
        //Not allowing the duplicate payment
        String fetch="Select * from userspayment where UserId =? and Pid =?";
        ps=db.connection.prepareStatement(fetch);
        ps.setInt(1,userId);
        ps.setInt(2,pid);
        rs=ps.executeQuery();
            if (rs.next()){
                System.out.println("You already have that payment method");
            }
            else {
                String insert = "insert into userspayment (userid, pid)  values (?,?)";
                ps = db.connection.prepareStatement(insert);
                ps.setInt(1, userId);
                ps.setInt(2, pid);
                ps.executeUpdate();
            }
    }
    public void viewOwnPaymentMethod(int userId) throws SQLException {
String view="SELECT  Pname from userspayment \n" +
        "inner join payment p on userspayment.Pid = p.Pid\n" +
        "inner join users u on userspayment.UserId = u.UserId \n" +
        "where u.UserId=?";
//        User user = new Customer();
//        System.out.println("Enter your id : ");
//        user.userId=in.nextInt();
        ps=db.connection.prepareStatement(view);
        ps.setInt(1,userId);
        rs=ps.executeQuery();
        System.out.println("Payment Name ");
        while (rs.next()){
            System.out.println(rs.getString(1));
        }
    }
    public void PaymentMenu(int userId) throws SQLException {
        int opt;
        while (true){
            System.out.println("1- Select Payment Method");
            System.out.println("2- Check your  Own Selected Payment Method");
            System.out.println("3- Back");
            opt=in.nextInt();
            if (opt==1){
                choosePaymentMethod(userId);
            }if (opt==2){
                viewOwnPaymentMethod(userId);
            }if (opt==3){
                break;
            }
        }
    }


    public void viewAllUserPaymentMethod() throws SQLException {
        String view="SELECT Pname ,UserName from userspayment \n" +
                "inner join payment p on userspayment.Pid = p.Pid\n" +
                "inner join users u on userspayment.UserId = u.UserId \n";
//        User user = new Customer();
//        System.out.println("Enter your id : ");
//        user.userId=in.nextInt();
        ps=db.connection.prepareStatement(view);
        rs=ps.executeQuery();
        System.out.println("Payment Name ||   UserName ");
        while (rs.next()){
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
        }
    }
}
