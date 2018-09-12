<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',split:true" title="角色用户列表" style="width:200px;">
		<ul id="roleUserTree"></ul>
	</div>
    <!-- Begin of toolbar -->
    <div id="role-toolbar-2">
        <div class="role-toolbar-search" style="margin:10px 0 10px 10px">
            <label>起始时间：</label><input class="easyui-datebox" style="width:100px">
            <label>结束时间：</label><input class="easyui-datebox" style="width:100px">
            <label>用户组：</label> 
            <select class="easyui-combobox" panelHeight="auto" style="width:100px">
                <option value="0">选择用户组</option>
                <option value="1">黄钻</option>
                <option value="2">红钻</option>
                <option value="3">蓝钻</option>
            </select>
            <label>关键词：</label><input class="role-text" style="width:100px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
        </div>
        <div class="role-toolbar-button" style="border-top:1px solid #ccc">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>|
        </div>
    </div>
    <!-- End of toolbar -->
    <div data-options="region:'center'" style="min-width:760px">
	    <table id="role-datagrid-2" toolbar="#role-toolbar-2">
	    </table>
    </div>
    
</div>
<!-- Begin of easyui-dialog -->
<div id="role-dialog-2" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
	<form id="role-form-2" method="post">
        <table>
            <tr>
                <td width="60" align="right">角色名:</td>
                <td><input type="text" name="roleName" class="role-text" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="role-dialog-3" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
	<form id="role-form-3" method="post">
		<input id="roleId" type="hidden" name="roleId" >
        <table>
            <tr>
                <td width="60" align="right">用户:</td>
                <td><input id="userId" class="easyui-combobox" name="userId" ></td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
	$('#roleUserTree').tree({
	    url: "role/getRoleUserTree",
	    loadFilter: function(result){
	    	console.log(result);
	        if (result.isSuccess){
	            return result.data;
	        } else {
	        	$.messager.alert('信息提示',result.msg,'info');
	        }
	    }
	});
	/**
	* Name 添加记录
	*/
	function add(){
		$('#role-form-2').form('submit', {
			url:'role/add',
			success:function(data){
				data=JSON.parse(data);
				console.log(data)
				if(data.isSuccess){
					$.messager.alert('信息提示','提交成功！','info');
					reloads();
					$('#role-dialog-2').dialog('close');
					
				}
				else
				{
					$.messager.alert('信息提示',data.msg,'info');
				}
			}
		});
	}
	
	/**
	* Name 添加记录
	*/
	function addUser(){
		$('#role-form-3').form('submit', {
			url:'role/addUser',
			success:function(data){
				data=JSON.parse(data);
				if(data.isSuccess){
					$.messager.alert('信息提示','提交成功！','info');
					reloads();
					$('#role-dialog-3').dialog('close');
					
				}
				else
				{
					$.messager.alert('信息提示',data.msg,'info');
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#role-form-2').form('submit', {
			url:'',
			success:function(data){
				if(data.isSuccess){
					$.messager.alert('信息提示','提交成功！','info');
					reloads();
					$('#role-dialog-2').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示',data.msg,'info');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var items = $('#role-datagrid-2').datagrid('getSelected');
				var ids = [];
				$(items).each(function(){
					ids.push(this.roleId);	
				});
				console.log(ids)
				console.log(JSON.stringify(ids))
				$.ajax({
					type:"post",
					url:'role/delete',
					contentType: "application/json",
					dataType : 'json',
					data:JSON.stringify(ids),
					success:function(data){
						if(data){
							$.messager.alert('信息提示','删除成功！','info');		
							reloads();
						}
						else
						{
							$.messager.alert('信息提示','删除失败！','info');		
						}
					}	
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		$('#role-form-2').form('clear');
		$('#role-dialog-2').dialog({
			closed: false,
			modal:true,
            title: "添加信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#role-dialog-2').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAddUser(roleId){
		$('#userId').combobox({
		    url:'role/getUserCombbo',
		    valueField:'id',
		    textField:'text',
		    onBeforeLoad: function(param){
		    	param.roleId = roleId;
			}
		});
		$("#roleId").val(roleId);
		$('#role-dialog-3').dialog({
			closed: false,
			modal:true,
            title: "添加信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: addUser
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#role-dialog-3').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		$('#role-form-2').form('clear');
		var item = $('#role-datagrid-2').datagrid('getSelected');
		$('#role-form-2').form('load', item)
		$('#role-dialog-2').dialog({
			closed: false,
			modal:true,
            title: "修改信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#role-dialog-2').dialog('close');                    
                }
            }]
        });
	}	
	
	/**
	* Name 分页过滤器
	*/
	function pagerFilter(data){            
		if (typeof data.length == 'number' && typeof data.splice == 'function'){// is array                
			data = {                   
				total: data.length,                   
				rows: data               
			}            
		}        
		var dg = $(this);         
		var opts = dg.datagrid('options');          
		var pager = dg.datagrid('getPager');          
		pager.pagination({                
			onSelectPage:function(pageNum, pageSize){                 
				opts.pageNumber = pageNum;                   
				opts.pageSize = pageSize;                
				pager.pagination('refresh',{pageNumber:pageNum,pageSize:pageSize});                  
				dg.datagrid('loadData',data);                
			}          
		});           
		if (!data.originalRows){               
			data.originalRows = (data.rows);       
		}         
		var start = (opts.pageNumber-1)*parseInt(opts.pageSize);          
		var end = start + parseInt(opts.pageSize);        
		data.rows = (data.originalRows.slice(start, end));         
		return data;       
	}
	
	/**
	* Name 载入数据
	*/
	$('#role-datagrid-2').datagrid({
		url:'role/list',
		loadFilter:pagerFilter,		
		rownumbers:true,
		singleSelect:false,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{ checkbox:true},
			{ field:'roleId',title:'角色编号',width:100,sortable:true},
			{ field:'roleName',title:'角色名称',width:180,sortable:true},
			{ field: '_operator', title: '操作', width: 100, align: "center", formatter: btnDetailed}
		]],
		onLoadSuccess:function(data){
			$.parser.parse('td[field="_operator"]:gt(0)'); 
		}
	});
	function reloads(){
        $('#role-datagrid-2').datagrid('reload');//刷新
    }
	// 详细按扭
	function btnDetailed(value, rowData, rowIndex) {
		console.log(value)
	    return '<a href="#" id="opera" class="easyui-linkbutton" iconCls="icon-add" onclick="openAddUser(\''+rowData.roleId+'\')" plain="true"></a>';
	}
	function reloads(){
        $('#role-datagrid-2').datagrid('reload');//刷新
        $('#roleUserTree').tree('reload');//刷新
    }
</script>