package org.com.zeus.controller.user;

import org.com.zeus.common.base.entity.BasePagination;
import org.com.zeus.common.base.entity.BaseQuery;
import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.model.User;
import org.com.zeus.controller.BaseController;
import org.com.zeus.service.IUserService;
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
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	IUserService userService;

	@PostMapping("/add")
	@ResponseBody
	public BaseResullt add(@RequestBody User user) {
		return userService.register(user);

	}
	
	@GetMapping("/list")
	public String query() {
		return "user/list";
	}

	@PostMapping("/list")
	@ResponseBody
	public BasePagination query(BaseQuery query) {
		IPage<User> pageres=userService.page(new Page<User>(query.getPageNo(),query.getPageSize()), null);
		return new BasePagination(pageres.getRecords(), pageres.getTotal());
	}

}
