package jdbc.batching;

import jdbc.preparedStatement.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author GJXAIOU
 * @create 2019-08-02-15:15
 */
public class BatchPratice {
    public static void main(String[] args) {
        batch();
    }

    public static  void batch() {
        // 1.建立连接
        Connection connection = JdbcUtil.getConnection();

        // 2.准备是SQL语句
        String sql = "insert into person(name, gender, score, home, hobby) value(?, ?, ?, ?, ?)";

        // 3.使用preparedstatement运输SQL语句
        PreparedStatement preparedStatement = null;
        try {
             preparedStatement = connection.prepareStatement(sql);

             int flag = 0;
             for (int i = 0; i < 10; i++) {
                String name = "张三" + i + "号";
                String gender = "男";
                int score =  87 + i;
                String home = "江苏";
                String hobby = "游泳";

                 preparedStatement.setString(1, name);
                 preparedStatement.setString(2, gender);
                 preparedStatement.setInt(3, score);
                 preparedStatement.setString(4, home);
                 preparedStatement.setString(5, hobby);

                 // 4.添加批处理
                 preparedStatement.addBatch();
                 flag++;
                 // 设置每5条批处理一次
                 if (flag % 5 == 0){
                     // 执行保存到批处理里面的SQL语句
                     preparedStatement.executeBatch();
                     // 执行保存在批处理里面的SQL语句之后，清空批处理的缓冲区
                     preparedStatement.clearBatch();
                     flag = 0;
                 }
             }

             // 处理批处理中剩余的SQL语句，即剩余不够5个的倍数
            if (flag > 0){
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnection(connection, preparedStatement);
        }
    }
}
