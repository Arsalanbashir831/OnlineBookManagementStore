package com.company;

import java.sql.*;

public class DatabaseConnection {
    // constructor
    public DatabaseConnection() throws SQLException {

    }
    // connection with database
    public Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanagementsystem", "root", "root");

}
