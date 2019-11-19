package jdbc.connection;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**使用JdbcUtil工具类创建并操作数据表
 * @author GJXAIOU
 * @create 2019-08-01-16:07
 */
public class JdbcRealizationTest {

    /**
     * 通过JDBC创建数据表
     */
    @Test
    public void createTable()  {
        // 1.通过已经封装好的JdbcUtil工具类，获取数据库的连接对象
        Connection connection = JdbcUtil.getConnection();

        try {
            // 2.获取Statement，即SQL语句的运输者，作用是将SQL语句运输到MySQL中，让MySQL运行
            Statement statement = connection.createStatement();

            // 3.准备好SQL语句，语句最后不用分号
            String sql = "create table person(" +
                    "  id   tinyint  not null  primary key  auto_increment," +
                    "  name  char(5) not null," +
                    "  gender  char(1)," +
                    "  score  decimal(4, 2)," +
                    "  home  enum(\"江苏\", \"上海\", \"杭州\", \"苏州\")," +
                    "  hobby  set(\"游泳\", \"打球\", \"跑步\"))";

            // 4.通过statement执行SQL语句
            int count = statement.executeUpdate(sql);

            // 5.查看创建的结果
            System.out.println("影响的行数" + count);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用statement执行DML语句-- insert into
     */
    @Test
    public void testInsert(){
        // 1.建立数据库连接
        Connection connection = JdbcUtil.getConnection();

        Statement statement = null;
        try {
            // 2.获取到Statement
            statement = connection.createStatement();

            // 3.准备SQL语句
            String sql1 = "insert into person(name, gender, score, home, hobby) values(\"张三\", \"男\", 98.23, 2, 3)";
            String sql2 = "insert into person(name, gender, score, home, hobby) values(\"李四五\", \"女\",99.00,\"江苏\", \"游泳,打球\")";

            // 4.通过statement执行SQL语句
            int count1 = statement.executeUpdate(sql1);
            int count2 = statement.executeUpdate(sql2);

            // 5.打印影响的行数
            System.out.println("改变的行数：" + (count1 + count2));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 6.关闭所有资源:connection 是连接数据库的资源，statement是引用程序到MySQL之间的SQL语句运输者，都是资源
            JdbcUtil.closeConnection(connection, statement);
        }
    }

    @Test
    /**
     * 使用statement删除数据库中一条数据
     */
    public void testDelete() {
        // 1.建立数据库连接
        Connection connection = JdbcUtil.getConnection();

        Statement statement = null;
        try {
            // 2.获取到statement
            statement = connection.createStatement();

            // 3.准备SQL语句
            String sql = "delete from person where id = 1";

            // 4.使用statement执行SQL语句
            int count = statement.executeUpdate(sql);

            // 5.输出影响的行数
            System.out.println("改变的行数：" + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 6.关闭所有资源
            JdbcUtil.closeConnection(connection, statement);
        }
    }

    /**
     * 使用statement修改数据库中一条数据
     */
    public void testUpdate(){
        // 1.建立数据库连接
        Connection connection = JdbcUtil.getConnection();

        Statement statement = null;
        try {
            // 2.获取到statement
            statement = connection.createStatement();

            // 3.准备SQL语句
            String sql = "update person set name = '李四' where id = 2";

            // 4.使用statement执行SQL语句
            int count = statement.executeUpdate(sql);

            // 5.输出影响的行数
            System.out.println("改变的行数" + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 6.关闭所有的资源
            JdbcUtil.closeConnection(connection, statement);
        }
    }

    /**
     * 使用statement查询数据库中数据并返回结果集set
     */
    @Test
    public void testSelect(){
        // 1.连接数据库
        Connection connection = JdbcUtil.getConnection();

        Statement statement = null;
        // 查询语句返回的结果集对象
        ResultSet set = null;
        try {
            // 2.获取到statement
            statement = connection.createStatement();

            // 3.准备SQL语句
            String sql = "select * from person";

            // 4.通过statement执行SQL语句, 获得查询结果集
            set = statement.executeQuery(sql);

            // 5.输出影响的行数
            while(set.next()){
                int id = set.getInt("id");
                String name = set.getString("name");
                String gender = set.getString("gender");
                BigDecimal score = set.getBigDecimal("score");
                String home = set.getString("home");
                String hobby = set.getString("hobby");

                System.out.println("id :" + id + " name :" + name + " gender :" + gender +
                        " socore :" + score + " home :" + home + " hobby :" + hobby);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnectionWithResult(connection, statement, set);
        }
    }


}
