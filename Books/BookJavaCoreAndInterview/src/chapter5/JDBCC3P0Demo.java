package chapter5;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author GJX
 */
public class JDBCC3P0Demo {

	public static void main(String[] args) {
		ComboPooledDataSource ds;
		ds = new ComboPooledDataSource();
        
        try {
			ds.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} 
          
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/JDBCDemo");//设置连接数据库的URL           
        ds.setUser("root");//设置连接数据库的用户名            
        ds.setPassword("123456");//设置连接数据库的密码
        ds.setMaxPoolSize(40);//设置连接池的最大连接数  
        ds.setMinPoolSize(2);//设置连接池的最小连接数  
        ds.setInitialPoolSize(10);//设置连接池的初始连接数
        
        
        
        Connection conn = null;  
        PreparedStatement ps = null;
        try {  
            conn = ds.getConnection();
            
            String sql = "SELECT * FROM student ";
            ps = conn.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
            	System.out.println("Name is:" + rs.getString("name"));
            }
            
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        finally
        {
        	try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}        	
        }

	}

}
