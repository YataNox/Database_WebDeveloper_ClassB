package JDBC04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Member_Dao 
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
		
		// Memberlist 전체 조회 함수------------------------------------------------------------------------
		public ArrayList<Member_Dto> selectAll() 
		{
			ArrayList<Member_Dto> list = new ArrayList<Member_Dto>();
			
			return list;
		} 
		// Memberlist 튜플 삽입 함수------------------------------------------------------------------------
		public int insertSql(Member_Dto Mdto) 
		{
			
			return 0;
		}
		// Memberlist 튜플 삭제 함수------------------------------------------------------------------------
		public int deleteSql(String num) 
		{
			int result = 0;
			
			return result;
		}
		// Memberlist 튜플 수정 함수------------------------------------------------------------------------
		public int updateSql(Member_Dto Mdto) 
		{
			int result = 0;
			
			return result;
		}
		// Memberlist 전송객체 전달 함수------------------------------------------------------------------------
		public Member_Dto getDto(String num) 
		{
			Member_Dto mdto = null;
			
			return null;
		}
		
}
