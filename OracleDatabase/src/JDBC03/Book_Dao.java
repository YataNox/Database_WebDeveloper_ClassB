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
	
	// db 연결해제
	public void close() {
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
	
	// Booklist 전체 조회 메소드
	public ArrayList<Book_Dto> selectAll() 
	{
		// 데이터베이스에서 booklist 테이블 조회 후 리턴
		ArrayList<Book_Dto> list = new ArrayList<Book_Dto>();
		
		try {
			// db 접속
			con = getConnection();
			String sql = "Select * from booklist order by booknum desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// db의 각 필드 값을 저장
				int booknum = rs.getInt("booknum");
				String subject = rs.getString("subject");
				int makeyear = rs.getInt("makeyear");
				int inprice = rs.getInt("inprice");
				int rentprice = rs.getInt("rentprice");
				String grade = rs.getString("grade");
				
				// 전송 객체에 값을 저장 후 
				Book_Dto bdto = new Book_Dto();
				bdto.setBooknum(booknum);
				bdto.setSubject(subject);
				bdto.setMakeyear(makeyear);
				bdto.setInprice(inprice);
				bdto.setRentprice(rentprice);
				bdto.setGrade(grade);
				
				// 리턴 리스트에 추가
				list.add(bdto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		close();
		
		// 이후 저장된 레코드들 반환
		return list;
	}

	// 레코드 삽입 함수
	public int insertSql(Book_Dto dbto) {
		int result = 0;
		
		try {
			con = getConnection();
			
			String sql = "insert into Booklist values(book_seq.nextVal, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dbto.getSubject());
			pstmt.setInt(2, dbto.getMakeyear());
			pstmt.setInt(3, dbto.getInprice());
			pstmt.setInt(4, dbto.getRentprice());
			pstmt.setString(5, dbto.getGrade());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return result;
	}

	public int deleteSql(String num) 
	{
		int result = 0;
		try {
			con = getConnection();
			
			String sql = "delete from booklist where booknum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return result;
	}

	public Book_Dto getDto(String num) 
	{
		Book_Dto bdto = null;
		con = getConnection();
		String sql = "select * from booklist where booknum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bdto = new Book_Dto();
				
				bdto.setBooknum(rs.getInt("booknum"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setMakeyear(rs.getInt("makeyear"));
				bdto.setInprice(rs.getInt("inprice"));
				bdto.setRentprice(rs.getInt("rentprice"));
				bdto.setGrade(rs.getString("grade"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close();
		return bdto;
	}

	public int update(Book_Dto newDto) {
		int result = 0;
		con = getConnection();
		String sql = "update booklist set subject = ?, makeyear = ?, inprice = ?, rentprice = ?, grade = ?"
				+ " where booknum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newDto.getSubject());
			pstmt.setInt(2, newDto.getMakeyear());
			pstmt.setInt(3, newDto.getInprice());
			pstmt.setInt(4, newDto.getRentprice());
			pstmt.setString(5, newDto.getGrade());
			pstmt.setInt(6, newDto.getBooknum());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return result;
	}

}
