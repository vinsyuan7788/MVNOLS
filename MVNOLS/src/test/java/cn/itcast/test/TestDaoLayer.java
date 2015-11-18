package cn.itcast.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.test.dao.UserDao;
import cn.itcast.user.bean.User;
import cn.itcast.user.dao.mapper.UserMapper;

public class TestDaoLayer {

	/**
	 * 	Load the Spring configuration files
	 */
	private ApplicationContext applicationContext;
	@Before
	public void setUp () throws Exception {
		
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}
	
	/**
	 * 	Test the dynamic proxy DAO development integrated wit Spring
	 * @throws Exception 
	 */
	@Test
	public void testQueryById () throws Exception {
		
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		User user = userMapper.selectByPrimaryKey(1);
		System.out.println(user.getUsername());
	}
	
	/**
	 * 	Test the regular DAO development integrated with Spring
	 * @throws Exception 
	 */
	@Test
	public void testQueryUserById () throws Exception {
		
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		User user = userDao.queryUserById(1);
		System.out.println(user.getUsername());
	}
}
