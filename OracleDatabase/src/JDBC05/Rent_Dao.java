package JDBC05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Rent_Dao 
{
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String driver = "oracle.jdbc.driver.OracleDriver";
	private String id = "scott";
	private String pw = "tiger";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 클래스 내부에서 딱 한개의 유일한 객체를 생성합니다.
	private static Rent_Dao itc = new Rent_Dao();
	
	// 생성자를 private로 만들어서 외부에서 생성자를 사용할 수 없게 만듭니다.
	private Rent_Dao(){}
	
	// 외부에서 itc를 리턴받아 쓸 수 있게 해주는 public static 메소드를 생성합니다.
	public static Rent_Dao getInstance() {
		return itc;
	}
	
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

		public ArrayList<Rent_Dto> selectAll() {
			ArrayList<Rent_Dto> list = new ArrayList<Rent_Dto>();
			con = getConnection();
			String sql = "select * from rentlist order by rentdate";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Rent_Dto rdto = new Rent_Dto();
					rdto.setRentdate(rs.getDate("rentdate"));
					rdto.setNumseq(rs.getInt("numseq"));
					rdto.setBooknum(rs.getInt("booknum"));
					rdto.setMembernum(rs.getInt("membernum"));
					rdto.setDiscount(rs.getInt("discount"));
					
					list.add(rdto);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			close();
			return list;
		}
		
		
}
