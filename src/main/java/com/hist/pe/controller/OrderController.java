package com.hist.pe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hist.pe.entity.Order;
import com.hist.pe.entity.Student;
import com.hist.pe.entity.User;
import com.hist.pe.service.OfficeService;
import com.hist.pe.service.SystemService;
import com.hist.pe.utils.StringUtils;
import com.hist.pe.utils.UserUtils;

@Controller
public class OrderController {

	@RequestMapping(value = "insertOrder")
	public String insertOder(Model map, Order order) {

		map.addAttribute("order", order);
		return "Order/insertOrder";

	}

	@RequestMapping(value = "insertOrder", method = RequestMethod.POST)
	public String insertOderPost(Model map, @Validated Order order, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<ObjectError> listErrors = bindingResult.getAllErrors();
			map.addAttribute("message", listErrors);
			return "Order/insertOrder";
		}

		String id = UserUtils.getUser().getTeacher().getId();
		order.setTeacher_id(id);
		OfficeService.insertOrder(order);
		return "redirect:managerOrder";

	}

	@RequestMapping(value = "managerOrder")
	public String managerOrder(Model modelmap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teacherid", UserUtils.getUser().getId());
		modelmap.addAttribute("teacher_id", map);
		

		return "Order/managerOrder";
	}

	@RequestMapping(value = "viewOrderStudent")
	public String viewOrder(Model map) {
		Student student = UserUtils.getUser().getStudent();
		map.addAttribute("student", student);
		return "Order/viewOrder";
	}

	@RequestMapping(value = "viewOrderTeacher")
	public String viewOrderStudent(String order_id, Model modelmap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_id", order_id);
		List<Student> lists = OfficeService.getStudentByOrder(map);
		modelmap.addAttribute("students", lists);
		return "Order/viewOrderTeacher";
	}

	@RequestMapping(value = "updateOrder", method = RequestMethod.POST)
	public String updateOrderPost(Order order) {
		OfficeService.updateOrder(order);
		System.out.println(order);
		return "Order/managerOrder";

	}

	@RequestMapping(value = "updateOrder")
	public String updateOrder(String order_id, Model map) {
		Student student = UserUtils.getUser().getStudent();
		Order order = OfficeService.getOrderItem(order_id);
		int flag = order.getFlag();

		if (student != null) {
//			Order tempOrder = OfficeService.getOrderItem(student.getOrder_id());
//			if (tempOrder == null) {
//				student.setOrder_id(null);
//			}

			if (student.getOrder_id() == null || student.getOrder_id().equals("") || student.getOrder_id().equals("0") ) {

				String confirm = order.getConfirm();
				int temp = Integer.parseInt(confirm);
				int count = Integer.parseInt(order.getCount());
				if (temp >= count) {
					order.setFlag(1);
				} else {
					temp = temp + 1;
					confirm = temp + "";

					order.setConfirm(confirm);
					student.setOrder_id(order_id);

				}
				OfficeService.updateStudentOrder(order,student);
				

			}
			return "redirect:viewOrderStudent";
		}
		map.addAttribute("order", order);
		return "Order/updateOrder";

	}

	@RequestMapping(value = "deleteOrder")
	public String deleteOrder(String order_id, RedirectAttributes attributes) {
		Map<String , Object> orderMap = new HashMap<String,Object>();
		orderMap.put("order_id",order_id);
		if(OfficeService.getStudentByOrder(orderMap).isEmpty()){
		OfficeService.deleteOrder(order_id);
		attributes.addFlashAttribute("success", "删除成功");
		}else{
		//	session.setAttribute("deleteWrong", "请确认预约下无人后在删除");
			attributes.addFlashAttribute("deleteWrong", "请确认预约下无人后在删除");
			
		}
		return "redirect:managerOrder";
	}

	@RequestMapping(value = "helloajax")
	public @ResponseBody Student helloajax(String id) {
		System.out.println("///////////////////");
		return OfficeService.getOneStudent(id);
	}

	@RequestMapping(value = "sys/user/modifyPwd")
	public String userModify() {
		return "Order/userModifyPwd";
	}

	@RequestMapping(value = "sys/user/modifyPwd", method = RequestMethod.POST)
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {

			if (SystemService.validatePassword(oldPassword, user.getPassword())) {
				SystemService.updatePasswordById(user.getId(), user.getAccount(), newPassword);
				model.addAttribute("message", "修改密码成功");
			} else {
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "Order/userModifyPwd";
	}
//	退约
	@RequestMapping("back")
	public String backOrder(String order_id){
		String student_id =  UserUtils.getUser().getId();
		UserUtils.getUser().getStudent().setOrder_id("0");
		OfficeService.backOut(order_id,student_id);
		return "redirect:viewOrderStudent";
	}

}
