<%@page import="com.hist.pe.entity.QuestionsBank"%>
<%@page import="com.hist.pe.entity.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath }/css/choice_file.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.js"></script>


<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<style type="text/css">
body{ margin-top:0; margin-left:0; background-color:#F1F3FA; width:100%; font-family:"微软雅黑";}
#top-bar {
z-index: 999; position: absolute; padding-bottom: 0px; margin: 0px; padding-left: 0px; width: 100%; padding-right: 0px; height: 14px; overflow: hidden; top: 0px; padding-top: 0px; left: 0px; margin-left:1%;
}
#topbar-inner {
 background: #F1F3FA; height:14px;border-top:solid 1px #ccc;border-bottom:solid 1px #ccc;width:100%;
}
html > body #top-bar {position: fixed}
.container{margin:1%; padding:60px 100px; background-color:#FFF; line-height:30px; height:auto; width:99%;height:100%;}
#head{margin-bottom:-10%;}
#xuanze{margin-left:2%;}
#duoxuan{margin-left:1%;}
#panduan{margin-left:-1%;}
span{font-size:16px;font-family:"微软雅黑";font:bold;}
#timer{color:rgba(18, 192, 63, 0.88);
  width:20%;
  float:left;
  position:fixed;
  right:50px;
  bottom:15px;
  _position:absolute;
  _bottom:auto;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0)));
  _margin-bottom:15px;
  top:8%;
  right:0%;}

</style>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-2.0.0.min.js"></script>
	
<script type="text/javascript">
	var arr=new Array;
	var str ='';
	var num = 0;   //判断选择试题的题数
	$(function(){
		$("#submitForm").click(function(){
			
			alert(0);
			$("#radio input[type='radio']:checked").each(function(i,n){
				num ++;
				str+= n.name + "☯" + n.value  + "&";
			});
			var strmulName = '';   //记录上次的name属性
			var strmul = '';       //记录多选题的答案及题
			/* $("input[type='checkbox']:checked").each(function(i,n){
				if(n.name == strmulName){
					strmul+= "&" + n.name + "曱" + n.value;
				}else{
					strmul += "曱"+ n.value;
				}
				strmulName = n.name;
			});  */
			 $("input:checkbox:checked").each(function(i,n){
				 
				if(n.name == strmulName){
					strmul += n.value;
				}else{
					if(i == 0){
						num ++;
						strmul+= n.name + "☯" + n.value;
					}else{
						if(n.name == ''){
							strmul+= n.value;
						}else{
							strmul+= "&" + n.name + "☯" + n.value;
							num++;
						}
					}
				}
				strmulName = n.name;
			}); 

			judge_str = '';
			$("#judge input[type='radio']:checked").each(function(i,n){
				num++;
				judge_str+=n.name + "☯" + n.value  + "&";
			});
			
			//str = str + strmul;
			str = str.substr(0, str.length - 1);
			judge_str = judge_str.substr(0, judge_str.length - 1);
		$("#simeple_datas").attr("value",str);
		$("#mul_datas").attr("value",strmul);
		$("#judge_datas").attr("value",judge_str);
		str = "";
		strmul = "";
		judge_str = "";
		str = "";
		alert($("#simeple_datas").val());
		alert($("#mul_datas").val());
		alert($("#judge_datas").val());

		var length = $("#questionLength").val();
		alert("num : " + num + ",length : " + length);
		if(length == num){
			$("#assignment").submit();
		}else{
			alert("请将题做完在点击提交");
			num = 0;
		}
		});
	})
</script>
<body>


	<h2>Examine start</h2>
	
	<div id=top-bar>
		<div id=topbar-inner></div>
	</div>
	
	<div id="timer"></div>
