package com.company;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws SQLException {
        //checking connection
   DatabaseConnection db= new DatabaseConnection();
        System.out.println("Welcome to the Leo Book Store");
        System.out.println("1-Login \n 2- Sign Up");
        User user = new User();
        int opt;
        Scanner in= new Scanner(System.in);
        opt=in.nextInt();
        if (opt==1){
            Login login= new Login();
            login.LoginTheUser(user);
        }
        if (opt==2){
            Signup signup= new Signup();
           signup.RegisterUser(user);
        }

//        User u= new User();
//        Eclub e= new Eclub();
//        Book b= new Book();
//       double a=e.calculating_discount(103);
//        System.out.println(a);
//       int a=b.getPrice(1);
//        System.out.println(a);


        // inheritence = Book ,User
        // polymoriphism= Chairty Book, Admin ,Customer

    }
}
