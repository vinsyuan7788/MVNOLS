package cn.itcast.test.others.javaweb.testclass.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 	This is the listener for life-cycle change of application scope
 */
public class CustomServletContextListener implements ServletContextListener {

	/**
	 *  Life-cycle method: invoked right after application scope is created (most useful)
	 * 	1. namely invoked when the web application is started
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Me, the application, is created!");
	}

	/**
	 *  Life-cycle method: invoked right before application scope is destroyed
	 *  1. namely invoked when the web application is ended
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Me, the application, is destroyed!");
	}
}
