package cn.itcast.test.others.javaweb.testclass.web.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 	This is the listener for attribute change in session scope
 */
public class CustomHttpSessionAttributeListener implements HttpSessionAttributeListener {

	/**
	 * 	This method is invoked when attribute is added in session scope:
	 * 	1. e.g., this method is invoked when "HttpSession httpSession.setAttribute()" is called 
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
	
	}

	/**
	 * 	This method is invoked when attribute is removed in session scope:
	 * 	1. e.g., this method is invoked when "HttpSession httpSession.removeAttribute()" is called 
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {

	}

	/**
	 * 	This method is invoked when attribute is replaced in session scope:
	 * 	1. e.g., this method is invoked when "HttpSession httpSession.setAttribute()" is called 
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
	
	}

}
