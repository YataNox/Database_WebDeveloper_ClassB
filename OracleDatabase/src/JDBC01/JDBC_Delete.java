package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Delete 
{
	public static void main(String[] args)
	{
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
			System.out.print("삭제할 번호를 입력하세요 : ");
			int num = sc.nextInt();
			
			String sql = "delete from customer where num = ?"; // sql문 제작
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int result = pstmt.executeUpdate(); // 실행
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
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
