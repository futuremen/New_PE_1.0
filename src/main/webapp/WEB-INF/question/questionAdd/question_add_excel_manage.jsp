<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		.file-box{ position:relative;width:340px}
		.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
		.btn{ background-color:#FFF; border:1px solid #CDCDCD; width:70px;}
		.file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
		body{ margin-top:0; margin-left:0; background-color:#F1F3FA; height:100%; font-family:"微软雅黑";overflow:auto;}
			.main{margin:1%; padding:60px 100px; background-color:#FFF; line-height:30px; border:solid 1px #DEE0E0; width:99%; height:auto;}
			.form-group textarea{ margin-left:18.4%; float:left;}
			.row thead tr th{ text-align:center;}
	</style>
<title>Insert title here</title>
<link href="css/choice_file.css" rel="stylesheet">
</head>
<script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
	var list=new Array();
	$(function(){
		$("#b1").click(function(){
			$("#excel_selectOne").click();
		});
		$("#b2").click(function(){
			$("#excel_selectMany").click();
		});
		$("#b3").click(function(){
			$("#excel_judge").click();
		});
		$("#b4").click(function(){
			$("#excel_fill").click();
		});
		
		$("#excel_selectOne").change(function(){
			var data=$("#textfield").attr("id");
			if($(this).val()!=null){
				list.push(data);
			}
		});
		$("#excel_selectMany").change(function(){
			var data=$("#textfield2").attr("id");
			if($(this).val()!=null){
				list.push(data);
			}
		});
		$("#excel_judge").change(function(){
			var data=$("#textfield3").attr("id");
			if($(this).val()!=null){
				list.push(data);
			}
		});
		$("#excel_fill").change(function(){
			var data=$("#textfield4").attr("id");
			if($(this).val()!=null){
				list.push(data);
			}
		});
		$(":input[type='submit']").click(function(){
			$(":input[type='hidden']").attr("value",list);
		});
		$(":input[type='text']").change(function(){
			if($(this).attr("value")==undefined){
				list.splice($.inArray($(this).attr("id")+"",list),1);
			}
		});
		
		$("#subdiv").click(function(){
			$("#form").submit();
		});
	})
</script>
<body>

<div id=top-bar>
    <div id=topbar-inner>
    </div>
</div>

<div class="main">
	<div  class="row">
<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12" id="head"> 通过Excel导入试题总界面:
<hr/>
</div>
</div>

<div class="row" style="margin-top:-3%;">
  <div class="col-sm-2 col-md-2 col-lg-2" style="padding:-15px;font-family:微软雅黑;font-size:13px;">
        <div class="dropdown">院系:
          <select name="dp_id"  data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;" >
				<c:forEach items="${requestScope.departmentList}" var="dt">
					<option value="${dt.dp_id}">${dt.dp_name}</option>
				</c:forEach>
		  </select> 
       </div>
  </div>
  <div class="col-sm-2 col-md-2 col-lg-2" style="padding:-15px;font-family:微软雅黑;font-size:13px;">
        <div class="dropdown">科目:
        	<select name="id"  data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;">
					<c:forEach items="${requestScope.qbs}" var="q">
						<option value="${q.id}">${q.name}</option>
					</c:forEach>
		     </select>
       </div>
  </div>
  <div class="col-sm-2 col-md-2 col-lg-2" style="padding:-15px;font-family:微软雅黑;font-size:13px;">
        <div class="dropdown">难易度:
        	<select name="d_id" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;"> 
   					<c:forEach items="${requestScope.dgs}" var="dg">
   						<option value="${dg.d_id}">${dg.d_degree}</option>
   					</c:forEach>
 		   </select><br>
       </div>
  </div>
</div>
  <div class="container">
       <div class="row">
            <div class="col-md-8 col-md-offset-1  col-xs-8 col-xs-offset-1   col-lg-8 col-lg-offset-1 ">
                 <br>
                 <p style="margin-left:4%; margin-top:-4%;">添加试题请务必按以下格式进行添加否则无效&nbsp;:</p><br>
                 <p style="margin-left:4%; margin-top:-4%;">单选题&nbsp;:</p> 
                 <img alt="单选题" src="${pageContext.request.contextPath}/images/select.jpg" > 
                <form class="form-horizontal"  id="form" action="${pageContext.request.contextPath}/question_get_excel" method="Post" enctype="multipart/form-data" >
                    <div class="form-group">
                        <label for="" class="col-sm-2 col-lg-2 col-md-2 control-label">添加单选题&nbsp;:</label>
                        <div class="col-sm-5 col-lg-5 col-md-5">
                        	<input type='text' name='textfield2' id='textfield2' class='txt' />
							<input type='button' class='btn' value='浏览...' id="b2"/>
							<input type="file" name="excel_selectMany" class="file"  id="excel_selectMany" size="28" onchange="document.getElementById('textfield2').value=this.value" />
                        </div>
                    </div>
                    <br>
                    <br>
                    <br>
                    <hr>
                    <p style="margin-left:4%; margin-top:-4%;">多选题&nbsp;:</p> 
                 	<img alt="多选题" src="${pageContext.request.contextPath}/images/selects.jpg" > 
                    <div class="form-group">
                        <label for="" class="col-sm-2 col-lg-2 col-md-2 control-label">添加多选题&nbsp;:</label>
                        <div class="col-sm-5 col-lg-5 col-md-5">
                       		<input type='text' name='textfield2' id='textfield2' class='txt' />
							<input type='button' class='btn' value='浏览...' id="b2"/>
							<input type="file" name="excel_selectMany" class="file"  id="excel_selectMany" size="28" onchange="document.getElementById('textfield2').value=this.value" />
                        </div>
                    </div>
                    <br>
                    <br>
                    <br>
                    <hr>
                    <p style="margin-left:4%; margin-top:-4%;">判断题&nbsp;:</p> 
                 	<img alt="判断题" src="${pageContext.request.contextPath}/images/juge.jpg" > 
                    <div class="form-group">
                        <label for="" class="col-sm-2 col-lg-2 col-md-2 control-label">添加判断题&nbsp;:</label>
                        <div class="col-sm-5 col-lg-5 col-md-5">
                        	<input type='text' name='textfield3' id='textfield3' class='txt' />
							<input type='button' class='btn' value='浏览...' id="b3"/>
							<input type="file" name="excel_judge" class="file"  id="excel_judge" size="28" onchange="document.getElementById('textfield3').value=this.value" /><br>
                        </div>
                    </div>
                    <br>
                    <br>
                    <br>
                    <hr >
                    <p style="margin-left:4%; margin-top:-4%;">填空题&nbsp;:</p> 
                 	<img alt="填空题" src="${pageContext.request.contextPath}/images/fill.jpg" > 
                    <div class="form-group">
                        <label for="" class="col-sm-2 col-lg-2 col-md-2 control-label">添加填空题&nbsp;:</label>
                        <div class="col-sm-5 col-lg-5 col-md-5">
                        	<input type='text' name='textfield4' id='textfield4' class='txt' />
							<input type='button' class='btn' value='浏览...' id="b4"/>
							<input type="file" name="excel_fill" class="file"  id="excel_fill" size="28" onchange="document.getElementById('textfield4').value=this.value" />
                        </div>
                    </div>
                    <div id="subdiv" class="btn btn-primary" style=" margin-left:18%;">
                    	<input type="hidden" name="datas">
                    		上传
                    </div>
                </form>    
            </div>
       </div>      
     </div>
    </div>
</body>
</html>