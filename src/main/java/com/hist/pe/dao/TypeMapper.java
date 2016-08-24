package com.hist.pe.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hist.pe.entity.Type;

@Repository
public interface TypeMapper {
    //得到所有题型
	public List<Type> getAlltype();
	//查询是否已经拥有某种题型
	public Type findType(Integer typeId);
	//删除题型
	public void deleteType(Integer typeId);
	//更新题型
	public void updateType(Type type);
}
