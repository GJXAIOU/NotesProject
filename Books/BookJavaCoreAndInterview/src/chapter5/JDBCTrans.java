import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

	public class JDBCTrans {
		public static void main(String[] args) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();				
			}
			Connection connection = null;
	        PreparedStatement pstmt;
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/JDBCDemo", "root", "123456");
				
				connection.setAutoCommit(false);
					String query = "insert into student (id,name,score) values (?,?,?)";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1,"1");
					pstmt.setString(2,"Peter");
					pstmt.setInt(3,95);
					pstmt.addBatch();
					pstmt.setString(1,"2");
					pstmt.setString(2,"Mike");
					pstmt.setInt(3,90);
					pstmt.addBatch();
					//执行批处理
					pstmt.executeBatch();		
					connection.commit();
					
			} catch (SQLException e) {
				System.out.println("Some of Students were not inserted correctly, please check the student table and insert manually.");
				try {
					connection.rollback();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
