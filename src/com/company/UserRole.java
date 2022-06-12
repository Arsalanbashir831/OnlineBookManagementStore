package com.company;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserRole {
    int UserRoleId;
    String UserRoleName;

    Scanner in = new Scanner(System.in);
    // setting up the connection
    DatabaseConnection db=new DatabaseConnection();
    //preparing statement
    PreparedStatement ps=null;
    //result set for reterival of data
    ResultSet rs;

    public UserRole() throws SQLException {
    }

    public void EditUserRole() throws SQLException {
        viewUserRole();
        String UpdateQuery="UPDATE userrole set RoleName=? where UserRoleId = ? ";
        System.out.println("Enter the id of the UserRole : ");
        UserRoleId=in.nextInt();
        System.out.println("Enter the updated Name of the UserRole ");
        UserRoleName=in.next();
        ps=db.connection.prepareStatement(UpdateQuery);
        ps.setString(1,UserRoleName);
        ps.setInt(2,UserRoleId);
        ps.executeUpdate();
        System.out.println("New UserRole Name updated Successfully");

    }
    public void viewUserRole() throws SQLException {

        String viewQuery= "Select * from userrole order by UserRoleId ASC ";
        ps=db.connection.prepareStatement(viewQuery);
        rs=ps.executeQuery();
        System.out.println("=====================================");
        System.out.println("|| Role Id  || Role Name || ");
        System.out.println("=====================================");
        while (rs.next()){
            UserRoleId= rs.getInt(1);
            UserRoleName=rs.getString(2);
            System.out.println("||\t "+UserRoleId +"\t\t\t||\t "+ UserRoleName +"\t\t");
        }
        ps.close();

    }
}
