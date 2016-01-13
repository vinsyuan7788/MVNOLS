package cn.itcast.test.others.javaweb.testclass.web.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * 	This is the listener for attribute change in request scope
 */
public class CustomServletRequestAttributeListener implements ServletRequestAttributeListener {

	/**
	 * 	This method is invoked when attribute is added in request scope:
	 * 	1. e.g., this method is invoked when "HttpServletRequest httpServletRequest.setAttribute()" is called 
	 */
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {

	}

	/**
	 * 	This method is invoked when attribute is removed in request scope:
	 * 	1. e.g., this method is invoked when "HttpServletRequest httpServletRequest.removeAttribute()" is called 
	 */
	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
	
	}

	/**
	 * 	This method is invoked when attribute is replaced in request scope:
	 * 	1. e.g., this method is invoked when "HttpServletRequest httpServletRequest.setAttribute()" is called 
	 */
	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
	
	}
}
