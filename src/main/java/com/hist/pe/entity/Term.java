package com.hist.pe.entity;

import java.io.Serializable;
import java.util.Date;


public class Term implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date strat_date;
	
	private Date end_date;
	
	private String flag;
	
	private String id;
	
	private String mark;



	public Date getStrat_date() {
		return strat_date;
	}

	public void setStrat_date(Date strat_date) {
		this.strat_date = strat_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getFlag() {
		return flag;
	}

	public String getId() {
		return id;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setId(String id) {
		this.id = id;
	}

}
