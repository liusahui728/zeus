package org.com.zeus.service.impl;

import org.com.zeus.common.model.Example;
import org.com.zeus.mapper.ExampleMapper;
import org.com.zeus.service.IExampleService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class ExampleServiceImpl extends ServiceImpl<ExampleMapper, Example> implements IExampleService {
}