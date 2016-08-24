package com.hist.pe.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hist.pe.dao.DegreeMapper;
import com.hist.pe.dao.DepartmentMapper;
import com.hist.pe.dao.QuestionBankMapper;
import com.hist.pe.dao.QuestionMapper;
import com.hist.pe.dao.TypeMapper;
import com.hist.pe.entity.Degree;
import com.hist.pe.entity.Department;
import com.hist.pe.entity.FillQuestion;

import com.hist.pe.entity.JudgeQuestion;
import com.hist.pe.entity.Page_1;
import com.hist.pe.entity.Question;
import com.hist.pe.entity.QuestionsBank;
import com.hist.pe.entity.SelectQuestion;
import com.hist.pe.entity.Type;
import com.hist.pe.service.OfficeService;
import com.hist.pe.utils.ExcelUtils;

import jxl.read.biff.BiffException;

// write by zhaoshuo
//试题的控制器
@Controller
public class QuestionController {
	
	@Autowired
	private QuestionBankMapper questionBankMapper;
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private DegreeMapper degreeMapper;
	//=======================================
	//试题
	//=======================================
	@RequestMapping(value={"/question_control"})
	public String questionControl(){
		
		return "question/question";
	}
	

	//=======================================
	//科目
	//=======================================
	@RequestMapping(value="question_subject_manage")
	public String questionSubjectManage(Map<String, Object> map){
		
		List<QuestionsBank> questionBankList = questionBankMapper.getAllQB();
		map.put("questionBankList", questionBankList);
		System.out.println(questionBankList);
		return "question/question_subject_manage";
	}
	
	//添加科目  输入科目
	@RequestMapping(value="input_Subject")
	public String inputSubject(Map<String, Object> map){
		map.put("questionBank", new QuestionsBank());
		return "question/add_subject_input";
	}
	
	//执行添加
	@RequestMapping(value="add_subject")
	public String addSubject(QuestionsBank questionsBank,Map<String, Object> map){
		
		System.out.println("映射过来的"+questionsBank);
		QuestionsBank bank = questionBankMapper.findBank(questionsBank.getName());
		System.out.println("找到了没有"+bank);
		if(bank == null){
			questionBankMapper.addBank(questionsBank);
			return "redirect:/question_subject_manage";
		}else{
			map.put("message", "此科目已经添加过，不能重复添加");
			map.put("questionBank", new QuestionsBank());
			return "question/add_subject_input";
		}
	}
	
	//删除科目
	@RequestMapping(value="delete_subject/{bankId}")
	public String deleteSubject(@PathVariable("bankId") Integer bankId, HttpSession session){
		
			Integer questions = questionMapper.findByQuestionBank(bankId);
			if(questions <= 0){
				System.out.println("没有这个类型的试题，可以删除");
				questionBankMapper.deleteSubject(bankId);
				session.setAttribute("subject_message", "删除成功");
			}else{
				System.out.println("有这个类型的试题，不能删除");
				session.setAttribute("subject_message", "还有这个类型的试题，不能删除，请先删除试题在进行删除类型");
			}
		return "redirect:question_subject_manage";
	}
	
	//科目的修改
	@RequestMapping(value="modify_subject/{bankId}")
	public String modifySubject(@PathVariable(value="bankId") Integer bankId, Map<String, Object> map){
		
		QuestionsBank questionsBank = questionBankMapper.findById(bankId);
		map.put("questionsBank", questionsBank);
		return "question/question_subject_modify";
	}
	//执行修改
	@RequestMapping(value="subject_modify_success")
	public String modifySubjectSuccess(QuestionsBank questionsBank){
		
		System.out.println("就要更新这个了===============>"+questionsBank);
		questionBankMapper.updateBank(questionsBank);
		return "redirect:question_subject_manage";
	}
	
	
	//=======================================
	//题型
	//=======================================
	//列表
	@RequestMapping(value="question_type_manage")
	public String questionTypeManage(Map<String, Object> map){
		
		List<Type> typeList = typeMapper.getAlltype();
		map.put("typeList", typeList);
		System.out.println(typeList);
		return "question/question_type_manage";
	}
	
	//题型删除
	@RequestMapping(value="question_type_delete/{typeId}")
	public String questionTypeDelete(@PathVariable("typeId") Integer typeId, Map<String, Object> map, HttpSession session){
		
		Integer questions = questionMapper.findByQuestionType(typeId);
		if(questions == 0){
			typeMapper.deleteType(typeId);
			session.setAttribute("question_type_message", "没有这种类型的试题了，删除成功");
		}else{
			session.setAttribute("question_type_message", "未能删除成功，还存在这种类型的试题，请先删除此类型的试题后，在进行删除类型");
		}
		return "redirect:question_type_manage";
	}
	//题型修改的输入
	@RequestMapping(value="question_type_modify/{typeId}")
	public String questionTypeModifyInput(@PathVariable("typeId") Integer typeId, Map<String, Object> map){
		
		Type type = typeMapper.findType(typeId);
		map.put("type", type);
		System.out.println("题型的修改*****************");
		return "question/type_modify_input";
	}
	