<br>
	
	<form:form
		action="${pageContext.request.contextPath }/student/assignment"
		method="post" id="assignment" modelAttribute="result">
		<%-- <p>一、单项选择</p>
		<c:forEach items="${sessionScope.selectQuestions}" var="selectQuestion" varStatus="status"> 
			<p>${status.index + 1 }、
			${selectQuestion.title}</p>
			<div id="radio">
				<input type="radio" value="A" name="${selectQuestion.id}"/>${selectQuestion.selectOne }<br>
				<input type="radio" value="B" name="${selectQuestion.id}"/>${selectQuestion.selectTwo }<br>
				<input type="radio" value="C" name="${selectQuestion.id}"/>${selectQuestion.selectThree}<br>
				<input type="radio" value="D" name="${selectQuestion.id}"/>${selectQuestion.selectFour }<br>
			</div>
		</c:forEach> 
		<c:if test="${sessionScope.selectMulQuestions != null}">
			<p id="mul">二、多项选择</p>
		<c:forEach items="${sessionScope.selectMulQuestions}" var="selectMulQuestion" varStatus="status"> 
			<p>${status.index + 1 }、
			${selectMulQuestion.title}</p>
			<div id="checkbox">
				<input type="checkbox" value="A" name="${selectMulQuestion.id}"/>${selectMulQuestion.selectOne }<br>
				<input type="checkbox" value="B" name="${selectMulQuestion.id}"/>${selectMulQuestion.selectTwo }<br>
				<input type="checkbox" value="C" name="${selectMulQuestion.id}"/>${selectMulQuestion.selectThree}<br>
				<input type="checkbox" value="D" name="${selectQuestion.id}"/>${selectMulQuestion.selectFour }<br>
			</div>
		</c:forEach>
		</c:if>
		 
		<p>三、判断题</p>
		<c:forEach items="${sessionScope.judgeQuestions}" var="judgeQuestion" varStatus="status"> 
			<p>${status.index + 1 }、
			${judgeQuestion.title}</p>
			<div id="judge">
				<input type="radio" value="x" name="${judgeQuestion.id}" />x
				<input type="radio" value="V" name="${judgeQuestion.id}" />v
			</div>
		</c:forEach>  --%>
		<input type="hidden" name="examPage.id"
			value="${requestScope.examPage.id}" />
		<input type="hidden" name="user.id" value="1" />
		<input type="hidden" name="simeple_answer" id="simeple_datas">
		<input type="hidden" name="mul_answer" id="mul_datas">
		<input type="hidden" name="fill_answer" id="fill_datas">
		<input type="hidden" name="judge_answer" id="judge_datas">



		<input type="hidden" id="questionLength"
			value="${fn:length(sessionScope.selectQuestions) + fn:length(sessionScope.selectMulQuestions) +fn:length(sessionScope.judgeQuestions) }" />
		<div class="container">
			<div class="row">

				<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12" id="head">
					一 、单项选择:
					<hr />
				</div>
				<br>
				<br>
				<c:forEach items="${sessionScope.selectQuestions}"
					var="selectQuestion" varStatus="status">
					<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12" id="xuanze">
						<p>${status.index + 1 }、${selectQuestion.title}</p>
						<div class="radio" id="radio">
							<label class="col-md-2 col-sm-2 col-lg-2"> 
								<input type="radio" value="A" name="${selectQuestion.id}"
									style="margin-top: 4%;" />${selectQuestion.selectOne }
							</label> 
							<label class="col-md-2">
								 <input	type="radio" value="B" name="${selectQuestion.id}" 
									style="margin-top: 4%;" />${selectQuestion.selectTwo }
							</label>
						    <label class="col-md-2"> 
							   <input type="radio" value="C" name="${selectQuestion.id}" 
							   		style="margin-top: 4%;"/>${selectQuestion.selectThree}
							</label>
							<label class="col-md-2"> 
								<input type="radio" value="D" name="${selectQuestion.id}" 
									style="margin-top: 4%;"/>${selectQuestion.selectFour }
							</label>
						</div>
					</div>
				</c:forEach>
			

			<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12" id="head">
				二、多项选择:
				<hr />
			</div> 
			<br>
			<br>
			<c:forEach items="${sessionScope.selectMulQuestions}" var="selectMulQuestion" varStatus="status">
			<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12" id="duoxuan">
				<p>${status.index + 1 }、
			${selectMulQuestion.title}</p>
				<div class="checkbox">
					<label class="checkbox-inline col-md-2 col-sm-2 col-lg-2">
						<input type="checkbox" value="A" name="${selectMulQuestion.id}"/>${selectMulQuestion.selectOne }
					</label> 
					<label class="checkbox-inline col-md-2 col-sm-2 col-lg-2">
						<input type="checkbox" value="B" name="${selectMulQuestion.id}"/>${selectMulQuestion.selectTwo }
					</label> 
					<label class="checkbox-inline col-md-2 col-sm-2 col-lg-2">
						<input type="checkbox" value="C" name="${selectMulQuestion.id}"/>${selectMulQuestion.selectThree}
					</label> 
					<label class="checkbox-inline col-md-2 col-sm-2 col-lg-2">
						<input type="checkbox" value="D" name="${selectQuestion.id}"/>${selectMulQuestion.selectFour }
					</label>
				</div>
			</div>
			</c:forEach>
		
		<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12" id="head">
				三、判断题:
				<hr />
			</div>
			<br>
			<br>
			<c:forEach items="${sessionScope.judgeQuestions}" var="judgeQuestion" varStatus="status">
			<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12" id="duoxuan">
				<p>${status.index + 1 }、
			${judgeQuestion.title}</p>
				<div class="checkbox">
					<label class="checkbox-inline col-md-2 col-sm-2 col-lg-2">
						<input type="radio" value="x" name="${judgeQuestion.id}" />x
					</label> 
					<label class="checkbox-inline col-md-2 col-sm-2 col-lg-2">
						<input type="radio" value="V" name="${judgeQuestion.id}" />v
					</label> 
				</div>
			</div>
			</c:forEach>
		</div>
		</div>
		<input type="button" value="交卷" id="submitForm" />
	</form:form>
	<input type="hidden" id="example_time" value="${requestScope.examPage.examTime }">
