package com.company;

import java.sql.*;
import java.util.Scanner;

public class Book {
    //attributes

    int ISBN, Quantity, rating, category, price;
    String BookName, authorName, publisherName;
    String month, date, year;

    ResultSet rs;
    PreparedStatement ps;
    Scanner in = new Scanner(System.in);
    DatabaseConnection db = new DatabaseConnection();


    public Book() throws SQLException {

    }


    //methods
    public String setDate(String date, String month, String year) {
        String dateOfPublish = year + "-" + month + "-" + date;
        return dateOfPublish;
    }

    public void viewBooks() throws SQLException {
        String viewQuery = "Select ISBN, BookName, AuthorName," +
                " Quantity, PublisherName, PublisherDate," +
                " BookRatings, Price, Category_name " +
                "from Books " +
                "inner join bookcategories b on books.BookCategory = b.Cate_Id where Price !=0 ";
        ps = db.connection.prepareStatement(viewQuery);
        rs = ps.executeQuery();
        System.out.println("==========================================================================================================================================");
        System.out.println("|| ISBN  || Book Name || Author Name  || Quantity || Publisher Name  || Publisher Date || Book Ratings  || Price || || Book Category    ");
        System.out.println("==========================================================================================================================================");
        while (rs.next()) {
            System.out.print("|| \t" + rs.getString(1) + "\t ");
            System.out.print("|| \t" + rs.getString(2) + "\t ");
            System.out.print("|| \t" + rs.getString(3) + "\t ");
            System.out.print("|| \t" + rs.getString(4) + "\t ");
            System.out.print("|| \t" + rs.getString(5) + "\t ");
            System.out.print("|| \t" + rs.getString(6) + " \t");
            System.out.print("|| \t" + rs.getString(7) + "\t ");
            System.out.print("|| \t" + rs.getString(8) + "\t ");
            System.out.println("|| \t" + rs.getString(9) + "\t ");
        }
    }


    public int getPrice(int isbn) throws SQLException {
        String getPrice = "Select Price from Books where ISBN=?";
        int price = 0;
        ps = db.connection.prepareStatement(getPrice);
        ps.setInt(1, isbn);
        rs = ps.executeQuery();
        if (rs.next()) {
            price = rs.getInt(1);
        }
        return price;
    }

    public void addBooks() throws SQLException {
        System.out.println("Enter the Id of the Books : ");
        ISBN = in.nextInt();
        System.out.println("Enter the Name of the Books : ");
        in.nextLine();
        BookName = in.nextLine();
        System.out.println("Enter the Quantity of Books : ");
        Quantity = in.nextInt();
        System.out.println("Enter the ratings of the Book (out of 5) : ");
        rating = in.nextInt();
        BookCategories bc = new BookCategories();
        bc.viewCategories();
        System.out.println("Enter the category id  of the Book : ");
        category = in.nextInt();
        System.out.println("Enter the the price of the Book : ");
        price = in.nextInt();
        System.out.println("Enter the author name of that Book :");
        in.nextLine();
        authorName = in.nextLine();
        System.out.println("Enter the publisher Name of that Book :");
        publisherName = in.nextLine();
        System.out.println("Enter the date of the publish : ");
        System.out.println("date : ");
        date = in.next();
        System.out.println("month : ");
        month = in.next();
        System.out.println("Year : ");
        year = in.next();
        setDate(date, month, year);
        String insertQuery = "INSERT INTO `books` " +
                "(`ISBN`, `BookName`, `AuthorName`, `Quantity`, `PublisherName`, `PublisherDate`, `BookRatings`, `BookCategory`, `Price`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        ps = db.connection.prepareStatement(insertQuery);
        ps.setInt(1, ISBN);
        ps.setString(2, BookName);
        ps.setString(3, authorName);
        ps.setInt(4, Quantity);
        ps.setString(5, publisherName);
        ps.setString(6, setDate(date, month, year));
        ps.setInt(7, rating);
        ps.setInt(8, category);
        ps.setInt(9, price);
        ps.executeUpdate();

        System.out.println("Book is Inserted successfully ");
    }


    public void deleteBook() throws SQLException {
        viewBooks();
        System.out.println("Enter the ISBN of the book : ");
        ISBN = in.nextInt();
        String deleteQuery = "delete from books where ISBN=?";
        ps = db.connection.prepareStatement(deleteQuery);
        ps.setInt(1, ISBN);
        ps.executeUpdate();
        System.out.println("Books deleted successfully");
    }