	//题型修改的实现
	@RequestMapping(value="question_type_modify_success")
	public String questionTypeModifyChange(Type type, HttpSession session){
		
		System.out.println("即将要修改的Type是===========>"+type);
		typeMapper.updateType(type);
		return "redirect:question_type_manage";
	}
	//=======================================
	//系别
	//=======================================
	//系别列表
	@RequestMapping(value="department_list_all")
	public String departmentList(Map<String, Object> map){
		List<Department> departmentList = departmentMapper.getAllDp();
		map.put("departmentList", departmentList);
		return "department/department_list";
	}
	
	//系别的删除
	@RequestMapping(value="department_delete/{departmentId}")
	public String deleteDepartment(@PathVariable("departmentId") Integer departmentId, HttpSession session){
		
		Integer questions = questionMapper.findByDepartment(departmentId);
		if(questions <= 0){
			departmentMapper.deleteDepartment(departmentId);
			session.setAttribute("question_department_message", "删除成功");
		}else{
			session.setAttribute("question_department_message", "还存在这个系别的试题，未能删除成功！！");
		}
		
		return "redirect:department_list_all";
	}
	
	//修改系别  跳转到修改的界面
	@RequestMapping(value="department_modify_input/{departmentId}")
	public String updateDepartmentInput(@PathVariable("departmentId") Integer departmentId, Map<String, Object> map, HttpSession session){
		
		Integer questions = questionMapper.findByDepartment(departmentId);
		if(questions <= 0){
			Department department = departmentMapper.findById(departmentId);
			map.put("department", department);
			return "department/department_input";
		}else{
			session.setAttribute("question_department_message", "还存在这个系别的试题，未能修改成功");
			return "redirect:department_list_all";
		}
	}
	@RequestMapping(value="department_modify_success")
	public String updateDepartmentModify(Department department){
		
		departmentMapper.updateDepartment(department);
		return "redirect:department_list_all";
	}
	//系别的添加
	@RequestMapping(value="department_add_input")
	public String addDepartment(Map<String, Object> map){
		
		map.put("department", new Department());
		return "departmentdepartment_input";
	}
	
	//系别的添加成功
	@RequestMapping(value="department_add_success")
	public String addDepartmentSuccess(Department department, HttpSession session){
		
		Department department2 = departmentMapper.findByName(department.getDp_name());
		if(department2 == null){//可以添加，原来不存在
			System.out.println(department);
			departmentMapper.addDepartment(department);
			session.setAttribute("question_department_message", "添加成功");
		}else{//已经存在了
			session.setAttribute("question_department_message", "这个系别已经存在，不能重复添加");
		}
		System.out.println(department);
		return "redirect:department_list_all";
	}
	//=======================================
	//问题的添加
	//=======================================
	//手动导入
	@RequestMapping(value="question_add_hand")
	public String questionAddHandManage(Map<String, Object> map){
		List<Department> departmentList = departmentMapper.getAllDp();
		
		
		
		List<QuestionsBank> questionBankList = questionBankMapper.getAllQB();
		map.put("departmentList", departmentList);

		map.put("questionBankList", questionBankList);
		return "question/questionAdd/question_add_hand_manage";
	}
	//手动导入单选题的跳转
	@RequestMapping(value="question_add_hand_selectOne/{departmentId}")
	public String addQuestionSelectOneInput(@PathVariable("departmentId") Integer departmentId, Map<String, Object> map){
		
		System.out.println("接受的系别是=======》"+departmentId);
		SelectQuestion sQuestion = new SelectQuestion();
		sQuestion.setDepartment(departmentMapper.findById(departmentId));
		sQuestion.setType(typeMapper.findType(1));
		map.put("selectOneQuestion", sQuestion);
		List<QuestionsBank> questionBankList = questionBankMapper.getAllQB();
		List<Degree> degreeList = degreeMapper.getAllDegree();
		map.put("questionBankList", questionBankList);
		map.put("degreeList", degreeList);
		
		System.out.println("题库====》"+questionBankList+"试题的难度种类"+degreeList);
		return "question/questionAdd/question_add_hand_select_input";
	}
	//手动导入单选题的接受
	@RequestMapping(value="selectOneQuestionAddSuccess")
	public String addQuestionSelectOneSuccess(SelectQuestion sQuestion){
		
		System.out.println("接受到的单选题目是====》"+sQuestion);
		sQuestion.setIssue(sQuestion.getSelectOne()+"曱"+sQuestion.getSelectTwo()+"曱"+sQuestion.getSelectThree()+"曱"+sQuestion.getSelectFour());
		questionMapper.addSelectOneQuestion(sQuestion);
		return "redirect:question_add_hand";
	}
	
	//手动导入多选题的跳转
	@RequestMapping(value="question_add_hand_selectMany/{departmentId}")
	public String addQuestionSelctManyInput(@PathVariable("departmentId") Integer departmentId, Map<String, Object> map){
		
		System.out.println("接受的系别是=======》"+departmentId);
		SelectQuestion sQuestion = new SelectQuestion();
		sQuestion.setDepartment(departmentMapper.findById(departmentId));
		sQuestion.setType(typeMapper.findType(4));
		map.put("selectOneQuestion", sQuestion);
		List<QuestionsBank> questionBankList = questionBankMapper.getAllQB();
		List<Degree> degreeList = degreeMapper.getAllDegree();
		map.put("questionBankList", questionBankList);
		map.put("degreeList", degreeList);
		return "question/questionAdd/question_add_hand_select_input";
	}
	
