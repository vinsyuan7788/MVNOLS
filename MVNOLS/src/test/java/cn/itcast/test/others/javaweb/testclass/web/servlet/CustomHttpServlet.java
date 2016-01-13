package cn.itcast.test.others.javaweb.testclass.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	This is the HttpServlet class to demonstrate:
 * 	1. HttpServletRequest & HttpServerResponse
 *  2. 3 scopes: request|session|application
 *     -- Request: the interaction between user and web-app in a single HTTP request
 *        -- Created when a request is started (e.g., HttpServletRequest is instantiated) & ended when a request is ended (e.g., HttpServletRequest instance is collected by garbage collection)
 *        -- Operated by "HttpServletRequest httpServletrequest"
 *     -- Session: the interaction between user and web-app in mutliple HTTP requests
 *  	  -- Created when a session is started (e.g., HttpSession is instantiated) & ended when a session is ended (e.g., HttpSession instance is collected by garbage collection)
 *        -- Operated by "HttpSession httpSession"
 *     -- Application (by "ServletContext servletContext"): the interaction between user and web-app across the whole web application life-cycle
 *        -- Created when web-app (e.g., tomcat) is started & ended when web-app (e.g., tomcat) is ended
 *        -- Operated by "ServletContext servletContext"
 */
public class CustomHttpServlet extends HttpServlet {

	/**
	 * 	This is a method that allows a servlet to handle a GET request.
	 *  1. e.g., when the form attribute "method" equals to "get", the request is GET request. 
	 *     If this GET request is sent to this servlet, then this servlet will invoke this method to process this request.
	 * @param request: the request send by the client to the server
	 * @param response: the response send by the server to the client
	 * @throws ServletException: if an error occurred
	 * @throws IOException: if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*	
		 *	Obtain the URL related information
		 * 	e.g. for "http://localhost:8080/projectName/requestPath.servlet?XXX=xxx&YYY=yyy"
		 * 	1. request.getScheme(): return "http"
		 *  2. request.getServerName(): return "localhost"
		 *  3. request.getServerPort(): return "8080"
		 *  4. request.getContextPath(): return "/projectName"
		 *  5. request.getServletPath(): return "/requestPath.servlet"
		 *  6. request.getQueryString(): return "XXX=xxx&YYY=yyy"
		 *  7. request.getRequestURI(): return "/projectName/requestPath.servlet"
		 *  8. request.getRequestURL(): return "http://localhost:8080/projectName/requestPath.servlet"
		 */
		String Protocol = request.getScheme();
		String ServerName = request.getServerName();
		Integer ServerPort = request.getServerPort();
		String ProjectPath = request.getContextPath(); 			// (*******)
		String ServletPath = request.getServletPath();
		String ParameterInfo = request.getQueryString();
		String URI = request.getRequestURI();
		StringBuffer URL = request.getRequestURL();
		
		/* 	Obtain the PrintWriter object	*/
		PrintWriter Writer = response.getWriter();

		/* 	Display the result to browser	*/
		response.setContentType("text/html");					// print out to browser as html format
		Writer.print("Protocol: " + Protocol + "<br/>");
		Writer.print("Server Name: " + ServerName + "<br/>");
		Writer.print("Server Port: " + ServerPort + "<br/>");
		Writer.print("Project Path: " + ProjectPath + "<br/>");
		Writer.print("Servlet Path: " + ServletPath + "<br/>");
		Writer.print("Parameter Info: " + ParameterInfo + "<br/>");
		Writer.print("URI: " + URI + "<br/>");
		Writer.print("URL: " + URL + "<br/>");
	}
	
	/**
	 * 	This is a method that allows a servlet to handle a POST request.
	 *  1. e.g., when the form attribute "method" equals to "post", the request is POST request. 
	 *     If this POST request is sent to this servlet, then this servlet will invoke this method to process this request.
	 * @param request: the request send by the client to the server
	 * @param response: the response send by the server to the client
	 * @throws ServletException: if an error occurred
	 * @throws IOException: if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*	Operate the request scope through HttpServletRequest	*/
		request.setAttribute("testData", 100);
		
		/*	Operate the session scope through HttpSession	 */
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("testData", "Hello");
		
		/*	Operate the application scope through ServletContext	*/
		ServletContext servletContext = httpSession.getServletContext();
//		ServletContext servletContext = request.getServletContext();
		servletContext.setAttribute("testData", "JavaWeb");
	}
}
