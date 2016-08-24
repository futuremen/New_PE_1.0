package com.hist.pe.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WarpForScoreOnce implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<WarpForScore> rows = new ArrayList<WarpForScore>();

	public WarpForScoreOnce(String page, String total, String records, List<WarpForScore> rows) {
		
		this.page = page;
		this.total = total;
		this.records = records;
		this.rows=rows;
		
		
	}
	String page;

	public List<WarpForScore> getRows() {
		return rows;
	}
	public void setRows(List<WarpForScore> rows) {
		this.rows = rows;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getRecords() {
		return records;
	}
	public void setRecords(String records) {
		this.records = records;
	}

	String total;
	String records;
	

}
