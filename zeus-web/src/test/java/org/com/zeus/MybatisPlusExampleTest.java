package org.com.zeus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.zeus.common.model.Example;
import org.com.zeus.mapper.ExampleMapper;
import org.com.zeus.service.IExampleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class MybatisPlusExampleTest extends AppTest {
	@Autowired
	ExampleMapper exampleMapper;
	@Autowired
	IExampleService exampleService;
	@Test
	public void test() {
		Example example=new Example();
		example.setName("example2");
		example.setExComment("example1_comment");
		example.setExid(3);
		System.out.println("model-------------->");
		example.insert();
		example.insertOrUpdate();
		example.delete(new QueryWrapper<Example>().lambda().eq(Example::getId, 1L));
		example.deleteById();
		example.deleteById(1L);
		example.selectAll();
		example.selectById();
		example.selectById(1L);
		example.selectCount(null);
		example.selectOne(new QueryWrapper<Example>().lambda().eq(Example::getId, 1L));
		example.selectPage(new Page<Example>(1, 10), new QueryWrapper<Example>().lambda().orderByAsc(Example::getExid));
		example.updateById();
		//example.update(new QueryWrapper<Example>().lambda().eq(Example::getExid, example.getExid()));
		//删除
		System.out.println("mapper-------------->");
		exampleMapper.insert(example);
		List<Long> list=new ArrayList<>();
		list.add(1L);
		list.add(2L);
		exampleMapper.deleteBatchIds(list);
		Map<String,Object> map=new HashMap<>();
		map.put("id", 1L);
		map.put("exid", 1L);
		exampleMapper.deleteByMap(map);
		exampleMapper.deleteById(example);
		List<Long> list1=Arrays.asList(1L,2L);
		exampleMapper.selectBatchIds(list1);
		exampleMapper.update(example, new QueryWrapper<Example>().lambda().eq(Example::getExid, 3));
		System.out.println("service-------------->");
		List<Example> listb=new ArrayList<>();
		Example example1=new Example();
		example1.setName("example2");
		example1.setExComment("example1_comment");
		example1.setExid(4);
		Example example2=new Example();
		example2.setName("example2");
		example2.setExComment("example1_comment");
		example2.setExid(4);
		listb.add(example1);
		exampleService.saveBatch(listb);
	}

}
