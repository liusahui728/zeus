package org.com.zeus.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.model.User;
import org.com.zeus.mapper.UserMapper;
import org.com.zeus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@Autowired
	UserMapper userMapper;
	@Autowired
	IUserService userService;

	// 退出的时候是get请求，主要是用于退出
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// post登录
	@PostMapping("/login")
	@ResponseBody
	public BaseResullt login(@RequestBody Map<String, Object> map) {
		// 添加用户认证信息
		Subject subject = SecurityUtils.getSubject();
		String userName = map.get("username").toString();
		String password = map.get("password").toString();
		String newPs = new SimpleHash("MD5", password, ByteSource.Util.bytes(userName), 2).toHex();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, newPs);
		// 进行验证，这里可以捕获异常，然后返回对应信息
		try {
			subject.login(usernamePasswordToken);
		} catch (Exception e) {
			return BaseResullt.utils.setFailMsg(e.getMessage());
		}

		return BaseResullt.utils.setSuccess();
	}

	@GetMapping("/doRegister")
	public String doRegister() {
		return "register";

	}

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView(); 
		mav.setViewName("index");
		mav.addObject("username", SecurityUtils.getSubject().getPrincipal());
		return mav;

	}

	@GetMapping("/add")
	public String add() {
		return "add";

	}

	@GetMapping("/query")
	public String query() {
		return "query";

	}

	@GetMapping("/edit")
	public String edit() {
		return "edit";

	}

	@GetMapping("/delete")
	public String delete() {
		return "delete";

	}
	@PostMapping("/doRegister")
	@ResponseBody
	public BaseResullt doRegister(@RequestBody User user) {
		return userService.register(user);

	}
}
