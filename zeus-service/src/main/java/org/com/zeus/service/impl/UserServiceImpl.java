package org.com.zeus.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.model.Person;
import org.com.zeus.common.model.User;
import org.com.zeus.mapper.PersonMapper;
import org.com.zeus.mapper.UserMapper;
import org.com.zeus.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public List<User> selectListByWrapper(Wrapper<User> wrapper) {
		return baseMapper.selectList(wrapper);
	}

	@Override
	public BaseResullt<User> register(User user) {
		// 将用户名作为盐值
		ByteSource salt = ByteSource.Util.bytes(user.getUsername());
		/*
		 * MD5加密： 使用SimpleHash类对原始密码进行加密。 第一个参数代表使用MD5方式加密 第二个参数为原始密码 第三个参数为盐值，即用户名
		 * 第四个参数为加密次数 最后用toHex()方法将加密后的密码转成String
		 */
		String newPs = new SimpleHash("MD5", user.getPassword(), salt,2).toHex();
		user.setPassword(newPs);
		// 看数据库中是否存在该账户
		List<Map<String, Object>> list = baseMapper
				.selectMaps(new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername()));
		if (CollectionUtils.isEmpty(list)) {
			baseMapper.insert(user);
			return BaseResullt.utils.setSuccess();
		} else {
			return BaseResullt.utils.setFailMsg("用户名已被注册");
		}

	}

}