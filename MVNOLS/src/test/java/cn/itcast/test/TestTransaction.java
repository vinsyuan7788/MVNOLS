package cn.itcast.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.test.service.TestService;

/**
 * 	This is the class to test transaction
 */
public class TestTransaction {

	/**
	 * 	Load the Spring configuration files
	 */
	private ApplicationContext applicationContext;
	@Before
	public void setUp () throws Exception {
		
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}
	
	/**
	 * 	Test the transaction AOP for service
	 * @throws Exception 
	 *  1. The JUnit test will fail
	 * 	2. The result shows "Rolling back JDBC transaction on Connection...", means transaction management is working
	 */
	@Test
	public void testTransaction () throws Exception {
		TestService testService = (TestService) applicationContext.getBean("testService");
		testService.insertCountryForTransactionTesting();
	}
}
