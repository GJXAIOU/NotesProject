import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCWriter {

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
		PreparedStatement ps = null;
		int affectRows = 0;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/JDBCDemo", "root", "123456");
			if (connection != null) {

				stmt = connection.createStatement();
				String insSql = "insert into student (id,name,score) values ('3','Tom',80)";
				affectRows = stmt.executeUpdate(insSql);
				
				String updateSql = "update student set score=100 where id='3'";
				ps = connection.prepareStatement(updateSql);
				affectRows = ps.executeUpdate();
				
				String delSql = "delete from student where id = '3'";
				ps = connection.prepareStatement(delSql);
				affectRows = ps.executeUpdate();
				
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				ps.close();
				connection.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
