package com.ling.translator.model;

public class StatisticLight {
	
	private int id;
	
	private String german;
	
	private String russian;
	  
	private int corrects;
	  
	private int wrongs;
	
	private String wordType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGerman() {
		return german;
	}

	public void setGerman(String german) {
		this.german = german;
	}

	public String getRussian() {
		return russian;
	}

	public void setRussian(String russian) {
		this.russian = russian;
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
