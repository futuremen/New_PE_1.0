package com.hist.pe.dao.base;

public interface Curd<T> {

	int insert(T entity);

	int update(T entity);

	int delete(String id);

	public T get(String id);

	public T get(T entity);

	

}
