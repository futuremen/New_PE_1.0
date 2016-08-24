<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	.span{
	 color: blue;
	}
	body{ margin-top:0; margin-left:0; background-color:#F1F3FA; height:100%; font-family:"微软雅黑";overflow:auto;}
			.main{margin:1%; padding:60px 100px; background-color:#FFF; line-height:30px; border:solid 1px #DEE0E0; width:99%;}
			.dropdown{ float:left; margin-left:1%;}
			.row thead tr th{ text-align:center;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除试卷</title>
</head>
<link href="${pageContext.request.contextPath}/css/choice_file.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<body>
	<div class="main">
		<div class="row">
    <div class="col-sm-12 col-md-12 col-lg-12" style=" text-align:center;">
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover" style="font-size:12px;">
                <thead>
                    <tr class="active"  style="text-align:center;">
                        <th width="55">标号</th>
						<th width="100">试卷名称</th>
						<th width="300">试卷描述</th>
						<th width="55">考试时间</th>
						<th width="55">试卷总分数</th>
						<th width="55">题数</th>
						<th width="80">删除</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.page.recordList}" var="ex" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td>${ex.name}</td>
							<td>${ex.description }</td>
							<td>${ex.examTime}</td>
							<td>${ex.total_score}</td>
							<td>${ex.questionnumber}</td>
							<td><a href="${pageContext.request.contextPath }/delete_exam/${ex.id}">删除</a></td>
						</tr>
					</c:forEach>
                </tbody>
            </table>
            共<b>${requestScope.page.recordCount}</b>条 
	<c:forEach begin="${requestScope.page.beginPageIndex}" end="${requestScope.page.endPageIndex}" var="index">
		<a href="${pageContext.request.contextPath }/delete_exam_ui/${index}">${index}</a>
	</c:forEach>
        </div>
    </div>
</div> 
	</div>
</body>
</html>