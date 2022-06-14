package com.company;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Eclub  {
    int points;
    double discountAvailible;
    PreparedStatement ps = null;
    ResultSet rs;
   DatabaseConnection db = new DatabaseConnection();
    Subscription sub = new Subscription();
    Order order = new Order();
    Book book = new Book();
    Review review = new Review();
    Scanner in = new Scanner(System.in);
    public Eclub() throws SQLException {
    }
    double calculating_discount(int userId) throws SQLException {
        discountAvailible = calculating_points(userId) * 0.1;
        ps.close();
        String discountUpdate = "update e_club set DiscountAvailible=? where UserId=?;";
        ps = db.connection.prepareStatement(discountUpdate);
        ps.setDouble(1, discountAvailible);
        ps.setInt(2, userId);
        ps.executeUpdate();
        ps.close();
        return discountAvailible;
    }

    double discountedPrice(int isbn, int userId) throws SQLException {
        double price = book.getPrice(isbn);
        price = price - (calculating_discount(userId) * 100);
        return price;
    }

    int calculating_points(int userId) throws SQLException {
        String getCount = "Select count(ISBN) as BooksOrdered from orders where UserId=?";
        ps = db.connection.prepareStatement(getCount);
        ps.setInt(1, userId);
        rs = ps.executeQuery();
        if (rs.next()) {
            points = rs.getInt(1) * order.Quantity;
            ps.close();
        }
        String pointsUpdate = "update e_club set Points=? where UserId=?;";
        ps = db.connection.prepareStatement(pointsUpdate);
        ps.setInt(1, points);
        ps.setInt(2, userId);
        ps.executeUpdate();
        ps.close();
        return points;
    }

    public void upgradeRank() throws SQLException {
        String edit = "update e_club set MembershipRank= 2 where points>50";
        ps = db.connection.prepareStatement(edit);
        ps.executeUpdate();
        ps.close();
    }

    public void viewEClubMembers() throws SQLException {

        String view = "SELECT  UserName,RankName,Points,DiscountAvailible " +
                "from e_club inner join users u on e_club.UserId = u.UserId" +
                " inner join  `rank` r on e_club.MembershipRank = r.RankId";

        ps = db.connection.prepareStatement(view);
        rs = ps.executeQuery();
        System.out.println("Username ||  Rank      || Points || Discount Availible ");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "      " + rs.getString(2) + "         " + rs.getInt(3) + "           " + rs.getDouble(4));
        }
    }

    public boolean checkCredentials(int userId) throws SQLException {
        String checkCredentials = "select UserId from e_club where UserId=?";
        ps = db.connection.prepareStatement(checkCredentials);
        ps.setInt(1, userId);
        rs = ps.executeQuery();
        boolean flag = false;
        if (rs.next()) {
            flag = true;
        }
        return flag;
    }

    public void addToEclub(int userId) throws SQLException {
        System.out.println("You dont have the acount on E-CLUB kindly fill the data for creating your account");
        int subs;
        System.out.println("Select the subscription ");
        sub.viewSub();
        subs = in.nextInt();
        String insert = "insert into e_club " +
                "(e_club.UserId,e_club.Subscription)" +
                "values(?,?)";
        ps = db.connection.prepareStatement(insert);
        ps.setInt(1, userId);
        ps.setInt(2, subs);
        ps.executeUpdate();
        ps.close();
        System.out.println("CONGRATULATION YOU BECOME E-CLUB MEMBER ");

       EclubMenu(userId);
    }
public void viewOwnProfile(int userId ) throws SQLException {
String viewQuery="SELECT u.UserId,u.UserName,RankName,s.Sub_Name,Points,DiscountAvailible\n" +
        "from e_club\n" +
        "inner join users u on e_club.UserId = u.UserId\n" +
        "inner join `rank` r on e_club.MembershipRank = r.RankId\n" +
        "inner join subscription s on e_club.Subscription = s.Sub_ID \n" +
        "where e_club.UserId=?";
ps=db.connection.prepareStatement(viewQuery);
ps.setInt(1,userId);
rs=ps.executeQuery();
if (rs.next()){
    System.out.println("ID  ||  Rank    ||  Subscription Plan  ||  Points   ||  Discount Availible ");
    System.out.println(rs.getInt(1)+" \t "+rs.getString(3)+" \t"+rs.getString(4)+" \t"+rs.getInt(5)+"  \t"+rs.getDouble(6));
}
}
    public void eclubDoOrder(int userId) throws SQLException {
        int Quantity, isbn;
        double price;
        isbn = order.selectTheBook();
        Quantity = order.selectTheBookQuantity();
        price = discountedPrice(isbn, userId) * Quantity;
        String insert = "insert into  orders (UserId, ISBN, Quantity, Price) values(?,?,?,?)";
        ps = db.connection.prepareStatement(insert);
        ps.setInt(1, userId);
        ps.setInt(2, isbn);
        ps.setInt(3, Quantity);
        ps.setDouble(4, price);
        ps.executeUpdate();
        upgradeRank();
        order.decrementInQuantity(Quantity, isbn);
    }

    public boolean expirySubs(int userId) throws SQLException {
boolean flag=false;
        String fetchDate=
                "SELECT Subscription,Validity from e_club\n" +
                "                 left join subscription s on e_club.Subscription = s.Sub_ID where UserId=? \n";
        ps=db.connection.prepareStatement(fetchDate);
        ps.setInt(1,userId);
        rs=ps.executeQuery();
        if (rs.next()){
            String expire="DELETE from e_club where Subscription=" +
                    "ANY(SELECT Sub_ID from subscription where Validity=?)";
            ps=db.connection.prepareStatement(expire);
            ps.setInt(1,rs.getInt(2));
            LocalDate presentDate = LocalDate.now();
            int year= presentDate.getYear();
            if (year>=rs.getInt(2)){
                flag=true;
                ps.executeUpdate();
            }
        }
        return flag;
    }
    public void EclubMenu(int userId) throws SQLException {
        int opt;
        while (true) {
            if (checkCredentials(userId) == false) {
                System.out.println("1- Join E-club");
                opt = in.nextInt();
                if (opt == 1) {
                    addToEclub(userId);
                }
            }
            if (checkCredentials(userId) == true) {

                if (expirySubs(userId) == false) {
                    System.out.println("1- E-Club Order ");
                    System.out.println("2 - View Order");
                    System.out.println("3 - Do Reviews of Purchase Books");
                    System.out.println("4 - View Review of Purchase Books");
                    System.out.println("5 - Check E-CLub Profile");
                    System.out.println("6 - Back");
                    opt = in.nextInt();
                    if (opt == 1) {
                        eclubDoOrder(userId);
                    }
                    if (opt == 2) {
                        order.ViewSpecificUserOrder(userId);
                    }
                    if (opt == 3) {
                        review.addReview(userId);
                    }
                    if (opt == 4) {
                        review.viewReview();
                    }
                    if (opt == 5) {
                        viewOwnProfile(userId);
                    }
                    if (opt == 6) {
                        break;
                    }
                }
                else{
                    System.out.println("your account is expired");
                    continue;
                }
            }
        }
    }
}
