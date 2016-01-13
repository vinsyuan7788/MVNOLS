package cn.itcast.test.others.javaweb.testclass.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * 	This is the listener for attribute change in application scope
 */
public class CustomServletContextAttributeListener implements ServletContextAttributeListener {

	/**
	 * 	This method is invoked when attribute is added in application scope:
	 * 	1. e.g., this method is invoked when "ServletContext servletContext.setAttribute()" is called 
	 */
	@Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
    	System.out.println("The attribute added ints application: " + scae.getName());
    	System.out.println("its value: "  +  scae.getValue());
    }
	
	/**
	 * 	This method is invoked when attributed is removed
	 *  1. e.g., this method is invoked when "ServletContext servletContext.removeAttribute()" is called 
	 */
	@Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
    	System.out.println("The removed attribute in application: " + scae.getName());
    	System.out.println("its value: " + scae.getValue());
    }

	/**
	 * 	This method is invoked when attributed is replaced
	 *  1. e.g., this method is invoked when "ServletContext servletContext.setAttribute()" is called 
	 */
	@Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
    	System.out.println("The replaced attribute in application: " + scae.getName());
    	System.out.println("its original value: " + scae.getValue());
    	System.out.println("its new value: " + scae.getServletContext().getAttribute(scae.getName()));
    }
}
