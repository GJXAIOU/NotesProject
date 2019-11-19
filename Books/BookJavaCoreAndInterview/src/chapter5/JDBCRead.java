import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCRead {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/JDBCDemo", "root", "123456");
			if (connection != null) {

				stmt = connection.createStatement();
				String query = "select id,name,score from student";
				ResultSet rs=stmt.executeQuery(query);
				while(rs.next())
				{
					System.out.print("id:" + rs.getString("id") + " ");
					System.out.print("name:" + rs.getString("name") + " ");
					System.out.println("Score:" + rs.getInt("score"));					
				}
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
