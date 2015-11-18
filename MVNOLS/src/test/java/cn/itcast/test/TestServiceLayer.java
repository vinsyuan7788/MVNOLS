package cn.itcast.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.user.bean.User;
import cn.itcast.user.service.UserService;

public class TestServiceLayer {
	
	/**
	 * 	Load the Spring configuration files
	 */
	private ApplicationContext applicationContext;
	@Before
	public void setUp () throws Exception {
		
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}
	
	/**
	 * 	Test the service integrated with Spring
	 * @throws Exception 
	 */
	@Test
	public void testQueryUserById () throws Exception {
		
		UserService userService = (UserService) applicationContext.getBean("userService");
		User user = userService.queryUserById(1);
		System.out.println(user.getUsername());
	}
}
