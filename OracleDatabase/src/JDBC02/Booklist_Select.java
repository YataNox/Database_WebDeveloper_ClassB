package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Booklist_Select 
{
	public static void main(String[] args)
	{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		PreparedStatement pstmt = null; // con에 SQL실행해주는 객체
		ResultSet rs = null; // SQL 실행 결과를 저장하는 객체
		try 
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			String sql = "select * from booklist";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println("책번호 \t 제목 \t\t\t 제작년도 \t 가격 \t\t 대여가격 \t 연령");
			System.out.println("------------------------------------------------------------------------");
		
			while(rs.next()) {
				int booknum = rs.getInt("booknum");
				String subject = rs.getString("subject");
				int makeyear = rs.getInt("makeyear");
				int inprice = rs.getInt("inprice");
				int rentprice = rs.getInt("rentprice");
				String grade = rs.getString("grade");
				
				System.out.printf("%-5d \t\t %-10s \t %-4d \t %6d\t %4d\t %-3s\n", booknum, subject, makeyear, inprice, rentprice, grade);
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("별도의 사유로 실패");
		}
		
		try {
			if(con != null) 
				con.close();
			if(pstmt != null)
				pstmt.close();
			if(rs != null)
				rs.close();
		}
		catch(SQLException e)
		{
			System.out.println("연결이 종료되지 않았습니다.");
		}
	}
}
