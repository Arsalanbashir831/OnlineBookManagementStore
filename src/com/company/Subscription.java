package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Subscription {
    DatabaseConnection db= new DatabaseConnection();

    public Subscription() throws SQLException {
    }
    public void viewSub() throws SQLException {
        String viewQuery="Select * from subscription";
        PreparedStatement ps= db.connection.prepareStatement(viewQuery);
        ResultSet rs= ps.executeQuery();
        System.out.println("=======================================================================================");
        System.out.println("|| Subscription ID || Subscription Name  || Subscription Price ||  Validity Till  ");
        System.out.println("=======================================================================================");
        while(rs.next()){
            System.out.println("||\t "+rs.getString(1) +"||\t "+rs.getString(2) +"\t\t\t||\t "+ rs.getDouble(4) +"||\t "+rs.getInt(3) +"\t\t\t||\t ");
        }
    }
}
