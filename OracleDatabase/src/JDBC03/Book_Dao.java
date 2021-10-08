package JDBC03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Database Access Object
public class Book_Dao
{
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String driver = "oracle.jdbc.driver.OracleDriver";
	private String id = "scott";
	private String pw = "tiger";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ArrayList<Book_Dto> selectAll() 
	{
		// 데이터베이스에서 booklist 테이블 조회 후 리턴
		ArrayList<Book_Dto> list = new ArrayList<Book_Dto>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			
			String sql = "Select * from booklist";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int booknum = rs.getInt("booknum");
				String subject = rs.getString("subject");
				int makeyear = rs.getInt("makeyear");
				int inprice = rs.getInt("inprice");
				int rentprice = rs.getInt("rentprice");
				String grade = rs.getString("grade");
				
				Book_Dto bdto = new Book_Dto();
				bdto.setBooknum(booknum);
				bdto.setSubject(subject);
				bdto.setMakeyear(makeyear);
				bdto.setInprice(inprice);
				bdto.setRentprice(rentprice);
				bdto.setGrade(grade);
				
				list.add(bdto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
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
		return list;
	}
}
