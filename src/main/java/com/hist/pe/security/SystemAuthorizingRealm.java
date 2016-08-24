
package com.hist.pe.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hist.pe.entity.Menu;
import com.hist.pe.entity.User;
import com.hist.pe.service.SystemService;
import com.hist.pe.utils.Encodes;
import com.hist.pe.utils.SpringContextHolder;
import com.hist.pe.utils.UserUtils;

/**

 */
@Service
@Transactional
public class SystemAuthorizingRealm extends AuthorizingRealm {

	

	@Autowired
	private SystemService systemService;

	/**
	 * 认证回调函数
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {

		System.out.println("**********************开始验证*****************");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		/* 校验登录验证码
		 if (LoginController.isValidateCodeLogin(token.getUsername(), false,
		 false)){
		 Session session = UserUtils.getSession();
		 String code =
		 (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
		 if (token.getCaptcha() == null ||
		 !token.getCaptcha().toUpperCase().equals(code)){
		 throw new AuthenticationException("msg:验证码错误");
		 }
		 }*/
		
		User user = getSystemService().getUserByLoginName(token.getUsername());
		byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
		return new SimpleAuthenticationInfo(new Principal(user), user.getPassword().substring(16),
				ByteSource.Util.bytes(salt), getName());

	}
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		Principal principal = (Principal) getAvailablePrincipal(principals);

		System.out.println("权限检查的时候才会调用这个方法" + principal);

		User user = getSystemService().getUserByLoginName(principal.getAccount());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			List<Menu> list = UserUtils.getMenuList();
			for (Menu menu : list) {
				if (StringUtils.isNotBlank(menu.getPermission())) {
					// 添加基于Permission的权限
					for (String permission : StringUtils.split(menu.getPermission(), ",")) {
						info.addStringPermission(permission);
					}
				}
			}
			/* 添加用户角色信息
			 info.addRole(user.getRole().getName());*/
			return info;
		} else {
			return null;
		}
	}

	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}

	@Override
	protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermitted(permissions, info);
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals, Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}

	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermittedAll(permissions, info);
	}

	/**
	 * 授权验证方法
	 * 
	 * @param permission
	 */
	private void authorizationValidate(Permission permission) {
		// 预留接口
	}

	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(SystemService.HASH_ALGORITHM);
		matcher.setHashIterations(SystemService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	/**
	 * 获取系统对象
	 */
	public SystemService getSystemService() {
		if (systemService == null) {
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}

	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;

		private String id;
		private String account; 

		public Principal(User user) {
			
			String s = user.getAccount();
			this.account = user.getAccount();

		}

		public String getId() {
			return id;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSessionid() {
			try {
				return (String) UserUtils.getSession().getId();
			} catch (Exception e) {
				return "";
			}
		}

		@Override
		public String toString() {
			return id;
		}

	}

	

}
