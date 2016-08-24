package com.hist.pe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hist.pe.utils.UserUtils;

@Controller
public class UserInfoController {

	@RequestMapping(value = "viewUserInfo")
	public String viewUserInfo(ModelMap modelMap) {

		if (UserUtils.getUser().getStudent() != null) {
			modelMap.put("student", UserUtils.getUser().getStudent());
		} else {
			modelMap.put("teacher", UserUtils.getUser().getTeacher());
		}

		return "userInfo/userInfo";
	}

	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(ModelMap modelMap, String teacher_id) {

		return "userInfo/modifyPwd";

	}

	@RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
	public String modifyPwdPost(String oldPwd, String newPwd) {

		return "userInfo/modifyPwd";
	}

}
