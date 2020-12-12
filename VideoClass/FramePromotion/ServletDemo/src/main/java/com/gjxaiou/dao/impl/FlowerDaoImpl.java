package com.gjxaiou.dao.impl;
import java.sql.*;
import	java.util.ArrayList;

import com.gjxaiou.dao.FlowerDao;
import com.gjxaiou.pojo.Flower;

import java.util.List;

/**
 * @author GJXAIOU
 * @create
 * 2019-08-29-18:20
 */
public class FlowerDaoImpl implements FlowerDao {
    public List<Flower> selectAll() {
        ArrayList<Flower> flowers = new ArrayList();
        Connection conn = null;
        PreparedStatement param = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection("jdbc:mysql://localhost:3306/lianxi","root", "GJXAIOU");
            conn.prepareStatement("select * from flower")
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int AddFlower(Flower flower) {
        return 0;
    }
}
