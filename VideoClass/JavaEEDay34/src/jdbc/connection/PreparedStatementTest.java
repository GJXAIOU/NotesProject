package jdbc.connection;

import org.junit.jupiter.api.Test;

import java.sql.*;

/**使用preparedStatement防止SQL注入，因为在获取preparedStatement时候，已经对SQL语句进行了预处理
 * @author GJXAIOU
 * @create 2019-08-01-19:19
 */
public class PreparedStatementTest {
    @Test
    public void loginInTest(){
        // 1.建立连接
        Connection connection = null;
        connection= JdbcUtil.getConnection();

        // 2.准备预处理SQL语句，其中？为占位符，且顺序从1开始
        String sql = "select * from person where id = ? and name = ?";

        // 3.获取preparedStatement对象
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 4.准备参数
        try {
            preparedStatement.setInt(1,2);
            preparedStatement.setString(2,"李四五");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 5.准备接收查询结果
        ResultSet set = null;
        try {
            set = preparedStatement.executeQuery();
            System.out.println(set);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 6.判断输入的参数在数据表中有没有
        try {
            if (set.next()){
                System.out.println("登录成功");
            }else{
                System.out.println("登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnectionWithResult(connection, preparedStatement, set);
        }

    }


}
