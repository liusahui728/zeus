package org.com.zeus.service.impl;

import org.com.zeus.common.model.UserRole;
import org.com.zeus.mapper.UserRoleMapper;
import org.com.zeus.service.IUserRoleService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
}