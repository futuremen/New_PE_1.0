<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tags/fns.tld" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>content_menu</title>
<head>
<style type="text/css">
body{margin:0;padding:0;overflow-x:hidden;}
html, body{height:100%; }
img{border:none;}
*{font-family:'微软雅黑';font-size:15px;color:#626262;}
dl,dt,dd{display:block;margin-left:-3%;margin:0 0 0 -3%;}
a{text-decoration:none;}
.container{width:100%;height:100%;margin:auto;}

/*left*/
.leftsidebar_box{
	width:230px;
	height:auto !important;
	overflow:visible !important;
	height:100% !important;
	background-color:#3D4A53;
	padding-left:16px;
	}
.line{
	height:24px;
	width:100%;
	background-image:url(images/line_bg.png);
	background-repeat:repeat-x;
	}
.leftsidebar_box dt{
	padding-left:16%;
	padding-right:10px;
	background-repeat:no-repeat;
	background-position:10px center;
	color:#f5f5f5;
	font-size:15px;
	position:relative;
	line-height:48px;
	cursor:pointer;
	}
.leftsidebar_box dd{
	font-size:13px;
	background-color:#3D4A53;
	padding-left:19%;
	}
.leftsidebar_box dd a{
	color:#f5f5f5;
	line-height:38px;
	}
.leftsidebar_box dt img{
	position:absolute;
	right:34%;
	top:20px;
	}
.system_log dt{
	background-image:url(images/system.png);
	}
.custom dt{
	background-image:url(images/custom.png);
	}
.channel dt{
	background-image:url(images/channel.png);
	}
.app dt{
	background-image:url(images/app.png);
	}
.cloud dt{
	background-image:url(images/cloud.png);
	}
.syetem_management dt{
	background-image:url(images/syetem_management.png);
	}
.source dt{
	background-image:url(images/source.png); 
	}
.statistics dt{
	background-image:url(images/statistics.png);
	}
.leftsidebar_box dl dd:last-child{padding-bottom:10px;}
</style>

</head>




 

<body id="bg">

<div class="container">

<c:forEach items="${fns:getMenuList()}" var="menu"></c:forEach>


	<div class="leftsidebar_box">
		<div class="line"></div>
		<c:forEach items="${fns:getMenuList()}" var="menu">
		<c:if test="${menu.parent_id eq '1' }">
		       <dl class="system_log">
			      <dt onClick="changeImage()">${menu.name }<img src="images/select_xl01.png"></dt>
			        <c:forEach items="${fns:getMenuList()}" var="son">
			          <c:if test="${son.parent_id eq menu.id }">
			            <dd class="first_dd" onclick="javascript:parent.document.getElementById('main').src='${son.href}' "><a href="#">${son.name }</a></dd>
			          </c:if>
			        </c:forEach>
		       </dl>
	    </c:if>
	  </c:forEach>
	
	</div>

</div>

<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript">
$(".leftsidebar_box dt").css({"background-color":"#3D4A53"});
$(".leftsidebar_box dt img").attr("src","images/select_xl01.png");
$(function(){
	$(".leftsidebar_box dd").hide();
	$(".leftsidebar_box dt").click(function(){
		$(".leftsidebar_box dt").css({"background-color":"#3D4A53"})
		$(this).css({"background-color": "#2F3D46"});
		$(this).parent().find('dd').removeClass(("menu_chioce"),6000);
		$(".leftsidebar_box dt img").attr("src","images/select_xl01.png");
		$(this).parent().find('img').attr("src","images/select_xl01.png");
		$(".menu_chioce").slideUp();
		$(this).parent().find('dd').slideToggle();
	$(this).parent().find('dd').addClass("menu_chioce");

    /*$(function(){
     $(".leftsidebar_box dd").hide();
     $(".leftsliderbar_box dl").hover(function(){
          $(this).height(362).sblings().height(100);
        })
     $(".leftsidebar_box dt").mouseover(function(){
         $(".leftsidebar_box dt").css({"background-color":"#3D4A53"})
         $(this).css({"background-color": "#2F3D46"});
      	 $(this).parent().find('dd').removeClass(("menu_chioce"),6000);
         $(".leftsidebar_box dt img").attr("src","images/select_xl01.png");
	     $(this).parent().find('img').attr("src","images/select_xl01.png");
	     $(".menu_chioce").slideUp();
         $(this).parent().find('dd').slideToggle();
         $(this).parent().find('dd').addClass("menu_chioce");
	})*/
})
})
</script>
</body>
</html>
