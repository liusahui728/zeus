package org.com.zeus.service.impl;

import org.com.zeus.common.model.Role;
import org.com.zeus.mapper.RoleMapper;
import org.com.zeus.service.IRoleService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}