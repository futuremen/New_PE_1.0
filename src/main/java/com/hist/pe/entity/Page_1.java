package com.hist.pe.entity;

/**
 * 分页使用 write by zhaoshuo
 */
public class Page_1 {
	/**
	 * 总条数
	 */
	private int totalNumber;
	/**
	 * 当前第几页
	 */
	private int currentPage;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 每页显示的条数
	 */
	private int pageNumber = 5;
	/**
	 * 数据库中需要的数据，从第几条开始取
	 */
	private int dbIndex;
	/**
	 * 一共取多少条数据
	 */
	private int dbNumber;

	/**
	 * 根据已知的总条数和每页显示数量来计算出其他的属性的值
	 */
	private void count() {
		// 计算总页数
		int totalPageTemp = this.totalNumber / this.pageNumber;
		int plus = (this.totalNumber % this.pageNumber == 0) ? 0 : 1;
		totalPageTemp = totalPageTemp + plus;

		// 当没有一条数据的时候，总页数设置为 1
		if (totalPageTemp <= 0) {
			totalPageTemp = 1;
		}
		this.totalPage = totalPageTemp;

		// 设置当前页
		// 当前页大于总页数的时候，当前页设置为总页数
		if (this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		}

		// 当前页小于 1的时候，就把当前页设置为第一页
		if (this.currentPage <= 1) {
			this.currentPage = 1;
		}

		// 计算数据库中需要的两个参数
		this.dbIndex = (this.currentPage - 1) * this.pageNumber;
		if (this.currentPage == this.totalPage) {
			this.dbNumber = this.totalNumber - this.dbIndex;
		} else {
			this.dbNumber = this.pageNumber;
		}

	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
		this.count();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.count();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
		this.count();
	}

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	public int getDbNumber() {
		return dbNumber;
	}

	public void setDbNumber(int dbNumber) {
		this.dbNumber = dbNumber;
	}

	@Override
	public String toString() {
		return "Page [totalNumber=" + totalNumber + ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", pageNumber=" + pageNumber + ", dbIndex=" + dbIndex + ", dbNumber=" + dbNumber + "]";
	}
	
}
