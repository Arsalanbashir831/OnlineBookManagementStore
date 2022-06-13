package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Payment {
    int Pid;
    String Pname;
    DatabaseConnection db = new DatabaseConnection();
    Scanner in = new Scanner(System.in);
    //preparing statement
    PreparedStatement ps=null;
    //result set for reterival of data
    ResultSet rs;

    public Payment() throws SQLException {

    }

    public void viewPaymentMethod() throws SQLException {
        String viewQuery= "Select * from payment order by Pid ASC ";
        ps=db.connection.prepareStatement(viewQuery);
        rs=ps.executeQuery();
        System.out.println("=====================================");
        System.out.println("|| Payment Id  || Payment Name || ");
        System.out.println("=====================================");
        while (rs.next()){
            Pid= rs.getInt(1);
            Pname=rs.getString(2);
            System.out.println("||\t "+Pid +"\t\t\t||\t "+ Pname +"\t\t");
        }
        ps.close();
    }
    public void addPaymentMethod() throws SQLException {
        System.out.println("Enter the Payment Id : ");
        Pid=in.nextInt();
        System.out.println("Enter the Payment Name : ");
        in.nextLine();
        Pname=in.nextLine();
        String insertQuery="insert into payment (Pid,Pname)values (?,?)";
        ps=db.connection.prepareStatement(insertQuery);
        ps.setInt(1,Pid);
        ps.setString(2,Pname);
        ps.executeUpdate();
        System.out.println("Payment Inserted Successfully");
        ps.close();
    }
    public void deletePaymentMethod() throws SQLException {
        System.out.println("Enter the Payment id of the Payment method : ");
        Pid=in.nextInt();
        String deleteFromChild="delete from userspayment where Pid=?";
        ps=db.connection.prepareStatement(deleteFromChild);
        ps.setInt(1,Pid);
        ps.executeUpdate();
        String deleteQueryById="delete from payment where Pid=?";
        ps=db.connection.prepareStatement(deleteQueryById);
        ps.setInt(1,Pid);
        ps.executeUpdate();
        System.out.println(" Payment method of  "+ Pid +"is deleted successfully");
    }
    public void UpdatePaymentMethod() throws SQLException {
        viewPaymentMethod();
        System.out.println("Enter the id of the Payment method : ");
        Pid=in.nextInt();
        System.out.println("Enter the updated Name of the Payment");
        Pname=in.next();
        String UpdateQuery="UPDATE payment set Pname=? where Pid = ? ";
        ps=db.connection.prepareStatement(UpdateQuery);
        ps.setString(1,Pname);
        ps.setInt(2,Pid);
        ps.executeUpdate();
        System.out.println("Payment Name updated Successfully");

    }

}
