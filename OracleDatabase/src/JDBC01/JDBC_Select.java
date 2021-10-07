package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		PreparedStatement pstmt = null; // con에 SQL실행해주는 객체
		ResultSet rs = null; // SQL 실행 결과를 저장하는 객체
		try 
		{
			Class.forName(driver);
			// 연결 드라이버 매니저가 데이터베이스 연결을 하고, 연결해준 연결 인스턴스를 con 변수에 저장
			con = DriverManager.getConnection(url, id, pw);
			// System.out.println("데이터베이스 연결에 성공했습니다.");
			
			// 데이터베이스 연결 후에는 SQL 명령을 사용하기 위해 String 변수에 SQL 명령을 준비합니다.
			// 데이터베이스에 제공되어질 명령이므로 String형으로 준비합니다.
			String sql = "select * from customer";
			
			// SQL문을 장작한 con을 pstmt에 전달합니다.
			pstmt = con.prepareStatement(sql);
			// pstmt = con.prepareStatement("select * from customer");
			
			// pstmt에 담겨진 SQL 명령 실행하고 그 결과를 re에 저장합니다.
			rs = pstmt.executeQuery();
			
			System.out.println("번호 \t 이름 \t\t 이메일 \t\t\t 전화번호");
			System.out.println("-----------------------------------------------------");
		
			// rs.next() : 최초 실행은 객체의 시작부분(데이터 없는 곳)에서 첫 번째 레코드로 이동하는 메소드
			// 그 다음부터는 다음 레코드로 이동. 
			// rs.next() 메소드가 실행될 때 다음 레코드가 있으면 true, 없으면 false를 리턴
			while(rs.next()) { // 결과의 처음부터 끝까지 반복 실행 : 레코드 단위로 반복 실행
				// rs.getInt() : number형 필드 값을 추출하는 메소드.
				// 괄호안에 필드 이름을 정확히 써야합니다. 필드명이 오타가 있거나 안맞으면 부적합한 열이름이란 에러가 발생.
				int num = rs.getInt("num");
				// rs.getString() : varchar2형 필드값을 추출하는 메소드.
				// 모든 자료형에 대해 get~() 메소드가 모두 존재합니다.
				String name = rs.getString("name");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				System.out.printf("%d \t %s \t %s \t %s\n", num, name, email, tel);
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