	//多选题的实际添加
	@RequestMapping("selectManyQuestionAddSuccess")
	public String addQuestionSelectManySuccess(SelectQuestion sQuestion){

		System.out.println("接受到的多选题目是====》"+sQuestion);
		System.out.println("就是这个人的洞悉");
		sQuestion.setIssue(sQuestion.getSelectOne()+"曱"+sQuestion.getSelectTwo()+"曱"+sQuestion.getSelectThree()+"曱"+sQuestion.getSelectFour());
		String standard_answer = "";
		String[] answerStr = sQuestion.getStandard_answer().split("");
		for(int i=0;i<answerStr.length;i++){
			standard_answer+=answerStr[i]+"曱";
		}
		System.out.println(standard_answer.substring(0, standard_answer.length()-1));
		sQuestion.setStandard_answer(standard_answer.substring(0, standard_answer.length()-1));
		questionMapper.addSelectOneQuestion(sQuestion);
		return "redirect:question_add_hand";
	}
	
	//添加填空题
	@RequestMapping(value="question_add_hand_fill/{departmentId}")
	public String addQuestionFillInput(@PathVariable("departmentId") Integer departmentId, Map<String, Object> map){
		
		FillQuestion fQuestion = new FillQuestion();
		fQuestion.setDepartment(departmentMapper.findById(departmentId));
		fQuestion.setType(typeMapper.findType(3));
		map.put("fQuestion", fQuestion);
		List<QuestionsBank> questionBankList = questionBankMapper.getAllQB();
		List<Degree> degreeList = degreeMapper.getAllDegree();
		map.put("questionBankList", questionBankList);
		map.put("degreeList", degreeList);
		return "question/questionAdd/question_add_hand_fill_input";
	}
	//填空题的添加
	@RequestMapping(value="fillQuestionAddSuccess")
	public String addQuestionFillSuccess(FillQuestion fQuestion){
		
		System.out.println("即将要添加的题目是===》"+fQuestion);
		questionMapper.addFillQuestion(fQuestion);
		return "redirect:question_add_hand";
	}
	
	//判断题的添加
	@RequestMapping(value="question_add_hand_judge/{departmentId}")
	public String addQuestionJudgeInput(@PathVariable("departmentId") Integer departmentId, Map<String, Object> map){
		
		JudgeQuestion jQuestion = new JudgeQuestion();
		jQuestion.setDepartment(departmentMapper.findById(departmentId));
		jQuestion.setType(typeMapper.findType(2));
		map.put("jQuestion", jQuestion);
		List<QuestionsBank> questionBankList = questionBankMapper.getAllQB();
		List<Degree> degreeList = degreeMapper.getAllDegree();
		map.put("questionBankList", questionBankList);
		map.put("degreeList", degreeList);
		return "question/questionAdd/question_add_hand_judge_input";
	}
	@RequestMapping(value="judgeQuestionAddSuccess")
	public String addQuestionJudgeSuccess(JudgeQuestion jQuestion){
		
		System.out.println("即将要添加的题目是===》"+jQuestion);
		questionMapper.addJudgeQuestion(jQuestion);
		return "redirect:question_add_hand";
	}
	//=======================================
	//试题的管理
	//========================================
	//    单选
	@RequestMapping(value="question_control_hand_selectOne/{departmentId}/{questionBankId}")
	public String cotrolSelectOneQuestionShow(@PathVariable("departmentId") Integer departmentId, @PathVariable("questionBankId") Integer questionBankId, Map<String, Object> map, HttpSession session, @RequestParam(value="currentPage", required=false) String currentPage){
		//得到该系下指定题库的的所有的单选题的题目
		
		System.out.println("系别是===》"+departmentId+"科目是====》"+questionBankId);
		Map<String, Object> requiredMessage = new HashMap<String, Object>();
		
		requiredMessage.put("typeId", 1);
		requiredMessage.put("questionBankId", questionBankId);
		requiredMessage.put("departmentId", departmentId);
		//分页实现
		Page_1 page = new Page_1();
		System.out.println("当前的页数是----》"+currentPage);
		if(currentPage == null || "".equals(currentPage.trim())){
			
			currentPage = 1 + "";
		}
		
		Integer totalNumber = questionMapper.getSpecialNum(requiredMessage);
		page.setTotalNumber(totalNumber);
		page.setCurrentPage(Integer.valueOf(currentPage));
		
		
		//分页
		requiredMessage.put("dbIndex", page.getDbIndex());
		requiredMessage.put("dbNumber", page.getDbNumber());
		
		
		session.setAttribute("selectOneQuestionBankId", questionBankId);
		session.setAttribute("selectOneDepartmentId", departmentId);
		
		map.put("page", page);
		
		List<Question> questionList = questionMapper.getSpecialSelectOne(requiredMessage);
		List<SelectQuestion> selectOneQuestionList = convertToSelectType(questionList, questionBankId, departmentId, 1);
		map.put("selectOneQuestionList", selectOneQuestionList);
		System.out.println(selectOneQuestionList);
		return "question/questionControl/select_one_control";
	}

