package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Booklist_Insert 
{
	public static void main(String[] args) 
	{
		// Booklist 테이블에 레코드를 추가하는 코드를 작성하세요.
		// booknum에는 sequence를 사용해주세요.
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try 
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			Scanner sc = new Scanner(System.in);
			
			System.out.print("제목을 입력하세요: ");
			String subject = sc.nextLine();
			System.out.print("제작년도를 입력하세요: ");
			String makeyear = sc.nextLine();
			System.out.print("가격을 입력하세요: ");
			String inprice = sc.nextLine();
			System.out.print("대여가격을 입력하세요: ");
			String rentprice = sc.nextLine();
			System.out.print("연령을 입력하세요: ");
			String grade = sc.nextLine();
			
			String sql = "insert into booklist values(book_seq.nextVal, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setInt(2, Integer.parseInt(makeyear));
			pstmt.setInt(3, Integer.parseInt(inprice));
			pstmt.setInt(4, Integer.parseInt(rentprice));
			pstmt.setString(5, grade);
			
			int result = pstmt.executeUpdate();
			if( result == 1)
				System.out.println("레코드 추가 성공");
			else
				System.out.println("레코드 추가 실패");
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
		}
		catch(SQLException e)
		{
			System.out.println("연결이 종료되지 않았습니다.");
		}
	}
}
