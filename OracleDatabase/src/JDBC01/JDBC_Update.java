package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Update 
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
			System.out.print("수정할 회원의 번호를 입력하세요 : ");
			String num = sc.nextLine();
			
			System.out.print("수정할 항복을 선택하세요. 1. 이메일 2. 전화번호");
			String input = sc.nextLine();
			
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