	//删除单选题目
	@RequestMapping(value="question_mananage_hand_delete/{questionId}/{currentPage}")
	public String deleteQuestion(@PathVariable("questionId") Integer questionId,@PathVariable("currentPage") String currentPage, HttpSession session){
		
		if(questionMapper.findQuestionForeignKey(questionId) != null){
			System.out.println(questionMapper.findQuestionForeignKey(questionId));
			questionMapper.deleteQuestionForeignKey(questionId);
		}
		questionMapper.deleteQuestion(questionId);
		String departmentId = session.getAttribute("selectOneDepartmentId").toString();
		String questionBankId = session.getAttribute("selectOneQuestionBankId").toString();
		System.out.println("<=======departmentId======>"+departmentId+"<=======questionBankId====>"+questionBankId);
		String urlPath = "redirect:/question_control_hand_selectOne/"+departmentId+"/"+questionBankId+"?currentPage="+currentPage;
		System.out.println(urlPath);
		return urlPath;
	}
	//单选的修改
	@RequestMapping("selectOne_manage_hand_modify_input/{questionId}/{currentPage}")
	public String modifyQuestionInput(@PathVariable("questionId") Integer questionId,@PathVariable("currentPage") String currentPage, Map<String, Object> map, HttpSession session){
		
		if(questionMapper.findQuestionForeignKey(questionId) != null){
			questionMapper.deleteQuestionForeignKey(questionId);
		}
		
		Question question = questionMapper.getQuestionBySpecil(questionId);
			System.out.println(question);
			
			session.setAttribute("currentPage", currentPage);
			
			SelectQuestion sQuestion = new SelectQuestion();
			//对应属性的赋值
			sQuestion.setId(question.getId());
			sQuestion.setTitle(question.getTitle());
			sQuestion.setStandard_answer(question.getStandard_answer());
			sQuestion.setScore(question.getScore());
			
			String[] selectOptions = question.getIssue().split("曱");
			
			sQuestion.setSelectOne(selectOptions[0]);
			sQuestion.setSelectTwo(selectOptions[1]);
			sQuestion.setSelectThree(selectOptions[2]);
			sQuestion.setSelectFour(selectOptions[3]);
			map.put("selectOneQuestion", sQuestion);
			System.out.println("完成之后的选择题"+sQuestion);
		return "question/questionControl/select_one_modify_input";
	}
	//接受单选的修改
	@RequestMapping(value="select_one_modify_change_success")
	public String modifyQuestionModify(SelectQuestion sQuestion, HttpSession session){
		
		System.out.println(sQuestion);
		
		Question question = new Question();
		question.setId(sQuestion.getId());
		question.setTitle(sQuestion.getTitle());
		question.setStandard_answer(sQuestion.getStandard_answer());
		question.setScore(sQuestion.getScore());
		question.setIssue(sQuestion.getSelectOne()+"曱"+sQuestion.getSelectTwo()+"曱"+sQuestion.getSelectThree()+"曱"+sQuestion.getSelectFour());
		
		questionMapper.updateSpecialQuestion(question);
		
		String departmentId = session.getAttribute("selectOneDepartmentId").toString();
		String questionBankId = session.getAttribute("selectOneQuestionBankId").toString();
		String urlPath = "redirect:/question_control_hand_selectOne/"+departmentId+"/"+questionBankId+"?currentPage="+session.getAttribute("currentPage");
		System.out.println(urlPath);
		return urlPath;
	}
	
	//多项选择
	@RequestMapping(value="question_control_hand_selectMany/{departmentId}/{questionBankId}")
	public String selectManyControl(@PathVariable("departmentId") Integer departmentId, @PathVariable("questionBankId") Integer questionBankId, Map<String, Object> map, HttpSession session, @RequestParam(value="currentPage", required=false) String currentPage){
		
		
		System.out.println("系别是===》"+departmentId+"科目是====》"+questionBankId);
		Map<String, Object> requiredMessage = new HashMap<String, Object>();
		requiredMessage.put("typeId", 4);
		requiredMessage.put("questionBankId", questionBankId);
		requiredMessage.put("departmentId", departmentId);
		
		//分页第一步
		Page_1 page = new Page_1();
		System.out.println("当前的页数是----》"+currentPage);
		if(currentPage == null || "".equals(currentPage.trim())){
			
			currentPage = 1 + "";
		}
		
		Integer totalNumber = questionMapper.getSpecialNum(requiredMessage);
		page.setTotalNumber(totalNumber);
		page.setCurrentPage(Integer.valueOf(currentPage));
		
		
		//分页第二部
		requiredMessage.put("dbIndex", page.getDbIndex());
		requiredMessage.put("dbNumber", page.getDbNumber());
		
		//分页第四部
		map.put("page", page);
		session.setAttribute("selectManyQuestionBankId", questionBankId);
		session.setAttribute("selectManyDepartmentId", departmentId);
		
		List<Question> questionList = questionMapper.getSpecialSelectOne(requiredMessage);
		List<SelectQuestion> selectManyQuestionList = convertToSelectType(questionList, questionBankId, departmentId, 4);
		
		map.put("selectManyQuestionList", selectManyQuestionList);
		
		
		
		System.out.println(selectManyQuestionList);
		
		return "question/questionControl/select_many_control";
	}
	
