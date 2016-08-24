package com.hist.pe.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hist.pe.dao.NoticeMapper;
import com.hist.pe.entity.Notice;
import com.hist.pe.entity.Page_1;

/**
 *  write by zhaoshuo
 */
@Controller
public class NoticeController {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	
	
	//分页之后的删除和修改效果添加后还要回到原来的页面
	@RequestMapping(value="list/{currentPage}")
	public String list(@PathVariable("currentPage") String currentPage, Map<String, Object> map){
		
		//List<Notice> noticeList = noticeMapper.findAll();
		//map.put("noticeList", noticeList);
		Page_1 page = new Page_1();
		System.out.println("当前的页数是----》"+currentPage);
		if(currentPage == null || "".equals(currentPage.trim())){
			
			currentPage = 1 + "";
		}
		
		Integer totalNumber = noticeMapper.getCount();
		page.setTotalNumber(totalNumber);
		page.setCurrentPage(Integer.valueOf(currentPage));
		
		List<Notice> noticeList = noticeMapper.getList(page);
		
		map.put("noticeList", noticeList);
		map.put("page", page);
		System.out.println("page-->对象"+page + "noticeList-->对象"+ noticeList);
		return "notice/list";
	}
	
	
	/**
	 * 登陆就可以看到公告
	 */
	@RequestMapping(value="list")
	public String login(@RequestParam(value="currentPage", required=false) String currentPage, Map<String, Object> map){
		
		//List<Notice> noticeList = noticeMapper.findAll();
		//map.put("noticeList", noticeList);
		Page_1 page = new Page_1();
		System.out.println("当前的页数是----》"+currentPage);
		if(currentPage == null || "".equals(currentPage.trim())){
			
			currentPage = 1 + "";
		}
		
		Integer totalNumber = noticeMapper.getCount();
		page.setTotalNumber(totalNumber);
		page.setCurrentPage(Integer.valueOf(currentPage));
		
		List<Notice> noticeList = noticeMapper.getList(page);
		
		map.put("noticeList", noticeList);
		map.put("page", page);
		System.out.println("page-->对象"+page + "noticeList-->对象"+ noticeList);
		return "notice/list";
	}
	
	/**
	 * 管理员删除公告
	 */
	@RequestMapping(value="notice_delete/{id}/{currentPage}")
	public String deleteNotice(@PathVariable("id") Integer id,
			@PathVariable("currentPage") Integer currentPage){
		System.out.println("当前的页数是----->"+currentPage);
		noticeMapper.delete(id);
		System.out.println("删除成功");
		return "redirect:list/"+currentPage;
	}
	
	/**
	 *添加的跳转 
	 */
	@RequestMapping(value="notice_input")
	public String inputNotice( Map<String, Object> map){
		map.put("notice", new Notice());
		System.out.println("我要跳转到添加界面了");
		return "notice/input";
	}
	
	
	
	/**
	 * 修改的跳转
	 */
	@RequestMapping(value="notice_input/{id}")
	public String inputNotice(@PathVariable("id") Integer id,Map<String, Object> map){
		System.out.println("id---->"+id);
		//当没有id的时候说明这是一次添加的请求，否则就是修改的请求
		Notice notice = null;
		if(id == null){
			notice = new Notice();
		}else{
			notice = noticeMapper.getById(id);
			System.out.println("得到了指定id的公告"+notice);
		}
		map.put("notice", notice);
		System.out.println("我要跳转到添加界面了");
		return "notice/input";
	}
	
	//  升级的 修改的跳转
	@RequestMapping(value="notice_input/{id}/{currentPage}")
	public String beforemodifyNotice(@PathVariable("id") Integer id, 
				@PathVariable("currentPage") String currentPage,  Map<String, Object> map){
		
		System.out.println("id---->"+id);
		
		Notice	notice = noticeMapper.getById(id);
		System.out.println("得到了指定id的公告"+notice);
		map.put("currentPage", currentPage);
		map.put("notice", notice);
		System.out.println("我要跳转到添加界面了");
		System.out.println("当前页面是=====>"+currentPage);
		return "notice/modify";
	}
	
	/**
	 * 管理员添加公告
	 */
	@RequestMapping(value="notice_add",method=RequestMethod.POST)
	public String addNotice(Notice notice){
		System.out.println("################");
		if(notice.getDate() == null){
			notice.setDate(new Timestamp(System.currentTimeMillis()));
		}
		noticeMapper.save(notice);
		System.out.println(notice);
		return "redirect:list";
	}
	
	/**
	 * 管理员更改
	 */
	@RequestMapping(value="notice_modify/{currentPage}")
	public String modifyNotice(@PathVariable("currentPage") String currentPage, Notice notice){
		System.out.println("更新之后的公告是--->"+notice);
		System.out.println("我要更新了");
		System.out.println("当前的页数是===>"+currentPage);
		noticeMapper.update(notice);
		return "redirect:/list/"+currentPage;
	}
	/**
	 * 每次访问都经过这个
	 */
	/*@ModelAttribute
	public void getEmployee(@RequestParam(value="id", required=false) Integer id,
			Map<String, Object> map){
		System.out.println("我执行了");
		if(id != null){
			Notice	notice = noticeMapper.getById(id);
			System.out.println("得到了指定id的公告"+notice);
			map.put("notice", notice);
		}
	}*/
	
	
	//================================
	/**
	 * 公告内容展示模块
	 */
	@RequestMapping(value="notice_show/{id}")
	public String noticeShow(@PathVariable("id") Integer id, Map<String, Object> map){
		
		Notice notice = noticeMapper.getById(id);
		System.out.println("要展示的id"+notice);
		map.put("notice", notice);
		return "notice/showContent";
	}
	
}
