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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试题类型管理</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen"/>
<script type="text/javascript" src="js/jquery.paginate.js"></script>
<script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript"></script>
</head>
<body>



		<div id=top-bar>
    <div id=topbar-inner>
    </div>
</div>

<div class="main">
        <div class="row">
        <div class="col-sm-8 col-md-8 col-lg-8" style="margin-left:-15px;">
            <div class="table-responsive" style="margin-left: 37%;margin-right: -19%;">
                <table class="table table-striped table-bordered table-hover" style="font-size:12px;">
                    <caption>试题类型管理</caption>
                    <thead>
                        <tr class="active" >
                            <th>QuestionType</th>
                            <th>编辑</th>
                        </tr>
                    </thead>
                    <tbody>  
                    <c:choose>
						<c:when test="${!empty typeList }">
							<c:forEach items="${typeList }" var="type" varStatus="status" >
								<tr>
									<td>${type.name }</td>
									<td><a href="${pageContext.request.contextPath }/question_type_delete/${type.id}"style="margin-right:10%;"><img src="${pageContext.request.contextPath }/images/shan.png">&nbsp;&nbsp;删除</a>
									<a href="${pageContext.request.contextPath }/question_type_modify/${type.id}"style="margin-right:10%;"><img src="${pageContext.request.contextPath }/images/bian.png">&nbsp;&nbsp;编辑</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							目前没有公告
						</c:otherwise>
					</c:choose>
					<c:if test="${!empty sessionScope.question_type_message }">
					<tr>
						<td colspan="3">
							<input type="text" value="${sessionScope.question_type_message }"/>
						</td>
					</tr>
					</c:if>
                    </tbody>
                </table>
            </div>
        </div>
        </div>    
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.paginate.js"></script>
</body>
</html>