    public void deleteBook(int ISBN) throws SQLException {
        String deleteQuery = "delete from books where ISBN=?";
        ps = db.connection.prepareStatement(deleteQuery);
        ps.setInt(1, ISBN);
        ps.executeUpdate();
    }

    public void EditBook() throws SQLException {
        String updateName, updateAuthorName, updateQuantity, updatePublisherName, updatePublisherDate,
                updateRatings, updateCategory, updatePrice;

        int opt;
        updateName = "   UPDATE `books` SET `BookName` = ? WHERE `books`.`ISBN` = ?;";
        updateAuthorName = "  UPDATE `books` SET `AuthorName` = ? WHERE `books`.`ISBN` = ?;";
        updateQuantity = " UPDATE `books` SET `Quantity` = ? WHERE `books`.`ISBN` = ?;";
        updatePublisherName = " UPDATE `books` SET `PublisherName` = ? WHERE `books`.`ISBN` = ?;";
        updatePublisherDate = " UPDATE `books` SET `PublisherDate` = ? WHERE `books`.`ISBN` = ?;";
        updateRatings = "   UPDATE `books` SET `BookRatings` = ? WHERE `books`.`ISBN` = ?;";
        updateCategory = "   UPDATE `books` SET `BookCategory` = ? WHERE `books`.`ISBN` = ?;";
        updatePrice = "        UPDATE `books` SET `Price` = ? WHERE `books`.`ISBN` = ?;";

        System.out.println("What do you want to update ? ");
        System.out.println("1-Name\n2-Author\n3-Quantity\n4-Publisher" +
                "\n5-Publisher Date\n 6-Ratings\n7-Category \n8-Price ");
        opt = in.nextInt();
        if (opt == 1) {
            viewBooks();
            System.out.println("Enter the ISBN of the book");
            ISBN = in.nextInt();
            System.out.println("Enter the New Name of the Book");
            in.nextLine();
            BookName = in.nextLine();
            ps = db.connection.prepareStatement(updateName);
            ps.setString(1, BookName);
            ps.setInt(2, ISBN);
            ps.executeUpdate();
            System.out.println("Name Updated Successfully");
        }
        if (opt == 2) {
            viewBooks();
            System.out.println("Enter the ISBN of the book");
            ISBN = in.nextInt();
            System.out.println("Enter the New Name of the Author");
            in.nextLine();
            authorName = in.nextLine();
            ps = db.connection.prepareStatement(updateAuthorName);
            ps.setString(1, authorName);
            ps.setInt(2, ISBN);
            ps.executeUpdate();
            System.out.println("Name Updated Successfully");
        }
        if (opt == 3) {
            viewBooks();
            System.out.println("Enter the ISBN of the book");
            ISBN = in.nextInt();
            System.out.println("Enter the New Quantity of the Book");
            in.nextLine();
            Quantity = in.nextInt();
            ps = db.connection.prepareStatement(updateQuantity);
            ps.setInt(1, Quantity);
            ps.setInt(2, ISBN);
            ps.executeUpdate();
            System.out.println("Quantity Updated Successfully");
        }
        if (opt == 4) {
            viewBooks();
            System.out.println("Enter the ISBN of the book");
            ISBN = in.nextInt();
            System.out.println("Enter the New Publisher Name of the Book");
            in.nextLine();
            publisherName = in.nextLine();
            ps = db.connection.prepareStatement(updatePublisherName);
            ps.setString(1, publisherName);
            ps.setInt(2, ISBN);
            ps.executeUpdate();
            System.out.println("Name Updated Successfully");
        }
        if (opt == 5) {
            viewBooks();
            System.out.println("Enter the ISBN of the book");
            ISBN = in.nextInt();
            System.out.println("Enter the New Publish date of the Book");
            in.nextLine();
            date = in.nextLine();
            System.out.println("Enter the New Publish month of the Book");
            month = in.nextLine();
            System.out.println("Enter the New Publish year of the Book");
            year = in.nextLine();
            ps = db.connection.prepareStatement(updatePublisherDate);
            ps.setString(1, setDate(date, month, year));
            ps.setInt(2, ISBN);
            ps.executeUpdate();
            System.out.println("Date Updated Successfully");
        }
        if (opt == 6) {
            viewBooks();
            System.out.println("Enter the ISBN of the book");
            ISBN = in.nextInt();
            System.out.println("Enter the Update Ratings of the Book");
            rating = in.nextInt();
            ps = db.connection.prepareStatement(updateRatings);
            ps.setInt(1, rating);
            ps.setInt(2, ISBN);
            ps.executeUpdate();
            System.out.println("Book Ratings Updated Successfully");
        }
        if (opt == 7) {
            viewBooks();
            System.out.println("Enter the ISBN of the book");
            ISBN = in.nextInt();
            System.out.println("Enter the Updated Category of the Book");
            category = in.nextInt();
            ps = db.connection.prepareStatement(updateCategory);
            ps.setInt(1, category);
            ps.setInt(2, ISBN);
            ps.executeUpdate();
            System.out.println("Category Updated Successfully");
        }
        if (opt == 8) {
            viewBooks();
            System.out.println("Enter the ISBN of the book");
            ISBN = in.nextInt();
            System.out.println("Enter the Updated Price of the Book");
            price = in.nextInt();
            ps = db.connection.prepareStatement(updatePrice);
            ps.setInt(1, price);
            ps.setInt(2, ISBN);
            ps.executeUpdate();
            System.out.println("Price Updated Successfully");
        }

    }

