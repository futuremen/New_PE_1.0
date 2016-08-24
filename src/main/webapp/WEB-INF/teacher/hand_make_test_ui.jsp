<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	.span{
	 color: blue;
	}
	body{ margin-top:0; margin-left:0; background-color:#F1F3FA; height:100%; font-family:"微软雅黑";overflow:auto;}
			.main{margin:1%; padding:60px 100px; background-color:#FFF; line-height:30px; border:solid 1px #DEE0E0; width:99%;}
			.dropdown{ float:left; margin-left:1%;}
			.row thead tr th{ text-align:center;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手动生成试卷</title>
<link href="${pageContext.request.contextPath}/css/choice_file.css" rel="stylesheet">
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<script type="text/javascript">
    //当前页
	var currentPage = 1;
    //首页
    var begin;
    //尾页
    var end;
    
	//隐藏返回按钮
	$("#rt").hide();
	//定义全局数组
	var selectdatas = new Array();
	//确定按钮的状态
	var statement = 1;
	//提交按钮的有效
	var state = 1;
	//单个分数
	var onescore=0;
	//总分数的全局变量
	var score=0;
	//确定按钮加上监听事件
	$(function() {
		$("#test1").click(function(){
		});
		$("#btn").click(
		//ajax方法
		function getdata() {
			var subject = $("#subject").val();
			var qtype = $("#qtype").val();
			var department=$("#department").val();
			var datas = {
				"subject" : subject,
				"qtype" : qtype,
				"department":department,
				"currentPage" : currentPage
			}
			
			var url = "hand_make_test";
			if (state == 0) {
				return false;
			}
			$.post(url,datas,function(data) {
								var question = data;
								$.each(question,function(i, n) {
													if (n.pageCount != undefined) {
														//  n   page对象
														//能得到首页，尾页和总页数
														begin=n.beginPageIndex-1;
														end=n.endPageIndex+1;
														if (n.beginPageIndex != 1) {
															$("#div").append("<span id='aa'  class='span'><<<</span>")
														}
														
														for (var i = 0; i < n.pageCount; i++) {
															var index;
															if(index!=n.endPageIndex){
																index=n.beginPageIndex+i;
															$("#div").append(
																"<span id='aa'  class='span' > " + index +"</span>")
															}
														}
														
														if (n.pageCount != n.endPageIndex) {
															$("#div").append("<span id='aa' class='span' >>>></span>")
														}
													} else {
														//   n   questions集合
														$.each(n,function(i,n) {
																			onescore=n.score;
																			$("#tb").append(
																							"<tr id='empty' class='active'>"
																									+ "<td width='300'>"
																									+ n.title
																									+ "<td width='600'>"
																									+ n.issue
																									+ "</td>"
																									+ "<td width='55'>"
																									+ n.standard_answer
																									+ "</td>"
																									+"<td width='55'>"
																									+"<span>"+onescore+"分"+"</span>"
																									+"</td>"
																									+ "<td width='55'>"
																									+"<div class='checkbox'>"
																									+"<label class='checkbox-inline'>"
																									+ getc(n.id)
																									+"</label>"
																									+"</div>"
																									+ "</td>"
																									+ "</tr>");
																		});
													}
												});
							});

			if (statement == 1) {
				$("#div1").append("<input type='submit' value='提交' id='sm'>");
				$("#rt").show();
			}
			statement = 2;
			state = 0;
		}
	);
		//返回按钮的监听事件
		$("#rt").click(function() {
			location.href = "index.jsp";
		});

		//提交按钮的监听事件  
		$(document).on("click", "#sm", function() {
			var datas = selectdatas.toString();
			$(":input[name='datas']").attr("value", datas);
			$(":input[name='total_score']").attr("value",score.toString());
			$("#form").submit();

		});
		//判断数组是否被选中
		$(document).on("click", "#juge", function() {
			var data= $(this).attr("value");
			var d = $(this).attr("checked");
			
			if (d != null) {
				//alert($.inArray(data+"",selectdatas))
				//alert("取消选中!");
				$(this).attr("checked", false);
				//去除取消的元素
				selectdatas.splice($.inArray(data+"",selectdatas),1);
				alert(selectdatas);
				score=score-onescore;
			} else {
				//alert($.inArray(data+"",selectdatas))
				//alert("被选中了!")
				$(this).attr("checked", true);
				//把得到的数据放到数组里面 
				selectdatas.push(data);
				//alert(selectdatas);
				score=onescore+score;
			}
			//alert(selectdatas);
			$("#score").click();
	 });
		$("#score").click(function(){
			$(this).text(score);
		});
		
	   //select改变清空#tb
	   $("select").change(function(){
		   //清空
		   $("#tb").empty();
		   $("#div").empty();
		   //改变#btn的有效性	
		   state=1;
	   });
	   
   })
		//为所有a标签添加单机事件
		$(document).on("click","#aa",function(){
			state=1;
			var value;
			value=$(this).text();
			if(value==">>>"){
				value=end;
			}
			if(value=="<<<"){
				value=begin;
			}
			currentPage=value;	
			$("#tb").empty();
			$("span").empty();
			$("#btn").click();
		});
	
		//返回字符串
		
		function getc(i){
			if($.inArray(i+"",selectdatas)!=-1){
				return "<input id='juge' type='checkbox'  checked='checked' value= '" +i+"'  />"
			}
			    return "<input id='juge' type='checkbox'  value= '" +i+"' />"
		}
		
</script>
<body>
	<div id=top-bar>
        <div id=topbar-inner>
        </div>
    </div>
    <div class="main">
    	<div class="row" style="margin-top:2%; margin-left:3%;">
    		<div class="col-sm-12 col-md-12 col-lg-12" style="font-family:微软雅黑;font-size:13px;">
    				<div class="dropdown">系别:
			             <select name="department" id="department" class="btn btn-default drop-toggle" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;">
						    <c:forEach items="${requestScope.departments}" var="dp">
						    	<option value="${dp.dp_id}">${dp.dp_name}</option>
						    </c:forEach>
						 </select>
           			</div>
           			 <div class="dropdown">科目:
					         <select name="subject" id="subject" name="department" id="department" class="btn btn-default drop-toggle" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;">
									<c:forEach items="${requestScope.qbs}" var="q">
										<option value="${q.id}">${q.name}</option>
									</c:forEach>
							</select>
					  </div>
					   <div class="dropdown">题型:
				             <select name="qtype" id="qtype"  class="btn btn-default drop-toggle" data-toggle="dropdown" aria-expanded="true" style="border:solid #ccc 1px;">
								<c:forEach items="${requestScope.types}" var="t">
									<option value="${t.id}">${t.name}</option>
								</c:forEach>
							</select> 
						     您选中了:&nbsp;<b id="score"></b>&nbsp;分
					    </div>
					    <div class="btn btn-group dropup" style="float:left; margin-left:1%; margin-top:-0.7%;"> 
           						<button  class=" btn btn-primary" id="btn">确定</button>
    					</div>
			           <div class="btn btn-group dropup" style="float:left; margin-left:1.8%; margin-top:-0.7%;"> 
			            <button  class="btn btn-primary" id="rt">返回</button>
			           </div>	
           			
    		 </div>
    		 <hr></hr>
    		 <div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12">
            <div class="table-responsive">
                <table cellpadding="0" cellspacing="0" class="table table-striped table-bordered table-hover" style="font-size:12px; margin-top:3%; text-align:center;">
                    <thead>
                        <tr class="active" >
                            <th width='300'>题目</th>
                            <th width='600'>选项</th>
	                        <th width='55'>答案</th>
                            <th width='55'>分数</th>
                            <th width='55'>选择</th>
                        </tr>
                    </thead>
                </table>
                <table border="1" cellpadding="0" cellspacing="0" id="tb" class="table table-striped table-bordered table-hover" style="font-size:12px; margin-top:-2%; text-align:center;">
				</table> 
            </div>
        </div>
        <div class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-4 col-md-offset-4 col-sm-offset-4">
          <ul class="pagination" id="div">
          </ul>
        </div>
        <div id="div1">
		</div>
    	</div>
    </div>
  </div>
</div>
	<form action="makeTest" id="form"
		method="post">
		<input type="hidden"  name="datas">
		<input type="hidden" name="total_score">
	</form>
</body>
</html>