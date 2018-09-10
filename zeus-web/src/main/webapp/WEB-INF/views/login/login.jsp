<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="images/favicon.ico">

<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->

<script type="text/javascript" src="jquery/jquery-3.3.1.min.js"></script>
<!-- 引入bootstrap样式 -->
<link href="bootstrap-4.0.0-dist/css/bootstrap.min.css" rel="stylesheet">
<!-- 引入bootstrap-table样式 -->
<link href="bootstrap-4.0.0-dist/css/bootstrap-table.min.css"
	rel="stylesheet">
<script src="bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
<!-- bootstrap-table.min.js -->
<script src="bootstrap-4.0.0-dist/js/bootstrap-table.min.js"></script>
<!-- 引入中文语言包 -->
<script src="bootstrap-4.0.0-dist/js/bootstrap-table-zh-CN.min.js"></script>
<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
	<form class="form-signin">
		<img class="mb-4" src="images/favicon.ico" alt="" width="72"
			height="72">
		<h1 class="h3 mb-3 font-weight-normal">请登陆</h1>
		<label for="name" class="sr-only">用户名</label> <input type="text"
			id="username" class="form-control" placeholder="用户名" required
			autofocus> <label for="inputPassword" class="sr-only">密码</label>
		<input type="password" id="password" class="form-control"
			placeholder="密码" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me"
				id="rememberMe"> 记住我
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="button"
			id="submit">登陆</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
	</form>
</body>
</html>
<script>
	$("#submit").click(function() {
		var rememberMe = $("#rememberMe").is(':checked');
		$.ajax({
			type : "POST",
			url : "login",
			data : JSON.stringify({
				"username" : $("#username").val(),
				"password" : $("#password").val(),
				"rememberMe" : rememberMe
			}),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if (data.isSuccess) {
					location.href = "index"
				} else {
					alert("登录失败")
				}
			}
		});
	});
</script>