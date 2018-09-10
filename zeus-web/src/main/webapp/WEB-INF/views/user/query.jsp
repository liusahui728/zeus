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

<title>用户管理</title>
<%@ include file="../menu.jsp" %>
<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10">
			<button class="btn btn-primary" data-toggle="modal" data-target="#myModal">新增</button>
			<table id="table"></table>
			</main>
		</div>
	</div>
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="addForm">
					<div class="form-group">
						<label for="account" class="col-sm-2 control-label">账号</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="account" 
								   placeholder="请输入账号">
						</div>
					</div>
					<div class="form-group">
						<label for="userName" class="col-sm-2 control-label">花名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userName" 
								   placeholder="请输入花名">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password" 
								   placeholder="请输入密码">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" id="addBtn">
					提交更改
				</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script>
$("#table").bootstrapTable({ // 对应table标签的id
	  method:"post",
      url: "user/query", // 获取表格数据的url
      cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
      striped: true,  //表格显示条纹，默认为false
      pagination: true, // 在表格底部显示分页组件，默认false
      pageList: [10, 20], // 设置页面可以显示的数据条数
      pageSize: 10, // 页面数据条数
      pageNumber: 1, // 首页页码
      pagination: true, 
      sidePagination: 'server', // 设置为服务器端分页
      queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
    	  var queryData = {};    //如果没有额外的查询参数的话就新建一个空对象，如果有的话就先装你的查询参数
          //然后增加这两个
          queryData.pageSize = params.limit;
          queryData.offset = params.offset;
          return queryData;    //这个就是向服务端传递的参数对象
/*           return {
              pageSize: params.limit, // 每页要显示的数据条数
              offset: params.offset, // 每页显示数据的开始行号
              sort: params.sort, // 要排序的字段
              sortOrder: params.order, // 排序规则
              dataId: $("#dataId").val() // 额外添加的参数
          } */
      },
      sortName: 'id', // 要排序的字段
      sortOrder: 'desc', // 排序规则
      columns: [
          {
              checkbox: true, // 显示一个勾选框
              align: 'center' // 居中显示
          }, {
              field: 'userId', // 返回json数据中的name
              title: '编号', // 表格表头显示文字
              align: 'center', // 左右居中
              valign: 'middle' // 上下居中
          }, {
              field: 'userName',
              title: '名称',
              align: 'center',
              valign: 'middle'
          }, {
              field: 'account',
              title: '账号',
              align: 'center',
              valign: 'middle',
          }, {
              title: "操作",
              align: 'center',
              valign: 'middle',
              width: 160, // 定义列的宽度，单位为像素px
              formatter: function (value, row, index) {
            	  _html='';
            	  _html+='<button class="btn btn-primary btn-sm" onclick="del(\'' + row.userId + '\')">删除</button>';
            	  _html+='<button class="btn btn-primary btn-sm" onclick="edit(\'' + row.userId + '\')">编辑</button>';
                  return _html;
              }
          }
      ],
      onLoadSuccess: function(data){  //加载成功时执行
    	  console.log(data)
            console.info("加载成功");
      },
      onLoadError: function(){  //加载失败时执行
            console.info("加载数据失败");
      }

}),
$("#addBtn").click(function(data){
	$.ajax({
		type : "POST",
		url : "user/add",
		data : JSON.stringify({
			"userName" : $("#userName").val(),
			"password" : $("#password").val(),
			"account" : $("#account").val()
		}),
		dataType : "json",
		contentType : "application/json",
		success : function(data) {alert(data。msg)
			if (data.isSuccess) {
				alert("添加成功")
				$("#myModal").modal('hide'); 
				$('#table').bootstrapTable('refresh', '');
			} else {
				alert(data。msg)
			}
		}
	});
}),
function del(data){
	alert(data)
}
</script>
