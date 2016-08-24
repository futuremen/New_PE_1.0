
package com.hist.pe.utils;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.hist.pe.cache.CacheUtils;
import com.hist.pe.dao.MenuDao;
import com.hist.pe.dao.UserDao;
import com.hist.pe.entity.Menu;
import com.hist.pe.entity.User;
import com.hist.pe.security.SystemAuthorizingRealm.Principal;


/**
 * 
 * @author liuPeng
 * @version 
 */
@Service
public class UserUtils{

	
public static final String CACHE_MENU_LIST = "menuList";


	

	public static final String CACHE_ROLE_LIST = "roleList";
//	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ACCOUNT_ = "account";
	
	
	
	
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_MENU_LIST);
		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){
		
		CacheUtils.remove(USER_CACHE, USER_CACHE_ACCOUNT_ + user.getAccount());
		CacheUtils.remove(USER_CACHE, USER_CACHE_ACCOUNT_ + user);
		
		
	}
	
	/**
	 * 根据account获取用户
	 * @param account
	 * @return 取不到返回null
	 */
	public static User get(String account){
		
		
		
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_ACCOUNT_ + account);
		if (user ==  null){
			user = userDao.getUser(account);
			if (user == null){
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ACCOUNT_ + user, user);
		}
		return user;
	}
	
	/**
	 * 根据account获取user对象
	 * @param account
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String account){
		
		
		
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_ACCOUNT_ + account);
		
		if (user == null){
			System.out.println("utils的getByLoginName函数开始执行。。。。。。。。。。。。。。。。。");
			user = userDao.getUser(account);
		
			if (user == null){
				return null;
			}

				CacheUtils.put(USER_CACHE, USER_CACHE_ACCOUNT_ + user.getAccount(), user);

		
		}
		return user;
	}

	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {

		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}
	
	/**
	 * 获取当前用户的菜单，并且缓存，退出的时候清理缓存
	 * @return
	 */
	public static List<Menu> getMenuList(){
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			User user = getUser();
			String[] roleids = user.getRoleids();
			if (roleids[0].equals("3")){
				
				menuList = menuDao.findAllList(new Menu());
			}else{
				Menu m = new Menu();
				m.setRoleid(roleids[0]);
				menuList = menuDao.findByRoleId(m);
			}
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}
	
	/**
	 * 获取当前授权的Principal
	 * @return Principal
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}

		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	
	// 以下是用户信息缓存
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}

		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	/**
	 * 获取当前授权的对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前用户
	 * @return 取不到返回新的空对象
	 */
	
	public static User getUser(){
		Principal principal = getPrincipal();
		if (principal!=null){
			User user = get(principal.getAccount());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象
		return new User();
	}

	public static void putCache(String key, Object value) {

		getSession().setAttribute(key, value);
	}
	public static void removeCache(String key) {

		getSession().removeAttribute(key);
	
	}
	
	
	

	

	
}
