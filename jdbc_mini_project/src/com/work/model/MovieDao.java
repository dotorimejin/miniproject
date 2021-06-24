package com.work.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.util.Utility;

public class MovieDao {
	
	private FactoyDao factory = FactoyDao.getInstance();

	private static MovieDao instance = new MovieDao();
	
	private MovieDao() {}
	
	public static MovieDao getInstance() {
		return instance;
	}
	
	/**
	 * 상영 영화정보 조회
	 * @return
	 */
	public ArrayList<Movie> selectListM() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			
			String sql = "SELECT * FROM movie";
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String title = rs.getString("title");
				int rating = rs.getInt("rating");
				String director = rs.getString("director");
				String actor = rs.getString("actor");
				String seat = rs.getString("seat");
				
				Movie dto = new Movie(title, rating, director, actor, seat);
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
	 * 영화이름 중복조회
	 * @param reserveNo
	 * @return
	 */
	public boolean exsistTitle(String title) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			
			String sql = "select 1 from movie where title =?"; 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, title);
			
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
	 * 영화 등록
	 * @param dto
	 * @return 1 성공, 0 실패
	 */
	public int insertMovie(Movie dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
		
			String sql = "insert into movie values(?,?,?,?,?)";
		
			stmt = conn.prepareStatement(sql);
		
			stmt.setString(1, dto.getTitle());
			stmt.setInt(2, dto.getRating());
			stmt.setString(3, dto.getDirector());
			stmt.setString(4, dto.getActor());
			stmt.setString(5, dto.getSeat());
			
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
	 * 영화 삭제
	 * @param reserveNo
	 * @param memberId
	 * @return 1 성공, 0 실패
	 */
	public int deleteMovie(String title) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			
			String sql = "DELETE movie where title =?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, title);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("[오류] 영화 삭제");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
}
