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
	<h1>注册</h1>
	<form id="form1">
	<p>用户名：<input type="text" name="account" id="account"></p>
	<p>花名：<input type="text" name="userName" id="userName"></p>
	<p>密码<input type="password" name="password" id="password"></p>
	<p><input type="button" id="sub" value="提交"></p>
	</form>
</body>
</html>
<script>
	$.fn.serializeObject = function()  
	{  
	   var o = {};  
	   var a = this.serializeArray();  
	   $.each(a, function() {  
	       if (o[this.name]) {  
	           if (!o[this.name].push) {  
	               o[this.name] = [o[this.name]];  
	           }  
	           o[this.name].push(this.value || '');  
	       } else {  
	           o[this.name] = this.value || '';  
	       }  
	   });  
	   return o;  
	};
	
	$("#sub").click(function() {
		$.ajax({
			type : "POST",
			url : "doRegister",
			data : JSON.stringify($('#form1').serializeObject()),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if(data.isSuccess){
					alert(data.msg)
					location.href="login"
				}else{
					alert(data.msg)
				}
			}
		});
	});
</script>