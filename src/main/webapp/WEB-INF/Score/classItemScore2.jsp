
<xml version="1.0" encoding="utf-8" />
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tags/fns.tld"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>content_body_academy</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
<script type="text/javascript" src="js/jquery.paginate.js"></script>
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>

<script type="text/javascript">
var id 

function requestjson(x){
	
	
	id= x.value
	
	 
	

 	$.ajax({
 		
		  url: "http://localhost:8080/hello?institute_id="+id,
	     
		  dataType: 'json',  
	      success:function(xx){	
	    	  
	    	  var Table = document.getElementById('myTable');
	    	     var rowNum=Table.rows.length;
	    	     for (i=0;i<rowNum;i++)
	    	     {
	    	    	 Table.deleteRow(i);
	    	         rowNum=rowNum-1;
	    	         i=i-1;
	    	     }     
	      }
	    
	});
} 
			
</script>



</head>






<c:set var="terms" value="${fns:getAllTerm()}"></c:set>

<body>

	<div class="navbar navbar-default" id="navbar">
		<!--<div class="navbar-container" id="navbar-container">-->
		<!--<div class="navbar-header">-->
		<div class="row" style="height: 90%; margin-top: -1px;">
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"
				style="height: 100%; margin-left: 1%;">
				<a href="#" class="navbar-brand"
					style="font-family: 微软雅黑; font-size: 12px; margin-top: -3%;">当前位置&nbsp;:&nbsp;学院&nbsp;>&nbsp;添加信息</a>
			</div>
			<div
				class="col-xs-8 col-sm-5 col-md-5 col-lg-5 col-xs-offset-4 col-md-offset-4 col-sm-offset-4 col-lg-offset-4"
				style="margin-left: 32%;">
				<a href="#" class="navbar-brand"
					style="font-family: 微软雅黑; font-size: 12px; margin-top: -2%;">当前登录&nbsp;:&nbsp;以梦为马</a>
				<a href="#" class="navbar-brand"
					style="font-family: 微软雅黑; font-size: 12px; margin-top: -2%;">欢迎登录本系统!</a>
			</div>
		</div>
	</div>




	<div class="main">
   <form id="my" action="getScoreByTerm" method="post">
	
			<div class="row" style="margin-top: -3%;">
			<div class="col-sm-3 col-md-3 col-lg-3"
				style="font-family: 微软雅黑; font-size: 13px;">
				选择学期&nbsp;:&nbsp;
				<div class="btn-group">		
					<select id="s2" class="form-control" name="term_id" >
                      <c:forEach items="${terms}" var="item">
                      <option value="${item.id }">${item.mark}</option>
                      </c:forEach>
                    </select>
				</div>
			</div>
	 <input type="hidden" name="class_id" value="${studentClass_id}">		
   <input type="submit" value="检索">
        </form>
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-12"
					style="margin-left: -15; margin-right: -15;">
					<div class="table-responsive">







<table id="myTable" border="1"   bgcolor=#FFF5EE >

  
<c:forEach items="${students}" var="student">
    <tr>
        <th>姓名</th>
        <td width="20%">${student.name }</td>
        <th>学号</th>
        <td width="20%">${student.studentAccount }</td>
		 <th>性别</th>
        <td width="20%">${student.sex}</td>
    </tr> 
    
    <c:if test="${scores!=null}">
   
    <c:forEach items="${scores}" var="score" varStatus="sta">
    
    

       <c:if test="${ student.id eq score.student_id }"> 
           
    <tr>
        <th>长跑：</th>
        <td width="20%">50</td>
        <th>跳远：</th>
        <td width="10%">30</td>
        <th width="20%">肺活量：</th>
        <td width="20%">5000</td>
    </tr>
    <tr>
        <th>耐力跑：</th>
        <td width="20%">5</td>
        <th>立定跳远：</th>
        <td width="10%">220</td>
        <th width="10%">坐位体前屈：</th>
        <td width="20%">4</td>
    </tr>
    <tr >
        <th>短跑：</th>
        <td width="20%">4.5</td>
        <th>性别</th>
        <td width="10%">男</td>
		 <th>性别</th>
        <td width="10%">男</td>
    </tr>
     </c:if>
    </c:forEach>
    </c:if>
     <tr bgcolor="red">
       
    </tr>
  
</c:forEach>
    
</table>				
					</div>
				</div>
			</div>

			<div class="content">
				<div id="demo4"></div>
			</div>
			<script type="text/javascript" src="js/jquery.min.js"></script>
			<script type="text/javascript" src="js/jquery.paginate.js"></script>
			<script type="text/javascript">
				$(function() {
					$("#demo4").paginate({
						count : 5,
						start : 1,
						display : 3,
						border : false,
						text_color : '#79B5E3',
						background_color : 'none',
						text_hover_color : '#2573AF',
						background_hover_color : 'none',
						images : false,
						mouse : 'press',
						onChange : function(page_index) {
					    location.href = "xxxAction?page=";
						}
					});
				});
			</script>
		</div>
</body>
</html>
