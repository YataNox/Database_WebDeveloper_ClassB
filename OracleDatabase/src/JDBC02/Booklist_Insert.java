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
		PreparedStatement pstmt = null; // con에 SQL실행해주는 객체
		// insert 명령의 경우 결과 값이 따로 없어, ResultSet은 사용하지 않습니다.
		try 
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			//	System.out.println("연결 성공");
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
			
			
			// 요즘 방식
			String sql = "insert into booklist values(book_seq.nextVal, ?, ?, ?, ?, ?)";
			// SQL 명령을 먼저 장착하고
			pstmt = con.prepareStatement(sql);
			// ? 순서에 맞춰서 해당 데이터를 세팅
			pstmt.setString(1, subject);
			pstmt.setInt(2, Integer.parseInt(makeyear));
			pstmt.setInt(3, Integer.parseInt(inprice));
			pstmt.setInt(4, Integer.parseInt(rentprice));
			pstmt.setString(5, grade);
			
			// SQL select 명령만 excuteQuery를 사용하고, 나머지는 executeUpdate 메소드를 사용합니다.
			// executeUpdate의 결과는 SQL 명령이 정상 동작했을 때 1, 실패했을 때 0이 리턴됩니다.
			
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
