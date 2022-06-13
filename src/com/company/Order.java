package com.company;

import java.sql.*;
import java.util.Scanner;

public class Order {
    Scanner in = new Scanner(System.in);
    PreparedStatement ps = null;
    ResultSet rs;
    DatabaseConnection db = new DatabaseConnection();
    int Quantity, price, isbn;

    public Order() throws SQLException {
    }


    public int selectTheBook() throws SQLException {
        Book book = new Book();
        book.viewBooks();
        System.out.println("Select the id of the book");
        book.ISBN = in.nextInt();
        isbn = book.ISBN;
        return book.ISBN;
    }

    public int selectTheBookQuantity() throws SQLException {
        System.out.println("Select the Quantity of the book");
        Quantity = in.nextInt();
        return Quantity;
    }

    int getTotalPrice(int isbn) throws SQLException {
        String fetch = "select  price from books where ISBN=?";
        PreparedStatement ps = new DatabaseConnection().connection.prepareStatement(fetch);
        ps.setInt(1, isbn);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            price = rs.getInt(1);
        }
        return price * Quantity;
    }

    int GetQuantity(int isbn) throws SQLException {
        int Quantity = 0;
        String returnQuantity = "Select Quantity from Books where ISBN=?";
        ps = db.connection.prepareStatement(returnQuantity);
        ps.setInt(1, isbn);
        rs = ps.executeQuery();
        while (rs.next()) {
            Quantity = rs.getInt(1);
        }
        return Quantity;
    }

    public void decrementInQuantity(int userQuantity, int ISBN) throws SQLException {
        while (true) {
            if (GetQuantity(ISBN) > userQuantity) {
                Quantity = GetQuantity(ISBN) - userQuantity;
                String UpdateQuantity = "Update  Books set Quantity= ? where ISBN=?";
                ps = db.connection.prepareStatement(UpdateQuantity);
                ps.setInt(1, Quantity);
                ps.setInt(2, ISBN);
                ps.executeUpdate();
                break;
            } else {
                System.out.println("WE dont have book in this Quantity");
                System.out.println("Try again !! ");
                String checkQuantity = "Select Quantity from Books where ISBN=?";
                ps = db.connection.prepareStatement(checkQuantity);
                ps.setInt(1, ISBN);
                rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("We have only " + rs.getInt(1) + "in Quantity");
                }
            }
        }

    }

    public void DoOrder(int userId) throws SQLException {
        try {
            Order order = new Order();
            int Quantity, isbn, price;
            isbn = order.selectTheBook();
            Quantity = order.selectTheBookQuantity();
            price = order.getTotalPrice(isbn);
            String insert = "insert into  orders (UserId, ISBN, Quantity, Price) values(?,?,?,?)";
            ps = db.connection.prepareStatement(insert);
            ps.setInt(1, userId);
            ps.setInt(2, isbn);
            ps.setInt(3, Quantity);
            ps.setInt(4, price);
            ps.executeUpdate();
            decrementInQuantity(Quantity, isbn);
            ViewSpecificUserOrder(userId);
        } finally {
            ps.close();
        }
    }

    public void viewAllOrder() throws SQLException {
        String view = "select u.UserId ,u.UserName ,b.BookName,orders.Quantity,orders.Price,s.StatusName " +
                " from orders inner join books b on orders.ISBN = b.ISBN " +
                "inner  join users u on orders.UserId = u.UserId " +
                "inner  join status s on orders.Delivery_Status = s.StatusId";

        PreparedStatement ps = new DatabaseConnection().connection.prepareStatement(view);
        ResultSet rs = ps.executeQuery();
        // formating is needed
        System.out.println("User Id ||  Username || Book Name || Quantity || Total Price || Order Status ");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + "  " + rs.getInt(4) + "  " + rs.getInt(5) + "  " + rs.getString(6));
        }
    }

    public void viewOrderHistory(int userId) throws  SQLException{
        String view = "select u.UserId ,u.UserName ,b.BookName,orders.Quantity,orders.Price,s.StatusName " +
                " from orders inner join books b on orders.ISBN = b.ISBN " +
                "inner  join users u on orders.UserId = u.UserId " +
                "inner  join status s on orders.Delivery_Status = s.StatusId where u.UserId=?";
        PreparedStatement ps = new DatabaseConnection().connection.prepareStatement(view);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        // formating is needed
        System.out.println("User Id ||  Username || Book Name || Quantity || Total Price || Order Status ");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " \t" + rs.getString(2) + " \t" + rs.getString(3) + "  \t" + rs.getInt(4) + "  \t" + rs.getInt(5) + "  \t" + rs.getString(6));
                price += rs.getInt(5);
        }
        System.out.println("Your Total Order = "+price);
    }
    public void ViewSpecificUserOrder(int userId) throws SQLException {
        String view = "select u.UserId ,u.UserName ,b.BookName,orders.Quantity,orders.Price,s.StatusName " +
                " from orders inner join books b on orders.ISBN = b.ISBN " +
                "inner  join users u on orders.UserId = u.UserId " +
                "inner  join status s on orders.Delivery_Status = s.StatusId where u.UserId=? and orders.Delivery_Status=1";
        PreparedStatement ps = new DatabaseConnection().connection.prepareStatement(view);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        boolean flag = true;
        // formating is needed
        System.out.println("User Id ||  Username || Book Name || Quantity || Total Price || Order Status ");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + "  " + rs.getInt(4) + "  " + rs.getInt(5) + "  " + rs.getString(6));
            if (rs.getString(6).compareTo("Pending")==0) {
                flag = false;
                price += rs.getInt(5);
            }
        }

        if (flag == false) {
            System.out.println("Total Bill : " + price);
            UserPayment userPayment = new UserPayment();
            System.out.println("Select Your Selected Payment Option ");
            userPayment.choosePaymentMethod(userId);
            System.out.println("Your Order is Completed");
            String UpdateStatus="Update Orders set Delivery_Status=2 where UserId=?";
            ps=db.connection.prepareStatement(UpdateStatus);
            ps.setInt(1,userId);
            ps.executeUpdate();
        } else {
            price=0;
            System.out.println("You already have done your payment");
        }

    }
    public void calculateSalesTotal() throws SQLException {
        String calculation = "select sum(price) AS totalSales from orders";
        ps = db.connection.prepareStatement(calculation);
        rs = ps.executeQuery();
        System.out.println("Total sales : ");
        System.out.println(rs.getInt(1));

    }

    public void updateOrderStatus() throws SQLException {
        Status status = new Status();
        status.viewStatus();
        String UpdateQuery = "UPDATE orders set Delivery_Status=? where UserId = ? ";
        System.out.println("Enter the Id of the user ");
        int uid;
        uid = in.nextInt();
        System.out.println("Enter the id of the Status : ");
        status.statusId = in.nextInt();
        ps = db.connection.prepareStatement(UpdateQuery);
        ps.setString(1, status.statusName);
        ps.setInt(2, uid);
        ps.executeUpdate();
        System.out.println("New Status Name updated Successfully");
    }

}
