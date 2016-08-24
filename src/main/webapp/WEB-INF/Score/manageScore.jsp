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

var s2_id

function requestjson(x){
	
	id= x.value
	
	
	
	$("#s2").empty()

 	$.ajax({	
		  url: "http://localhost:8080/hello?institute_id="+id,
		  dataType: 'json',  
	      success:function(xx){	
	    	 for(var i =0;i<xx.length;i++)
	    		 {	 
	    		 document.getElementById("s2").options.add(new Option(xx[i].name,xx[i].id)); 
	    		 }	      
	      } 
	});
} 



function changS2(x){
	
	s2_id= x.value
	


 	$.ajax({	
		  url: "http://localhost:8080/helloClass?class_id="+s2_id,
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
	    	     
	    	     NewRow = Table.insertRow();                        //添加行
	    	     NewCell1= NewRow.insertCell();                     //添加列
	    	     NewCell2=NewRow.insertCell();
	    	     NewCell3=NewRow.insertCell();
	    	     NewCell4=NewRow.insertCell();
	    	     NewCell5=NewRow.insertCell();
	    	     
	    	     NewCell1.innerHTML = "1";          //添加数据
	    	     NewCell2.innerHTML = xx.name;   
	    	     NewCell3.innerHTML = xx.number;  
	    	     NewCell4.innerHTML = xx.major;   
	    	    
	    	     NewCell5.innerHTML="<a href=viewClassScore?class_id="+xx.id+">查看班级成绩</a>";

	    	      
	      } 
	});
} 


			
</script>



</head>


<c:set var="institutes" value="${fns:getAllInstitute(null)}"></c:set>

<c:set var="classes" value="${fns:getAllClass()}"></c:set>


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
  
		<div class="row" style="margin-top: -3%;">
			<div class="col-sm-3 col-md-3 col-lg-3"
				style="font-family: 微软雅黑; font-size: 13px;">
				选择院系&nbsp;:&nbsp;
				<div class="btn-group">		
					<select id="s1" class="form-control" name="institute_id" onchange="requestjson(this)">
                      <c:forEach items="${institutes}" var="item"> 
                    <option value="${item.id}">${item.instituteName}</option>
                     
                      </c:forEach>
                    </select>
				</div>
			</div>
         <form id="my" action="manageScore" method="post">
			<div class="row" style="margin-top: -3%;">
			<div class="col-sm-3 col-md-3 col-lg-3"
				style="font-family: 微软雅黑; font-size: 13px;">
				选择班级&nbsp;:&nbsp;
				<div class="btn-group">		
					<select id="s2" class="form-control" name="class_id" >
                      <c:forEach items="${classes}" var="item">
                      <option value="${item.id}">${item.name}</option>
                      </c:forEach>
                    </select>
				</div>
			</div>
			<div class="row" style="margin-top: -3%;">
			<div class="col-sm-3 col-md-3 col-lg-3"
				style="font-family: 微软雅黑; font-size: 13px;">
				&nbsp;&nbsp;
				<div class="btn-group">		
					<input type="submit" value="检索信息">
				</div>
			</div>
			
			  </form>
			



         

      
			<!--  <a href="insertInstitute" style="margin-right:10%;"><img src="images/bian.png">&nbsp;&nbsp;添加</a>  -->
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-12"
					style="margin-left: -15; margin-right: -15;">
					<div class="table-responsive">
						<table id="myTable" class="table table-striped table-bordered table-hover"
							style="font-size: 12px;">
							<thead>
								<tr class="active">
									<th>序号</th>
									<th>班级编号</th>
									<th>班级名称</th>
									<th>主修专业</th>
									<th>基本操作</th>
								</tr>
							</thead>
							<tbody>
							
							
							<c:if test="${classItem==null }">
						       
								<c:forEach items="${classes}" var="classItem" varStatus="sta">
									<tr>
										<td>${sta.count }</td>
										<td>${classItem.number }</td>
										<td>${classItem.name }</td>
										<td>${classItem.major }</td>
										<td><a href="viewClassScore?class_id=${classItem.id}"
											style="margin-right: 10%;"><img src="images/bian.png">&nbsp;&nbsp;查看班级成绩</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							</tbody>
							
							<tbody>
							
							
							<c:if test="${classItem!=null }">
						       
								
									<tr>
										<td>1</td>
										<td>${classItem.number }</td>
										<td>${classItem.name }</td>
										<td>${classItem.major }</td>
										<td><a href="viewClassScore?class_id=${classItem.id}"
											style="margin-right: 10%;"><img src="images/bian.png">&nbsp;&nbsp;查看班级成绩</a>
										</td>
									</tr>
								
							</c:if>
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
