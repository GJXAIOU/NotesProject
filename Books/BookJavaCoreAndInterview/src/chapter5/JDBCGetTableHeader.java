import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCGetTableHeader {

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
				String query = "select * from student";
				ResultSet rs=stmt.executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData(); 
				int numberOfColumns = rsmd.getColumnCount();
				int index = 1;
				while(index<=numberOfColumns)
				{
					System.out.println(rsmd.getColumnName(index));
					index++;					
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