	//多项选择删除
	@RequestMapping(value="selectManyquestion_mananage_hand_delete/{questionId}/{currentPage}")
	public String deleteSelectManay(@PathVariable("questionId") Integer questionId, HttpSession session, @PathVariable("currentPage") String currentPage){
		if(questionMapper.findQuestionForeignKey(questionId) != null){
			questionMapper.deleteQuestionForeignKey(questionId);
		}
		questionMapper.deleteQuestion(questionId);
		
		String departmentId = session.getAttribute("selectManyDepartmentId").toString();
		String questionBankId = session.getAttribute("selectManyQuestionBankId").toString();
		System.out.println("<=======departmentId======>"+departmentId+"<=======questionBankId====>"+questionBankId);
		String urlPath = "redirect:question_control_hand_selectMany/"+departmentId+"/"+questionBankId+"?currentPage="+currentPage;
		System.out.println(urlPath);
		return urlPath;
	}
	//多项选择的修改跳转
	@RequestMapping(value="selectMany_manage_hand_modify_input/{questionId}/{currentPage}")
	public String modifySelectManyInput(@PathVariable("questionId") Integer questionId, Map<String, Object> map, @PathVariable("currentPage") String currentPage, HttpSession session){
		
		if(questionMapper.findQuestionForeignKey(questionId) != null){
			questionMapper.deleteQuestionForeignKey(questionId);
		}
		
		//分页
		session.setAttribute("currentPage", currentPage);
		
		Question question = questionMapper.getQuestionBySpecil(questionId);
		System.out.println(question);
		SelectQuestion sQuestion = new SelectQuestion();
		//对应属性的赋值
		sQuestion.setId(question.getId());
		sQuestion.setTitle(question.getTitle());
		sQuestion.setStandard_answer(question.getStandard_answer().replaceAll("曱", ""));
		sQuestion.setScore(question.getScore());
		
		String[] selectOptions = question.getIssue().split("曱");
		
		sQuestion.setSelectOne(selectOptions[0]);
		sQuestion.setSelectTwo(selectOptions[1]);
		sQuestion.setSelectThree(selectOptions[2]);
		sQuestion.setSelectFour(selectOptions[3]);
		map.put("selectManyQuestion", sQuestion);
		System.out.println("完成之后的选择题"+sQuestion);
		return "question/questionControl/select_many_modify_input";
	}
	//多项选择的接受
	@RequestMapping(value="select_many_modify_change_success")
	public String modifySelectManySuccess(SelectQuestion sQuestion, HttpSession session){
		

		System.out.println(sQuestion);
		
		Question question = new Question();
		question.setId(sQuestion.getId());
		question.setTitle(sQuestion.getTitle());
		
		String[] answerStr = sQuestion.getStandard_answer().split("");
		String standard_answer = "";
		for(int i=0;i<answerStr.length;i++){
			standard_answer+=answerStr[i]+"曱";
		}
		System.out.println(standard_answer.substring(0, standard_answer.length()-1));
		question.setStandard_answer(standard_answer.substring(0, standard_answer.length()-1));
		
		question.setScore(sQuestion.getScore());
		question.setIssue(sQuestion.getSelectOne()+"曱"+sQuestion.getSelectTwo()+"曱"+sQuestion.getSelectThree()+"曱"+sQuestion.getSelectFour());
		
		questionMapper.updateSpecialQuestion(question);
		
		String departmentId = session.getAttribute("selectManyDepartmentId").toString();
		String questionBankId = session.getAttribute("selectManyQuestionBankId").toString();
		String urlPath = "redirect:question_control_hand_selectMany/"+departmentId+"/"+questionBankId+"?currentPage="+session.getAttribute("currentPage");
		System.out.println(urlPath);
		return urlPath;
	}
	
	
	//填空题
	@RequestMapping(value="question_control_hand_fill/{departmentId}/{questionBankId}")
	public String fillQuestionControl(@PathVariable("departmentId") Integer departmentId, @PathVariable("questionBankId") Integer questionBankId, Map<String, Object> map, HttpSession session, @RequestParam(value="currentPage", required=false) String currentPage){
		
		//得到该系下指定题库的的所有的单选题的题目
		System.out.println("系别是===》"+departmentId+"科目是====》"+questionBankId);
		Map<String, Object> requiredMessage = new HashMap<String, Object>();
		requiredMessage.put("typeId", 3);
		requiredMessage.put("questionBankId", questionBankId);
		requiredMessage.put("departmentId", departmentId);
		
		//分页第一步
		Page_1 page = new Page_1();
		System.out.println("当前的页数是----》"+currentPage);
		if(currentPage == null || "".equals(currentPage.trim())){
			
			currentPage = 1 + "";
		}
		
		Integer totalNumber = questionMapper.getSpecialNum(requiredMessage);
		page.setTotalNumber(totalNumber);
		page.setCurrentPage(Integer.valueOf(currentPage));
		
		
		//分页第二部
		requiredMessage.put("dbIndex", page.getDbIndex());
		requiredMessage.put("dbNumber", page.getDbNumber());
		
		//第三部
		session.setAttribute("page", page);
		session.setAttribute("fillQuestionBankId", questionBankId);
		session.setAttribute("fillDepartmentId", departmentId);
		
		List<Question> questionList = questionMapper.getSpecialSelectOne(requiredMessage);
		List<FillQuestion> fillQuestionList = convertToFillQuestion(questionList, questionBankId, departmentId, 3);
		map.put("fillQuestionList", fillQuestionList);
		System.out.println("填空题的管理所有列表的实体总个数是"+fillQuestionList.size());
		return "question/questionControl/fill_question_control";
	}
	//填空题的删除
	@RequestMapping(value="fillQuestion_mananage_hand_delete/{questionId}/{currentPage}")
	public String deleteFillQuestion(@PathVariable("questionId") Integer questionId, HttpSession session, @PathVariable("currentPage") String currentPage){
		
		if(questionMapper.findQuestionForeignKey(questionId) != null){
			questionMapper.deleteQuestionForeignKey(questionId);
		}
		questionMapper.deleteQuestion(questionId);
		
		String departmentId = session.getAttribute("fillDepartmentId").toString();
		String questionBankId = session.getAttribute("fillQuestionBankId").toString();
		System.out.println("<=======departmentId======>"+departmentId+"<=======questionBankId====>"+questionBankId);
		String urlPath = "redirect:question_control_hand_fill/"+departmentId+"/"+questionBankId+"?currentPage="+currentPage;
		System.out.println(urlPath);
		return urlPath;
	}
	//试题修改跳转
	@RequestMapping(value="fillQuestion_manage_hand_modify_input/{questionId}/{currentPage}")
	public String modifyFillQuestionInput(@PathVariable("questionId") Integer questionId, Map<String, Object> map, @PathVariable("currentPage") String currentPage, HttpSession session){
		
		if(questionMapper.findQuestionForeignKey(questionId) != null){
			questionMapper.deleteQuestionForeignKey(questionId);
		}
		
		Question question = questionMapper.getQuestionBySpecil(questionId);
		System.out.println(question);
		

		//分页相关
		session.setAttribute("currentPage", currentPage);
		
		FillQuestion fQuestion = new FillQuestion();
		fQuestion.setId(question.getId());
		fQuestion.setTitle(question.getTitle());
		fQuestion.setStandard_answer(question.getStandard_answer());
		fQuestion.setScore(question.getScore());
		
		map.put("fQuestion", fQuestion);
		System.out.println(fQuestion);
		return "question/questionControl/fill_question_modify_input";
	}
	//修改的接受
	@RequestMapping(value="fill_question_modify_change_success")
	public String modifyFillQuestionSuccess(FillQuestion fQuestion, HttpSession session){
		
		System.out.println("========>>>>>>>>>>>"+fQuestion);
		
		Question question = new Question();
		question.setId(fQuestion.getId());
		question.setTitle(fQuestion.getTitle());
		question.setStandard_answer(fQuestion.getStandard_answer());
		question.setScore(fQuestion.getScore());
		 question.setIssue(null);
		questionMapper.updateSpecialQuestion(question);
		
		String departmentId = session.getAttribute("fillDepartmentId").toString();
		String questionBankId = session.getAttribute("fillQuestionBankId").toString();
		String urlPath = "redirect:question_control_hand_fill/"+departmentId+"/"+questionBankId+"?currentPage="+session.getAttribute("currentPage");
		return urlPath;
	}
	
