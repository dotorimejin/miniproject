package com.work.model;

/**
 * <pre>
 * Movie 
 * 1. title 영화제목
 * 2. rating 상영등급
 * 3. director 감독 
 * 4. actor 배우
 * 5. seat 좌석
 * </pre>
 * @author Hyejin_Kim
 * @version ver.2.0
 * @since jdk1.8
 *
 */
public class Movie {
	
	/** 영화제목 */
	private String title;
	
	/** 상영등급 */
	private int rating;
	
	/** 감독 */
	private String director;
	
	/** 배우 */
	private String actor;
	
	/** 좌석 */
	private String seat;
	
	
	/**
	 * 기본생성자
	 */
	public Movie() {}

	
	/**
	 * 예약서비스 데이터 초기화 생성자
	 * @param title
	 * @param rating
	 * @param seat
	 */
	public Movie(String title, int rating, String seat) {
		super();
		this.title = title;
		this.rating = rating;
		this.seat = seat;
	}
	
	
	/**
	 * 관리자 관리데이터 초기화 생성자
	 * @param title
	 * @param rating
	 * @param director
	 * @param actor
	 */
	public Movie(String title, int rating, String director, String actor) {
		super();
		this.title = title;
		this.rating = rating;
		this.director = director;
		this.actor = actor;
	}


	/**
	 * 영화 전체데이터 초기화 생성자
	 * @param title
	 * @param rating
	 * @param director
	 * @param actor
	 * @param seat
	 */
	public Movie(String title, int rating, String director, String actor, String seat) {
		super();
		this.title = title;
		this.rating = rating;
		this.director = director;
		this.actor = actor;
		this.seat = seat;
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
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}


	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}


	/**
	 * @return the actor
	 */
	public String getActor() {
		return actor;
	}


	/**
	 * @param actor the actor to set
	 */
	public void setActor(String actor) {
		this.actor = actor;
	}


	/**
	 * @return the seat
	 */
	public String getSeat() {
		return seat;
	}


	/**
	 * @param seat the seat to set
	 */
	public void setSeat(String seat) {
		this.seat = seat;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Movie other = (Movie) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(title);
		builder.append(", ");
		builder.append(rating);
		builder.append(", ");
		builder.append(director);
		builder.append(", ");
		builder.append(actor);
		builder.append(", ");
		builder.append(seat);
		return builder.toString();
	}

	
	

}