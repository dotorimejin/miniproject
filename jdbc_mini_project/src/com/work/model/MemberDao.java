package com.work.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *<pre>
 * ## JDBC 프로그래밍 기본절차
	1. 드라이버 로딩 : 생성자에서 선행처리
	2. DB 서버연결 url, user, password => Connection
	3. SQL 통로개설 => Statement, PreparedStatement, CallableStatement
	    동적 SQL 수행 
	4. 통로이용 SQL 실행 요청
		-- C U D => 레코드 추가, 변경, 삭제 => 수행결과 적용된 레코드 수 반환
			int rows = stmt.executeUpdate(sql);
		
		-- R => 조회 => 결과 여러개(0~n) 반환
			ResultSet rs = stmt.executeQuery(sql);
	5. 실행결과처리
	6. 자원해제 
 *</pre>
 * @author Hyejin_Kim
 * @version ver.2.0
 * @since jdk1.8
 */
public class MemberDao {
	// JDBC Resource property
		private String driver = "oracle.jdbc.driver.OracleDriver";  
		private String url = "jdbc:oracle:thin:@localhost:1521:XE"; 
		private String user = "scott";
		private String password = "tiger";
	
	/**
	 * factory 객체 멤버변수 선언 및 할당
	 */
	private FactoyDao factory = FactoyDao.getInstance();
	
	private static MemberDao getInstance = new MemberDao();    // Singleton Pattern 3. 
	
	private MemberDao() {    // Singleton Pattern 1. 생성자 private 선언
		try {
			Class.forName(driver);
			System.out.println("[성공] 드라이버 로딩");
		} catch (ClassNotFoundException e) {
			System.out.println("[오류] 드라이버 로딩 오류");
			e.printStackTrace();
		}
	}
	
	public static MemberDao getInstance() {    // Singleton Pattern 2. 
		return getInstance;
	}
	
	
	/**
	 * 아이디 중복여부
	 * @param memberId
	 * @return
	 */
	public boolean selectMemberId(String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			
			String sql = "select 1 from member where member_ID =?"; 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, memberId);
			
			rs = stmt.executeQuery();
			
