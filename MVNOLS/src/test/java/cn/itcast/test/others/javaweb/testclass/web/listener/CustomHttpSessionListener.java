package cn.itcast.test.others.javaweb.testclass.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 	This is the listener for life-cycle change of session scope
 */
public class CustomHttpSessionListener implements HttpSessionListener {

	/**
	 *  Life-cycle method: invoked right after session scope is created (most useful)
	 * 	1. namely invoked when a session is started (e.g., HttpSession is instantiated)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		
	}
	
	/**
	 *  Life-cycle method: invoked right before session scope is ended (most useful)
	 * 	1. namely invoked when a session is ended (e.g., HttpSession object is collected by garbage collection)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}
}
