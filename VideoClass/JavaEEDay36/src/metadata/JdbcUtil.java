package metadata;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**实现数据库连接和资源关闭操作
 * @author GJXAIOU
 * @create 2019-08-01-15:16
 */
public class JdbcUtil {
   private static String url = null;
   private static String user = null;
   private static String password = null;
   private static String driver = null;
   private static InputStream inputStream = null;

   // 这里利用静态代码块的特征，在类文件加载到内存的时候，
   // 就会执行在静态代码块中的代码
    static {
       // 1.读取配置文件信息，即读取properties文件
       Properties properties = new Properties();
       // 如果一个properties文件加载到内存中，需要借助IO流；
       try {
           inputStream = new FileInputStream(
                   "E:\\Program\\Java\\Project\\VideoClass\\" +
                           "JavaEEDay36\\src\\metadata\\db.properties");
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }

       // 2.利用properties里面的load方法
       try {
           properties.load(inputStream);
       } catch (IOException e) {
           e.printStackTrace();
       }

       // 3.可以通过properties类对象，获取想要的数据
       url = properties.getProperty("url");
       user = properties.getProperty("user");
       password = properties.getProperty("password");
       driver = properties.getProperty("driver");

       // 4.加载类文件
       try {
           Class.forName(driver);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
           System.out.println("驱动加载失败");
       }finally {
           // 关闭文件连接
           if (inputStream != null){
               try {
                   inputStream.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
   }


    /**
     * 获取数据库连接对象
     * @return Connection对象
     */
   public static Connection getConnection(){
       Connection connection = null;
       try {
           connection = DriverManager.getConnection(url, user, password);
       } catch (SQLException e) {
           e.printStackTrace();
       }

       return  connection;
   }

    /**
     * 关闭数据库连接，释放statement
     * @param connection 数据库连接对象
     * @param statement statement对象
     */
    public static void closeConnection(Connection connection, Statement statement){

        try {
            if (statement != null){
                statement.close();
            }

            if (connection != null){
            connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭带有结果集的查询语句资源
     * @param connection 数据库连接对象
     * @param statement statement 对象
     * @param set 结果集
     */
    public static void closeConnectionWithResult(Connection connection, Statement statement, ResultSet set){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (set != null){
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }


}
