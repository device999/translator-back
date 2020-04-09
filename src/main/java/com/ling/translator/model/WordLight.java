package com.ling.translator.model;

public class WordLight {
	
    private int id;
    private String word;
    private String correctAnswer;
    private String[] wrongAnswers;
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
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String[] getWrongAnswers() {
		return wrongAnswers;
	}
	public void setWrongAnswers(String[] wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}
    
    
}
