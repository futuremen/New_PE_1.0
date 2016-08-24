package com.hist.pe.entity;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	//指定的或是页面参数
	private int currentPage;//当前页
	private int pageSize;//每页显示多少条
	private int top;
	//查询数据库
	private int recordCount;//总记录数
	private List recordList;//本页的数据列表 
	private List needlist=null; //所需要的数据列表
	//计算
	private int pageCount;//总页数
	private int beginPageIndex;//列表开始索引
	private int endPageIndex;//列表结束索引
	
	public PageBean(int currentPage, int pageSize, int recordCount,List recordList) {
			this.currentPage = currentPage;
			this.pageSize = pageSize;
			this.recordCount = recordCount;
			this.recordList=recordList;
			System.out.println(recordList);
			needlist=new ArrayList();
			// 计算总页码
			pageCount = (recordCount + pageSize - 1) / pageSize;
			//if()
			top=(currentPage-1)*pageSize;
			System.out.println(pageSize);
			// 计算 beginPageIndex 和 endPageIndex
			// >> 总页数不多于10页，则全部显示
			if (pageCount <= 10) {
				beginPageIndex = 1;
				endPageIndex = pageCount;
			}
			// >> 总页数多于10页，则显示当前页附近的共10个页码
			else {
				// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
				beginPageIndex = currentPage - 4;
				endPageIndex = currentPage + 5;
				// 当前面的页码不足4个时，则显示前10个页码
				if (beginPageIndex < 1) {
					beginPageIndex = 1;
					endPageIndex = 10;
				}
				// 当后面的页码不足5个时，则显示后10个页码
				if (endPageIndex > pageCount) {
					endPageIndex = pageCount;
					beginPageIndex = pageCount - 10 + 1;
				}
			}
			int y;
			if (recordList.size()-top<this.pageSize) {
				y=recordList.size();
				
			}else{
				y=(top+pageSize);
			}
			for (int i = top; i < y; i++) {
				needlist.add(recordList.get(i));
			}
			
		}

	
	
	public int getTop() {
		return top;
	}
	



	public void setTop(int top) {
		this.top = top;
	}
	



	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getRecordCount() {
		return recordCount;
	}
	
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	public List getRecordList() {
		return recordList;
	}
	
	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	
	public int getEndPageIndex() {
		return endPageIndex;
	}
	
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public List getNeedlist() {
		return needlist;
	}

	public void setNeedlist(List needlist) {
		this.needlist = needlist;
	}

	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", top=" + top + ", recordCount="
				+ recordCount + ", recordList=" + recordList + ", pageCount=" + pageCount + ", beginPageIndex="
				+ beginPageIndex + ", endPageIndex=" + endPageIndex + "]";
	}

}
