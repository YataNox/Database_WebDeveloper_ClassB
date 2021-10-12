package JDBC05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rent_Dao 
{
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String driver = "oracle.jdbc.driver.OracleDriver";
	private String id = "scott";
	private String pw = "tiger";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// db 연결함수
		public Connection getConnection() 
		{
			Connection con = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, id, pw);
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return con;
		}
		
		// db 연결 해제 함수
		public void close() 
		{
			try {
				if(con != null)
					con.close();
				if(pstmt != null)
					pstmt.close();
				if(rs != null)
					rs.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
}