	//判断题
	@RequestMapping(value="question_control_hand_judge/{departmentId}/{questionBankId}")
	public String judgeQuestionControl(@PathVariable("departmentId") Integer departmentId, @PathVariable("questionBankId") Integer questionBankId, Map<String, Object> map, HttpSession session, @RequestParam(value="currentPage", required=false) String currentPage){
		
		//得到该系下指定题库的的所有的单选题的题目
		System.out.println("系别是===》"+departmentId+"科目是====》"+questionBankId);
		Map<String, Object> requiredMessage = new HashMap<String, Object>();
		requiredMessage.put("typeId", 2);
		requiredMessage.put("questionBankId", questionBankId);
		requiredMessage.put("departmentId", departmentId);
		
		//分页第一步
		Page_1 page = new Page_1();
		System.out.println("当前的页数是----》"+currentPage);
		if(currentPage == null || "".equals(currentPage.trim())){
			
			currentPage = 1 + "";
		}
		
		Integer totalNumber = questionMapper.getSpecialNum(requiredMessage);
		page.setTotalNumber(totalNumber);
		page.setCurrentPage(Integer.valueOf(currentPage));
		
		
		//分页第二部
		requiredMessage.put("dbIndex", page.getDbIndex());
		requiredMessage.put("dbNumber", page.getDbNumber());
		
		//第三部
		session.setAttribute("page", page);
		session.setAttribute("judgeQuestionBankId", questionBankId);
		session.setAttribute("judgeDepartmentId", departmentId);
		
		List<Question> questionList = questionMapper.getSpecialSelectOne(requiredMessage);
		List<JudgeQuestion> judgeQuestionList = convertToJudgeQuestion(questionList, questionBankId, departmentId, 2);
		map.put("judgeQuestionList", judgeQuestionList);
		System.out.println(judgeQuestionList);
		return "question/questionControl/judge_question_control";
	}
	
