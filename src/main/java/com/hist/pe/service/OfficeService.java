package com.hist.pe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hist.pe.dao.InstituteDao;
import com.hist.pe.dao.OrderDao;
import com.hist.pe.dao.ScoreDao;
import com.hist.pe.dao.StudentClassDao;
import com.hist.pe.dao.StudentDao;
import com.hist.pe.dao.TeacherDao;
import com.hist.pe.dao.TermDao;
import com.hist.pe.entity.Institute;
import com.hist.pe.entity.Order;
import com.hist.pe.entity.Score;
import com.hist.pe.entity.Student;
import com.hist.pe.entity.StudentClass;
import com.hist.pe.entity.Teacher;
import com.hist.pe.entity.Term;
import com.hist.pe.entity.WarpForScore;
import com.hist.pe.utils.SpringContextHolder;

@Service
@Transactional(readOnly = true)
public class OfficeService implements InitializingBean {

	private static StudentClassDao studentClassDao = SpringContextHolder.getBean(StudentClassDao.class);

	private static ScoreDao scoreDao = SpringContextHolder.getBean(ScoreDao.class);

	private static StudentDao studentDao = SpringContextHolder.getBean(StudentDao.class);

	private static InstituteDao instituteDao = SpringContextHolder.getBean(InstituteDao.class);

	private static TeacherDao teacherDao = SpringContextHolder.getBean(TeacherDao.class);
	
	private static OrderDao orderDao = SpringContextHolder.getBean(OrderDao.class);
	
	private static TermDao termDao = SpringContextHolder.getBean(TermDao.class);

	/**
	 * **********************************学院***********************************
	 */
    
	public static void insertInstitute(Institute institute) {
		instituteDao.insert(institute);

	}

	public static void updateInstitute(Institute institute) {
		instituteDao.update(institute);

	}

	public static void deleteInstitute(String institute_id) {
		instituteDao.delete(institute_id);

	}
	
	public static List<Institute> getAllInstitute(Map<String, Object> map) {
	return	instituteDao.getAllInstituteByPage(map);

	}
	
	


	public static Institute getInstituteItem(String institute_id) {
		return instituteDao.get(institute_id);

	}

	

	/**
	 * **********************************学院 end***********************************
	 * ***********************
	 */

	/**
	 * **********************************班级***********************************
	 * ***********************
	 */

