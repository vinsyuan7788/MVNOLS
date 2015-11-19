package cn.itcast.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 	This is an interceptor that can handle exception
 */
public class ParameterValidator implements HandlerInterceptor {

	/**
	 * 	Object handler: This is the H(Handler) found by HM, which only contains 1 target method mapped by request URL
	 *  -- This target method is namely the handler|action method that will process the request (according to the SpringMVC request processing workflow)
	 *  -- To get this method: use ((HandlerMethod) handler).getMethod()
	 * 	When to execute this method:
	 *  -- Before executing the method of handler
	 *     -- False: means intercept
	 *     -- True: means release
	 * 	In what situation to use:
	 *  -- Login|Registration information check
	 * 	-- User Role check
	 *  -- User Privilege check 
	 *  
	 *  Note: user HttpServletRequest & HttpServletResponse can get the parameters from the view just as we did in JavaWeb.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		/*	
		 * 	Check the id value:
		 * 	If 0: then return an error message
		 * 	If not 0: release
		 */
		String id = request.getParameter("id");
		
		if (id.equals("0")) {
			request.setAttribute("msg", "Please select a id");
			request.getRequestDispatcher("/WEB-INF/jsp/testAction.jsp").forward(request, response);
			return false;
		} 
		return true;
	}
	
	/**
	 * 	Object handler: This is the H found by HM, which only contains 1 target method mapped by request URL
	 *  -- This target method is namely the handler|action method that will process the request (according to the SpringMVC request processing workflow)
	 *  -- To get this method: use ((HandlerMethod) handler).getMethod()
	 * 	When to execute this method:
	 *  -- After executing the method of H|AC, before returning ModelAndView
	 * 	In what situation to use:
	 * 	-- Use ModelAndView to pass common data (e.g. username, userpoints(用户积分), etc.)
	 *  -- Use ModelAndView to unify the view
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		
		System.out.println("ParameterChecker.postHandle()...");
		
	}

	/**
	 * 	Object handler: This is the H found by HM, which only contains 1 target method mapped by request URL
	 *  -- This target method is namely the handler|action method that will process the request (according to the SpringMVC request processing workflow)
	 *  -- To get this method: use ((HandlerMethod) handler).getMethod()
	 * 	When to execute this method:
	 *  -- After completing execution of method in H (already returning ModelAndView)
	 * 	In what situation to use:
	 * 	-- Exception processing
	 *  -- Action method monitoring
	 *  -- Do system logging
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {

		System.out.println("ParameterChecker.afterCompletion()...");
		
	}

}