	//删除
	@RequestMapping(value="judgeQuestion_mananage_hand_delete/{questionId}/{currentPage}")
	public String deleteJudgeQuestion(@PathVariable("questionId") Integer questionId, HttpSession session, @PathVariable("currentPage") String currentPage){
		

		if(questionMapper.findQuestionForeignKey(questionId) != null){
			questionMapper.deleteQuestionForeignKey(questionId);
		}
		questionMapper.deleteQuestion(questionId);
		
		String departmentId = session.getAttribute("judgeDepartmentId").toString();
		String questionBankId = session.getAttribute("judgeQuestionBankId").toString();
		System.out.println("<=======departmentId======>"+departmentId+"<=======questionBankId====>"+questionBankId);
		String urlPath = "redirect:question_control_hand_judge/"+departmentId+"/"+questionBankId+"?currentPage="+currentPage;
		System.out.println(urlPath);
		return urlPath;
	}
	//修改的跳转页面
	@RequestMapping(value="judgeQuestion_manage_hand_modify_input/{questionId}/{currentPage}")
	public String modifyJudegeQuestionInput(@PathVariable("questionId") Integer questionId, Map<String, Object> map, @PathVariable("currentPage") String currentPage, HttpSession session){
		
		if(questionMapper.findQuestionForeignKey(questionId) != null){
			questionMapper.deleteQuestionForeignKey(questionId);
		}
		
		//分页相关
		session.setAttribute("currentPage", currentPage);
		Question question = questionMapper.getQuestionBySpecil(questionId);
		System.out.println(question);
		
		JudgeQuestion jQuestion = new JudgeQuestion();
		jQuestion.setId(question.getId());
		jQuestion.setTitle(question.getTitle());
		jQuestion.setStandard_answer(question.getStandard_answer());
		jQuestion.setScore(question.getScore());
		
		map.put("jQuestion", jQuestion);
		
		return "question/questionControl/judge_question_modify_input";
	}
	//修改接受
	@RequestMapping(value="judge_question_modify_change_success")
	public String modifyJudgeQuestionSuccess(JudgeQuestion jQuestion, HttpSession session){
		
		System.out.println("========>>>>>>>>>>>"+jQuestion);
		
		Question question = new Question();
		question.setId(jQuestion.getId());
		question.setTitle(jQuestion.getTitle());
		question.setStandard_answer(jQuestion.getStandard_answer());
		question.setScore(jQuestion.getScore());
		 question.setIssue(null);
		questionMapper.updateSpecialQuestion(question);
		
		String departmentId = session.getAttribute("judgeDepartmentId").toString();
		String questionBankId = session.getAttribute("judgeQuestionBankId").toString();
		String urlPath = "redirect:question_control_hand_judge/"+departmentId+"/"+questionBankId+"?currentPage="+session.getAttribute("currentPage");
		
		return urlPath;
	}
	
	
	
	
	
	
	
	
	//类型转换
public  List<SelectQuestion> convertToSelectType(List<Question> questionList, Integer questionBankId, Integer departmentId, Integer typeId){
		
		List<SelectQuestion> selectQuestionList = new ArrayList<SelectQuestion>();
		for(int i=0;i<questionList.size();i++){
			
			SelectQuestion sQuestion = new SelectQuestion();
			//类型的设定
			sQuestion.setType(typeMapper.findType(typeId));
			sQuestion.setQb(questionBankMapper.findById(questionBankId));
			sQuestion.setDepartment(departmentMapper.findById(departmentId));
			
			//对应属性的赋值
			sQuestion.setId(questionList.get(i).getId());
			sQuestion.setTitle(questionList.get(i).getTitle());
			
			//多选例外
			if("4".equals(typeId+"")){
				System.out.println(questionList.get(i).getStandard_answer()+"***********%");
				String answerStr = questionList.get(i).getStandard_answer().replaceAll("曱", "");
				System.out.println(answerStr);
				sQuestion.setStandard_answer(answerStr);
				
			}
			sQuestion.setScore(questionList.get(i).getScore());
			
			String[] selectOptions = questionList.get(i).getIssue().split("曱");
			
			sQuestion.setSelectOne(selectOptions[0]);
			sQuestion.setSelectTwo(selectOptions[1]);
			sQuestion.setSelectThree(selectOptions[2]);
			sQuestion.setSelectFour(selectOptions[3]);
			//难度
			sQuestion.setDegree(questionList.get(i).getDegree());
			
			selectQuestionList.add(sQuestion);
		}
		
		
		return selectQuestionList;
	}
	
