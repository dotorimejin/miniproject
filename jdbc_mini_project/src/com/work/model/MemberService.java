package com.work.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.work.util.Utility;

/**
 * 
 * @author Hyejin_Kim
 * @version ver.2.0
 * @since jdk1.8
 *
 */
public class MemberService {
	/** MemberDao 객체 생성 */
	private MemberDao dao = MemberDao.getInstance();   // Singleton Pattern 4. 반영
	
	/**
	 * 아이디 중복여부
	 * @param memberId
	 * @return
	 */
	public boolean existMemberId(String memberId) {
		return dao.selectMemberId(memberId);
	}
	
	/**
	 * 휴대폰 중복여부
	 * @param mobile
	 * @return
	 */
	public boolean existMobile(String mobile) {
		return dao.selectMobile(mobile);
	}
	
	/**
	 * 이메일 중복여부
	 * @param mobile
	 * @return
	 */
	public boolean existEmail(String email) {
		return dao.selectMobile(email);
	}
	
	/**
	 * 회원가입 
	 * 아이디 중복확인
	 * 핸드폰 중복확인
	 * 이메일 중복확인
	 * 모두 확인 되면 DB에 회원정보 저장
	 * @return
	 */
	public int addMember(Member dto) {
		if (existMemberId(dto.getMemberId())) {
			System.out.println("[오류] 사용할 수 없는 아이디입니다." + dto.getMemberId());
			return 0;
		}
		
		if (existMobile(dto.getMobile())) {
			System.out.println("[오류] 사용할 수 없는 휴대폰입니다." + dto.getMobile());
			return 0;
		}
		
		if (existEmail(dto.getEmail())) {
			System.out.println("[오류] 사용할 수 없는 이메일입니다." + dto.getEmail());
			return 0;
		}
		return dao.insertMember(dto);
	}
	
	/**
	 * <pre>
	 * 회원가입
	 * - 회원 입력 정보 : 아이디, 비밀번호, 이름, 생년월일, 휴대폰, 이메일
	 * - 시스템 입력 정보 : 가입일, 등급, 마일리지
	 * </pre>
	 * @param memberId
	 * @param memberPw
	 * @param name
	 * @param birth
	 * @param mobile
	 * @param email
	 * @return
	 */
	public int addMember(String memberId, String memberPw, String name, String birth, String mobile, String email) {
		Member dto = new Member(memberId, memberPw, name, birth, mobile, email);
		dto.setEntryDate(Utility.getCurrentDate());
		dto.setGrade("G");
		dto.setMileage(1000);
		return addMember(dto);
	}
	
	/**
	 * <pre>
	 * 로그인
	 * -- 로그인 성공시 : 회원 이름(name), 회원 등급(grade), 마일리지(Mileage)
	 * 		1000마일리지 자동 적립
	 * -- 회원 자동등업 성공시 : 회원 등급(grade)
	 * 		마일리지 100,000이상 200,000미만 vip회원
	 * 		마일리지 200,000이상 vvip회원
	 * </pre>
	 * @param memberId 아이디
	 * @param memberPw 비밀번호 
	 * @return 회원등급(grade), 미존재시 null  
	 */
	public HashMap<String, String> login(String memberId, String memberPw) {
		
		return null;
	}
	
	/**
	 * 내정보조회 / 회원상세조회
	 * @param memberId 아이디
	 * @return 회원객체, 미존재시 null
	 */
	public Member getMember(String memberId) {
		return dao.selectOne(memberId);
	}
	
	/**
	 * 전체회원 조회
	 * @return
	 */
	public ArrayList<Member> getMemberList() {
		return dao.selectList();
	}
	
	/**
	 * 등급별 전체회원 조회
	 * @param grade
	 * @return
	 */
	public ArrayList<Member> getMemberListByGrade(String grade) {
		return dao.selectListByGrade(grade);
	}
	
	/**
	 * 아이디 찾기 : 이메일
	 * @param name
	 * @param email
	 * @return
	 */
	public String findMemberIdByEmail(String name, String email) {
		return dao.selectMemberIdByEmail(name, email);
	}
	
	/**
	 * 아이디 찾기 : 핸드폰
	 * @param name
	 * @param mobile
	 * @return
	 */
	public String findMemberIdByMobile(String name, String mobile) {
		return dao.selectMemberIdByMobile(name, mobile);
	}
	
	/**
	 * 비밀번호 찾기 : 이메일
	 * @param memberId
	 * @param name
	 * @param email
	 * @return
	 */
	public String findMemberPwByEmail(String memberId, String name, String email) {
		if (dao.selectMemberPwByEmail(memberId, name, email)) {
			String tmpMemberPw = Utility.getSecureAlphabetString(9, true, true);
			if(dao.updateMemberPw(memberId, tmpMemberPw)) {
				return tmpMemberPw;
			}
		}
		
		return null;
	}
	
	/**
	 * 비밀번호 찾기 : 핸드폰
	 * @param memberId
	 * @param name
	 * @param mobile
	 * @return
	 */
	public String findMemberPwByMobile(String memberId, String name, String mobile) {
		if (dao.selectMemberPwByEmail(memberId, name, mobile)) {
			String tmpMemberPw = Utility.getSecureAlphabetString(9, true, true);
			if(dao.updateMemberPw(memberId, tmpMemberPw)) {
				return tmpMemberPw;
			}
		}
		return null; 
	}
	
	
	/**
	 * 비밀번호 변경
	 * @param memberId
	 * @param memberPw
	 * @param modifyMemberPw
	 * @return 성공 1, 실패 0
	 */
	public int setMemberPw(String memberId, String memberPw, String modifyMemberPw) {
		return dao.updateMemberPw(memberId, memberPw, modifyMemberPw);
	}
	
	
	/**
	 * 회원 탈퇴
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	public Member removeMember(String memberId, String memberPw) {
		return null;
	}
}
