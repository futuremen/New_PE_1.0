
package com.hist.pe.dao;

import com.hist.pe.dao.base.Curd;
import com.hist.pe.entity.User;

/**
 * 用户DAO接口
 */

public interface UserDao extends Curd<User> {

	User getUser(String account);


}
