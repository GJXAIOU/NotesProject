package metadata;

import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * @author GJXAIOU
 * @create 2019-08-02-16:14
 */
public class getMetadata {
    /**
     * 由Connection对象的getMetaData()方法获取DatabaseMetaData对象
     * @throws SQLException
     */
    @Test
    public void getMetadataByConnection() throws SQLException {
        // 1.建立连接
        Connection connection = JdbcUtil.getConnection();

        // 2.获取数据库元数据
        DatabaseMetaData metaData = connection.getMetaData();

        // 3.具体的方法
        //获取数据库产品名称
        String productName = metaData.getDatabaseProductName();
        //获取数据库版本号
        String productVersion = metaData.getDatabaseProductVersion();
        //获取数据库用户名
        String userName = metaData.getUserName();
        //获取数据库连接URL
        String userUrl = metaData.getURL();
        //获取数据库驱动
        String driverName = metaData.getDriverName();
        //获取数据库驱动版本号
        String driverVersion = metaData.getDriverVersion();
        //查看数据库是否允许读操作
        boolean isReadOnly = metaData.isReadOnly();
        //查看数据库是否支持事务操作
        boolean supportsTransactions = metaData.supportsTransactions();

        System.out.println("productName : " + productName + "\n"+ "productVersion : " + productVersion + "\n"+"userName : " + userName +"\n"+
                "userUrl : " + userUrl +"\n"+ "driverName : " + driverName + "\n"+"driverVersion : " + driverVersion + "\n"+
                "isReadOnly : " + isReadOnly + "\n"+ "supportsTransactions : " + supportsTransactions);
    }

    /**
     *  由PreparedStatement对象的getParameterMetaData ()方法获取的是ParameterMetaData对象
     * @throws SQLException
     */
    @Test
    public void getMetadataByPreparedStatement() throws SQLException {
        // 1.建立连接
        Connection connection = JdbcUtil.getConnection();

        // 2.准备SQL语句
        String sql = "delete from  person where id = ?";

        // 3.建立preparedStatement运输SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 2);

        // 4.获取元数据
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

        // 5.常见的方法：
        //获取参数个数
        int paramCount = parameterMetaData.getParameterCount();
        //以字符串形式获取指定参数的SQL类型，这里有问题
        String paramTypeName = parameterMetaData.getParameterTypeName(1);
        //返回指定参数的SQL类型，以java.sql.Types类的字段表示，这里有问题
        int paramType = parameterMetaData.getParameterType(1);
        //返回指定参数类型的Java完全限定名称，这里有问题
        String paramClassName = parameterMetaData.getParameterClassName(1);
        //返回指定参数的模，，这里有问题
        int paramMode = parameterMetaData.getParameterMode(1);
        //返回指定参数的列大小，这里有问题
        int precision = parameterMetaData.getPrecision(1);
        //返回指定参数的小数点右边的位数，这里有问题
        int scale = parameterMetaData.getScale(1);

        System.out.println("paramCount : " + paramCount + "\n" + "paramTypeName : " + paramTypeName + "\n" +
                "paramType : " + paramType + "\n" + "paramClassName : " + paramClassName + "\n" +
                "paramMode : " + paramMode + "\n" + "precision : " + precision + "\n" + "scale : " + scale);
    }

    /**
     * 由ResultSet对象的getMetaData()方法获取的是ResultSetMetaData对象
     * @throws SQLException
     */
    @Test
    public void getMetadataByRestltSet() throws SQLException {
        // 1.建立连接
        Connection connection = JdbcUtil.getConnection();

        // 2.准备SQL语句
        String sql = "select * from person";

        // 3.使用preparedStatement运输SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 4.指向SQL语句，返回结果集
        ResultSet set = preparedStatement.executeQuery();

        // 5.获取元数据
        ResultSetMetaData resultMetaData = set.getMetaData();

        //获取结果集的列数
        int columnCount = resultMetaData.getColumnCount();
        //获取指定列的名称
        String columnName = resultMetaData.getColumnName(1);
        //获取指定列的SQL类型对应于java.sql.Types类的字段
        int columnType = resultMetaData.getColumnType(1);
        //获取指定列的SQL类型
        String columnTypeName = resultMetaData.getColumnTypeName(1);
        //获取指定列SQL类型对应于Java的类型
        String className = resultMetaData.getColumnClassName(1);
        //获取指定列所在的表的名称
        String tableName = resultMetaData.getTableName(1);

        System.out.println("columnCount : " + columnCount + "\n" + "columnName : " + columnName + "\n" +
                "columnType : " + columnType + "\n" + "columnTypeName :" + columnTypeName + "\n" +
                "className : " + className + "\n" + "tableName : " + tableName);

    }
}
