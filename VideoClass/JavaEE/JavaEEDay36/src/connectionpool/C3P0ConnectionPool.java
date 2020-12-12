package connectionpool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author GJXAIOU
 * @create 2019-08-02-21:42
 */
public class C3P0ConnectionPool {
    /**
     * 使用硬连接的方式
     * @throws PropertyVetoException
     * @throws SQLException
     */
    public void HardcodeStyleTest() throws PropertyVetoException, SQLException {
        // 1.创建连接池的核心工具类
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        // 2.设置连接数据库所需的参数
        comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/day34jdbc?serverTimezone = GMT%2B8 ");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("GJXAIOU");

        // 3.设置C3P0连接池的属性:初始化连接数、最大连接数、等待时间
        comboPooledDataSource.setInitialPoolSize(3);
        comboPooledDataSource.setMaxPoolSize(6);
        comboPooledDataSource.setMaxIdleTime(1000);

        // 4.从连接池中获取数据库连接对象
        Connection connection = comboPooledDataSource.getConnection();

        // 5.准备preparedStatement执行SQL语句
        connection.prepareStatement("delete from person where id = 3").executeUpdate();
    }

    /**
     * 通过配置XML文件，创建C3P0连接池
     * @throws SQLException
     */
    public void XmlStyleTest() throws SQLException {
        // 1.创建C3P0核心类，会自动的加载s目录下的c3p0-config.xml文件
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        // 2.创建连接
        Connection connection = comboPooledDataSource.getConnection();

        // 3.准备preparedStatement以及SQL语句
        PreparedStatement preparedStatement = null;
        String sql = "insert into person(name, gender, score, home, hobby) value(?, ?, ?, ?, ?)";

        for (int i = 0; i < 10; i++) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"钱十");
            preparedStatement.setString(2,"男");
            preparedStatement.setInt(3, 98);
            preparedStatement.setString(4, "江苏");
            preparedStatement.setString(5, "游泳");
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        connection.close();
    }
}
