<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tags/fns.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>content_body_academy</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen"/>
<script type="text/javascript" src="js/jquery.paginate.js"></script>
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
</head>

<body>
   <%
   
   String wrong = (String)request.getAttribute("deleteWrong");
   String success = (String)request.getAttribute("success");
   String display=null;
    if(success!=null && wrong == null  ){
	   display=success;
   }
   else  if(wrong !=null && success == null ){
	   display=wrong;
   } else{
	   display="";
   }
   
   
   %>
   <h1><%=display %></h1>
   
   
   

   
   
   
        <div class="navbar navbar-default" id="navbar">
                <!--<div class="navbar-container" id="navbar-container">-->
                    <!--<div class="navbar-header">-->
                     <div class="row"  style="height:90%;margin-top:-1px;">
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3" style="height:100%;margin-left:1%;">
                               <a href="#" class="navbar-brand" style="font-family:微软雅黑;font-size:12px;margin-top:-3%;">当前位置&nbsp;:&nbsp;学院&nbsp;>&nbsp;添加信息</a>
                        </div>
                        <div class="col-xs-8 col-sm-5 col-md-5 col-lg-5 col-xs-offset-4 col-md-offset-4 col-sm-offset-4 col-lg-offset-4" style="margin-left:32%;">
                               <a href="#" class="navbar-brand" style="font-family:微软雅黑;font-size:12px;margin-top:-2%;">当前登录&nbsp;:&nbsp;以梦为马</a>
                               <a href="#" class="navbar-brand" style="font-family:微软雅黑;font-size:12px;margin-top:-2%;">欢迎登录本系统!</a>
                        </div>
                     </div>
        </div>
 
 <div class="main">
  <a href="insertOrder" style="margin-right:10%;"><img src="images/bian.png">&nbsp;&nbsp;添加</a> 
<div class="row">

<div class="col-sm-12 col-md-12 col-lg-12" style="margin-left:-15;margin-right:-15;">
    <div class="table-responsive">
        <table class="table table-striped table-bordered table-hover" style="font-size:12px;">
            <thead>
                <tr class="active" >
                    <th>序号</th>
                    <th>项目1</th>
                    <th>项目2</th>
                    <th>项目3</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>测试人数</th>
                    
                    <th>基本操作</th>
                </tr>
            </thead>
            <tbody>
            <c:set var="teacher_id" value="${teacher_id}"/>
         
            <c:forEach items="${fns:getTeacherOrderInfoByPage(teacher_id)}" var="order" varStatus="sta">
     
                <tr>
                <td>${sta.count }</td>
                <td>${order.one }</td>
                <td>${order.two}</td>
                <td>${order.three }</td>

                
               
                

                <td ><fmt:formatDate  value="${order.startDate}" type="both" dateStyle="default"/></td>
                <td ><fmt:formatDate value="${order.endDate}" type="both" dateStyle="default"/></td>

                <td>${order.count }</td>
                
                <td>
                    <a href="updateOrder?order_id=${order.id}" style="margin-right:10%;"><img src="images/bian.png">&nbsp;&nbsp;编辑</a>     
                    <a href="deleteOrder?order_id=${order.id}" class="tablelink1"><img src="images/shan.png">&nbsp;&nbsp;删除</a>
                    <a href="viewOrderTeacher?order_id=${order.id}" class="tablelink1"><img src="images/shan.png">&nbsp;&nbsp;预约人员</a>
                </td>
                </tr>
               </c:forEach>
            </tbody>
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
		count 		: 5,
		start 		: 1,
		display     : 3,
		border					: false,
		text_color  			: '#79B5E3',
		background_color    	: 'none',	
		text_hover_color  		: '#2573AF',
		background_hover_color	: 'none', 
		images		: false,
		mouse		: 'press',
		onChange: function(page_index) {
       location.href = "xxxAction?page=";
      }
	});
});
</script>


</div>

</body>
</html>
