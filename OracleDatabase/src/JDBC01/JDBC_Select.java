package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Select 
{
	public static void main(String[] args)
	{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		
		try 
		{
			Class.forName(driver);
			// 연결 드라이버 매니저가 데이터베이스 연결을 하고, 연결해준 연결 인스턴스를 con 변수에 저장
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("데이터베이스 연결에 성공했습니다.");
			// 데이터 베이스 작업
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
	}
}