	//类型转换
	public List<FillQuestion> convertToFillQuestion(List<Question> questionList, Integer questionBankId, Integer departmentId, Integer typeId){
		
		List<FillQuestion> fillQuestionList = new ArrayList<FillQuestion>();
		
		for(int i=0;i<questionList.size();i++){
			
			FillQuestion fQuestion = new FillQuestion();
			
			//类型设定
			fQuestion.setType(typeMapper.findType(typeId));
			fQuestion.setDepartment(departmentMapper.findById(departmentId));
			fQuestion.setQb(questionBankMapper.findById(questionBankId));
			fQuestion.setDegree(questionList.get(i).getDegree());
			
			fQuestion.setId(questionList.get(i).getId());
			fQuestion.setTitle(questionList.get(i).getTitle());
			fQuestion.setStandard_answer(questionList.get(i).getStandard_answer());
			fQuestion.setScore(questionList.get(i).getScore());
			
			fillQuestionList.add(fQuestion);
		}
		return fillQuestionList;
	}
	
	//转换成判断题
	public List<JudgeQuestion> convertToJudgeQuestion(List<Question> questionList, Integer questionBankId, Integer departmentId, Integer typeId){
		
		List<JudgeQuestion> judgeQuestionList = new ArrayList<JudgeQuestion>();
		
		for(int i=0;i<questionList.size();i++){
			
			JudgeQuestion jQuestion = new JudgeQuestion();
			//类型设定
			jQuestion.setType(typeMapper.findType(typeId));
			jQuestion.setDepartment(departmentMapper.findById(departmentId));
			jQuestion.setQb(questionBankMapper.findById(questionBankId));
			jQuestion.setDegree(questionList.get(i).getDegree());
			
			jQuestion.setId(questionList.get(i).getId());
			jQuestion.setTitle(questionList.get(i).getTitle());
			jQuestion.setStandard_answer(questionList.get(i).getStandard_answer());
			jQuestion.setScore(questionList.get(i).getScore());
			
			judgeQuestionList.add(jQuestion);
		}
		
		return judgeQuestionList;
	}
	
	
	
	
	
	
	
	
	
	//通过Excel导入
	@RequestMapping(value="question_add_excel")
	public String questionAddExcelManage(Map<String, Object> map){
		List<Department> departmentList = departmentMapper.getAllDp();
		System.out.println(departmentList);
		List<QuestionsBank> qbs = questionBankMapper.getAllQB();
		List<Degree> degrees=degreeMapper.getAllDegree();
		map.put("qbs", qbs);
		map.put("departmentList", departmentList);
		map.put("dgs", degrees);
		return "question/questionAdd/question_add_excel_manage";
	}
	
	//得到Excel文件
	@RequestMapping(value="question_get_excel",method=RequestMethod.POST)
	public String questionGetExcel(
			Department dp,
			QuestionsBank qb,
			Degree dg,
			@RequestParam("datas")String[] datas,
			HttpServletRequest request
			) throws IllegalStateException, IOException, BiffException{
		 //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        System.out.println(dg);
        List<String> list=Arrays.asList(datas);
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
			String data=list.get(i);
			System.out.println("data>>>"+data);
			for (int j = i+1; j < list.size(); j++) {
				System.out.println(">>>"+list.get(j));
				if (data.equals(list.get(j))) {
					list.remove(j);
				}
			}
		}
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();
            int i=0;
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = "demoUpload" + file.getOriginalFilename();  
                        //定义上传路径  
                        /**
                         * 构建文件保存的目录
                         */
                        String logoPath="/resources/excel/" ;
                        /**
                         * 得到文件保存目录的真实路径
                         */
                        String logoRealPath=request.getSession().getServletContext().getRealPath(logoPath);
                        //根据真实路径创建目录
                        File logoSave=new File(logoRealPath);
                        if (!logoSave.exists()) {
                        	logoSave.mkdirs();
						}
                        String path =  logoRealPath+File.separator+myFileName;  
                        File localFile = new File(path); 
                        file.transferTo(localFile);
                        if (list.get(i).equals("textfield")) {
                        	 ExcelUtils.readExecl(localFile,dp,qb,dg,0);
						}
						if (list.get(i).equals("textfield2")) {
							ExcelUtils.readExecl(localFile,dp,qb,dg,1);						
												}
						if (list.get(i).equals("textfield3")) {
							ExcelUtils.readExecl(localFile,dp,qb,dg,2);
						}
						if (list.get(i).equals("textfield4")) {
							ExcelUtils.readExecl(localFile,dp,qb,dg,3);
						}
                        i++;
                    }  
                }  
            }  
        }
        for (SelectQuestion se:ExcelUtils.questions) {
        	questionMapper.addSelectOneQuestion(se);
		}
        for (JudgeQuestion ju:ExcelUtils.judgeQuestions) {
        	questionMapper.addJudgeQuestion(ju);
		}
        for(FillQuestion fill:ExcelUtils.fillQuestions){
        	questionMapper.addFillQuestion(fill);
        }
		return "success";
		
	}
	
}
