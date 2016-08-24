<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${pageContext.request.contextPath }/question_subject_manage">试题科目管理</a>
	<a href="${pageContext.request.contextPath }/question_type_manage">试题的题型管理</a>
	
	<!-- 添加试题 -->
	<a href="${pageContext.request.contextPath }/question_add_hand">管理和手动添加试题</a>
	<a href="${pageContext.request.contextPath }/question_add_excel">Excel导入试题</a>
</body>
</html>