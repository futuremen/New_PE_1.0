<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自动生成试卷</title>
<link href="${pageContext.request.contextPath}/css/choice_file.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<style type="text/css">
	body{ margin-top:0; margin-left:0; background-color:#F1F3FA; height:100%; font-family:"微软雅黑";overflow:auto;}
	.main{margin:1%; padding:60px 100px; background-color:#FFF; line-height:30px; border:solid 1px #DEE0E0; width:99%; height:auto;}
	.form-group textarea{ margin-left:18.4%; float:left;}
	.row thead tr th{ text-align:center;}
</style>
</head>
<body>
<div class="main">
	<div class="container">
       <div class="row">
            <div class="col-md-8 col-md-offset-1  col-xs-8 col-xs-offset-1   col-lg-8 col-lg-offset-1 ">
                 <br>
                <form class="form-horizontal" action="${pageContext.request.contextPath}/uo_make_finish" method="post"  accept-charset="utf-8" >
                  <div class="form-group" style="font-family:微软雅黑;font-size:13px;margin-left:4.5%; margin-top:-4%;">
    				<div class="dropdown" style="display:none">
    				
			             <select name="dp_id" id="department" class="btn btn-default drop-toggle" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;">
						   <c:forEach items="${requestScope.departments}" var="dt">
							<option value="${dt.dp_id}">${dt.dp_name}</option>
						   </c:forEach>
						 </select>
						 
					         <select name="subject" id="subject" class="btn btn-default drop-toggle" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;" >
									<c:forEach items="${requestScope.qbs}" var="q">
										<option value="${q.id}">${q.name}</option>
									</c:forEach>
							</select>
							</div>
							<div class="dropdown" >
							
						困难度:
			             <select name="d_id"   class="btn btn-default drop-toggle" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;">
							<c:forEach items="${requestScope.dgs}" var="dg">
		   						<option value="${dg.d_id}">${dg.d_degree}</option>
		   					</c:forEach>
						</select> 
           			</div>
           			<br>
    		 	</div>
                  <p style="margin-left:4%; margin-top:-4%;">试卷简述&nbsp;:</p>  
                    <div class="form-group">
                           <textarea  name="description" cols="40" rows="2" ></textarea>
              		</div>
                    <div class="form-group">
                        <label  class="col-sm-2 col-lg-2 col-md-2 control-label">试卷名称&nbsp;:</label>
                        <div class="col-sm-10 col-lg-10 col-md-10">
                          <input type="text" class="form-control" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 col-lg-2 col-md-2 control-label">考试时间&nbsp;:</label>
                        <div class="col-sm-10 col-lg-10 col-md-10">
                          <input type="text" class="form-control" name="examTime">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 col-lg-2 col-md-2 control-label">试卷分数&nbsp;:</label>
                        <div class="col-sm-10 col-lg-10 col-md-10">
                          <input type="text" class="form-control" name="total_score" ">
                          <div class="btn btn-group dropup" style="float:left; margin-left:110%; margin-top:-10%;"> 
                             <button type="submit" class="btn btn-primary">确定</button>
                          </div>
                        </div>
                    </div>
                </form>    
            </div>
       </div>      
  </div>
</div>
</body>
</html>