
package com.hist.pe.dao;

import java.util.List;

import com.hist.pe.dao.base.Curd;
import com.hist.pe.entity.Menu;


public interface MenuDao extends Curd<Menu> {

	public List<Menu> findByParentIdsLike(Menu menu);

	public List<Menu> findByRoleId(Menu menu);
	
	public int updateParentIds(Menu menu);
	
	public int updateSort(Menu menu);

	public List<Menu> findAllList(Menu menu);
	
}
