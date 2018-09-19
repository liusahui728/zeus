<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="user-toolbar-2">
        <div class="user-toolbar-search" style="margin:10px 0 10px 10px">
            <label>起始时间：</label><input class="easyui-datebox" style="width:100px">
            <label>结束时间：</label><input class="easyui-datebox" style="width:100px">
            <label>用户组：</label> 
            <select class="easyui-combobox" panelHeight="auto" style="width:100px">
                <option value="0">选择用户组</option>
                <option value="1">黄钻</option>
                <option value="2">红钻</option>
                <option value="3">蓝钻</option>
            </select>
            <label>关键词：</label><input class="user-text" style="width:100px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
        </div>
        <div class="user-toolbar-button" style="border-top:1px solid #ccc">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>|
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="user-datagrid-2" class="easyui-datagrid" toolbar="#user-toolbar-2">
    </table>
</div>
<!-- Begin of easyui-dialog -->
<div id="user-dialog-2" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
	<form id="user-form-2" method="post">
        <table>
            <tr>
                <td width="60" align="right">用户名:</td>
                <td><input type="text" name="account"/></td>
            </tr>
            <tr>
                <td align="right">花名:</td>
                <td><input type="text" name="userName"/></td>
            </tr>
            <tr>
                <td align="right">密码:</td>
                <td><input type="password" name="password"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- End of easyui-dialog -->
<script type="text/javascript">
	/**
	* Name 添加记录
	*/
	function add(){
		$('#user-form-2').form('submit', {
			url:'user/add',
			success:function(data){
				data=JSON.parse(data);
				console.log(data)
				if(data.isSuccess){
					$.messager.alert('信息提示','提交成功！','info');
					reloads();
					$('#user-dialog-2').dialog('close');
					
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
		$('#user-form-2').form('submit', {
			url:'',
			success:function(data){
				if(data.isSuccess){
					$.messager.alert('信息提示','提交成功！','info');
					reloads();
					$('#user-dialog-2').dialog('close');
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
				var items = $('#user-datagrid-2').datagrid('getSelections');
				var ids = [];
				$(items).each(function(){
					ids.push(this.userId);	
				});
				console.log(ids)
				console.log(JSON.stringify(ids))
				$.ajax({
					type:"post",
					url:'user/delete',
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
		$('#user-form-2').form('clear');
		$('#user-dialog-2').dialog({
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
                    $('#user-dialog-2').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		$('#user-form-2').form('clear');
		var item = $('#user-datagrid-2').datagrid('getSelected');
		$('#user-form-2').form('load', item)
		$('#user-dialog-2').dialog({
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
                    $('#user-dialog-2').dialog('close');                    
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
	$('#user-datagrid-2').datagrid({
		url:'user/list',
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
			{ field:'userId',title:'用户编号',width:100,sortable:true},
			{ field:'account',title:'账号',width:180,sortable:true},
			{ field:'userName',title:'花名',width:100},
		]]
	});
	function reloads(){
        $('#user-datagrid-2').datagrid('reload');//刷新
    }
</script>