package JDBC05;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Rent_Dao 
{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBManager dbm = new DBManager();
	
	// 클래스 내부에서 딱 한개의 유일한 객체를 생성합니다.
	private static Rent_Dao itc = new Rent_Dao();
	
	// 생성자를 private로 만들어서 외부에서 생성자를 사용할 수 없게 만듭니다.
	private Rent_Dao(){}
	
	// 외부에서 itc를 리턴받아 쓸 수 있게 해주는 public static 메소드를 생성합니다.
	public static Rent_Dao getInstance() {
		return itc;
	}
		
	public ArrayList<Rent_Dto> selectAll() {
		ArrayList<Rent_Dto> list = new ArrayList<Rent_Dto>();
		con = dbm.getConnection();
		String sql = "select to_char(rentdate, 'YYYY-MM-DD') as rn, numseq, booknum, membernum, discount from rentlist order by rentdate";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Rent_Dto rdto = new Rent_Dto();
				rdto.setRentdate(rs.getString("rn"));
				rdto.setNumseq(rs.getInt("numseq"));
				rdto.setBooknum(rs.getInt("booknum"));
				rdto.setMembernum(rs.getInt("membernum"));
				rdto.setDiscount(rs.getInt("discount"));
				
				list.add(rdto);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		dbm.close(con, pstmt, rs);
		return list;
	}

	public int insertSql(Rent_Dto rdto) {
		int result = 0;
		con = dbm.getConnection();
		
		String sql = "insert into rentlist values(?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(rdto.getRentdate()));
			pstmt.setInt(2, rdto.getNumseq());
			pstmt.setInt(3, rdto.getBooknum());
			pstmt.setInt(4, rdto.getMembernum());
			pstmt.setInt(5, rdto.getDiscount());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		dbm.close(con, pstmt, rs);
		return result;
	}
		
}