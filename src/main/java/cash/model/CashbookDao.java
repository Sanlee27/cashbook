package cash.model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.ExpandVetoException;

import cash.vo.Cashbook;
import cash.vo.Member;

public class CashbookDao {
	
	// 태그별 수입지출내역 출력 메소드
	public List<Cashbook> selectCashbookListByTag(String memberId, String word, int beginRow, int rowPerPage){
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		String sql = "SELECT c.cashbook_no cashbookNo, c.member_id memberId, c.category, c.cashbook_date cashbookDate, c.price, c.memo, c.createdate, c.updatedate FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE c.member_id = ? AND h.word = ? ORDER BY c.cashbook_date DESC LIMIT ?,?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, word);
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				c.setCreatedate(rs.getString("createdate"));
				list.add(c);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return  list;
	}
	
	// 태그별 수입지출내역 개수 구하기(페이지)
	public int listByTagCnt(String memberId, String word) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE c.member_id = ? AND h.word = ?;";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, word);
			
			rs = stmt.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	// 월별 수입지출내역 출력 메소드
	public List<Cashbook> selectCashbookListByMonth (String memberId, int targetYear, int targetMonth){
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, cashbook_date cashbookDate, category, price FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? ORDER BY cashbook_date ASC";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);	
			stmt.setInt(3, targetMonth);	
			
			System.out.println(stmt + " : stmt");
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				list.add(c);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 날짜별 수입지출 상세내역 출력 메소드
	public List<Cashbook> selectCashbookOne(String memberId, int targetYear, int targetMonth, int targetDate) {
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, member_id memberId, category, cashbook_date cashbookDate, price, memo, createdate, updatedate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setInt(4, targetDate);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				c.setCreatedate(rs.getString("createdate"));
				c.setUpdatedate(rs.getString("updatedate"));
				list.add(c);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 내역 개별삭제 메소드
	public int removeOneByNo(int cashbookNo) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "DELETE FROM cashbook WHERE cashbook_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			
			row = stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	    return row;
	}
	
	// 반환값 cashbook_no 키값
	public int insertCashbook(Cashbook cashbook) {
		int cashbookNo = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO cashbook (member_id, category, cashbook_date, price, memo, createdate, updatedate) VALUES (?, ?, ?, ?, ?, NOW(), NOW())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashbook.getMemberId());
			stmt.setString(2, cashbook.getCategory());
			stmt.setString(3, cashbook.getCashbookDate());
			stmt.setInt(4, cashbook.getPrice());
			stmt.setString(5, cashbook.getMemo());
			
			int row = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return cashbookNo;
	}
	
	// 수입 / 지출 ?
	public int totalPrice(String memberId, String category, int targetYear, int targetMonth) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT SUM(price) FROM cashbook WHERE member_id = ? AND category = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, category);
			stmt.setInt(3, targetYear);
			stmt.setInt(4, targetMonth);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
}
