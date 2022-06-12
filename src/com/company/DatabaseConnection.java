package com.company;

import java.sql.*;

public class DatabaseConnection {
    // constructor
    public DatabaseConnection() throws SQLException {

    }
    // connection with database
    // if your xammp dont have pass than remove the password 'root'
    public Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanagementsystem", "root", "root");

}
