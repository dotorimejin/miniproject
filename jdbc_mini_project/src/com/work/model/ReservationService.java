package com.work.model;

import java.util.ArrayList;

import com.work.util.Utility;

/**
 * 
 * @author Hyejin_Kim
 * @version ver.2.0
 * @since jdk1.8
 *
 */
public class ReservationService {

	private ReservationDao dao = ReservationDao.getInstance();
	
	
	/**
	 * 회원 예약 조회
	 * @param memberId
	 * @return
	 */
	public Reservation getReservation(String memberId) {
		return dao.selectOneR(memberId);
	}
	
	/**
	 * 전체 예약 조회
	 * @return
	 */
	public ArrayList<Reservation> getReservationList() {
		return dao.selectListR();
	}
	
	/**
	 * 예약번호 중복조회
	 * @param reserveNo
	 * @return
	 */
	public boolean existReserveNo(String reserveNo) {
		return dao.selectReserveNo(reserveNo);
	}
	
	/**
	 * 예약 등록
	 * @param dto
	 * @return
	 */
	private int addReservation(Reservation dto) {
		
		if (existReserveNo(dto.getReserveNo())) {
			System.out.println("[오류] 예약번호 중복");
			return 0;
		}
		return dao.insertReservation(dto);
	}

	
	/**
	 *<pre>
	 * 예약 등록
	 - Member 정보 : 아이디
	 - Movie 정보 : 영화제목, 상영등급, 좌석
	 - 시스템 입력 정보 : 예약번호
	 * </pre>
	 * @param reserveNo
	 * @param memberId
	 * @param title
	 * @param rating
	 * @param seatNo
	 * @return
	 */
	public int addReservation(String memberId, String title, int rating, String seatNo) {
		Reservation dto = new Reservation(memberId, title, rating, seatNo);
		dto.setReserveNo(Utility.getSecureAlphabetString(6));
		return addReservation(dto);
	}
	
	
	/**
	 * 예약 취소
	 * @param reserveNo
	 * @param memberId
	 * @return
	 */
	public int removeReservation(String reserveNo, String memberId) {
		return dao.deleteReservation(reserveNo, memberId);
	}

	


}
