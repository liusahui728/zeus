package org.com.zeus.controller;

import java.util.ArrayList;

import org.com.zeus.common.base.entity.BasePagination;
import org.com.zeus.common.base.entity.BaseQuery;
import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.model.Role;
import org.com.zeus.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	IRoleService roleService;

	@PostMapping("/add")
	@ResponseBody
	public BaseResullt add(Role role) {
		return BaseResullt.utils.setSuccessResult(role.insert());
	}
	
	@GetMapping("/list")
	public String query() {
		return "role/list";
	}

	@PostMapping("/list")
	@ResponseBody
	public BasePagination query(BaseQuery query) {
		IPage<Role> pageres=roleService.page(new Page<>(query.getPageNo(),query.getPageSize()), null);
		return new BasePagination(pageres.getRecords(), pageres.getTotal());
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public BaseResullt delete(@RequestBody ArrayList<Long> ids) {
		return BaseResullt.utils.setSuccessResult(roleService.removeByIds(ids));
	}
	
	
}
