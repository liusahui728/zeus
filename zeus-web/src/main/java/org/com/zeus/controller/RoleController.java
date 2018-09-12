package org.com.zeus.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.com.zeus.common.base.entity.BaseCombobox;
import org.com.zeus.common.base.entity.BasePagination;
import org.com.zeus.common.base.entity.BaseQuery;
import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.base.entity.BaseTree;
import org.com.zeus.common.model.Role;
import org.com.zeus.common.model.User;
import org.com.zeus.common.model.UserRole;
import org.com.zeus.service.IRoleService;
import org.com.zeus.service.IUserRoleService;
import org.com.zeus.service.IUserService;
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
@RequestMapping("/role")
public class RoleController extends BaseController{
	@Autowired
	IRoleService roleService;
	@Autowired
	IUserService userService;
	@Autowired
	IUserRoleService userRoleService;

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
	
	@PostMapping("/getUserCombbo")
	@ResponseBody
	public List getUserCombbo(String roleId) {
		List<UserRole> listUserRole=userRoleService.list(new QueryWrapper<UserRole>().lambda().eq(UserRole::getRoleId, roleId));
		List<Long> listUser=listUserRole.stream().map(UserRole::getUserId).collect(Collectors.toList());
		List<User> list=userService.list(null);
		List<BaseCombobox> listCombo=BaseCombobox.getDefaultSelect();
		list.stream().forEach(p->{
			if(!listUser.contains(p.getUserId())) {
				BaseCombobox c=new BaseCombobox();
				c.setId(p.getUserId());
				c.setText(p.getAccount());
				listCombo.add(c);
			}
		});
		return listCombo;
	}
	
	@PostMapping("/addUser")
	@ResponseBody
	public BaseResullt add(UserRole userRole) {
		return BaseResullt.utils.setSuccessResult(userRole.insert());
	}
	
	@PostMapping("/getRoleUserTree")
	@ResponseBody
	public BaseResullt getRoleUserTree() {
		List<BaseTree> list=new ArrayList<>();
		List<Role> listRole=roleService.list(null);
		for(Role role:listRole) {
			BaseTree baseTree=new BaseTree();
			baseTree.setId(role.getRoleId());
			baseTree.setText(role.getRoleName());
			List<UserRole> listUserRole=userRoleService.list(new QueryWrapper<UserRole>().lambda().eq(UserRole::getRoleId, role.getRoleId()));
			List<Long> idList=listUserRole.stream().map(UserRole::getUserId).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(idList)) {
				baseTree.setState("closed");
				List<User> listUser=(List<User>) userService.listByIds(idList);
				List<BaseTree> listUserTree=listUser.stream().map(p->{
					BaseTree usbaseTree=new BaseTree();
					usbaseTree.setId(p.getUserId());
					usbaseTree.setText(p.getUserName());
					return usbaseTree;
					}).collect(Collectors.toList());
				baseTree.setChildren(listUserTree);
			}
			list.add(baseTree);
		}
		return BaseResullt.utils.setSuccessResult(list);
	}
}
