package metadata;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**提供增删改查数据库的通用Dao层
 * @author GJXAIOU
 * @create 2019-08-02-19:31
 */
public class BaseDao {

    /**
     * 增删改的通用方法
     * @param sql 要执行的SQL语句，可以是：insert、delete、update
     * @param paramValue 参数数组，用来填充SQL语句中的占位符参数，若无参数，请传入null
     */
    public void updateCurrent(String sql, Object[] paramValue){
        // 1.连接数据库
        Connection connection = JdbcUtil.getConnection();

        // 2.获取PreparedStatement
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            // 3.得到SQL语句中参数元数据个数（即占位符？的个数）
            int parameterCount = preparedStatement.getParameterMetaData().getParameterCount();

            // 4.利用参数元数据给SQL语句的占位符需要的参数赋值
            if (paramValue != null && paramValue.length > 0) {
                // 通过循环给SQL语句完整赋值
                for (int i = 0; i < parameterCount; i++) {
                    // 因为参数（占位符）计数是从1开始，因此需要+1
                    preparedStatement.setObject(i + 1, paramValue[i]);
                }
            }

            // 5.执行
            preparedStatement.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnection(connection, preparedStatement);
        }
    }

    /**
     * 查询的通用方法（使用了泛型和反射）
     * @param sql 查询需要的SQL语句
     * @param paramValue 查询需要的参数，若没有这设置为null
     * @param tClass list集合中保存的数据类型
     * @param <T> list集合，返回一个带有指定数据类型的list集合
     * @return
     */
    public <T> List<T> inquiryCurrent(String sql, Object[] paramValue, Class<T> tClass){
        // 1.要返回的数据集合
        List<T> list = new ArrayList<>();

        // 2.确定list集合中要保存的对象
        T t = null;

        // 3.连接数据库
        Connection connection = JdbcUtil.getConnection();

        // 4.获取到PreparedStatement
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            // 5.得到SQL语句中参数元数据个数（即占位符？的个数）
            int parameterCount = preparedStatement.getParameterMetaData().getParameterCount();

            // 6.利用参数元数据给SQL语句的占位符需要的参数赋值
            if (paramValue != null && paramValue.length > 0) {
                for (int i = 0; i < parameterCount; i++) {
                    preparedStatement.setObject(i + 1, paramValue[i]);
                }
            }

            // 7.执行查询操作，返回ResultSet
            ResultSet set = preparedStatement.executeQuery();

            // 8.获取结果集元数据
            ResultSetMetaData metaDataResultSet = set.getMetaData();
            // 获取数据库列的数量
            int columnCount = metaDataResultSet.getColumnCount();

            // 9.遍历ResultSet数据集
            while (set.next()){
                // 创建要保存的对象
                t = tClass.newInstance();

                    // 10.遍历数据行的每一列，得到每一列的名字，再获取到数据，保存到T对象中
                    for (int i = 0; i < columnCount; i++) {
                        // 首先获取每一列的名字
                        String columnName = metaDataResultSet.getColumnName(i + 1);
                        // 获取每一列的数据
                        Object value = set.getObject(columnName);
                        // 利用Beanutils给T对象赋值
                        BeanUtils.setProperty(t, columnName, value);
                    }
                // 将创建好的T对象，放入list对象中
                list.add(t);
                }
            } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
