<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tags/fns.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setCharacterEncoding("utf8");
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">

<title>4</title>
<link href="css/insert.css" rel="stylesheet">

<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
body {
	margin-top: 0;
	margin-left: 0;
	background-color: #F1F3FA;
	width: 100%;
	height: 100%;
	font-family: "微软雅黑";
	overflow: auto;
}

#top-bar {
	z-index: 999;
	position: absolute;
	padding-bottom: 0px;
	margin: 0px;
	padding-left: 0px;
	width: 100%;
	padding-right: 0px;
	height: 14px;
	overflow: hidden;
	top: 0px;
	padding-top: 0px;
	left: 0px;
	margin-left: 1%;
}

#topbar-inner {
	background: #F1F3FA;
	height: 14px;
	border-top: solid 1px #ccc;
	border-bottom: solid 1px #ccc;
}

html>body #top-bar {
	position: fixed
}

.container {
	margin: 1%;
	padding: 60px 100px;
	background-color: #FFF;
	line-height: 30px;
	border: solid 1px #DEE0E0;
	width: 100%;
	height: auto;
}
</style>



<script src="My97DatePicker/WdatePicker.js"></script>

</head>




<body>
	<div id=top-bar>
		<div id=topbar-inner></div>
	</div>

	<div class="container">

		<c:if test="${message!=null }">
	
		<c:forEach items="${message }" var="error">
			<h2 >${error.defaultMessage } </h2>
			
		
		</c:forEach>
	
	</c:if>
		<div class="row">
			<div
				class="col-md-8 col-md-offset-2  col-xs-8 col-xs-offset-2   col-lg-8 col-lg-offset-2 ">
				<br>
				<form id="MyForm" class="form-horizontal" action="insertOrder"
					method="post" accept-charset="UTF-8">
<%-- 					> <br> <input name="id" type="hidden" value="${order.id }"> --%>
				
					<!--<div style="border-left:#3399FF solid 2px; padding:10px;"><h4>修改人员信息</h4></div>
                <hr>-->
					<div class="form-group">
						<label for="" class="col-sm-3 control-label">项目1</label>
						<div class="col-sm-7">
							<input name="one" type="text" class="form-control"
								>
						</div>
					</div> 

					<div class="form-group">
						<label for="" class="col-sm-3 control-label">项目2</label>
						<div class="col-sm-7">
							<input name="two" type="text" class="form-control"
								>
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-3 control-label">项目3</label>
						<div class="col-sm-7">
							<input name="three" type="text" class="form-control"
								>
						</div>
					</div>
			
				<div class="form-group">
						<label for="" class="col-sm-3 control-label">开始时间</label>
						<div class="col-sm-7">
							<input type="text"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm:ss'})" name="startDate"/>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-3 control-label">结束时间</label>
						<div class="col-sm-7">
					
						<input type="text"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm:ss'})" name="endDate"/>
								
								
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-3 control-label">测试人数</label>
						<div class="col-sm-7">
							<input name="count" type="text" class="form-control"
								>
						</div>
					</div>



			


					<div class="form-group">
						<br>
						<div class="col-sm-1 col-sm-offset-7 ">
							 <button type="reset" class="btn btn-primary">取消</button> 
						</div>
						<div class="col-sm-1 col-sm-offset-1 ">
							<button type="submit" class="btn btn-primary">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div style="height: 50px;"></div>
	</div>


	<script src="js/jquery-2.1.4.js"></script>

</body>
</html>
