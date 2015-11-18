package cn.itcast.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 	This class is to test data source
 */
public class TestDataSource {

	/**
	 * 	Load the Spring configuration files
	 */
	private ApplicationContext applicationContext;
	@Before
	public void setUp () throws Exception {
		
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}
	
	/**
	 * 	This method is to test the data source
	 */
	@Test
	public void testDataSource () {
		String dataSource = applicationContext.getBean("dataSource").toString();
		System.out.println(dataSource);
	}
	
	@Test
	public void testSqlSession () {
		
		String sqlSessionFactoryBean = applicationContext.getBean("sqlSessionFactoryBean").toString();
		System.out.println(sqlSessionFactoryBean);
	}
}
