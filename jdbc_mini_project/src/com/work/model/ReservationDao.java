package com.work.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 예약 관리 서비스 
 * @author Hyejin_Kim
 * @version ver.2.0
 * @since jdk1.8
 *
 */
public class ReservationDao {
	private FactoyDao factory = FactoyDao.getInstance();

	private static ReservationDao instance = new ReservationDao();
	
	private ReservationDao() {}
	
	public static ReservationDao getInstance() {
		return instance;
	}
	

	/**
	 * 회원 예약 조회
	 * @param memberId
	 * @return
	 */
	public Reservation selectOneR(String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			
			String sql = "SELECT * FROM reservation where MEMBER_ID =?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, memberId);
		
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				String reserveNo = rs.getString("reserve_no");
				String title = rs.getString("title");
				int rating = rs.getInt("rating");
				String seatNo = rs.getString("seat_no");
				
				Reservation dto = new Reservation(reserveNo, memberId, title, rating, seatNo);
				return dto;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류]");
			e.printStackTrace();
		} finally{ 
			factory.close(conn, stmt, rs);
		}
		
		return null;
	}
	
	/**
	 * 영화별 예약전체 조회
	 * @param title
	 * @return
	 */
	public ArrayList<Reservation> selectListByTitle(String title) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<Reservation> list = new ArrayList<Reservation>();
		
		try {
			conn = factory.getConnection();
			
			String sql = "select * from reservation where title=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String reserveNo = rs.getString("reserve_no"); 
				String memberId = rs.getString("member_id");  
				int rating = rs.getInt("rating");
				String seatNo = rs.getString("seat_no");
				
				Reservation dto = new Reservation(reserveNo, memberId, title, rating, seatNo);
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 영화별 예약전체 조회");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		
		return list;
	}
	
	/**
	 * 전체 예약 조회
	 * @return
	 */
	public ArrayList<Reservation> selectListR() {
		ArrayList<Reservation> list = new ArrayList<Reservation>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			
			String sql = "SELECT * FROM reservation";
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String reserveNo = rs.getString("reserve_no"); 
				String memberId = rs.getString("member_id");  
				String title = rs.getString("title");
				int rating = rs.getInt("rating");
				String seatNo = rs.getString("seat_no");
				
				Reservation dto = new Reservation(reserveNo, memberId, title, rating, seatNo);
				list.add(dto);
			} 
		} catch (SQLException e) {
			System.out.println("[오류] 전체 예약 조회");
			e.printStackTrace();
		} finally{
			factory.close(conn, stmt, rs);
		}
		return list;
	}
	
	
	/**
	 * 예약번호 중복조회
	 * @param reserveNo
	 * @return
	 */
	public boolean selectReserveNo(String reserveNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			
			String sql = "select 1 from reservation where reserve_no =?"; 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, reserveNo);
			
			rs = stmt.executeQuery();
			
			return rs.next();
			
		} catch (SQLException e) {
			System.out.println("[오류] 예약번호 중복조회");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return false;
	}	
	
	
	/**
	 * 예약 등록
	 * @param dto
	 * @return 1 성공, 0 실패
	 */
	public int insertReservation(Reservation dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
		
			String sql = "insert into reservation values(?,?,?,?,?)";
		
			stmt = conn.prepareStatement(sql);
		
			stmt.setString(1, dto.getReserveNo());
			stmt.setString(2, dto.getMemberId());
			stmt.setString(3, dto.getTitle());
			stmt.setInt(4, dto.getRating());
			stmt.setString(5, dto.getSeatNo());
		
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("[오류] 예약 등록");
			e.printStackTrace();
		} finally{
			factory.close(conn, stmt);
		}	
		return 0;
	}
	
	
	/**
	 * 예약 취소
	 * @param reserveNo
	 * @param memberId
	 * @return 1 성공, 0 실패
	 */
	public int deleteReservation(String reserveNo, String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			
			String sql = "DELETE reservation where RESERVE_NO =? AND MEMBER_ID =?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, reserveNo);
			stmt.setString(2, memberId);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("[오류] 예약 취소");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}

	
	
	
}
