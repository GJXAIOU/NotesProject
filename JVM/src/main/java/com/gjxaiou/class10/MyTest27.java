package com.gjxaiou.class10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//跟踪代码
public class MyTest27{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306//mydb","user","password");
    }
}
