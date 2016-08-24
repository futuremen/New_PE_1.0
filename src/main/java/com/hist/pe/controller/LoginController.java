package com.hist.pe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hist.pe.entity.Menu;
import com.hist.pe.entity.User;
import com.hist.pe.security.SystemAuthorizingRealm.Principal;
import com.hist.pe.utils.UserUtils;

@Controller
public class LoginController {

	@RequestMapping(value = "/sysLogin", method = RequestMethod.GET)
	public String login(ModelMap map) {

		Principal principal = UserUtils.getPrincipal();
		if (principal != null) {
			System.out.println(UserUtils.getUser());
			return "redirect:" + "index";
		} else {
			return "sysLogin";
		}

	}

	@RequestMapping(value = "/footing")
	public String footing() {
		return "footing";
	}
	
	
	@RequestMapping(value = "/heading")
	public String heading() {
		return "heading";
	}
	
	
	@RequestMapping(value = "/content_menu")
	public String content_menu() {
		return "content_menu";
	}
	
	@RequestMapping(value = "/loginOut")
	public String loginOut() {
		
		
		UserUtils.clearCache();
		
		UserUtils.getSubject().logout();
		
		return "redirect:" + "sysLogin";
	}

	
	@RequestMapping(value = "/sysLogin", method = RequestMethod.POST)
	public String slogin(ModelMap map) {
		return "sysLogin";
	}
	


	@RequestMapping(value = "/index")
	public String index(HttpSession httpSession) {

		List<Menu> list = UserUtils.getMenuList();
		List<Menu> parentMenu = new ArrayList<Menu>();
		List<Menu> sonMenu = new ArrayList<Menu>();

		for (Menu menu : list) {
			if (menu.getParentId().equals("1")) {
				System.out.println("father" + menu.getName());
				parentMenu.add(menu);
			} else {
				System.out.println("son" + menu.getName());
				sonMenu.add(menu);
			}
		}
		User user = UserUtils.getUser();
		httpSession.setAttribute("parentMenu", parentMenu);
		httpSession.setAttribute("sonMenu", sonMenu);
		httpSession.setAttribute("user", user);
		return "index";
	}

}
