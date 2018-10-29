<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<link rel="icon" type="image/x-icon" href="images/favicon.ico">
<script type="text/javascript" src="jquery/jquery-3.3.1.min.js"></script>
<html>
<head>
<meta charset="utf-8">
</head>

<body>
	<p>
		<input type="button" id="login" value="提交">
	</p>
</body>
</html>
<script>
	$("#login").click(function() {
		alert(123)
		$.ajax({
			type : "get",
			url : "http://localhost:61011/account/code/{mobile}?mobile=13291868015",
			data : {},
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				console.log(data)
			}
		});
	});
</script>