package com.company;
import java.sql.*;

public class CharityBook extends Book {

    public CharityBook() throws SQLException {
    }
    Order order= new Order();

    @Override
    public void filterTheBooksByCategory() throws SQLException {

        BookCategories bc = new BookCategories();
        bc.viewCategories();
        System.out.println("Select the Category Id");
        category = in.nextInt();
        String FilterQuery = "Select ISBN, BookName ,b.Category_name" +
                " from books inner join " +
                "bookcategories b on books.BookCategory = b.Cate_Id where books.BookCategory=? and price = 0 ";
        ps = db.connection.prepareStatement(FilterQuery);
        ps.setInt(1, category);
        rs = ps.executeQuery();
        System.out.println("ISBN || Books || Category");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "   " + rs.getString(2) + "   " + rs.getString(3));
        }
    }

    @Override
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
        ps.setInt(9, 0);
        ps.executeUpdate();
        String insertQuery2 = "insert into charitybooks (ISBN, Credit) value (?,?)";
        System.out.println("Enter your Name : ");
        String UserName;
        in.nextLine();
        UserName = in.nextLine();
        ps = db.connection.prepareStatement(insertQuery2);
        ps.setInt(1, ISBN);
        ps.setString(2, UserName);
        ps.executeUpdate();
        System.out.println("Book is add successfully ");
    }
    @Override
    public void viewBooks() throws SQLException {
        String viewQuery = "Select charitybooks.ISBN,BookName,Credit" +
                " from charitybooks " +
                "inner join books b on charitybooks.ISBN = b.ISBN";
        ps = db.connection.prepareStatement(viewQuery);
        rs = ps.executeQuery();
        System.out.println("ISBN  || Book Name || Credits ");
        while (rs.next()) {
            int ISBN = rs.getInt(1);
            String BookName = rs.getString(2);
            String Credit = rs.getString(3);
            System.out.println(ISBN + "   \t" + BookName + "   \t" + Credit);
        }
    }
    @Override
    public void deleteBook() throws SQLException {
        //deleting from the child table
        String deleteQuery = "delete\n" +
                "from charitybooks \n" +
                "where ISBN=?";
        viewBooks();
        System.out.println("Select the id of the book you want to delete ");
        ISBN = in.nextInt();
        ps = db.connection.prepareStatement(deleteQuery);
        ps.setInt(1, ISBN);
        ps.executeUpdate();
        //deleting from the parent table
        super.deleteBook(ISBN);
    }
    public void getCharityBooks(int id) throws SQLException {
        String getBook = "INSERT INTO `orders`" +
                " (`UserId`, `ISBN`, `Quantity`, `Price`, `Delivery_Status`)" +
                " VALUES (?, ?, ?, 0, 2)";
        viewBooks();
        System.out.println("Enter the ISBN of the book");
        ISBN = in.nextInt();
        while (true) {
            System.out.println("Enter the Quantity");
            Quantity = in.nextInt();
            if (Quantity >= 2) {
                System.out.println("Can not have more than 2");
            } else {
                break;
            }
        }
        ps = db.connection.prepareStatement(getBook);
        ps.setInt(1, id);
        ps.setInt(2, ISBN);
        ps.setInt(3, Quantity);
        ps.executeUpdate();
        order.decrementInQuantity(Quantity,ISBN);
    }

    public void ViewUserCharityInOrder(int userId) throws SQLException {
        String view = "SELECT orders.ISBN,b.BookName from  orders " +
                "inner join books b on orders.ISBN = b.ISBN " +
                "where orders.Price=0 AND orders.UserId=?; ";
        ps=db.connection.prepareStatement(view);
        ps.setInt(1,userId);
        rs=ps.executeQuery();
        System.out.println("ISBN    ||  Book Name  ");
        while (rs.next()){
            System.out.println(rs.getInt(1)+ "  "+ rs.getString(2));
        }
    }
    @Override
    public void BookMenu(int userId) throws SQLException {
        int opt;
        while (true) {
            System.out.println("1- View Charity Books");
            System.out.println("2- Get Charity Books ");
            System.out.println("3- View Your Charity In Order");
            System.out.println("4- Search Charity Book in Category");
            System.out.println("5- Do Donation");
            System.out.println("6- Back");
            opt=in.nextInt();
            if (opt == 1) {
                viewBooks();
            }if (opt == 2) {
                getCharityBooks(userId);
            }if (opt == 3) {
                ViewUserCharityInOrder(userId);
            }if (opt == 4) {
                filterTheBooksByCategory();
            }if (opt == 5) {
                addBooks();
            }if (opt == 6) {
                break;
            }
        }
    }
}
