package connectionpool;

import metadata.JdbcUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author GJXAIOU
 * @create 2019-08-02-20:37
 */
public class ConnectionPoolPractice {
    /*
    初始化连接数目，这里默认为3
     */
    private int initCount = 3;
    /*
    连接池最大连接数目，这里定义为6
     */
    private final int maxCount = 6;
    /*
     记录当前的连接数目
     */
    private int currentCount = 0;

    // 新建连接池，使用linkedlist操作
    private  LinkedList<Connection> connectionPool = new LinkedList<Connection>();

    // 1.构造方法，按照指定初始化连接个数并且创建新的连接
    public ConnectionPoolPractice(){
        for (int i = 0; i < initCount; i++) {
            // 类内创建连接的方式
            Connection connection = createConnection();
            connectionPool.addLast(connection);
            currentCount++;
        }
    }

    public int getCurrentCount(){
        return currentCount;
    }

    // 2.创建一个新的连接
    private Connection createConnection(){
        // 2.1加载驱动
       final Connection connection = JdbcUtil.getConnection();

        Connection proxy = (Connection)Proxy.newProxyInstance(connection.getClass().getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 定义一个返回值
                Object result = null;
                // 获取要执行方法的方法名
                String methodName = method.getName();
                // 这里只是限制了close的操作，其他的方法按照原来的操作进行
                // 修改关闭close执行的任务
                if ("close".equals(methodName)) {
                    System.out.println("执行close方法");
                    // 放回连接池内
                    connectionPool.addLast(connection);
                    System.out.println("数据库连接已经放回到连接池中");
                }else{
                    result = method.invoke(connection, args);
                }
                return result;
            }
        });
        return proxy;

    }

    // 3.从连接池中获取连接的方法
    public Connection getConnection(){
        // 3.1判断池子中有没有连接
        if (connectionPool.size() > 0){
            return connectionPool.removeFirst();
        }

        // 3.2如果没有连接，判断当前连接数是否达到最大值限制
        if (currentCount < maxCount){
            currentCount++;
            return createConnection();
        }
        throw new RuntimeException("当前连接数已经达到最大值");
    }

    // 4.释放连接
    public void realeaseConnection(Connection connection){
        // 4.1如果池子中读取的数目小于初始化连接，放入池子
        if(connectionPool.size() < initCount){
            connectionPool.addLast(connection);
        }else {
            currentCount--;
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ConnectionPoolPractice connectionPoolPractice = new ConnectionPoolPractice();
        System.out.println("当前连接数目为：" + connectionPoolPractice.getCurrentCount());

        Connection connection = connectionPoolPractice.getConnection();
        Connection connection1 = connectionPoolPractice.getConnection();
        Connection connection2 = connectionPoolPractice.getConnection();
        System.out.println(connection);
        System.out.println(connection1);
        System.out.println(connection2);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connection connection3 = connectionPoolPractice.getConnection();
        System.out.println(connection3);

    }

}
