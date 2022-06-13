package com.company;

import com.company.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Status {
    int statusId;
    String statusName;

    Scanner in = new Scanner(System.in);
    // setting up the connection
    DatabaseConnection db=new DatabaseConnection();
    //preparing statement
    PreparedStatement ps=null;
    //result set for reterival of data
    ResultSet rs;

    public Status() throws SQLException {

    }

    public void viewStatus() throws SQLException {
        
        String viewQuery= "Select * from status order by StatusId ASC ";
        ps=db.connection.prepareStatement(viewQuery);
        rs=ps.executeQuery();
        System.out.println("=====================================");
        System.out.println("|| Status Id  || Status Name || ");
        System.out.println("=====================================");
        while (rs.next()){
            statusId= rs.getInt(1);
            statusName=rs.getString(2);
            System.out.println("||\t "+statusId +"\t\t\t||\t "+ statusName +"\t\t");
        }
        
        ps.close();
    }
    public void addStatus() throws SQLException {
        System.out.println("Enter the Status Id : ");
        statusId=in.nextInt();
        System.out.println("Enter the Status Name : ");
        statusName=in.next();
        String insertQuery="insert into status (StatusId,StatusName)values (?,?)";
        ps=db.connection.prepareStatement(insertQuery);
        ps.setInt(1,statusId);
        ps.setString(2,statusName);
        ps.executeUpdate();
        System.out.println("New Status  Inserted Successfully");
    }
    public void updateStatus() throws SQLException {
        viewStatus();
        String UpdateQuery="UPDATE status set StatusName=? where StatusId = ? ";
        System.out.println("Enter the id of the Status : ");
        statusId=in.nextInt();
        System.out.println("Enter the updated Name of the Status ");
        statusName=in.next();
        ps=db.connection.prepareStatement(UpdateQuery);
        ps.setString(1,statusName);
        ps.setInt(2,statusId);
        ps.executeUpdate();
        System.out.println("New Status Name updated Successfully");
    }
//    public void DeleteStatus() throws SQLException {
//        String deleteQueryById="delete from status where StatusId=?";
//        System.out.println("Enter the Status id  : ");
//        statusId=in.nextInt();
//        ps=db.connection.prepareStatement(deleteQueryById);
//        ps.setInt(1,statusId);
//        ps.executeUpdate();
//        System.out.println("Status of id "+ statusId +"is deleted successfully");
//
//    }
}
