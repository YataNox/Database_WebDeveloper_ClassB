package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Update 
{
	public static void main(String[] args) 
	{
		// 첫 번째로 수정할 도서의 도서번호 입력
		// 두 번째로 수정할 도서의 항목( 출판년도, 입고가격, 대여가격, 등급)
		// 네 가지 중 하나를 입력받고 해당 내용을 수정하는 코드를 작성하세요
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
			System.out.print("수정할 도서의의 번호를 입력하세요 : ");
			String num = sc.nextLine();
			
			System.out.print("수정할 항복을 선택하세요. 1. 출판년도 2. 입고가격 3. 대여가격 4. 연령 : ");
			String input = sc.nextLine();
			
			String sql = "";
			switch(input) {
				case "1":
					System.out.print("수정할 출판년도을 입력하세요 : ");
					String makeyear = sc.nextLine();
					sql = "Update booklist set makeyear = ? where booknum = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(makeyear));
					pstmt.setInt(2, Integer.parseInt(num));
					break;
				case "2":
					System.out.print("수정할 입고가격을 입력하세요 : ");
					String inprice = sc.nextLine();
					sql = "Update booklist set inprice = ? where booknum = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(inprice));
					pstmt.setInt(2, Integer.parseInt(num));
					break;
				case "3":
					System.out.print("수정할 대여가격을 입력하세요 : ");
					String rentprice = sc.nextLine();
					sql = "Update booklist set rentprice = ? where booknum = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(rentprice));
					pstmt.setInt(2, Integer.parseInt(num));
					break;
				case "4":
					System.out.print("수정할 연령을 입력하세요 : ");
					String grade = sc.nextLine();
					sql = "Update booklist set grade = ? where booknum = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, grade);
					pstmt.setInt(2, Integer.parseInt(num));
					break;
			}
			int result = pstmt.executeUpdate();
			if(result == 1)
				System.out.println("수정 성공!");
			else
				System.out.println("수정 실패!");
			
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
