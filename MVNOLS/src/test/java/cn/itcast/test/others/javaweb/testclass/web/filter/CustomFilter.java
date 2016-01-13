package cn.itcast.test.others.javaweb.testclass.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 	This is the filter class to demonstrate life-cycle methods
 */
public class CustomFilter implements Filter {

	/**
	 *  Life-cycle method: invoked right after filter is created: filter will be created when server is started
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		System.out.println("CustomFilter is created!");
	}
	 
	/**
	 *  Life-cycle method: invoked right before filter is destroyed: filter will be destroyed when server is closed
	 */
	@Override
	public void destroy() {
		
		System.out.println("CustomFilter is destroyed!");
	}

	/**
	 *  Life-cycle method: invoked each time do filtering
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		System.out.println("CustomFilter filters target resource!");
		chain.doFilter(request, response);		// invoke target resource & return here
		System.out.println("CustomFilter is back after discharging the request");
	}

}
