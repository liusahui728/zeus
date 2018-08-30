package org.com.zeus.mapper;

import java.util.List;

import org.com.zeus.common.model.Person;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface PersonMapper extends BaseMapper<Person> {
	public List<String> cgetAllName();
}
