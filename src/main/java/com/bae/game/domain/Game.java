package com.bae.game.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {

	@Id // Sets Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // sets Auto increment
	private Integer id;

	@Column(nullable = false)
	private String gameTitle;

	@Column(nullable = false)
	private String genre;

	@Column(nullable = false)
	private String publisher;

	@Column(nullable = false)
	private String platform;

	@Column(nullable = false)
	private Integer releaseYear;

	// Constructor methods
	public Game() {
		super();
	}

	public Game(Integer id, String gameTitle, String genre, String publisher, String platform, Integer releaseYear) {
		super();
		this.id = id;
		this.gameTitle = gameTitle;
		this.genre = genre;
		this.publisher = publisher;
		this.platform = platform;
		this.releaseYear = releaseYear;
	}

	// Getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	// toString method
	@Override
	public String toString() {
		return "Game [id=" + id + ", gameTitle=" + gameTitle + ", genre=" + genre + ", publisher=" + publisher
				+ ", platform=" + platform + ", releaseYear=" + releaseYear + "]";
	}

}
