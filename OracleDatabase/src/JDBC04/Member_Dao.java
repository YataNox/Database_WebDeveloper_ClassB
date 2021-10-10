package JDBC04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class Member_Dao 
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
		
		// db 연결 해제 함수
		public void close() 
		{
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
		
		// Memberlist 전체 조회 함수------------------------------------------------------------------------
		public ArrayList<Member_Dto> selectAll() 
		{
			ArrayList<Member_Dto> list = new ArrayList<Member_Dto>();
			
			try {
				// db 접속
				con = getConnection();
				String sql = "Select * from Memberlist order by membernum";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					// db의 각 필드 값을 저장
					String membernum = rs.getString("Membernum");
					String name = rs.getString("name");
					String phone = rs.getString("phone");
					Date birth = rs.getDate("birth");
					Date joindate = rs.getDate("joindate");
					int bpoint = rs.getInt("bpoint");
					String gender = rs.getString("gender");
					int age = rs.getInt("age");
					
					// 전송 객체에 값을 저장 후 
					Member_Dto ddto = new Member_Dto();
					ddto.setMembernum(membernum);
					ddto.setName(name);
					ddto.setPhone(phone);
					ddto.setBirth(birth);
					ddto.setJoindate(joindate);
					ddto.setBpoint(bpoint);
					ddto.setGender(gender);
					ddto.setAge(age);
					
					// 리턴 리스트에 추가
					list.add(ddto);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			close();
			
			// 이후 저장된 레코드들 반환
			return list;
		} 
		// Memberlist 튜플 삽입 함수------------------------------------------------------------------------
		public int insertSql(Member_Dto Mdto) 
		{
			int result = 0;
			con = getConnection();
			
			try {
				String sql = "insert into memberlist values(member_seq.nextVal, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, Mdto.getName());
				pstmt.setString(2, Mdto.getPhone());
				pstmt.setDate(3, Mdto.getBirth());
				pstmt.setDate(4, Mdto.getJoindate());
				pstmt.setInt(5, Mdto.getBpoint());
				pstmt.setString(6, Mdto.getGender());
				pstmt.setInt(7, Mdto.getAge());
				
				result = pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		// Memberlist 튜플 삭제 함수------------------------------------------------------------------------
		public int deleteSql(String num) 
		{
			int result = 0;
			
			return result;
		}
		// Memberlist 튜플 수정 함수------------------------------------------------------------------------
		public int updateSql(Member_Dto Mdto) 
		{
			int result = 0;
			
			return result;
		}
		// Memberlist 전송객체 전달 함수------------------------------------------------------------------------
		public Member_Dto getDto(String num) 
		{
			Member_Dto mdto = null;
			
			return null;
		}
		
}
