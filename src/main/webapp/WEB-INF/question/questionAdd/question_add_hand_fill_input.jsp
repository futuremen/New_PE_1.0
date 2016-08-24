
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加选择题的界面</title>
<link href="${pageContext.request.contextPath }/resources/css/choice_file.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap.js"></script>
<style type="text/css">
body{ margin-top:0; margin-left:0; background-color:#F1F3FA; height:100%; font-family:"微软雅黑";overflow:auto;}
.main{margin:1%; padding:60px 100px; background-color:#FFF; line-height:30px; border:solid 1px #DEE0E0; width:99%; height:600px;}
</style>
</head>
<body>




<div id=top-bar>
    <div id=topbar-inner>
    </div>
</div>



<div class="main">

<!--<div div class="row">
<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12" id="head"> 通过Excel导入试题总界面:
<hr/ >
</div>
</div>-->
<form:form action="${pageContext.request.contextPath }/fillQuestionAddSuccess" modelAttribute="fQuestion" method="POST">
	<form:hidden path="id"/>
<div class="row" style="margin-top:-1%; margin-left:3%;">
  <div class="col-sm-3 col-md-3 col-lg-3" style="font-family:微软雅黑;font-size:13px;">
   		
        <div class="dropdown">所属题库:
        <form:select path="qb.id" class="btn btn-default drop-toggle" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;">
				<form:option value="" label="--请选择--"/>
				<form:options items="${questionBankList }" itemLabel="name" itemValue="id"/>
		</form:select>
       </div>
  </div>
  <div class="col-sm-3 col-md-3 col-lg-3" style="font-family:微软雅黑;font-size:13px;">
        <div class="dropdown">难易程度:
        <form:select path="degree.d_id" class="btn btn-default drop-toggle" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;">
				<form:option value="" label="--请选择--"/>
				<form:options items="${degreeList }" itemLabel="d_degree" itemValue="d_id"/>
		</form:select>
       </div>
  </div>
  <div class="col-sm-3 col-md-3 col-lg-3" style="font-family:微软雅黑;font-size:13px;">
        <div class="dropdown">所属系别:
       	<form:input path="department.dp_id"class="btn btn-default drop-toggle" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;"/>
       </div>
  </div>
  <div class="col-sm-3 col-md-3 col-lg-3" style="font-family:微软雅黑;font-size:13px;">
        <div class="dropdown">所属题型:
        <form:input path="type.id"class="btn btn-default drop-toggle" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;"/>
       </div>
  </div>
  <div class="container">
       <div class="row">
            <div class="col-md-8 col-md-offset-1  col-xs-8 col-xs-offset-1   col-lg-8 col-lg-offset-1 ">
                 <br>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="" class="col-sm-2 col-lg-2 col-md-2 control-label">题目名称&nbsp;:</label>
                        <div class="col-sm-7 col-lg-7 col-md-7">
                        	<form:input path="title"class="form-control"/>
                        </div>
                    </div>
                    <br></br>
                   
                    <div class="form-group">
                        <label for="" class="col-sm-2 col-lg-2 col-md-2 control-label">标准答案&nbsp;:</label>
                        <div class="col-sm-7 col-lg-7 col-md-7">
                        	<form:input path="standard_answer" class="form-control"/>
                        </div>
                    </div>
                    <br></br>
                    <div class="form-group">
                        <label for="" class="col-sm-2 col-lg-2 col-md-2 control-label">题目分数&nbsp;:</label>
                        <div class="col-sm-7 col-lg-7 col-md-7">
                        	<form:input path="score"  class="form-control"/>
                        </div>
                    </div>
                    <input type="submit" value="提交"/>  
                </form> 
            </div>
       </div>
     </div>
</div> 
</form:form>
</div>
</body>
</html>
