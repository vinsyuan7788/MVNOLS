package cn.itcast.test.others.javaweb.testclass.web.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 	This is the servlet class to demonstrate life-cycle methods
 */
public class CustomServlet implements Servlet {

	/**
	 *  Life-cycle method: called by Tomcat (server)
	 *  1. Executed once before Servlet object is destroyed (when server (e.g. Tomcat) is stopped)
	 */
	@Override
	public void destroy() {
		System.out.println("destory() in CustomServlet is called by server.");
	}

	/*
	 *  Used to return Servlet configuration information (by other methods like init())
	 */
	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig() in CustomServlet.....");
		return null;
	}

	/*
	 *	Used to return Servlet information: usually unused 
	 */
	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo() in CustomServlet.....");
		return null;
	}

	/**
	 *  Life-cycle method: called by Tomcat (server)
	 *  1. Executed once after Servlet object is instantiated (when it is first time accessed after server is started, or when the server is started)  
	 */
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println("init() in CustomServlet is called by server");
		
		/*	Obtain the initial parameter value by using SerletConfig methods  */
		System.out.println(servletConfig.getInitParameter("p1"));
		System.out.println(servletConfig.getInitParameter("p2"));
		
		/* 	Obtain the initial parameter name by using SerletConfig methods	 */
		Enumeration e = servletConfig.getInitParameterNames();
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement());
//			System.out.println(servletConfig.getInitParameter((String) e.nextElement()));
		}
	}

	/**
	 *  Life-cycle method: called by Tomcat (server)
	 *  1. Executed multiple times for processing the request
	 */
	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse)
			throws ServletException, IOException {
		System.out.println("service() in CustomServlet is called by server");
	}
}
