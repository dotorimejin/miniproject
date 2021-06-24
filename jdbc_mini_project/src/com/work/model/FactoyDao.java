package com.work.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <pre>
 * 모든 DAO 클래스에서 사용
 * -- Connection 반환 
 * -- close() 자원해제를 담당하는 기능 분리설계
 * -- 모든 DAO 클래스에서 getConnection(), close(conn, stmt, rs) : 호출사용
 *   
 * -- Singleton pattern 구현
 * 1. private 생성자
 * 2. private static 클래스이름 instance = new 클래스이름();
 * 3. public static 클래스이름 getInstance() { return instance; }
 * </pre>
 * @author Hyejin_Kim
 * @version ver.2.0
 * @since jdk1.8
 *
 */
public class FactoyDao {
	// JDBC Resource property
	private String driver = "oracle.jdbc.driver.OracleDriver";   
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "scott"; 
	private String password = "tiger";
	
	private static FactoyDao instance = new FactoyDao(); 
	
	private FactoyDao() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 생성자
	 * @return
	 */
	public static FactoyDao getInstance() {  
		return instance;
	}
	
	/**
	 * DB 연결 Connection 반환 메서드
	 * @return Connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 자원해제 : SELECT(R) 수행에 대한 자원
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null) {
				conn.close();
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 자원해제 : INSERT, UPDATE, DELETE(C U D) 수행에 대한 자원
	 * @param conn
	 * @param stmt
	 */
	public void close (Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}

	
	
	
	
	
	
}
