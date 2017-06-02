package cn.itheima.bos.dao.base.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.itheima.bos.dao.base.StandardRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class StandardRepositoryTest {
	@Autowired
	private StandardRepository standardRepository; 
	
	
	@Test
	public void testQuery1(){
		System.out.println(standardRepository.findByName("30-40公斤"));
	}
	
	@Test
	//在查询方法上配置Query注解
	public void testQuery2(){
		System.out.println(standardRepository.queryName("20-30公斤"));
	}
	
	@Test
	//在实体类上配置Query注解
	public void testQuery3(){
		System.out.println(standardRepository.queryName2("30-40公斤"));
	}
	
	//带有条件的修改和删除操作，将记录一的最小长度改为15
	@Test
	@Transactional
	@Rollback(false)
	public void testUpdate4(){
		standardRepository.updateMinLength(1, 15);
	}
	
}
