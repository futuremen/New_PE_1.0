<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单选题修改</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath }/fill_question_modify_change_success" modelAttribute="fQuestion" method="POST">
	<form:hidden path="id"/>
	题干：<form:input path="title"/><br/>
	题目标准答案：<form:input path="standard_answer"/><br/>
	题目分数：<form:input path="score"/><br/>
	<input type="submit" value="submit"/>
</form:form>
	
</body>
</html>