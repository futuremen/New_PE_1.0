<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加系别或者修改系别的输入界面</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath }/${dp_id == null?'department_add_success':'department_modify_success' }" modelAttribute="department" method="POST">
		<c:if test="${!empty dp_id }">
			<form:hidden path="dp_id"/>
		</c:if>
		<form:input path="dp_name"/>
		<input type="submit" value="submit"/>
	</form:form>
</body>
</html>