	public static void insertUnionTable(String teacher_id,String class_id) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("teacher_id", teacher_id);
		map.put("class_id", class_id);
		
		
		studentClassDao.insertUnionTable(map);

	}
	
	public static StudentClass  insertStudentClass(StudentClass studentClass) {
		studentClassDao.insert(studentClass);
		return studentClass;

	}
	
	public static List<StudentClass>  getAllClass() {
		return studentClassDao.getAllClass();
	}

	public static StudentClass getStudentClassItem(String class_id) {
		return studentClassDao.get(class_id);

	}

	public static void updateStudentClass(StudentClass studentClass,String teacher_id) {
		studentClassDao.update(studentClass);
		
		String class_id = studentClass.getId();
		
      Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("teacher_id", teacher_id);
		map.put("class_id", class_id);
		
		studentClassDao.updateUnionTable(map);

	}

	public static void deleteStudentClass(String studentClass_id) {
		studentClassDao.delete(studentClass_id);

	}
	
	public static List<StudentClass>  getInstituteClass(Map<String, Object> map) {
		return studentClassDao.getInstituteClassByPage(map);

	}
	
	public static void deleteUnionTable(String class_id) {
		studentClassDao.deleteUnionTable(class_id);

	}
	public static StudentClass getOneStudentClass(String studentClass_id) {
		return studentClassDao.get(studentClass_id);
		

	}
	
	
	
	public static List<StudentClass> getTeacherClsaaByPage(Map<String, Object> map) {
		return studentClassDao.getTeacherClsaaByPage(map);
	}
	
	
	

	/**
	 * **********************************班级end***********************************
	 */

	/**
	 * **********************************老师***********************************
	 */

	

	public static void insertTeacher(Teacher teacher) {
		teacherDao.insert(teacher);
	}
	
	public static void updateTeacher(Teacher teacher) {
		teacherDao.update(teacher);
	}
	
	public static void deleteTeacher(String teacher_id) {
		teacherDao.delete(teacher_id);
	}
	
	
	
	public static Teacher getOneTeacher(String teacher_id) {
		return teacherDao.get(teacher_id);
	}
	
	
	public static List<Teacher> getTeachers(Map<String, Object>  map) {
		return teacherDao.getTeachers(null);
	}

	/**
	 * **********************************老师 end***********************************
	 */

	
	
	/**
	 * **********************************成绩***********************************
	 */
	

	
	public static Score insertScore(Score score) {
		scoreDao.insert(score);
		return score;
	}
	
	public static void updateScore(Score score) {
		scoreDao.update(score);
	}
	
	public static void deleteScore(String score_id) {
		scoreDao.delete(score_id);
	}
	
	public static Score getScore(String score_id) {
		return scoreDao.get(score_id);
	}
	public static void updateOnlineScore(Map<String, Object> map) {
		
		scoreDao.updateOnlineScore(map);
	}
	
	
	
	public static List<Score> getStudentScore(Map<String, Object> map) {
		return scoreDao.getStudentScoreByPage(map);
	}
	
	public static List<Score> getOriginalStudentScore(Map<String, Object> map) {
		return scoreDao.getOriginalStudentScoreByPage(map);
	}

	
	
	public static List<Score> getClassScore(Map<String, Object> map) {
		return scoreDao.getClssScoreByPage(map);
	}
	
	public static void getReallyScore() {
		scoreDao.getReallyScore();
	}
	
	
	
	public static List<WarpForScore> getStudentScoreByClass(Map<String, Object> map){
		
		
		return scoreDao.getClassStudentScore(map);
	}
	
	public static void insertOnlineScore(Map<String, Object> map){
		
		
	 scoreDao.insertOnlineScore(map);
	}
	
	
	
	
	
	/**
	 * **********************************成绩 end***********************************
	 */
	
	
	/**
	 * **********************************预约***********************************
	 */
	
	public static Order getStudentOrder(Order order) {
		return orderDao.get(order);
	}
	
	
	public static Order getOrderItem(String id){
		return orderDao.get(id);
	}
	
	public static List<Order> getTeacherOrderInfoByPage(Map<String, Object> map){
		return orderDao.getTeacherOrderInfoByPage(map);
		
	}
	
	
	public static void insertOrder(Order order) {
		orderDao.insert(order);
	}
	
	public static void updateOrder(Order order) {
		orderDao.update(order);
	}
	
	public static void deleteOrder(String order_id) {
		orderDao.delete(order_id);
	}
	public static List<Order> getAllOrder(){
	return	orderDao.getAll();
	}
	
	/**
	 * **********************************预约 end***********************************
	 */
	
	
	/**
	 * **********************************学生***********************************
	 */
	
	public static void insertStudent(Student student) {
		studentDao.insert(student);
	}
	
	public static void updateStudent(Student student) {
		studentDao.update(student);
	}
	
	public static void deleteStudent(String student_id) {
		studentDao.delete(student_id);
	}
	public static Student getOneStudent(String student_id) {
		return studentDao.get(student_id);
	}
	
	
	public static List<Student> getClassStudent(Map<String, Object> map) {
		return studentDao.getClassStudentByPage(map);
	}

	public static List<Student> getStudentByOrder(Map<String, Object> map) {
		// TODO Auto-generated method stub
			return 	studentDao.getStudentByOrder(map);
		
	}
	/**
	 * **********************************学生 end***********************************
	 */
	
	/**
	 * **********************************学期***********************************
	 */
		
	public static Term insertTerm(Term term) {
		termDao.insert(term);
		return term;
	}
	
	public static void updateTerm(Term term) {
		termDao.update(term);
	}
	
	public static Term  getTerm(String term_id) {
		return termDao.get(term_id);
	}
	
	public static List<Term>  getAllTerm() {
		return termDao.getAllTerm();
	}
	
	public static void deleteTerm(String term_id) {
		termDao.delete(term_id);
	}
	
	public static void cancelCurrentTerm() {
		termDao.cancelCurrentTerm();
	}
	
	
	public static void setCurrentTerm(String term_id) {
		termDao.setCurrentTerm(term_id);
	}
	
	
	
	public static Term getCurrentTerm() {
		return termDao.getCurrentTerm();
	}

	/**
	 * **********************************学期 end***********************************
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public static void updateStudentPwd(Student student) {
		// TODO Auto-generated method stub
		studentDao.updateStudentPwd(student);
	}

	public static void updateTeacherPwd(Teacher teacher) {
		// TODO Auto-generated method stub
		
		teacherDao.updatePwd(teacher);
		
	}

	public static void updateStudentOrder(Order order, Student student) {
		orderDao.update(order);
		studentDao.update(student);
		
	}

	public static void backOut(String order_id, String student_id) {
		studentDao.updateStudentForOrder(student_id);
		orderDao.updateOrderForStudent(order_id);
	}

}
