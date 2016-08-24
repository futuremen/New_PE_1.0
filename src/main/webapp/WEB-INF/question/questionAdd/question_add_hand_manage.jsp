<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//得到url的根路径
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	.container{margin:1%; padding:60px 100px; background-color:#FFF; line-height:30px;  width:98%;}
	body { overflow-x:hidden;height:100%; }  
</style>
<link href="${pageContext.request.contextPath }/css/right4.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/alert-handle.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/alert-handle2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-2.0.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		var dStatus = 1;
		$(function() {
			$(".add").bind("click", function(){
				if(dStatus == 1){
					this.href= this.href + $("#department option:selected").val();
					dStatus ++;
				}
				this.setAttribute("onclick", '');
				this.click("return false"); 
			});
		});
	</script>
	
<div id=top-bar>
    <div id=topbar-inner>
    </div>
</div>


 <div class="container">
 	  <h1 style="margin-left: 40%;">试题手动添加管理总界面 </h1>
       <div class="row">
        <div class="col-md-12 col-md-offset-1 col-xs-12 col-xs-offset-1   col-lg-12 col-lg-offset-1 ">
           <form class="form-horizontal">	
                  <div class="form-group" style="margin-left: 10%;">
                        <label for="" class="col-sm-3 control-label">系别&nbsp;:</label>
                        <div class="col-sm-2">
                        <select id="department" class="form-control">
							<c:forEach items="${departmentList }" var="department"
								varStatus="status">
								<c:if test="${status.first }">
									<option value="${department.dp_id }" selected>${department.dp_name }</option>
								</c:if>
								<c:if test="${!status.first }">
									<option value="${department.dp_id }">${department.dp_name }</option>
								</c:if>
							</c:forEach>
						</select>
                       </div>
                  </div>           
           </form>  
           </br>
           <div class="col-sm-6 col-md-6 col-lg-6" style="margin-left:27%; margin-top: -2%;">
                 <a class="add" href="${pageContext.request.contextPath }/question_add_hand_selectOne/">添加单选题</a>&nbsp;&nbsp;&nbsp;
				<a class="add" href="${pageContext.request.contextPath }/question_add_hand_selectMany/">添加多选题</a>&nbsp;&nbsp;&nbsp;
				<a class="add" href="${pageContext.request.contextPath }/question_add_hand_fill/">添加填空题</a>&nbsp;&nbsp;&nbsp;
				<a class="add" href="${pageContext.request.contextPath }/question_add_hand_judge/">添加判断题</a>&nbsp;&nbsp;&nbsp;
           </div>             
       	</div>
       </div>        
    <hr>   
    	 <script type="text/javascript">
			var qStatus = 1;
			$(function() {
				$(".control").bind("click", function(){
					if(qStatus == 1){
						this.href= this.href + $("#department option:selected").val()+"/"+$("#questionBank option:selected").val();
						qStatus ++;
					}
					this.setAttribute("onclick", '');
					this.click("return false"); 
				});
			});
		</script> 
       <div class="row">
        <div class="col-md-12 col-md-offset-1 col-xs-12 col-xs-offset-1   col-lg-12 col-lg-offset-1 ">
        
           <form class="form-horizontal">	
                  <div class="form-group" style="margin-left: 13%;">
                        <label for="" class="col-sm-3 control-label">所教科目&nbsp;:</label>
                        <div class="col-sm-2">
                        	<select id="questionBank"class="form-control">
								<c:forEach items="${questionBankList }" var="questionBank"
									varStatus="status">
									<c:if test="${status.first }">
										<option  value="${questionBank.id }" selected>${questionBank.name }</option>
									</c:if>
									<c:if test="${!status.first }">
										<option value="${questionBank.id }">${questionBank.name }</option>
									</c:if>
								</c:forEach>
							</select>
                       </div>
                  </div>           
           </form>  
           </br>
           <div class="col-sm-6 col-md-6 col-lg-6" style="margin-left:27%; margin-top: -2%;">
                
                <a class="control" href="${pageContext.request.contextPath }/question_control_hand_selectOne/">管理单选题</a>&nbsp;&nbsp;&nbsp;
				<a class="control" href="${pageContext.request.contextPath }/question_control_hand_selectMany/">管理多选题</a>&nbsp;&nbsp;&nbsp;
				<a class="control" href="${pageContext.request.contextPath }/question_control_hand_fill/">管理填空题</a>&nbsp;&nbsp;&nbsp;
				<a class="control" href="${pageContext.request.contextPath }/question_control_hand_judge/">管理判断题</a>&nbsp;&nbsp;&nbsp;
           </div>             
       	</div>
       </div> 
	</div>
</body>
</html>