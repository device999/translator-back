package com.ling.translator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Words {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String russian;
    private String german;
    private boolean isNoun;
    private boolean isVerb;
    private boolean isAdverb;
    private boolean isPronoun;
    private boolean isAdjective;
    private boolean isOther;
    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRussian() {
		return russian;
	}
	public void setRussian(String russian) {
		this.russian = russian;
	}
	public String getGerman() {
		return german;
	}
	public void setGerman(String german) {
		this.german = german;
	}
	public boolean isNoun() {
		return isNoun;
	}
	public void setNoun(boolean isNoun) {
		this.isNoun = isNoun;
	}
	public boolean isVerb() {
		return isVerb;
	}
	public void setVerb(boolean isVerb) {
		this.isVerb = isVerb;
	}
	public boolean isAdverb() {
		return isAdverb;
	}
	public void setAdverb(boolean isAdverb) {
		this.isAdverb = isAdverb;
	}
	public boolean isPronoun() {
		return isPronoun;
	}
	public void setPronoun(boolean isPronoun) {
		this.isPronoun = isPronoun;
	}
	public boolean isAdjective() {
		return isAdjective;
	}
	public void setAdjective(boolean isAdjective) {
		this.isAdjective = isAdjective;
	}
	public boolean isOther() {
		return isOther;
	}
	public void setOther(boolean isOther) {
		this.isOther = isOther;
	}
    
    

}
