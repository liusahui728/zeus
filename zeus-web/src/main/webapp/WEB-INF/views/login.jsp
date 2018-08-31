<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<link rel="icon" type="image/x-icon" href="images/favicon.ico">
<script type="text/javascript" src="jquery/jquery-3.3.1.min.js"></script>
<html>
<head>
<meta charset="utf-8">
<title>登陆</title>
</head>

<body>
	<h1>欢迎登录!${user.username }</h1>
	<form>
		<input type="text" name="username" id="username"><br> <input
			type="password" name="password" id="password"><br> <input
			type="button" id="sub" value="提交">
	</form>
</body>
</html>
<script>
	$("#sub").click(function() {
		$.ajax({
			type : "POST",
			url : "login",
			data : JSON.stringify({"username":$("#username").val(),"password":$("#password").val()}),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
			}
		});
	});
</script>