			return rs.next();
			
		} catch (SQLException e) {
			System.out.println("[오류] 아이디 중복조회");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return false;
	}

	/**
	 * 휴대폰 중복여부
	 * @param mobile
	 * @return
	 */
	public boolean selectMobile(String mobile) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			
			String sql = "select 1 from member where mobile =?"; 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, mobile);
			
			rs = stmt.executeQuery();
			
			return rs.next();
			
		} catch (SQLException e) {
			System.out.println("[오류] 휴대폰 중복조회");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return false;
	}	
	
	/**
	 * 이메일 중복여부 
	 * @param email
	 * @return
	 */
	public boolean selectEmail(String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			
			String sql = "select 1 from member where email =?"; 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, email);
			
			rs = stmt.executeQuery();
			
			return rs.next();
			
		} catch (SQLException e) {
			System.out.println("[오류] 휴대폰 중복조회");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return false;
	}	
	
	
	/**
	 * 회원가입 
	 * @return 성공 0, 실패 1
	 */
	public int insertMember(Member dto) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = factory.getConnection();
			
			String sql = "insert into member values(?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, dto.getMemberId());
			stmt.setString(2, dto.getMemberPw());
			stmt.setString(3, dto.getName());
			stmt.setString(4, dto.getBirth());
			stmt.setString(5, dto.getMobile());
			stmt.setString(6, dto.getEmail());
			stmt.setString(7, dto.getEntryDate());
			stmt.setString(8, dto.getGrade());
			stmt.setInt(9, dto.getMileage());
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("[오류] 회원가입");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		
		return 0;
		
	}
	
	/**
	 * 마일리지 변경 : 로그인시 500 마일리지 적립
	 * @param memberId
	 * @param mileage
	 * @return
	 */
	public boolean updateLoginMileage(String memberId, int mileage) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
		
			String sql = "update member set MILEAGE = MILEAGE + 500 where MEMBER_ID = ?";
		
			stmt = conn.prepareStatement(sql);
		
			stmt.setString(1, memberId);
		
			int rows = stmt.executeUpdate(sql);
		
			if(rows > 0) {
				return true; 
			}
		
		} catch (SQLException e) {
			System.out.println("[오류]");
			e.printStackTrace();
		} finally{
			factory.close(conn, stmt);
		}	
		return false;
	}
	
	// 로그인시 등업처리 : G => V 회원 조회 
	// 성공 0, 실패 1
	public int updateGradeV() {
		
		return 0; 
	}
	
	// 로그인시 등업처리 : V => VV 회원 조회 
	// 성공 0, 실패 1
	public int updateGradeVV() {
	
		return 0;
	}
	
	/* 
	 * 로그인
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 회원등급(grade), 미존재시 null
	 */
	public String login (String memberId, String memberPw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
		
			String sql = "select member_id, grade, mileage from member where MEMBER_ID =? and MEMBER_PW =?";
			stmt = conn.prepareStatement(sql);
		
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
		
			rs = stmt.executeQuery();

			if(rs.next()) {
				String memberid = rs.getString("member_id");
				String grade = rs.getString("grade");
				String mileage = rs.getString("mileage");
				return grade;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 로그인");
			e.printStackTrace();
		} finally{   // 6.
			factory.close(conn, stmt, rs);
		}
	
		return null;
	}

	
	/**
	 *<pre>
	 * 회원상세조회
	 *</pre>
	 * @param memberId
	 * @return
	 */
	public Member selectOne(String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			
			String sql = "SELECT * FROM member where MEMBER_ID =?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, memberId);
		
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				String memberPw = rs.getString("member_pw");
				String name = rs.getString("name");
				String birth = rs.getString("birth");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String entryDate = rs.getString("entry_date");
				String grade = rs.getString("grade");
				int mileage = rs.getInt("mileage");
				
				Member dto = new Member(memberId, memberPw, name, birth, mobile, email, entryDate, grade, mileage);
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
	 * 전체회원조회
	 * @return
	 */
	public ArrayList<Member> selectList() {
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			
			String sql = "SELECT * FROM member";
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery(sql);

			
			while(rs.next()) {
				String memberId = rs.getString("member_id");  
				String memberPw = rs.getString("member_pw");
				String name = rs.getString("name");
				String birth = rs.getString("birth");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String entryDate = rs.getString("entry_date");
				String grade = rs.getString("grade");
				int mileage = rs.getInt("mileage");
				
				Member dto = new Member(memberId, memberPw, name, birth, mobile, email, entryDate, grade, mileage);
				list.add(dto); 
			} 
		} catch (SQLException e) {
			System.out.println("[오류]");
			e.printStackTrace();
		} finally{
			factory.close(conn, stmt, rs);
		}
		
		return list;
	}

	
	/**
	 * 회원등급별 전체조회
	 * @param grade
	 * @return 회원
	 */
	public ArrayList<Member> selectListByGrade(String grade) {
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			
			String sql = "SELECT * FROM member";
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memberId = rs.getString("member_id");  
				String memberPw = rs.getString("member_pw");
				String name = rs.getString("name");
				String birth = rs.getString("birth");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String entryDate = rs.getString("entry_date");
				int mileage = rs.getInt("mileage");
				
				Member dto = new Member(memberId, memberPw, name, birth, mobile, email, entryDate, grade, mileage);
				list.add(dto); 
			} 
		} catch (SQLException e) {
			System.out.println("[오류]");
			e.printStackTrace();
		} finally{
			factory.close(conn, stmt, rs);
		}
		
		return list;
	}
	
	
	/**
	 * 아이디 찾기 : 이메일
	 * @param name
	 * @param email
	 * @return
	 */
	public String selectMemberIdByEmail(String name, String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			
			String sql = "select MEMBER_ID from member where NAME =? and EMAIL =?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, name);
			stmt.setString(2, email);
		
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				String memberId = rs.getString("member_id");  
				return memberId;
			}
		} catch (SQLException e) {
			System.out.println("[오류] 입력하신 회원정보를 확인해주세요.");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * 아이디 찾기 : 핸드폰
	 * @param name
	 * @param mobile
	 * @return
	 */
	public String selectMemberIdByMobile(String name, String mobile) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			
			String sql = "select MEMBER_ID from member where NAME = ? and MOBILE = ?";
			
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			stmt.setString(2, mobile);
		
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				String memberId = rs.getString("member_id");  
				return memberId;
			}
		} catch (SQLException e) {
			System.out.println("[오류] 입력하신 회원정보를 확인해주세요.");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	
	/**
	 * 비밀번호 찾기 (회원 존재유무 : 이메일)
	 * @param memberId
	 * @param name
	 * @param email
	 * @return 성공시 true, 실패시 false
	 */
	public boolean selectMemberPwByEmail(String memberId, String name, String email) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;	
			try {
				conn = factory.getConnection();
				
				String sql = "select member_pw from member where MEMBER_ID = ? and name = ? and email = ?";
				
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, memberId);
				stmt.setString(2, name);
				stmt.setString(3, email);
			
				rs = stmt.executeQuery(sql);
				
				if(rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("[오류] 회원 정보 확인");
				e.printStackTrace();
			} finally{
				factory.close(conn, stmt, rs);
			}	
		return false;
	}
	
	/**
	 * 비밀번호 찾기 (회원 존재유무 : 핸드폰)
	 * @param memberId
	 * @param name
	 * @param mobile
	 * @return 성공시 true, 실패시 false
	 */
	public boolean selectMemberPwByMobile(String memberId, String name, String mobile) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = factory.getConnection();
			
				String sql = "select member_pw from member where MEMBER_ID = ? and name = ? and mobile = ?";
			
				stmt = conn.prepareStatement(sql);
			
				stmt.setString(1, memberId);
				stmt.setString(2, name);
				stmt.setString(3, mobile);
		
				rs = stmt.executeQuery(sql);
			
				if(rs.next()) {
					return true;
				}			
			} catch (SQLException e) {
				System.out.println("[오류] 회원 정보 확인");
				e.printStackTrace();
			} finally{
				factory.close(conn, stmt, rs);
			}	
		return false;
	}
	
	/**
	 * 비밀번호(임시발급) 변경
	 * @param memberId
	 * @param name
	 * @param mobile
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateMemberPw(String memberId, String memberNewPw) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {	
				conn = factory.getConnection();
			
				String sql = "UPDATE MEMBER SET MEMBER_PW =? WHERE MEMBER_ID =? AND MEMBER_PW =?";
			
				stmt = conn.prepareStatement(sql);
			
				stmt.setString(1, memberNewPw);
				stmt.setString(2, memberId);
			
				// C,R,D 
				int rows = stmt.executeUpdate(sql);
			
				if(rows > 0) {
					return true; 
				}
			} catch (SQLException e) {
				System.out.println("[오류]");
				e.printStackTrace();
			} finally{
				factory.close(conn, stmt);
			}	
		return false;
	}
	
	/**
	 * 비밀번호 변경
	 * @param memberPw
	 * @return 
	 */
	public int updateMemberPw(String memberId, String memberPw, String modifyMemberPw) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				conn = factory.getConnection();
			
				String sql = "update member set MEMBER_PW =? where MEMBER_ID =?";
			
				stmt = conn.prepareStatement(sql);
			
				stmt.setString(1, modifyMemberPw);
				stmt.setString(2, memberId);
				stmt.setString(3, memberPw);
			
				int rows = stmt.executeUpdate(sql);
			
				return stmt.executeUpdate();
			
			} catch (SQLException e) {
				System.out.println("[오류]");
				e.printStackTrace();
			} finally{
				factory.close(conn, stmt);
			}	
		return 0;
	}
	
	
	/**
	 * 회원탈퇴
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	public int deletMember(String memberId, String memberPw) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				conn = factory.getConnection();
		
				String sql = "DELETE member where MEMBER_ID =? AND MEMBER_PW =?";
		
				stmt = conn.prepareStatement(sql);
		
				stmt.setString(1, memberId);
				stmt.setString(2, memberPw);
			
				int rows = stmt.executeUpdate(sql);
		
				return stmt.executeUpdate();
		
			} catch (SQLException e) {
				System.out.println("[오류] 회원탈퇴");
				e.printStackTrace();
			} finally{
				factory.close(conn, stmt);
			}	
		return 0;
	}




}
