package org.com.zeus.service.impl;

import org.com.zeus.common.model.Permission;
import org.com.zeus.mapper.PermissionMapper;
import org.com.zeus.service.IPermissionService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
}