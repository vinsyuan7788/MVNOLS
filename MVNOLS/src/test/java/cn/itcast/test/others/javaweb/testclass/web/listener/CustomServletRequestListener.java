package cn.itcast.test.others.javaweb.testclass.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 	This is the listener for life-cycle change of request scope
 */
public class CustomServletRequestListener implements ServletRequestListener {

	/**
	 *  Life-cycle method: invoked right after request scope is created (most useful)
	 * 	1. namely invoked when a request is started (e.g., HttpServletRequest is instantiated)
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	
	}

	/**
	 *  Life-cycle method: invoked right before application scope is destroyed
	 *  1. namely invoked when a request is ended (e.g., HttpServletRequest is collected by garbage collection)
	 */
	@Override
	public void requestInitialized(ServletRequestEvent sre) {

	}
}
