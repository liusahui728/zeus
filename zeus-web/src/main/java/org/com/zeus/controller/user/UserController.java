package org.com.zeus.controller.user;

import java.util.Map;

import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.model.User;
import org.com.zeus.controller.BaseController;
import org.com.zeus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@PostMapping("/query")
	@ResponseBody
	public Map query(@RequestBody Map map) {
/*		User user=new User();
		BaseQuery query=new BaseQuery();
		BeanUtils.copyProperties(map, user);
		BeanUtils.copyProperties(map, query);
		Map mapt = new HashMap<>();
		IPage<User> pageres=userService.page(new Page<User>(query.getPageNo(),query.getPageSize()), new QueryWrapper<User>(user));
		map.put("total", pageres.getTotal());
		map.put("rows", pageres.getRecords());*/
		return map;
	}

}
