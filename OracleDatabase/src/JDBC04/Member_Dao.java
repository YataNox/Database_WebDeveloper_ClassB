package JDBC04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			// db 접속
			con = getConnection();
			
			try {	
				String sql = "Select * from Memberlist order by membernum";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					// 전송 객체에 값을 저장 후 
					Member_Dto ddto = new Member_Dto();
					ddto.setMembernum(rs.getInt("Membernum"));
					ddto.setName(rs.getString("name"));
					ddto.setPhone(rs.getString("phone"));
					ddto.setBirth(rs.getDate("birth"));
					ddto.setJoindate(rs.getDate("joindate"));
					ddto.setBpoint(rs.getInt("bpoint"));
					ddto.setGender(rs.getString("gender"));
					ddto.setAge(rs.getInt("age"));
					
					// 리턴 리스트에 추가
					list.add(ddto);
				}
				
			}catch(SQLException e) {
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
			
			close();
			return result;
		}
		// Memberlist 튜플 삭제 함수------------------------------------------------------------------------
		public int deleteSql(String num)
		{
			int result = 0;
			con = getConnection();
			
			try {
				String sql = "delete from memberlist where membernum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(num));
				
				result = pstmt.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			close();
			return result;
		}
		// Memberlist 튜플 수정 함수------------------------------------------------------------------------
		public int updateSql(Member_Dto Mdto) 
		{
			int result = 0;
			con = getConnection();
			String sql = "update memberlist set name=?, phone=?, birth=?, joindate=?,"
					+ " bpoint=?, gender=?, age=? where membernum=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, Mdto.getName());
				pstmt.setString(2, Mdto.getPhone());
				pstmt.setDate(3, Mdto.getBirth());
				pstmt.setDate(4, Mdto.getJoindate());
				pstmt.setInt(5, Mdto.getBpoint());
				pstmt.setString(6, Mdto.getGender());
				pstmt.setInt(7, Mdto.getAge());
				pstmt.setInt(8, Mdto.getMembernum());
				
				result = pstmt.executeUpdate();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			close();
			return result;
		}
		// Memberlist 전송객체 전달 함수------------------------------------------------------------------------
		public Member_Dto getDto(String num) 
		{
			Member_Dto mdto = new Member_Dto();
			con = getConnection();
			String sql = "select * from memberlist where membernum = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(num));
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					mdto.setMembernum(rs.getInt("membernum"));
					mdto.setName(rs.getString("name"));
					mdto.setPhone(rs.getString("phone"));
					mdto.setBirth(rs.getDate("birth"));
					mdto.setJoindate(rs.getDate("joindate"));
					mdto.setBpoint(rs.getInt("bpoint"));
					mdto.setGender(rs.getString("gender"));
					mdto.setAge(rs.getInt("age"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			close();
			return mdto;
		}
		
}
