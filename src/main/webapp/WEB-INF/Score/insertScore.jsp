<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%response.setCharacterEncoding("utf8"); %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tags/fns.tld" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"  content="width=device-width,initial-scale=1">

<title>4</title>
<link href="css/insert.css" rel="stylesheet">

<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
 body{ margin-top:0; margin-left:0; background-color:#F1F3FA; width:100%; height:100%; font-family:"微软雅黑";overflow:auto;}
 
#top-bar {
z-index: 999; position: absolute; padding-bottom: 0px; margin: 0px; padding-left: 0px; width: 100%; padding-right: 0px; height: 14px; overflow: hidden; top: 0px; padding-top: 0px; left: 0px; margin-left:1%;
}
#topbar-inner {
 background: #F1F3FA; height: 14px;border-top:solid 1px #ccc;border-bottom:solid 1px #ccc;
}
html > body #top-bar {position: fixed}

.container{margin:1%; padding:60px 100px; background-color:#FFF; line-height:30px; border:solid 1px #DEE0E0; width:100%; height:auto; }
    </style>
    
    
    <script type="text/javascript">
    
    function smtForm ()
    {
    	
    	var from = document.getElementById("MyForm");
    	from.submit();
    	
    }
    
    
    
    </script>
    
    
</head>

<c:set var="student_id" value="${student_id}"></c:set>

<c:set var="student" value="${fns:getOneStudent(student_id)}"></c:set>








<body>
<div id=top-bar>
    <div id=topbar-inner>
    </div>
</div>

     <div class="container">
       <div class="row">
        <div class="col-md-8 col-md-offset-2  col-xs-8 col-xs-offset-2   col-lg-8 col-lg-offset-2 ">
             <br>
            <form id="MyForm"   class="form-horizontal" action="insertScore" method="post"  accept-charset="UTF-8">
             <br>
             	<!--<div style="border-left:#3399FF solid 2px; padding:10px;"><h4>修改人员信息</h4></div>
                <hr>-->
                
                 <input name="student_id" type="hidden" value="${student.id }">  <!-- 学生id的值 -->
                
                
                 <div class="form-group">
                    <label  for=""  class="col-sm-3 control-label">学生姓名</label>
                    <div class="col-sm-7">
                      <input name="name" type="text" class="form-control" value="${student.name }" readonly="readonly">
                    </div>
                  </div>	
              
              	<div class="form-group">
                    <label for="" class="col-sm-3 control-label">学号</label>
                    <div class="col-sm-7">
                      <input name="studentAccount" type="text" class="form-control"  value="${student.studentAccount }" readonly="readonly">
                    </div>
                    
                 <input name="id" type="hidden" class="form-control" value="${score.id}">
                    
                    
                  </div>
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">身高</label>
                    <div class="col-sm-7">
                      <input name="height" type="text" class="form-control" value="${score.height }">
                    </div>
                  </div>
                  
                       <div class="form-group">
                    <label for="" class="col-sm-3 control-label">体重</label>
                    <div class="col-sm-7">
                      <input name="weight" type="text" class="form-control" value="${score.weight }">
                    </div>
                  </div>
                  
                  
                  
                       <div class="form-group">
                    <label for="" class="col-sm-3 control-label">耐力跑</label>
                    <div class="col-sm-7">
                      <input name="endurance" type="text" class="form-control" value="${score.endurance }">
                    </div>
                  </div>
                  
                  
                       <div class="form-group">
                    <label for="" class="col-sm-3 control-label">短跑</label>
                    <div class="col-sm-7">
                      <input name="dash" type="text" class="form-control" value="${score.dash }">
                    </div>
                  </div>
                  
                  
                  
                     <div class="form-group">
                    <label for="" class="col-sm-3 control-label">坐位体前屈</label>
                    <div class="col-sm-7">
                      <input name="sitreach" type="text" class="form-control" value="${score.sitreach }">
                    </div>
                  </div>
                  
                   <div class="form-group">
                    <label for="" class="col-sm-3 control-label">肺活量</label>
                    <div class="col-sm-7">
                      <input name="lung" type="text" class="form-control" value="${score.lung }">
                    </div>
                  </div>
                  
                   <div class="form-group">
                    <label for="" class="col-sm-3 control-label">立定跳远</label>
                    <div class="col-sm-7">
                      <input name="standingleap" type="text" class="form-control" value="${score.standingleap }">
                    </div>
                  </div>
                  
                  
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">引体向上或仰卧起坐</label>
                    <div class="col-sm-7">
                      <input name="pullups_situps" type="text" class="form-control" value="${score.pullups_situps }">
                    </div>
                  </div>
                  
                  
                  
              
              
                  
                <div class="form-group">    
                 <br>
                    <div class="col-sm-1 col-sm-offset-7 ">  
                    <button type="submit" class="btn btn-primary">取消</button>
                    </div>
                    <div class="col-sm-1 col-sm-offset-1 ">
                    <button type="submit" class="btn btn-primary">保存</button>
                    </div>
                </div>
              </form>    
       	</div>
       </div>      
	</div>
    <div style="height:50px;">
    </div>
</div>


<script src="js/jquery-2.1.4.js"></script>

</body>
</html>
