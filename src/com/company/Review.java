package com.company;

import java.sql.*;
import java.util.Scanner;


public class Review {

    String desc;
    DatabaseConnection db = new DatabaseConnection();
    PreparedStatement ps;
    ResultSet rs;
    Scanner in = new Scanner(System.in);

    public Review() throws SQLException {
    }

    public void viewReview() throws SQLException {
        String view = "select UserName,BookName,Description from reviews " +
                "inner  join books b on reviews.ISBN = b.ISBN " +
                "inner join users u on reviews.UserId = u.UserId";
        ps = db.connection.prepareStatement(view);
        rs = ps.executeQuery();
        System.out.println("User Name   ||    Book      ||                    Description                 ||");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "         " + rs.getString(2) + "                " + rs.getString(3));
        }
    }
    public void deleteReviews() throws SQLException {
        String del="delete\n" +
                    "from reviews \n" +
                    "where UserId=? and ISBN=?";
        System.out.println("Enter the user id  : ");
        int userId;
        userId=in.nextInt();
        System.out.println("Enter the book id  : ");
        int bookId;
        bookId=in.nextInt();
        ps=db.connection.prepareStatement(del);
        ps.setInt(1,userId);
        ps.setInt(2,bookId);
        ps.executeUpdate();

    }
    public void addReview(int userId) throws SQLException {

        Review review = new Review();
        Book book = new Book();
//        System.out.println("Enter your id : ");
//        userId=in.nextInt();
        book.viewBooks();
        System.out.println("Enter the id of the book : ");
        book.ISBN = in.nextInt();
        System.out.println("Write your reviews : ");
        in.nextLine();
        review.desc = in.nextLine();
        String insert = "insert into reviews " +
                "(userid, isbn, description) " +
                "values (?,?,?);";
        ps = db.connection.prepareStatement(insert);
        ps.setInt(1, userId);
        ps.setInt(2, book.ISBN);
        ps.setString(3, review.desc);

        ps.executeUpdate();
        System.out.println("Your review is added");

    }
}
