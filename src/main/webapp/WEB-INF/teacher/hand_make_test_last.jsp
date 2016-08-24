<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手动生成试卷</title>
<link href="css/choice_file.css" rel="stylesheet">
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
      <div id=top-bar>
	    <div id=topbar-inner>
	    </div>
     </div>
     
     <div class="main">

  <div class="container">
       <div class="row">
            <div class="col-md-8 col-md-offset-1  col-xs-8 col-xs-offset-1   col-lg-8 col-lg-offset-1 ">
                 <br>
                <form class="form-horizontal" action="${pageContext.request.contextPath}/make_finish" method="post"  accept-charset="utf-8">
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
                        <input type="hidden" name="questionnumber" value="${fn:length(sessionScope.questions)}">
                    <div class="form-group">
                        <label for="" class="col-sm-2 col-lg-2 col-md-2 control-label">试卷分数&nbsp;:</label>
                        <div class="col-sm-10 col-lg-10 col-md-10">
                          <input type="text" class="form-control" name="total_score" disabled="disabled" value="${sessionScope.total_score}">
                      
                          <div class="btn btn-group dropup" style="float:left; margin-left:110%; margin-top:-10%;"> 
                             <button type="submit" class=" btn btn-primary">确定</button>
                          </div>
                        </div>
                    </div>
                </form>    
            </div>
       </div>      
  </div>

<div class="row">
    <div class="col-sm-12 col-md-12 col-lg-12" style=" text-align:center;">
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover" style="font-size:12px;">
                <thead>
                    <tr class="active"  style="text-align:center;">
                        <th width="55">题目</th>
						<th width="300">选项</th>
						<th width="600">答案</th>
						<th width="55">选择</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.questions}" var="q"  varStatus="status" >
						<tr>
							<td>${status.index+1}</td> 
							<td>${q.title}</td>
							<td>${q.issue}</td>
							<td>${q.standard_answer}</td>
						</tr>	
					</c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>    
</div> 
</body>
</html>