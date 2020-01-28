package com.gjxaiou.classloader;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyTest27 {
    public static void main(String[] args) throws Exception {
        System.out.println("System.getProperty(\"jdbc.drivers\"): " + System.getProperty("jdbc.drivers"));
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mytestdb",
                "username", "password");

    }
}
