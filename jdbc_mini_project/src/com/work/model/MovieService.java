package com.work.model;

import java.util.ArrayList;

public class MovieService {
	
	private MemberDao dao = MemberDao.getInstance(); 
	
	/**
	 * 상영 영화정보 조회
	 * @return
	 */
	public ArrayList<Movie> getReservationList() {
		return null;
	}
	
	/**
	 * 영화제목 중복조회
	 * @param reserveNo
	 * @return
	 */
	public boolean existTitle(String title) {
		return false;
	}
	
	/**
	 * 영화 등록
	 * @param dto
	 * @return
	 */
	private int addnull(Movie dto) {
		
		if (existTitle(dto.getTitle())) {
			System.out.println("[오류] 예약번호 중복");
			return 0;
		}
		return 0;
	}

	
	/**
	 *<pre>
	 * 영화 등록
	 - 관리자 입력 정보 : 영화제목, 상영등급, 감독, 배우
	 - 시스템 입력 정보 : 좌석(기본값)
	 * </pre>
	 * @param reserveNo
	 * @param memberId
	 * @param title
	 * @param rating
	 * @param seat
	 * @return
	 */
	public int addMovie(String title, int rating, String director, String actor, String seat) {
		Movie dto = new Movie(title, rating, director, actor, seat);
		return 0;
	}
	
	
	/**
	 * 영화 삭제
	 * @param reserveNo
	 * @param memberId
	 * @return
	 */
	public int removeMovie(String title) {
		return 0;
	}

	
	// 좌석 
	public static void seat() {
		String[][]seat = {{"□", "□", "□", "□", "□", "□", "□", "□", "□"}, 
						{"□", "□", "□", "□", "□", "□", "□", "□", "□"},
						{"□", "□", "□", "□", "□", "□", "□", "□", "□"}, 
						{"□", "□", "□", "□", "□", "□", "□", "□", "□"}, 
						{"□", "□", "□", "□", "□", "□", "□", "□", "□"}, 
						{"□", "□", "□", "□", "□", "□", "□", "□", "□"}};
		
		for(int i = 0; i < seat.length; i++) {
			for (int j = 0; j < seat[i].length; j++) {
			}
			System.out.print(seat);
		}
		System.out.println(seat);
	}

	
		
	
	
}
