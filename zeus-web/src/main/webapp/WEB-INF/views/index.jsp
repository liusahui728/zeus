<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<link rel="icon" type="image/x-icon" href="images/favicon.ico">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>菜单</title>
</head>
<body>
菜单 ---->当前用户：${username}
<ul>
	<li><a href="add">添加</a></li>
	<li><a href="edit">编辑</a></li>
	<li><a href="query">查询</a></li>
	<li><a href="delete">删除</a></li>
</ul>
</body>
</html>