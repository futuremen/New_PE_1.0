<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//得到url的根路径
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="s/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="js/list.js"></script>
<title>公告墙</title>
<style type="text/css">
.mytable {
	margin: 20px auto;
	border-collapse: collapse;
	font-size: 12px;
	text-align: center;
	color: #666;
}

.mytable th, .mytable td {
	padding: 4px 8px;
	border: 1px solid #ccc;
}

.mytable th {
	background-color: #f0f0f0;
}
</style>
</head>
<body>
<form id="mainForm" action="<%=basePath %>question_control_hand_selectMany/${sessionScope.selectManyDepartmentId}/${sessionScope.selectManyQuestionBankId}" >
	 <input id="currentPage" type="hidden" name="currentPage" value=""/>
	<table class="mytable" border="1px" bordercolor="#ccc" cellpadding="2px"
		cellspacing="0">
		<caption>多项选择管理</caption>
		<tr>
			<th>title</th>
 			<th>选项A</th>
 			<th>选项B</th>
 			<th>选项C</th>
 			<th>选项D</th>
 			<th>试题分数</th>
 			<th>标准答案</th>
 			
 			<th>试题类型</th>
 			<th>试题难度</th>
 			<th>试题所属科目</th>
 			
			<th>删除</th>
			<th>修改</th>
		</tr>
		<c:choose>
			<c:when test="${!empty selectManyQuestionList }">
				<c:forEach items="${selectManyQuestionList }" var="selectMany" varStatus="mStatus">
					<tr <c:if test="${mStatus.index % 2 != 0 }">style="background-color: #ECF6EE;"</c:if>>
						<td>${selectMany.title }</td>
						<td>${selectMany.selectOne }</td>
						<td>${selectMany.selectTwo }</td>
						<td>${selectMany.selectThree }</td>
						<td>${selectMany.selectFour }</td>
						<td>${selectMany.score }</td>
						<td>${selectMany.standard_answer }</td>
						<td>${selectMany.type.name }</td>
						<td>${selectMany.degree.d_degree }</td>
						<td>${selectMany.qb.name }</td>
						<td><a href="${pageContext.request.contextPath }/selectManyquestion_mananage_hand_delete/${selectMany.id}/${page.currentPage}">删除</a></td>
						<td><a href="${pageContext.request.contextPath }/selectMany_manage_hand_modify_input/${selectMany.id}/${page.currentPage}">修改</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				目前没有公告
			</c:otherwise>
		</c:choose>
			<tr>
			<td colspan="4">
				<div class='page fix'>
						共 <b>${page.totalNumber }</b> 条 
						<c:if test="${page.currentPage != 1 }">
							<a href='javascript:changeCurrentPage("1")'>首页</a> 
							<a href="javascript:changeCurrentPage('${page.currentPage - 1 }')" >上一页</a>
						</c:if>
						 当前第<span>${page.currentPage }/${page.totalPage }</span>页
						 <c:if test="${page.currentPage != page.totalPage }">
						  	<a href='javascript:changeCurrentPage("${page.currentPage +1 }")'>下一页</a>
						  <a href='javascript:changeCurrentPage("${page.totalPage }")' >末页</a>
						  </c:if>
						   跳至&nbsp;<input id="cPage" type='text' value='${page.currentPage }' class='allInput w28' />&nbsp;页&nbsp; <a href='javascript:changeCurrentPage($("#cPage").val())' class='go'>GO</a>
				</div>
				</td>
			</tr>
	</table>
</form>
</body>
</html>