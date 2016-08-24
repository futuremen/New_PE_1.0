/**
 */
package com.hist.pe.entity;

import java.io.Serializable;

/**
 * 角色Entity
 * 
 * @author liu
 * 
 */
public class Role extends BaseEntity<Role> implements Serializable  {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name; // 角色名称

	



	
	public Role() {

	
	}

	public Role(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}








}
