package org.com.zeus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.model.User;
import org.com.zeus.mapper.UserMapper;
import org.com.zeus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController {
	@Autowired
	UserMapper userMapper;
	@Autowired
	IUserService userService;

	@GetMapping("/login")
	public String login() {
		return "login/login";

	}

	// post登录
	@PostMapping("/login")
	@ResponseBody
	public BaseResullt login(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		// 添加用户认证信息
		Subject subject = SecurityUtils.getSubject();
		String userName = map.get("username").toString();
		String password = map.get("password").toString();
		Boolean rememberme = (Boolean) map.get("rememberMe");
		/*
		 * String newPs = new SimpleHash("MD5", password,
		 * ByteSource.Util.bytes(userName), 2).toHex();
		 */
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
		usernamePasswordToken.setRememberMe(rememberme);
		// 进行验证，这里可以捕获异常，然后返回对应信息
		try {
			subject.login(usernamePasswordToken);
		} catch (DisabledAccountException e) {
			return BaseResullt.utils.setFailMsg("账户已被禁用");
		} catch (AuthenticationException e) {
			return BaseResullt.utils.setFailMsg("登陆失败");
		}
		// 执行到这里说明用户已登录成功
		return BaseResullt.utils.setSuccess();
	}

	@GetMapping("/doRegister")
	public String doRegister() {
		return "login/register";

	}

	@GetMapping("/index")
	public ModelAndView index(HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		Cookie newCookie = new Cookie("userName", getCurrentUserName());
		response.addCookie(newCookie);
		mav.addObject("user", SecurityUtils.getSubject().getPrincipal());
		return mav;

	}

	@GetMapping("/add")
	public String add() {
		return "user/add";

	}

	@GetMapping("/query")
	public String query(Model model, HttpServletRequest request) {
		return "user/query";

	}
	
	@GetMapping("/getData")
	@ResponseBody
	public Map getData() {
		List list=userService.list(null);
		Map map=new HashMap<>();
		map.put("total", 120);
		map.put("rows", list);
		return map;

	}

	@GetMapping("/edit")
	public String edit() {

		System.out.println(getCurrentUserName());
		return "user/edit";

	}

	@GetMapping("/delete")
	public String delete() {
		return "user/delete";

	}

	@GetMapping("/order")
	public String order() {
		return "user/order";

	}

	@PostMapping("/doRegister")
	@ResponseBody
	public BaseResullt doRegister(@RequestBody User user) {
		return userService.register(user);

	}


	// 被踢出后跳转的页面
	@GetMapping(value = "/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "login/login";
	}
}
