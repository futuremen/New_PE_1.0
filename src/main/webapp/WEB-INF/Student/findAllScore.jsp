<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看本科所有学生成绩</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen"/>
<script type="text/javascript" src="js/jquery.paginate.js"></script>
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
</head>
<body>
	<%
		String exporToExcel=request.getParameter("exporToExcel");
		if(exporToExcel!=null&&exporToExcel.toString().equalsIgnoreCase("YES")){
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "inline;filename= "+new String( "成绩表.xls".getBytes("gb2312"), "ISO8859-1" ));
		}
	%> 
	<!--new String( fileName.getBytes("gb2312"), "ISO8859-1" )  -->
	<h3>本科所有学生成绩为</h3><hr>
	
	<div id=top-bar>
	    <div id=topbar-inner>
	    </div>
	</div>
		<div class="main">
        <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12" style="margin-left:-15;margin-right:-15;">
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover" style="font-size:12px;">
                    <thead>
                        <tr class="active" >
                            <td>学号</td>
							<td>姓名</td> 
							<td>试卷名</td>
							<td>得分</td>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.scores}" var="scores" > 
                        <tr>
	                        <td>${scores.user.studentNum}</td>
	                        <td>${scores.user.name}</td>
	                        <td>${scores.examPage.name }</td>
	                        <td> ${scores.final_score }</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${param.exporToExcel ==null}">
					<a href="${pageContext.request.contextPath }/student/findAllScore/${requestScope.exampageId}?exporToExcel=YES">导出成Excel</a>
				</c:if>
            </div>
        </div>
        </div>    
    
</div>    
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.paginate.js"></script>
</body>
</html>