<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>




<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<title>login</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link href="/css/login.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-2.1.4.js"></script>
<script src="/js/bootstrap.js"></script>

</head>

<body>
<div class="box">
		<div class="login-box">
			<div class="login-content ">
			<div class="login-title text-center">
				<h1>welcome</h1>
			</div>
			<div class="form">
			<form action="#" method="post">
				<div class="form-group">
					<div class="col-xs-12">
						<div class="input-group">
							<span class="input-group-addon"><img src="images/ren.png"></span>
							<input type="text" id="username" name="username" class="form-control" placeholder="用户名">
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12">
						<div class="input-group">
							<span class="input-group-addon"><img src="images/she.png"></span>
							<input type="password" id="password" name="password" class="form-control" placeholder="密码">
						</div>
					</div>
				</div>
				<div class="form-group form-actions">
						<button type="submit" class="btn btn-group-xs btn-info"> &nbsp;登&nbsp;&nbsp;&nbsp;录&nbsp;</button>
						<input type="checkbox" name="rememberMe" class="btn btn-group-xs btn-info">记住密码</button>
				</div>
                
				<div class="form-group">
                    <!--<div class="col-xs-6 link">
						<p class="text-center remove-margin">还没注册?<a href="javascript:void(0)" >注册</a>
						</p>
					</div>-->
					<div class="col-xs-6 link">
						<p class="text-center remove-margin">忘记密码？ <a href="javascript:void(0)" >找回</a>
						</p>
					</div>
					
				</div>
			</form>
			</div>
		</div>
	</div>
</div>


</body>

</html>