package com.hist.pe.entity;

public abstract class BaseEntity<T> {
	
	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