    public void filterTheBooksByCategory() throws SQLException {
        String FilterQuery = "Select ISBN, BookName ,BookCategory" +
                " from books inner join " +
                "bookcategories b on books.BookCategory = b.Cate_Id where books.BookCategory=? and price !=0 ";
        BookCategories bc = new BookCategories();
        bc.viewCategories();
        System.out.println("Select the Category Id");
        category = in.nextInt();
        ps = db.connection.prepareStatement(FilterQuery);
        ps.setInt(1, category);
        rs = ps.executeQuery();
        System.out.println("ISBN || Books || Category");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "   " + rs.getString(2) + "   " + rs.getString(3));
        }

    }

    public void searchBookByName() throws SQLException {
        System.out.println("Enter the name of the book ");
        in.nextLine();
        BookName = in.nextLine();
        String searchQuery = "Select ISBN, BookName, AuthorName," +
                " Quantity, PublisherName, PublisherDate," +
                " BookRatings, Price, Category_name " +
                "from Books " +
                "inner join bookcategories b on books.BookCategory = b.Cate_Id where BookName=?";
        ps = db.connection.prepareStatement(searchQuery);
        ps.setString(1, BookName);
        rs = ps.executeQuery();
        System.out.println("==========================================================================================================================================");
        System.out.println("|| ISBN  || Book Name || Author Name  || Quantity || Publisher Name  || Publisher Date || Book Ratings  || Price || || Book Category    ");
        System.out.println("==========================================================================================================================================");
        while (rs.next()) {
            System.out.print("|| \t" + rs.getString(1) + "\t ");
            System.out.print("|| \t" + rs.getString(2) + "\t ");
            System.out.print("|| \t" + rs.getString(3) + "\t ");
            System.out.print("|| \t" + rs.getString(4) + "\t ");
            System.out.print("|| \t" + rs.getString(5) + "\t ");
            System.out.print("|| \t" + rs.getString(6) + " \t");
            System.out.print("|| \t" + rs.getString(7) + "\t ");
            System.out.print("|| \t" + rs.getString(8) + "\t ");
            System.out.println("|| \t" + rs.getString(9) + "\t ");
        }
    }

    public void BookMenu(int userId) throws SQLException {
        int opt;
        Order order = new Order();
        while (true) {
            System.out.println("1- View Books");
            System.out.println("2- Search Books By Name ");
            System.out.println("3- Search Books By Category ");
            System.out.println("4- Order Books");
            System.out.println("5- View Orders History");
            System.out.println("6- Back");
            opt = in.nextInt();
            if (opt == 1) {
                viewBooks();
            }
            if (opt == 2) {
                searchBookByName();
            }
            if (opt == 3) {
                filterTheBooksByCategory();
            }
            if (opt == 4) {
                order.DoOrder(userId);
            }
            if (opt == 5) {
                order.viewOrderHistory(userId);
            }
            if (opt == 6) {
                break;
            }
        }
    }
}