<script src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
<script type="text/javascript">
var ti = $("#example_time").val();
var maxtime = ti*60 //一个小时，按秒计算，自己调整!
function CountDown(){
if(maxtime>=0){
minutes = Math.floor(maxtime/60);
seconds = Math.floor(maxtime%60);
msg = "距考试结束还有&nbsp;:&nbsp;"+minutes+"分"+seconds+"秒";
document.all["timer"].innerHTML=msg;
if(maxtime == 5*60) alert('注意，还有5分钟! , 请一定要将题填完否则将会交不了试卷。慎重！');
--maxtime;
}
else{
clearInterval(timer);
alert("时间到，结束!");
$(function(){
	alert(0);
	$("#radio input[type='radio']:checked").each(function(i,n){
		num ++;
		str+= n.name + "☯" + n.value  + "&";
	});
	var strmulName = '';   //记录上次的name属性
	var strmul = '';       //记录多选题的答案及题
	/* $("input[type='checkbox']:checked").each(function(i,n){
		if(n.name == strmulName){
			strmul+= "&" + n.name + "曱" + n.value;
		}else{
			strmul += "曱"+ n.value;
		}
		strmulName = n.name;
	});  */
	 $("input:checkbox:checked").each(function(i,n){
		 
		if(n.name == strmulName){
			strmul += n.value;
		}else{
			if(i == 0){
				num ++;
				strmul+= n.name + "☯" + n.value;
			}else{
				if(n.name == ''){
					strmul+= n.value;
				}else{
					strmul+= "&" + n.name + "☯" + n.value;
					num++;
				}
			}
		}
		strmulName = n.name;
	}); 

	judge_str = '';
	$("#judge input[type='radio']:checked").each(function(i,n){
		num++;
		judge_str+=n.name + "☯" + n.value  + "&";
	});
	
	//str = str + strmul;
	str = str.substr(0, str.length - 1);
	judge_str = judge_str.substr(0, judge_str.length - 1);
$("#simeple_datas").attr("value",str);
$("#mul_datas").attr("value",strmul);
$("#judge_datas").attr("value",judge_str);
str = "";
strmul = "";
judge_str = "";
str = "";
alert($("#simeple_datas").val());
alert($("#mul_datas").val());
alert($("#judge_datas").val());

var length = $("#questionLength").val();
alert("num : " + num + ",length : " + length);
if(length == num){
	$("#assignment").submit();
}else{
	alert("请将题做完在点击提交");
	num = 0;
}
	
})
}
}
timer = setInterval("CountDown()",1000);
</script>
	
</body>
</html>