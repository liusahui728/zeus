package org.com.zeus.service;

import java.util.List;

import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.dto.UserPermissionDTO;
import org.com.zeus.common.model.Person;
import org.com.zeus.common.model.User;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

	List<User> selectListByWrapper(Wrapper<User> wrapper);

	BaseResullt<User> register(User wrapper);
	
	List<String> getPermissionByAccount(User wrapper);
	
	List<String> getRoleByAccount(User wrapper);
}