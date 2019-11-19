package jdbc.connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author GJXAIOU
 * @create 2019-08-01-14:14
 */
public class JdbcConnection {
    @Test
    public void connection()  {
        // 1.注册驱动 JDBC 连接MySQL
        try {
         Class.forName("com.mysql.cj.jdbc.Driver");


        // 2.准备url，是JDBC所要连接MySQL数据库的URL
        String url = "jdbc:mysql://localhost:3306/day34jdbc?serverTimezone = GMT%2B8" ;

        // 3.通过DriverManager连接对象
        Connection con = null;

        con = DriverManager.getConnection(url, "root", "GJXAIOU");


        System.out.println(con);

        // 4.关闭数据库连接，释放资源

        con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
