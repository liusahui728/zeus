<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<link rel="icon" type="image/x-icon" href="images/favicon.ico">
<script type="text/javascript" src="jquery/jquery-1.8.0.min.js"></script>
<html>
<head>
<meta charset="utf-8">
<title>登陆</title>
</head>

<body>
	<h1>欢迎登录!</h1>
	<form>
		<p>session:<%=session.getId()%></p>
		<p><input type="text" name="account" id="account"></p>
		<p><input type="password" name="password" id="password"></p>
		<input type="checkbox" name="rememberMe" id="rememberMe">记住密码
		<p><input type="button" id="login" value="提交"><a href="doRegister"><input type="button" id="register" value="注册"></a></p>
	</form>
</body>
</html>
<script>
	$("#login").click(function() {
		var rememberMe=$("#rememberMe").is(':checked');
		$.ajax({
			type : "POST",
			url : "login",
			data : JSON.stringify({"account":$("#account").val(),"password":$("#password").val(),"rememberMe":rememberMe}),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if(data.isSuccess){
					location.href="index"
				}else{
					alert("登录失败")
				}
			}
		});
	});
</script>