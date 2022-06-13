package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends User{

    Book book= new Book();
    BookCategories bookCategories= new BookCategories();
    Order order= new Order();
    CharityBook charityBook= new CharityBook();
    Eclub eclub = new Eclub();
    UserPayment userPayment= new UserPayment();
    Payment payment= new Payment();
    Rank rank= new Rank();
    Review rev = new Review();
    Status status = new Status();
    Scanner in = new Scanner(System.in);

    public Admin() throws SQLException {
    }

    public void AdminMenu() throws SQLException {
        System.out.println("Welcome to the admin menu");
        int opt;
        int subOpt;
        while (true) {
            System.out.println("From where you want to perform operation: ");
            System.out.println("1- Book");
            System.out.println("2- Book Categories");
            System.out.println("3- Charity Book");
            System.out.println("4- Order");
            System.out.println("5- Payment");
            System.out.println("6- Rank");
            System.out.println("7- Review");
            System.out.println("8- View E-club Members ");
            System.out.println("9- Status");
            System.out.println("10- Logout ");
            opt = in.nextInt();


            //sub options for all
            if (opt == 1) {
                System.out.println("1- Add Book ");
                System.out.println("2- Delete Book ");
                System.out.println("3- Update Book ");
                System.out.println("4- View all Book ");
                System.out.println("5- Search Book By name ");
                System.out.println("6- Search by category ");
                subOpt = in.nextInt();
                if (subOpt == 1) {
                    book.addBooks();
                }
                if (subOpt == 2) {
                    book.deleteBook();
                }
                if (subOpt == 3) {
                    book.EditBook();
                }
                if (subOpt == 4) {
                    book.viewBooks();
                }
                if (subOpt == 5) {
                    book.searchBookByName();
                }
                if (subOpt == 6) {
                    book.filterTheBooksByCategory();
                }
            }
            if (opt==2){
                System.out.println("1- Add Book Category");
                System.out.println("2- View Book Category");
                System.out.println("3- Update Book Category");
                System.out.println("4- Search Book Category");
                subOpt=in.nextInt();
                if (subOpt==1){
                    bookCategories.insertNewCategory();
                }if (subOpt==2){
                    bookCategories.viewCategories();
                }if (subOpt==3){
                    bookCategories.UpdateCategory();
                }if(subOpt==4){
                    bookCategories.SearchCategoryByName();
                }
            }
            if (opt == 3) {
                System.out.println(" 1- View Chairity Books ");
                System.out.println(" 2- Delete Chairity Books ");
                subOpt = in.nextInt();
                if (subOpt == 1) {
                    charityBook.viewBooks();
                }
                if (subOpt == 2) {
                    charityBook.deleteBook();
                }
            }
            if (opt == 4) {
                System.out.println("1- View All Orders  ");
                System.out.println("2- Update the Delivery Status");
                System.out.println("3- Check the total sales");
                subOpt = in.nextInt();
                if (subOpt == 1) {
                    order.viewAllOrder();
                }
                if (subOpt == 2) {
                    order.updateOrderStatus();
                }
                if (subOpt == 3) {
                    order.calculateSalesTotal();
                }
            }
            if (opt == 5) {
                System.out.println("1- View Payment methods ");
                System.out.println("2- add Payment methods ");
                System.out.println("3- delete Payment methods ");
                System.out.println("4- update Payment methods ");
                System.out.println("5- Check Users Payment methods ");
                subOpt = in.nextInt();
                if (subOpt == 1) {
                    payment.viewPaymentMethod();
                }
                if (subOpt == 2) {
                    payment.addPaymentMethod();
                }
                if (subOpt == 3) {
                    payment.deletePaymentMethod();
                }
                if (subOpt == 4) {
                    payment.UpdatePaymentMethod();
                }
                if (subOpt == 5) {
                    userPayment.viewAllUserPaymentMethod();
                }
            }
            if (opt == 6) {
                System.out.println("1- view Ranks");
                System.out.println("2- Update  Ranks");
                System.out.println("3- delete  Ranks");
                System.out.println("4- Add  Ranks");
                subOpt = in.nextInt();
                if (subOpt == 1) {
                    rank.viewRank();
                }
                if (subOpt == 2) {
                    rank.UpdateRank();
                }
                if (subOpt == 3) {
                    rank.deleteRank();
                }
                if (subOpt == 4) {
                    rank.addRank();
                }
            }
            if (opt == 7) {
                System.out.println("1-View Reviews");
                System.out.println("2-Delete Reviews");
                subOpt = in.nextInt();
                if (subOpt == 1) {
                    rev.viewReview();
                }
                if (subOpt == 2) {
                    rev.viewReview();
                    rev.deleteReviews();
                }
            }
            if (opt == 8) {
                eclub.viewEClubMembers();
            }
            if (opt == 9) {
                System.out.println("1- View Status");
                System.out.println("2- add Status");
                System.out.println("3- update Status");
                subOpt = in.nextInt();
                if (subOpt == 1) {
                    status.viewStatus();
                }
                if (subOpt == 2) {
                    status.addStatus();
                }

                if (subOpt == 3) {
                    status.updateStatus();
                }
            }
            if (opt==10){
                break;
            }
        }
    }
}
