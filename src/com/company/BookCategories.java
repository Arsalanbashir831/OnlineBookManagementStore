package com.company;
import java.sql.*;
import java.util.Scanner;

public class BookCategories {
    //constructor
    public BookCategories() throws SQLException {
    }
    // attributes
    int Cate_Id;
    String categoryName;
    Scanner in = new Scanner(System.in);
// setting up the connection
    DatabaseConnection db=new DatabaseConnection();
    //preparing statement
    PreparedStatement ps=null;
    //result set for reterival of data
    ResultSet rs;

    public void viewCategories() throws SQLException {
        String viewQuery= "Select * from bookcategories order by Cate_Id ASC ";
        ps=db.connection.prepareStatement(viewQuery);
        rs=ps.executeQuery();
        System.out.println("=====================================");
        System.out.println("|| Category Id  || Category Name || ");
        System.out.println("=====================================");
        while (rs.next()){
            Cate_Id= rs.getInt(1);
            categoryName=rs.getString(2);
            System.out.println("||\t "+Cate_Id +"\t\t\t||\t "+ categoryName +"\t\t");
        }
        ps.close();

    }
    // insert the category
    public void insertNewCategory() throws SQLException {

        System.out.println("Enter the category Id : ");
        Cate_Id=in.nextInt();
        System.out.println("Enter the category Name : ");
        categoryName=in.next();
        String insertQuery="insert into bookcategories (Cate_Id,Category_name)values (?,?)";
        ps=db.connection.prepareStatement(insertQuery);
        ps.setInt(1,Cate_Id);
        ps.setString(2,categoryName);
        ps.executeUpdate();
        System.out.println("Category Inserted Successfully");
    }
//    public void deleteCategoryById() throws SQLException{
//        String deleteQueryById="delete from bookcategories where Cate_Id=?";
//        System.out.println("Enter the Category id of the book : ");
//        Cate_Id=in.nextInt();
//        //delete from charity
////        String delFromCharity="delete from charitybooks where ";
//        //delete from book
//        ps=db.connection.prepareStatement(deleteQueryById);
//        ps.setInt(1,Cate_Id);
//        ps.executeUpdate();
//        System.out.println("Category of id "+ Cate_Id +"is deleted successfully");
//
//    }
        public void SearchCategoryByName () throws SQLException {
        String searchQuery = "Select  * from bookcategories where Category_name = ?";
            System.out.println("Enter the name of Category : ");
            categoryName=in.next();
            ps=db.connection.prepareStatement(searchQuery);
            ps.setString(1,categoryName);
            rs=ps.executeQuery();
            System.out.println("=====================================");
            System.out.println("|| Category Id  || Category Name || ");
            System.out.println("=====================================");
           while (rs.next()){
               categoryName=rs.getString(2);
               Cate_Id=rs.getInt(1);
               System.out.println("||\t "+Cate_Id +"\t\t\t||\t "+ categoryName +"\t\t");
           }
        }
        public void UpdateCategory() throws SQLException {
        viewCategories();
       String UpdateQuery="UPDATE bookcategories set Category_name=? where Cate_Id = ? ";
            System.out.println("Enter the id of the category : ");
            Cate_Id=in.nextInt();
            System.out.println("Enter the updated Name of the category ");
            categoryName=in.next();
            ps=db.connection.prepareStatement(UpdateQuery);
            ps.setString(1,categoryName);
            ps.setInt(2,Cate_Id);
            ps.executeUpdate();
            System.out.println("Category Name updated Successfully");
        }

}
