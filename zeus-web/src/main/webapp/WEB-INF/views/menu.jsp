<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<link rel="icon" type="image/x-icon" href="images/favicon.ico">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="jquery/jquery-3.3.1.min.js"></script>

<!-- 引入bootstrap样式 -->
<link href="bootstrap-4.0.0-dist/css/bootstrap.min.css" rel="stylesheet">
<!-- 引入bootstrap-table样式 -->
<link href="bootstrap-4.0.0-dist/css/bootstrap-table.min.css"
	rel="stylesheet">
<script src="bootstrap-4.0.0-dist/js/bootstrap.bundle.min.js"></script>
<!-- bootstrap-table.min.js -->
<script src="bootstrap-4.0.0-dist/js/bootstrap-table.min.js"></script>
<!-- 引入中文语言包 -->
<script src="bootstrap-4.0.0-dist/js/bootstrap-table-zh-CN.min.js"></script>
<html>
<head>
<meta charset="utf-8">
<title>菜单</title>
</head>
<body>
	<nav
		class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">zeus</a>
		<!-- <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search"> -->
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link" href="logout">${cookie.userName.value}退出</a></li>
		</ul>
	</nav>
		<nav class="col-md-2 d-none d-md-block bg-light sidebar">
			<div class="sidebar-sticky">
				<ul class="nav flex-column">
					<li class="nav-item">
						<a class="nav-link active" href="#">系统管理
						</a>
						<ul>
						<li class="nav-item"><a class="nav-link" href="query"> 用户管理
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> 角色管理
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> 权限管理
						</a></li>
						</ul>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="query">配置管理
						</a>
						<ul>
						<li class="nav-item"><a class="nav-link" href="#"> 用户管理
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> 角色管理
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> 权限管理
						</a></li>
						</ul>
					</li>
					<!-- <li class="nav-item"><a class="nav-link" href="#"> <span
							data-feather="users"></span> Customers
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <span
							data-feather="bar-chart-2"></span> Reports
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <span
							data-feather="layers"></span> Integrations
					</a></li> -->
				</ul>

				<!-- <h6
					class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
					<span>Saved reports</span> <a
						class="d-flex align-items-center text-muted" href="#"> <span
						data-feather="plus-circle"></span>
					</a>
				</h6>
				<ul class="nav flex-column mb-2">
					<li class="nav-item"><a class="nav-link" href="#"> <span
							data-feather="file-text"></span> Current month
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <span
							data-feather="file-text"></span> Last quarter
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <span
							data-feather="file-text"></span> Social engagement
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <span
							data-feather="file-text"></span> Year-end sale
					</a></li>
				</ul> -->
			</div>
		</nav>
</body>
</html>