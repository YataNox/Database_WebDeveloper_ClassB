package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connect 
{
	public static void main(String[] args)
	{
		// 자바에서 지원하는 데이터베이스 연결을 위한 구성요소들과 객체를 세팅
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";
		
		// JDBC를 통한 데이터베이스 연결 클래스의 객체 : Connection
		Connection con = null;
		//	ctrl + shift + o
		try 
		{
			Class.forName(driver);
			// 연결 드라이버 매니저가 데이터베이스 연결을 하고, 연결해준 연결 인스턴스를 con 변수에 저장
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("데이터베이스 연결에 성공했습니다.");
			// 데이터 베이스 작업
		}
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("별도의 사유로 실패");
		}
		
		try {
			if(con != null) 
				con.close();
			
			System.out.println("데이터베이스 종료");
		}
		catch(SQLException e)
		{
			System.out.println("연결이 종료되지 않았습니다.");
		}
	}
}
