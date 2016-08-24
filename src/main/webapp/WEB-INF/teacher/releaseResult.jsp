<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>发布成绩！</h3>
	<form:form action="${pageContext.request.contextPath }/teacher/result" method="post"
		commandName="scoreForm">
		学号 ：<form:input path="studentNum" />
		<br>
		姓名 ： <form:input path="name" />
		<br>
		试卷 ： 
			<form:select path="examPage.id" id="examPage"
				items="${examPages }" itemValue="id" itemLabel="name"></form:select><br>
		所考成绩 : <form:input path="final_score"/>
		<input type="submit" value="提交" />
		
	</form:form>


</body>
</html>