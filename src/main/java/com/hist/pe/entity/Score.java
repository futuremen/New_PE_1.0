package com.hist.pe.entity;

import java.io.Serializable;

public class Score extends BaseEntity<Score> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	private String lung;
	private String dash;
	private String sitreach;
	private String standingleap;
	private String pullups_situps;
	private String endurance;
	private String height;
	private String weight;
	private String flag;   //标识  更新  是否插入  
	private String onlineScore;	
	private String sunScore;
	private String term_id;
	private String student_id;
	
	public String getStandingleap() {
		return standingleap;
	}
	public void setStandingleap(String standingleap) {
		this.standingleap = standingleap;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLung() {
		return lung;
	}
	public void setLung(String lung) {
		this.lung = lung;
	}
	public String getDash() {
		return dash;
	}
	public void setDash(String dash) {
		this.dash = dash;
	}
	public String getSitreach() {
		return sitreach;
	}
	public void setSitreach(String sitreach) {
		this.sitreach = sitreach;
	}
	public String getPullups_situps() {
		return pullups_situps;
	}
	public void setPullups_situps(String pullups_situps) {
		this.pullups_situps = pullups_situps;
	}
	public String getEndurance() {
		return endurance;
	}
	public void setEndurance(String endurance) {
		this.endurance = endurance;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getOnlineScore() {
		return onlineScore;
	}
	public void setOnlineScore(String onlineScore) {
		this.onlineScore = onlineScore;
	}
	public String getSunScore() {
		return sunScore;
	}
	public void setSunScore(String sunScore) {
		this.sunScore = sunScore;
	}
	public String getTerm_id() {
		return term_id;
	}
	public void setTerm_id(String term_id) {
		this.term_id = term_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	
}
