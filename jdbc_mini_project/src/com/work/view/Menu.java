package com.work.view;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.work.model.Member;
import com.work.model.MemberService;
import com.work.model.Reservation;
import com.work.model.ReservationService;

/**
 * 회원전용 영화예매 서비스 메뉴   
 * 
 * @author Hyejin Kim
 * @version ver.1.0
 * @since jdk1.8
 */
public class Menu {
	
	/** 회원관리 서비스 클래스 */
	public MemberService memberService = new MemberService();
	
	/** 영화 예매 서비스 클래스 */
	public ReservationService reservationService = new ReservationService();
	
		
	/** 
	 * 프로그램 종료 메뉴 
	 */
	public void exitMenu() {
		printTitle("프로그램 정상 종료");
		System.exit(0);
	}
	
	/** 
	 * 프로그램 메인 메뉴 
	 */
	public void mainMenu() {
		printTitle("회원 영화예매시스템 메인메뉴");
		
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 아이디찾기");
		System.out.println("4. 비밀번호찾기");
		System.out.println("0. 프로그램 종료");
		printLine();
		
		System.out.print("메뉴번호 입력 : ");
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1:
			loginMenu();
			break;
		case 2:
			addMemberMenu();
			break;
		case 3:
			getMemberIdMenu();
			break;
		case 4:
			getMemberPwMenu();
			break;
		case 0:
			exitMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}
		
	}
	
	/**
	 * 로그인 메뉴
	 */
	public void loginMenu() {
		printTitle("로그인 메뉴");
		
		System.out.print("아이디 : ");
		String memberId = inputString();

		System.out.print("비밀번호 : ");
		String memberPw = inputString();

		
		HashMap<String, String> loginMap = memberService.login(memberId, memberPw);
		print("[로그인성공] " + loginMap.get("name") + "님\t" + "/등급 : " + loginMap.get("grade") 
		+ "\t" + "/마일리지 : " + loginMap.get("mileage"));
		switch(loginMap.get("grade")) {
		case "G":
		case "V":
		case "VV":
			serviceMainMenu();
			break;
		case "A":
			adminMainMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}
	}	
	
	/** 
	 * 회원 가입 메뉴 
	 */
	public void addMemberMenu() {
		printTitle("회원 가입 메뉴");
		
		System.out.print("아이디 : ");
		String memberId = inputString();

		System.out.print("비밀번호 : ");
		String memberPw = inputString();

		System.out.print("이름 : ");
		String name = inputString();

		System.out.print("생년월일 : " + "\n* 입력형식 : YYYYMMDD");
		String birth = inputString();
		
		System.out.print("휴대폰 : ");
		String mobile = inputString();
		
		System.out.print("이메일 : ");
		String email = inputString();
		
	
		
		int dto  = memberService.addMember(memberId, memberPw, name, birth, mobile, email);
		if (dto > 0) {
			System.out.println(dto);
		} else {
			print("[오류] 입력하신 정보를 확인해주세요.");
		}
		
		print("[성공] " + name + "님 회원등록 완료되었습니다. 로그인 후 회원전용 서비스를 이용하시기 바랍니다.");	
		loginMenu();
		
			mainMenu();
	
	}
	
	/** 
	 * 아이디 조회 메뉴 
	 */
	public void getMemberIdMenu() {
		printTitle("아이디 찾기");
		
		System.out.print("1. 이메일로 비밀번호 찾기");
		System.out.print("2. 휴대폰으로 비밀번호 찾기");
		printLine();
		System.out.print("메뉴번호 입력 : ");
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1: 
			getMemberPwByEmailMenu();
			break;
		case 2:
			getMemberPwByMobileMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}
		
		System.out.print("이름 : ");
		String name = inputString();
		
		System.out.print("휴대폰 : ");
		String mobile = inputString();
		
		String dto = memberService.findMemberIdByMobile(name, mobile);
		System.out.print(dto);
		mainMenu();
	}
	
	public void getMemberIdByEmailMenu() {
		
	}
	
	public void getMemberIdByMobileMenu() {
		
	}
	
	public void getMemberPwMenu() {
		printTitle("비밀번호 찾기");
		
		System.out.print("1. 이메일로 비밀번호 찾기");
		System.out.print("2. 휴대폰으로 비밀번호 찾기");
		printLine();
		System.out.print("메뉴번호 입력 : ");
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1: 
			getMemberPwByEmailMenu();
			break;
		case 2:
			getMemberPwByMobileMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}
		
	}
	
	public void getMemberPwByEmailMenu() {
		System.out.print("아이디 : ");
		String memberId = inputString();
		
		System.out.print("이름 : ");
		String name = inputString();
		
		System.out.print("이메일 : ");
		String email = inputString();
		
		String dto = memberService.findMemberPwByEmail(memberId, name, email);
		System.out.print(dto);
	}
	
	public void getMemberPwByMobileMenu() {
		System.out.print("아이디 : ");
		String memberId = inputString();
		
		System.out.print("이름 : ");
		String name = inputString();
		
		System.out.print("휴대폰 : " + "\n* 형식 : '-'없이 입력");
		String mobile = inputString();
		
		String dto = memberService.findMemberPwByMobile(memberId, name, mobile);
		System.out.print(dto);
	}
	
	public void setMemberPwMenu() {
		printTitle("비밀번호 변경");
		
		print("아이디 : ");
		String memberId = inputString();
		
		print("기존 비밀번호 : ");
		String memberPw = inputString();
		
		print("새 비밀번호 : ");
		String modifyMemberPw = inputString();
		
		int dto = memberService.setMemberPw(memberId, memberPw, modifyMemberPw);
		if (dto > 0) {
			System.out.print(dto);
		} else {
			print("[오류] 입력하신 정보를 확인해주세요.");
		}
	}
	
	
	
	/**
	 * 회원 전용 서비스 메인메뉴
	 */
	public void serviceMainMenu() {
		printTitle("회원전용 서비스 메뉴");
		
		System.out.println();
		System.out.println("1. 내정보 조회");
		System.out.println("2. 비밀번호 변경");
		System.out.println("3. 예매 조회");
		System.out.println("4. 예매하기");
		System.out.println("8. 회원 탈퇴");
		System.out.println("9. 로그아웃");
		System.out.println("0. 프로그램 종료");
		printLine();
		System.out.print("메뉴번호 입력 : ");
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1:
			getMemberMenu();
			break;
		case 2:
			setMemberPwMenu();
			break;
		case 3:
			getReservationMenu();
			break;
		case 4:
			reservationMenu();
			break;
		case 8:
			removeMemberMenu();
			break;
		case 9:
			print("[로그아웃 성공] 서비스를 이용하시려면 로그인하시기 바랍니다.");
			mainMenu();
			break;
		case 0:
			exitMenu();
			break;
		default:
			System.out.println();
			print("메뉴번호 오류");
			break;
		}
	}
	
	/**
	 * 예약 조회 메뉴
	 */
	public void getReservationMenu() {
		printTitle("예약 조회");
	}
	
	/**
	 * 예약하기 메뉴
	 */
	public void reservationMenu() {
		printTitle("영화 예약");
		print("관람하실 영화의 번호를 입력해주세요. : ");
		int menuNo = inputNumber();
		
	}
	

	
	/**
	 * 관리자 서비스 메인메뉴
	 */
	public void adminMainMenu() {
		printTitle("관리자 서비스 메뉴");
		
		System.out.println("1. 내 정보 조회");
		System.out.println("2. 비밀번호 변경");
		System.out.println("3. 회원 조회");
		System.out.println("4. 예약 조회");
		System.out.println("5. 영화 관리");
		System.out.println("9. 로그아웃");
		System.out.println("0. 프로그램 종료");
		printLine();
		System.out.print("메뉴번호 입력 : ");
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1:
			getMemberMenu();
			break;
		case 2:
			setMemberPwMenu();
			break;
		case 3:
			getMemberListMenu();
			break;
		case 4:
			getAllMemberMenu();
			break;
		case 9:
			print("[로그아웃 성공] 서비스를 이용하시려면 로그인하시기 바랍니다.");
			mainMenu();
			break;
		case 0:
			exitMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}	
	}
	
	
	
	public void getMemberMenu() {
		printTitle("내 정보 조회");

		print("비밀번호 : ");
		String memberPw = inputString();
		
		System.out.println(memberService.getMemberList()); 
		print("[오류] 입력하신 정보를 확인해주세요.");
		
	}
	

	public void getAllMemberMenu() {
		printTitle("회원 조회");
		System.out.println("1. 전체회원 조회");
		System.out.println("2. 등급별 전체회원 조회");
		System.out.println("0. 이전 메뉴");
		printLine();
		System.out.print("메뉴번호 입력 : ");
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1:
			getMemberListMenu();
			break;
		case 2:
			getMemberListByGradeMenu();
			break;
		case 0:
			adminMainMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}	
		
	}
	
	public void getMemberListMenu() {
		printTitle("전체회원 조회");
		ArrayList<Member> list = memberService.getMemberList();
		System.out.println(list);
	}
	
	public void getMemberListByGradeMenu() {
		printTitle("등급별 전체회원 조회");
		
		print("등급 : ");
		String grade = inputString();
		
		ArrayList<Member> listgrade = memberService.getMemberListByGrade(grade);
		if (listgrade != null) {
			System.out.println(listgrade);
		} else {
			print("[오류] 입력하신 정보를 확인해주세요.");
		}
		
	}
	
	public void getAllReservationMenu() {
		printTitle("예약 조회");
		System.out.println("1. 전체예약 조회");
		System.out.println("2. 영화별예약 조회");
		System.out.println("0. 이전 메뉴");
		printLine();
		System.out.print("메뉴번호 입력 : ");
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1:
			getMemberListMenu();
			break;
		case 2:
			getMemberListByGradeMenu();
			break;
		case 0:
			adminMainMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}	
		
		
	}
	
	private void removeMemberMenu() {
		printTitle("회원 탈퇴");
		
	}
	
	/**
	 * 구분선 출력 : 기호 "="
	 */
	public void printLine() {
		System.out.println("==================================");
	}
	
	/**
	 * 제목 출력
	 * @param title 제목
	 */
	public void printTitle(String title) {
		System.out.println();
		System.out.println();
		printLine();
		System.out.println();
		System.out.println(title);
		System.out.println();
		printLine();
	}
	
	/**
	 * 문자열 입력 반환
	 * @return 입력 문자열
	 */
	public String inputString() {
		String data = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			data = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 숫자 입력 반환
	 * @return 입력 정수형 숫자
	 */
	public int inputNumber() {
		String data = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			data = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(data);
	}
	
	/**
	 * 메세지 출력
	 * @param message 메세지
	 */
	public void print(String message) {
		System.out.println(message);
	}
}
