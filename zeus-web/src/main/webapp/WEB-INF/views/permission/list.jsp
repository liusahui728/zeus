<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="permission-toolbar-2">
        <div class="permission-toolbar-search" style="margin:10px 0 10px 10px">
            <label>起始时间：</label><input class="easyui-datebox" style="width:100px">
            <label>结束时间：</label><input class="easyui-datebox" style="width:100px">
            <label>用户组：</label> 
            <select class="easyui-combobox" panelHeight="auto" style="width:100px">
                <option value="0">选择用户组</option>
                <option value="1">黄钻</option>
                <option value="2">红钻</option>
                <option value="3">蓝钻</option>
            </select>
            <label>关键词：</label><input class="permission-text" style="width:100px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
        </div>
        <div class="permission-toolbar-button" style="border-top:1px solid #ccc">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>|
        </div>
    </div>
    <div data-options="region:'center'" style="min-width:760px">
	    <table id="permission-treegrid-2" toolbar="#permission-toolbar-2">
	    </table>
    </div>
</div>
<!-- Begin of easyui-dialog -->
<div id="permission-dialog-2" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
	<form id="permission-form-2" method="post">
        <table>
            <tr>
                <td width="60" align="right">权限Key:</td>
                <td><input type="text" name="permissionKey" class="permission-text" /></td>
            </tr>
            <tr>
                <td align="right">权限名:</td>
                <td><input type="text" name="permissionName" class="permission-text" /></td>
            </tr>
            <tr>
                <td align="right">资源url:</td>
                <td><input type="text" name="permissionUrl" class="permission-text" /></td>
            </tr>
            <tr>
                <td align="right">排序:</td>
                <td><input type="text" name="orderNum" class="permission-text" /></td>
            </tr>
            <tr>
                <td align="right">父节点:</td>
                <td><input id="parentId" name="parentId" class="permission-text" /></td>
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
		$('#permission-form-2').form('submit', {
			url:'permission/add',
			success:function(data){
				data=JSON.parse(data);
				console.log(data)
				if(data.isSuccess){
					$.messager.alert('信息提示','提交成功！','info');
					reloads();
					$('#permission-dialog-2').dialog('close');
				}else{
					$.messager.alert('信息提示',data.msg,'info');
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#permission-form-2').form('submit', {
			url:'',
			success:function(data){
				if(data.isSuccess){
					$.messager.alert('信息提示','提交成功！','info');
					reloads();
					$('#permission-dialog-2').dialog('close');
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
				var items = $('#permission-datagrid-2').datagrid('getSelections');
				var ids = [];
				$(items).each(function(){
					ids.push(this.permissionId);	
				});
				console.log(ids)
				console.log(JSON.stringify(ids))
				$.ajax({
					type:"post",
					url:'permission/delete',
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
		$('#permission-form-2').form('clear');
		$('#parentId').combotree({
		    url: 'permission/getPremissionTree',
		    loadFilter: function(result){
		    	console.log(result);
		        if (result.isSuccess){
		            return result.data;
		        } else {
		        	$.messager.alert('信息提示',result.msg,'info');
		        }
		    }
		});
		$('#permission-dialog-2').dialog({
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
                    $('#permission-dialog-2').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		$('#permission-form-2').form('clear');
		var item = $('#permission-datagrid-2').datagrid('getSelected');
		$('#permission-form-2').form('load', item)
		$('#permission-dialog-2').dialog({
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
                    $('#permission-dialog-2').dialog('close');                    
                }
            }]
        });
	}	
	
	$('#permission-treegrid-2').treegrid({
        url: 'permission/getPremissionTreeGrid',
        idField: 'permissionId',            //定义关键字段来标识树节点。也就是数据的id
        treeField: 'permissionName',        //定义树形显示字段
		rownumbers:true,
		singleSelect:false,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		fit:true,
        columns: [[                //定义表格头名称
        	{field: 'permissionName',title: '权限名称',width:100},
        	{title: '权限Key',field: 'permissionUrl',width:100},
        	{title: '资源路径',field: 'permissionUrl'},
        	{title: '排序',field: 'orderNum'},
        ]],
        loadFilter: function(result){
	    	console.log(result);
	        if (result.isSuccess){
	            return result.data;
	        } else {
	        	$.messager.alert('信息提示',result.msg,'info');
	        }
	    } 
    });
	function reloads(){
        $('#permission-datagrid-2').treegrid('reload');//刷新
    }
</script>