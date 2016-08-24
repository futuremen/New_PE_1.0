package com.hist.pe.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hist.pe.entity.Notice;
import com.hist.pe.entity.Page_1;

@Repository
public interface NoticeMapper {
	
	/**
	 * 保存
	 */
	public void save(Notice notice);
	
	/**
	 * 修改
	 */
	public void update(Notice notice);
	/**
	 * 删除
	 */
	public void delete(Integer id);
	/**
	 * 查取所有公告
	 */
	public List<Notice> findAll();
	/**
	 * 通过id得到指定的公告
	 */
	public Notice getById(Integer id);
	/**
	 * 得到所有的记录数
	 */
	public Integer getCount();
	
	/**
	 * 分页
	 */
	public List<Notice> getList(Page_1 page);
	
}
