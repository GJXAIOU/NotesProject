package dao.impl;
import java.sql.*;

import dao.FlowerDao;
import pojo.Flower;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

/**
 * @author GJXAIOU
 * @create 2019-09-12-19:14
 */
public class FlowerDaoImpl implements FlowerDao {
    public List<Flower> selectAll() throws SQLException {
        // 新建存放返回对象的列表
        ArrayList<Flower> flowers = new ArrayList<Flower>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lianxi", "root",
                    "GJXAIOU");
            preparedStatement = connection.prepareStatement("select * from flower");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                flowers.add(new Flower(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDouble(3), resultSet.getString(4)));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动加载错误");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flowers;
    }

    public int addFlower(Flower flower) throws SQLException {
        int index = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lianxi", "root"
                    , "GJXAIOU");
            preparedStatement = connection.prepareStatement("insert into flower values(default, " +
                    "?, ?, ?)");
            preparedStatement.setString(1,flower.getName() );
            preparedStatement.setDouble(2, flower.getPrice());
            preparedStatement.setString(3, flower.getProduction());

            index = preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return index;
    }
}
