package org.com.zeus;

import org.com.zeus.common.model.Person;
import org.junit.Test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class ModelTest extends AppTest {
	@Test
	public void test() {
		Person person=new Person();
		person.selectPage(new Page<>(0,10), new QueryWrapper<>());
	}
}
