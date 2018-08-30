package org.com.zeus.service;

import java.util.List;

import org.com.zeus.common.model.Person;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<Person> {

	public List<Person> selectListByWrapper(Wrapper wrapper);
}