package com.hist.pe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.future.hist.serviceinter.DegreeService;
import com.future.hist.serviceinter.DepartmentService;
import com.future.hist.serviceinter.ExamineService;
import com.hist.pe.dao.ExamineMapper;
import com.hist.pe.dao.QuestionBankMapper;
import com.hist.pe.dao.QuestionMapper;
import com.hist.pe.dao.TypeMapper;
import com.hist.pe.entity.Degree;
import com.hist.pe.entity.Department;
import com.hist.pe.entity.ExamPage;
import com.hist.pe.entity.PageBean;
import com.hist.pe.entity.Question;
import com.hist.pe.entity.QuestionsBank;
import com.hist.pe.entity.Type;

@Controller
public class MakeTestController {
	@Autowired
	private QuestionBankMapper qbm;
	@Autowired
	private TypeMapper tm;
	@Autowired
	private QuestionMapper qm;
	@Autowired
	private ExamineMapper em;
	@Autowired
	private ExamineService es;
	@Autowired
	private DepartmentService ds;
	@Autowired
	private DegreeService dgs;
	private int pageSize=5;
	// 得到所有题型和题的科目
	@RequestMapping(value = "hand_make_test_ui")
	public String getAllQb(Map<String, Object> map) {
		List<QuestionsBank> qbs = qbm.getAllQB();
		List<Type> types = tm.getAlltype();
		
		List<Department> departments=ds.getAllDp();
		
		
		
	
		map.put("qbs", qbs);
		map.put("types", types);
		map.put("departments", departments);
		return "teacher/hand_make_test_ui";
	}
	
	//提交选中的试题并生成试卷
		@RequestMapping(value={"makeTest"} ,method=RequestMethod.POST)
		public String makeTest(@RequestParam("datas") String datas,HttpSession session,@RequestParam("total_score")int total_score){
			String[] arr=datas.split(",");
			List<String>  list=Arrays.asList(arr);
			List<Question>  questios=qm.getQuestionById(list);
			
			session.setAttribute("questions", questios);
			session.setAttribute("total_score",total_score);
			return "teacher/hand_make_test_last";
		}
		
		//完成试卷的添加
		@RequestMapping(value={"make_finish"},method=RequestMethod.POST)
		public String finishMake(ExamPage exampage,HttpSession session){
			Map<String,Object> map=new HashMap<String, Object>();
			Integer total_score =  (Integer) session.getAttribute("total_score");
			exampage.setTotal_score(total_score);
			
			em.addExamPage(exampage);
			List<Question>  questios=(List<Question>) session.getAttribute("questions");
			
			map.put("examPageId", exampage.getId());
			map.put("questios", questios);
			qm.updateQuestion(map);
			session.removeAttribute("questions");
			return "success";
		}
		
	//自动生成试卷
		@RequestMapping(value={"auto_make_test_ui"})
		public String autoMakeQuestion(Map<String, Object> map){
			List<Department> departments=ds.getAllDp();
		
			List<QuestionsBank> qbs = qbm.getAllQB();
			List<Degree> degrees=dgs.getAllDegree();
			map.put("qbs", qbs);
			map.put("departments", departments);
			map.put("dgs", degrees);
			return "teacher/auto_make_test_ui";
			
		}
	//完成自动生成试卷
		@RequestMapping(value={"uo_make_finish"},method=RequestMethod.POST)
		public String qutoMakefinish(ExamPage exam,Department dt,Degree dg){
			List<Question> easy=new ArrayList<Question>();
			List<Question> common=new ArrayList<Question>();
			List<Question> diffcult=new ArrayList<Question>();
			
			 Object[] object=new Object[]{easy,common,diffcult};
			 Map<String, Object> map= new HashMap<String, Object>();
			 exam.setDg(dg);
			 exam.setDepartment(dt);
			 List<Degree> degrees=dgs.getAllDegree();
			 map.put("department_id", dt.getDp_id());
			 map.put("subject", exam.getSubject());
			 for (int i = 0; i < degrees.size(); i++) {
				 map.put("degree_id", degrees.get(i).getD_id());  
				if (degrees.get(i).getD_degree().equals("简单")) {
					easy=qm.getQuestionBy(map);
					System.out.println("easy>>>"+easy.size());
				}
				if (degrees.get(i).getD_degree().equals("一般")) {
					common=qm.getQuestionBy(map);	
					System.out.println("common>>>"+common.size());
				}
				if (degrees.get(i).getD_degree().equals("较难")) {
					diffcult=qm.getQuestionBy(map);
					System.out.println("dif>>"+diffcult.size());
				}
			}
			
			 exam.uoMakeFinish(easy, common, diffcult);
			 em.addExamPage(exam);
			 System.out.println(exam.getId());
			 System.out.println(exam.questions.size());
			 map.put("examPageId", exam.getId());
			 map.put("questios", exam.questions);
			 qm.updateQuestion(map);
			 return "success";
		}
		
		
		//跳到删除试卷的页面
		@RequestMapping(value="delete_exam_ui/{currentPage}")
		public String deleteExampage(@PathVariable("currentPage") int currentPage,Map<String,Object> map){
			if (currentPage==0) {
				currentPage=1;
			}
			List<ExamPage> examPages=em.getAllExampages();
			map.put("examPages", examPages);
			int recordCount=examPages.size();
			PageBean page=new PageBean(currentPage, pageSize, recordCount, examPages);
			examPages=em.getExamPageByPage(page);
			page.setRecordList(examPages);
			map.put("page", page);
			return "teacher/delete_examPage_ui";
		}
		//删除试卷
		@RequestMapping(value={"delete_exam/{id}"})
		public String deleteExampage(@PathVariable ("id")int id){
			es.deleteExamPage(id);
			return "redirect:/delete_exam_ui/1";
		}
		
	
		
		
		
		
		
		
		@RequestMapping(value="make_test")
		public String make_test(){
			return "teacher/make_test";
		}
}
