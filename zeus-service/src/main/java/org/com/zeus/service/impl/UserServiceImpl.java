package org.com.zeus.service.impl;

import java.util.List;

import org.com.zeus.common.model.Person;
import org.com.zeus.mapper.PersonMapper;
import org.com.zeus.service.IUserService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<PersonMapper, Person> implements IUserService {

	@Override
	public List<Person> selectListByWrapper(Wrapper wrapper) {
		return baseMapper.selectList(wrapper);
	}
}