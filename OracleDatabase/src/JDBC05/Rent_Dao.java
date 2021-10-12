package JDBC05;

import java.sql.Connection;
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
		String sql = "select to_char(rentdate, 'YYYY-MM-DD') as rn, numseq, booknum, membernum, discount from rentlist order by numseq";
		
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
		
		String sql = "insert into rentlist(rentdate, numseq, booknum, membernum, discount)"
				+ "values(to_date(''||?||'', 'yyyy-MM-dd'), rent_seq.nextVal, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rdto.getRentdate());
			pstmt.setInt(2, rdto.getBooknum());
			pstmt.setInt(3, rdto.getMembernum());
			pstmt.setInt(4, rdto.getDiscount());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		dbm.close(con, pstmt, rs);
		return result;
	}

	public Rent_Dto getDto(String num) {
		Rent_Dto rdto = new Rent_Dto();
		con = dbm.getConnection();
		
		String sql = "select to_char(rentdate, 'YYYY-MM-DD') as rn, numseq, booknum, membernum, discount "
				+ "from rentlist where numseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rdto.setRentdate(rs.getString("rn"));
				rdto.setNumseq(rs.getInt("numseq"));
				rdto.setBooknum(rs.getInt("booknum"));
				rdto.setMembernum(rs.getInt("membernum"));
				rdto.setDiscount(rs.getInt("discount"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		dbm.close(con, pstmt, rs);
		return rdto;
	}

	public int updateSql(Rent_Dto new_Rdto) {
		int result = 0;
		con = dbm.getConnection();
		
		String sql = "update rentlist set rentdate = to_date(''||?||'', 'yyyy-MM-dd'), numseq = ?,"
				+ " booknum = ?, membernum = ?,  discount = ? where numseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, new_Rdto.getRentdate());
			pstmt.setInt(2, new_Rdto.getNumseq());
			pstmt.setInt(3, new_Rdto.getBooknum());
			pstmt.setInt(4, new_Rdto.getMembernum());
			pstmt.setInt(5, new_Rdto.getDiscount());
			pstmt.setInt(6, new_Rdto.getNumseq());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		dbm.close(con, pstmt, rs);
		return result;
	}

	public int deleteSql(String numseq) {
		int result = 0;
		con = dbm.getConnection();
		
		String sql = "delete from rentlist where numseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(numseq));
			result = pstmt.executeUpdate();
			
			sql = "drop sequence rent_seq";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "select max(numseq) as numseq from rentlist";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sql = "create sequence rent_seq start with " + (rs.getInt("numseq")+1) + 
						"increment by 1";
				pstmt = con.prepareStatement(sql);
				pstmt.execute();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		dbm.close(con, pstmt, rs);
		return result;
	}
		
}