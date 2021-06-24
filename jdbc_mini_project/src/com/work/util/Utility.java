package com.work.util;

import java.text.DecimalFormat;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


/**
 * /**
 * <pre>
 * 회원 영화예매 웹사이트에 사용하는 공통 메서드를 위한 유틸리티
 * 모든 메서드는 객체생성없이 사용가능 Class Method 선언(static method)
 * </pre>
 * @author Hyejin_Kim
 * @version ver.2.0
 * @since jdk1.8
 */
public class Utility {
	
	
	/**
	 * 현재 날짜를 기본날짜형식 년도 4자리.월2자리.일2자리 형식의 문자열 변환 반환 메서드
	 * @return 기본 형식의 현재날짜 문자열
	 */
	public static String getCurrentDate() {
		return getCurrentDate("yyyy-MM-dd");
	}
	
	/**
	 * 아규먼트로 전달받은 날짜 형식의 현재날짜 변환 반환 메서드 
	 * @param pattern 날짜형식
	 * @return 날짜형식의 현재날짜 문자열
	 */
	public static String getCurrentDate(String pattern) {
		return getCurrentDate(pattern, Locale.KOREAN);
	}
	
	/**
	 * <pre>
	 * 아규먼트로 전달받은 날짜 형식, 로케일 형식의 현재날짜 변환 반환
	 * 로케일 : java.util.Locale => FIELD 참고
	 * java.util.SimpleDateFormat 생성자 중복정의 => public SimpleDateFormat(String, Locale)
	 * 
	 * </pre>
	 * @see java.util.Locale
	 * @see java.util.SimpleDateFormat
	 * @param pattern  날짜형식  및 시간 형식
	 * @param locale 로케일형식
	 * @return  날짜 형식, 로케일 형식의 현재날짜 문자열
	 */
	public static String getCurrentDate(String pattern, Locale locale) {
		return new SimpleDateFormat(pattern, locale).format(new Date());
	}
	
	
	/**
	 * 보안문자 지정길이 숫자 반환
	 * @param length 보안문자 길이
	 * @return 지정길이숫자 보안문자
	 */
	public static String getSecureNumberString(int length) {
		Random random = new Random((long)(Math.random() * System.nanoTime()));
		StringBuilder secureString = new StringBuilder();
		
		for (int index = 0; index < length; index++) {
			secureString.append(random.nextInt(10));
		}
		return secureString.toString();
	}

	
	/**
	 * 보안 영문 대문자 지정길이 반환
	 * @param length 보안문자 길이
	 * @return 지정길이숫자 영문대문자 보안문자 
	 */
	public static String getSecureAlphabetString(int length) {
		Random random = new Random((long)(Math.random() * System.nanoTime()));
		StringBuilder secureString = new StringBuilder();
		
		for (int index = 0; index < length; index++) {
			secureString.append((char)(random.nextInt(26) + 'A'));
		}
		return secureString.toString();
	}	
	
	/**
	 * 보안 영문 대/소문자 지정길이 반환
	 * @param length 보안문자 길이
	 * @param isUpper 영문 대소문자 여부   //
	 * @return 지정길이숫자 영문대/소문자 보안문자 
	 */
	public static String getSecureAlphabetString(int length, boolean isUpper) {
		Random random = new Random((long)(Math.random() * System.nanoTime()));
		StringBuilder secureString = new StringBuilder();
		
		for (int index = 0; index < length; index++) {
			if (isUpper) {
				secureString.append((char)(random.nextInt(26) + 'A'));
			} else {
				secureString.append((char)(random.nextInt(26) + 'a'));
			}
		}
		return secureString.toString();
	}	
	
	/**
	 * 보안 영문 대/소문자, 숫자 혼합 지정길이 반환
	 * @param length 보안문자 길이
	 * @param isUpper 영문 대/소문자 여부
	 * @param isMixed 영문 대/소문자, 숫자 혼합 여부, true 영문대/소문자 숫자 혼잡, false 영문 대/소문자
	 * @return
	 */
	public static String getSecureAlphabetString(int length, boolean isUpper, boolean isMixed) {
		if (isMixed) {
			Random random = new Random((long)(Math.random() * System.nanoTime()));
			StringBuilder secureString = new StringBuilder();
			
			for (int index = 0; index < length; index++) {
				if (random.nextBoolean()) {
					secureString.append(random.nextInt(10));
				} else {
					if (isUpper) {
						secureString.append((char)(random.nextInt(26) + 'A'));
					} else {
						secureString.append((char)(random.nextInt(26) + 'a'));
					}
				}
			}
			return secureString.toString();
			
		} else {
			return getSecureAlphabetString(length, isUpper);
		}
	}	
	
	/** 천단위마다 컴마표기 문자열 반환  
	 * @param string */
	public static DecimalFormat getCommaNumber(String string) {
		return new DecimalFormat("###,###");
	}
	
	
	
		
		
}
