
package com.hist.pe.entity;

import java.io.Serializable;

/**
 * 
 */
public class Menu extends BaseEntity<Menu> implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String href;
	private String icon;
	private String id;
	private String isShow;

	private String name;	
	private Menu parent; 
	private String parent_id; 	
	private String parent_ids; 	
	private String permission; //  	
	private String roleid; 	
	private Integer sort; 	
	private String target; 	
	public Menu(){
		super();
		this.sort = 30;
		this.isShow = "1";
	}
	public String getHref() {
		return href;
	}
	public String getIcon() {
		return icon;
	}


	public static String getRootId(){
		return "1";
	}

	public String getId() {
		return id;
	}

	public String getIsShow() {
		return isShow;
	}
	
	public String getName() {
		return name;
	}
	
	
	
	
	public Menu getParent() {
		return parent;
	}

	public String getParent_id() {
		return parent_id;
	}

	
	public String getParentId() {
		return parent_id;
	}

	public String getParentIds() {
		return parent_ids;
	}
	

	public String getPermission() {
		return permission;
	}

	public String getRoleid() {
		return roleid;
	}

	public Integer getSort() {
		return sort;
	}

	public String getTarget() {
		return target;
	}


	public void setHref(String href) {
		this.href = href;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public void setParentId(String parentId) {
		this.parent_id = parentId;
	}


	public void setParentIds(String parentIds) {
		this.parent_ids = parentIds;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}



	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}


	

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return name;
	}
}