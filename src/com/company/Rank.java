package com.company;

import com.company.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Rank {
    int rankId;
    String rankName;
    Scanner in = new Scanner(System.in);
    DatabaseConnection db=new DatabaseConnection();
    //preparing statement
    PreparedStatement ps=null;
    //result set for reterival of data
    ResultSet rs;


    public Rank() throws SQLException {
    }

    public void addRank() throws SQLException {
        System.out.println("Enter the Rank Id : ");
        rankId=in.nextInt();
        System.out.println("Enter the Rank Name : ");
        rankName=in.next();
        String insertQuery="insert into `rank` (RankId,RankName)values (?,?)";
        ps=db.connection.prepareStatement(insertQuery);
        ps.setInt(1,rankId);
        ps.setString(2,rankName);
        ps.executeUpdate();
        System.out.println("New Rank Inserted Successfully");
    }
    public void viewRank() throws SQLException {

        String viewQuery= "Select * from `rank` order by RankId ASC ";
        ps=db.connection.prepareStatement(viewQuery);
        rs=ps.executeQuery();
        System.out.println("=====================================");
        System.out.println("|| Rank Id  || Rank Name || ");
        System.out.println("=====================================");
        while (rs.next()){
            rankId= rs.getInt(1);
            rankName=rs.getString(2);
            System.out.println("||\t "+rankId +"\t\t\t||\t "+ rankName +"\t\t");
        }
        ps.close();

    }
    public void deleteRank() throws SQLException {

        String deleteQueryById="delete from `rank` where RankId =?";
        System.out.println("Enter the new Rank Id : ");
        rankId=in.nextInt();
        ps=db.connection.prepareStatement(deleteQueryById);
        ps.setInt(1,rankId);
        ps.executeUpdate();
        System.out.println("Rank of id "+ rankId +"is deleted successfully");
    }
    public void UpdateRank() throws SQLException {

        viewRank();
        String UpdateQuery="UPDATE `rank` set RankName=? where RankId = ? ";
        System.out.println("Enter the id of the rank : ");
        rankId=in.nextInt();
        System.out.println("Enter the updated Name of the Rank ");
        rankName=in.next();
        ps=db.connection.prepareStatement(UpdateQuery);
        ps.setString(1,rankName);
        ps.setInt(2,rankId);
        ps.executeUpdate();
        System.out.println("Rank Name updated Successfully");

    }


}
