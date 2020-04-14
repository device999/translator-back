package com.ling.translator.model;

public class StatisticLight {
	
	private int id;
	
	private String word;
	  
	private int corrects;
	  
	private int wrongs;
	
	private String wordType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCorrects() {
		return corrects;
	}

	public void setCorrects(int corrects) {
		this.corrects = corrects;
	}

	public int getWrongs() {
		return wrongs;
	}

	public void setWrongs(int wrongs) {
		this.wrongs = wrongs;
	}

	public String getWordType() {
		return wordType;
	}

	public void setWordType(String wordType) {
		this.wordType = wordType;
	}
		  
}
