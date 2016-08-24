package com.hist.pe.entity;

import java.sql.Timestamp;

/**
 * Created by 赵硕 on 2015/12/5.
 */
public class Notice {

    private long id;
    private String title;//公告名称
    private String content;//公告内容
    private Timestamp date;//公告发布的时间
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", date=" + date + "]";
	}
    
}
