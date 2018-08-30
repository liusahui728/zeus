package org.com.zeus;

import org.com.zeus.common.model.Person;
import org.com.zeus.mapper.PersonMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PersonTest extends AppTest {
	@Autowired
	PersonMapper personDao;

	@Test
	public <T> void test() {
		Person person=new Person();
		person.setName("张三");
		personDao.insert(person);
		System.out.println(personDao.selectById(1L));
		System.out.println(personDao.cgetAllName());
		Page<T> pagePlus = new Page<T>();
		pagePlus.setCurrent(0);
        pagePlus.setSize(10);
        pagePlus.setAsc("id");
		System.out.println(personDao.selectPage(new Page<Person>(1, 10), null));
	}
}
