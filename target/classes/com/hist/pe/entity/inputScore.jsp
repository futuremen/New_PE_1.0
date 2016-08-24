<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<form action="bizhao" method="post">
		<table border="1">

			<tr>

				<td>学号</td>
				<td>姓名</td>
				<td>肺活量</td>
				<td>耐力跑</td>
				<td>立定跳远</td>

			</tr>
			<c:forEach items="${student}" var="item"
				varStatus="status">
				<tr>
					<td><input type="text"
						name="student[${status.index}].studentAccount"
						value="${item.studentAccount}"></td>
					<td><input type="text"
						name="student[${status.index}].name"
						value="${item.name}"></td>
						
						<td><input type="text"
						name="sscores[${status.index}].lung_volume"
						 ></td>
						<td><input type="text"
						name="sscores[${status.index}].enduro"
						 ></td>
						 <td><input type="text"
						name="sscores[${status.index}].standing_jump"
						 ></td>
				</tr>>
			</c:forEach>
		</table>
		<input type="submit">
	</form>

</body>
</html>