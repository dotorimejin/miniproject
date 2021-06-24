package com.work.model;

/**
 * <pre>
 * 영화예매 웹사이트 예약 도메인 클래스
 * 1. 예약번호
 * 2. 회원 아이디
 * 3. 영화제목
 * 4. 영화관람등급
 	-- 전체관람가 : 0
	-- 12세 이상 관람가(보호자 동반시 관람 가능) : 12 막지는 말고 보호자 동반해야 한다는 안내문 출력
	-- 15세 이상 관람가(보호자 동반시 관람 가능) : 15
	-- 청소년 관람불가(만 18세 이상) : 18
 * 5. 좌석
 * </pre>
 * @author Hyejin_Kim
 * @version ver.2.0
 * @since jdk1.8
 *
 */
public class Reservation {
	
	/** 예약번호 : 식별키(PK), 랜덤 발급 */
	private String reserveNo;
	
	/** 회원 아이디 : 참조키(FK) */
	private String memberId;
	
	/** 영화제목 : 필수 */
	private String title;
	
	/** 영화관람등급 : 필수 */
	private int rating;
	
	/** 좌석 : 필수, 좌석 인덱스 번호 */
	private String seatNo;

	
	
	/** 기본생성자 */
	public Reservation() {
		
	}
	
	
	/**
	 * 예약 등록데이터 초기화 생성자
	 * @param reserveNo
	 * @param title
	 * @param rating
	 * @param seatNo
	 */
	public Reservation(String reserveNo, String title, int rating, String seatNo) {
		this.memberId = reserveNo;
		this.title = title;
		this.rating = rating;
		this.seatNo = seatNo;
	}

	/**
	 * 예약 전체데이터 초기화 생성자
	 * @param reserveNo 예약번호
	 * @param memberId 아이디
	 * @param title 영화제목
	 * @param rating 영화 관람등급
	 * @param seatNo 좌석
	 */
	public Reservation(String reserveNo, String memberId, String title, int rating, String seatNo) {
		this.reserveNo = reserveNo;
		this.memberId = memberId;
		this.title = title;
		this.rating = rating;
		this.seatNo = seatNo;
	}


	/**
	 * @return the reserveNo
	 */
	public String getReserveNo() {
		return reserveNo;
	}


	/**
	 * @param reserveNo the reserveNo to set
	 */
	public void setReserveNo(String reserveNo) {
		this.reserveNo = reserveNo;
	}


	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}


	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}


	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}


	/**
	 * @return the seatNo
	 */
	public String getSeatNo() {
		return seatNo;
	}


	/**
	 * @param seatNo the seatNo to set
	 */
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reserveNo == null) ? 0 : reserveNo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (reserveNo == null) {
			if (other.reserveNo != null)
				return false;
		} else if (!reserveNo.equals(other.reserveNo))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(reserveNo);
		builder.append(", ");
		builder.append(memberId);
		builder.append(", ");
		builder.append(title);
		builder.append(", ");
		builder.append(rating);
		builder.append(", ");
		builder.append(seatNo);
		return builder.toString();
	}
	
	

	
	
	
}
