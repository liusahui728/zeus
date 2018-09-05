package org.com.zeus.controller;

import org.apache.shiro.SecurityUtils;
import org.com.zeus.common.model.User;
import org.springframework.beans.BeanUtils;

public class BaseController {
	public User getCurrentUser() {
		//热部署和序列化有冲突，所以直接用反射方式
		User user=new User();
		BeanUtils.copyProperties(SecurityUtils.getSubject().getPrincipal(), user);
		return user;
	}

	public String getCurrentUserName() {
		return getCurrentUser().getUsername();
	}

	public Long getCurrentUserId() {
		return getCurrentUser().getId();
	}
}
