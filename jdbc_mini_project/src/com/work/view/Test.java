package com.work.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.work.model.Member;
import com.work.model.MemberDao;
import com.work.model.MemberService;
import com.work.util.Utility;


/**
 * <pre>
 * ## JDBC 관련 클래스 의존관계
		1. Test
		> MemberService#login(아이디, 비밀번호) : 등급
		> MemberDao#login(아이디, 비밀번호) : 등급
		
		2. 
		> MemberService#getMember(아이디) : 회원객체
		> MemberDao#selectOne(아이디) : 회원객체
 * <pre>
 * @author Hyejin_Kim
 * @version ver.2.0
 * @since jdk1.8 
 */
public class Test {

	public static void main(String[] args) {
		
		// MemberService 객체 생성
		MemberService service = new MemberService();
		
		
		print("로그인");
		HashMap<String, String> grade = service.login("user01", "password01");
		if (grade != null) {
			System.out.println("[로그인 성공] 등급 : " + grade);
		} else {
			System.out.println("[로그인 실패] 회원정보를 확인하시기 바랍니다.");
		}
		
		print("로그인");
		grade = service.login("user05", "password05");
		if (grade != null) {
			System.out.println("[로그인 성공] 등급 : " + grade);
		} else {
			System.out.println("[로그인 실패] 회원정보를 확인하시기 바랍니다.");
		}
		
		
		print("상세회원 조회 : user01");
		Member dto = service.getMember("user01");
		if (dto != null) {
			System.out.println(dto);
		} else {
			System.out.println("[조회 실패] 회원정보를 확인하시기 바랍니다.");
		}
		
		
		print("전체 회원조회");
		ArrayList<Member> list = service.getMemberList();
		for (Member member : list) {
			System.out.println(member);
		}
		
		
		print("회원등급별 전체조회 : 일반회원등급");
		list = service.getMemberListByGrade("G");
		for (Member member : list) {
			System.out.println(member);
		}
		
		print("회원등급별 전체조회 : VIP회원등급");
		list = service.getMemberListByGrade("V");
		for (Member member : list) {
			System.out.println(member);
		}
		
		print("회원등급별 전체조회 : VVIP회원등급");
		list = service.getMemberListByGrade("VV");
		for (Member member : list) {
			System.out.println(member);
		}
		
		print("회원등급별 전체조회 : 관리자등급");
		list = service.getMemberListByGrade("A");
		for (Member member : list) {
			System.out.println(member);
		}
		
		
		print("아이디 찾기 : 이메일");
		String id = service.findMemberIdByEmail("이순신", "user03@work.com");
		if(id != null) {
			System.out.println(id);
		} else {
			System.out.println("[회원조회 실패] 회원정보를 확인하시기 바랍니다.");
		}
		
		
		print("비밀번호 찾기 : user04");
		String pw = service.findMemberPwByEmail("user04", "김유신", "user04@work.com");
		if(id != null) {
			System.out.println("");
		} else {
			System.out.println();
		}
		print("비밀번호찾기 전 : user01");
		dto = service.getMember("user01");
		System.out.println(dto);
		String tmpMemberPw = service.findMemberPwByEmail(dto.getMemberId(), dto.getName(), dto.getEmail());
		print("비밀번호찾기(임시발급암호) 후: " + tmpMemberPw);
		dto = service.getMember("user01");
		System.out.println(dto);
		
		print("비밀번호 변경 전 조회 : user02");
		dto = service.getMember("user02");
		System.out.println(dto);
		service.setMemberPw(dto.getMemberId(), dto.getMemberPw(), "testtest");
		print("비밀번호 변경 후 조회 : user02");
		dto = service.getMember("user02");
		System.out.println(dto);
		
		print("좌석지정 Test");
		
		
		
		
	
		
		
		
	}		
	/** 
	 * 테스트시 테스트 항목을 출력하기 위한 메서드
	 * @param message 테스트 문자열
	 */
	public static void print(String message) {
		System.out.println("\n### " + message);
	}
	

		
}


