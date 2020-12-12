import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCByConfig {
	public static void main(String[] args) {
		String fileName;
		if (args != null && args.length == 1) {
			fileName = args[0] + "properties";
		} else {
			fileName = "qa.properties";
		}

		String driver = null;
		String url = null;
		String username = null;
		String pwd = null;

		Properties prop = new Properties();
		InputStream in = null;
		try {
			// 读配置文件
			in = new BufferedInputStream(new FileInputStream("src/prop/"
					+ fileName));
			prop.load(in);
            //读JDBC连接参数
			driver = prop.getProperty("mysql.Driver");
			url = prop.getProperty("mysql.url");
			username = prop.getProperty("mysql.username");
			pwd = prop.getProperty("mysql.pwd");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				prop.clear();
			}
		}

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DriverManager.getConnection(url, username, pwd);

			String query = "select id,name,score from student";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.print("id:" + rs.getString("id") + " ");
				System.out.print("name:" + rs.getString("name") + " ");
				System.out.println("Score:" + rs.getInt("score"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}

	}

}
