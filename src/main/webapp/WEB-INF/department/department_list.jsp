<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系别列表</title>
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/style.css" media="screen"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.paginate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap.js"></script>
</head>
<body>
	
	<div id="top-bar">
	    <div id="topbar-inner">
	    </div>
	</div>
	<div class="main">
	<a href="${pageContext.request.contextPath }/department_add_input">添加系别</a>
        <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12" style="margin-left:-15;margin-right:-15;">
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover" style="font-size:12px;">
                    <caption>系别列表</caption>
                    <thead>
						<tr>
							<th>系别</th>
							<th>删除</th>
							<th>编辑</th>
						</tr>
                    </thead>
                    <tbody>
                        
                        <c:choose>
							<c:when test="${!empty departmentList }">
								<c:forEach items="${departmentList }" var="department" varStatus="status">
									<tr>
										<td>${department.dp_name }</td>
										<td><a href="${pageContext.request.contextPath }/department_delete/${department.dp_id}" class="tablelink1"><img src="${pageContext.request.contextPath }/resources/images/shan.png"/>&nbsp;&nbsp;删除</a></td>
										<td><a href="${pageContext.request.contextPath }/department_modify_input/${department.dp_id}" class="tablelink1"><img src="${pageContext.request.contextPath }/resources/images/bian.png"/>&nbsp;&nbsp;编辑</a></td>				
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
							<tr>
								<td>
									当前无系别信息
								</td>
							</tr>
							</c:otherwise>
						</c:choose>
                    </tbody>
                </table>
                <c:if test="${!empty question_department_message }">
		<tr>
			<td>
				${question_department_message }
			</td>
		</tr>
	</c:if>
            </div>
        </div>
        </div>    
    
</div>    
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.paginate.js"></script>
</body>
</html>