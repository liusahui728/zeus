package org.com.zeus.controller;

import java.util.ArrayList;
import java.util.List;

import org.com.zeus.common.base.entity.BasePagination;
import org.com.zeus.common.base.entity.BaseQuery;
import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.base.entity.BaseTree;
import org.com.zeus.common.model.Permission;
import org.com.zeus.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	IPermissionService permissionService; 
	
	@PostMapping("/add")
	@ResponseBody
	public BaseResullt add(Permission permission) {
		return BaseResullt.utils.setSuccessResult(permission.insert());
	}
	
	@GetMapping("/list")
	public String query() {
		return "permission/list";
	}

	@PostMapping("/list")
	@ResponseBody
	public BasePagination query(BaseQuery query) {
		IPage<Permission> pageres=permissionService.page(new Page<>(query.getPageNo(),query.getPageSize()), new QueryWrapper<Permission>().lambda().orderByAsc(Permission::getOrderNum));
		return new BasePagination(pageres.getRecords(), pageres.getTotal());
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public BaseResullt delete(@RequestBody ArrayList<Long> ids) {
		return BaseResullt.utils.setSuccessResult(permissionService.removeByIds(ids));
	}
	
	@PostMapping("/getPremissionTree")
	@ResponseBody
	public BaseResullt getPremissionTree(BaseQuery query) {
		List<Permission> list=permissionService.list(new QueryWrapper<Permission>().lambda().eq(Permission::getParentId, 0L).orderByAsc(Permission::getOrderNum));
		List<BaseTree> listTree=new ArrayList<>();
		List<Permission> listAllNode=getNodeByList(list);
		return BaseResullt.utils.setSuccessResult(getTreeList(listAllNode,listTree));
	}
	
	public List<BaseTree> getTreeList(List<Permission> list,List<BaseTree> listTree) {
		for(Permission permission:list) {
			BaseTree baseTree=new BaseTree();
			baseTree.setId(permission.getPermissionId());
			baseTree.setText(permission.getPermissionName());
			//如果有子节点,只处理到第二节点
			if(!CollectionUtils.isEmpty(permission.getChildren())) {
					List<BaseTree> bs=new ArrayList<>();
					for(Permission temp1:permission.getChildren()) {
						BaseTree baseTree1=new BaseTree();
						baseTree1.setId(temp1.getPermissionId());
						baseTree1.setText(temp1.getPermissionName());
						bs.add(baseTree1);
					}
					baseTree.setChildren(bs);
			}
			listTree.add(baseTree);
		}
		return listTree;
	}
	
	
	public List<Permission> getNodeByList(List<Permission> list) {
		for(Permission permission:list) {
			//查询根节点的子节点
			List<Permission> listtemp=permissionService.list(new QueryWrapper<Permission>().lambda().eq(Permission::getParentId, permission.getPermissionId()).orderByAsc(Permission::getOrderNum));
			permission.setChildren(listtemp);
			//迭代
			getNodeByList(listtemp);
		}
		return list;
	}
	
	@PostMapping("/getPremissionTreeGrid")
	@ResponseBody
	public BaseResullt getPremissionTreeGrid(BaseQuery query) {
		List<Permission> list=permissionService.list(new QueryWrapper<Permission>().lambda().eq(Permission::getParentId, 0L).orderByAsc(Permission::getOrderNum));
		return BaseResullt.utils.setSuccessResult(getNodeByList(list));
	}
}
