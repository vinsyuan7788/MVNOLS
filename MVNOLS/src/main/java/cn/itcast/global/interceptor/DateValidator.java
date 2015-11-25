package cn.itcast.global.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.global.utils.DateUtils;

/**
 * 	This is an interceptor that can process the comparison between 2 dates for user birthday
 * 	1. Interceptor is executed before converter
 */
public class DateValidator implements HandlerInterceptor {

	/**
	 * 	Object handler: This is the H(Handler) found by HM, which only contains 1 target method mapped by request URL
	 *  -- This target method is namely the handler|action method that will process the request (according to the SpringMVC request processing workflow)
	 *  -- To get this method: use "((HandlerMethod) handler).getMethod()"
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
		
		/*	If there is no input on birthday field, release the request	 */
		if (request.getParameter("birthday") == null || request.getParameter("birthday").trim().length() == 0) {
			return true;
			
		/*	If there is an input on birthday field, then continue	*/	
		} else {
			
			/*	Parse the Date data	 */
			try {
				
				/*	If the birthday does not exceed current date, return to error page	*/
				if (DateUtils.compareDates(new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("birthday")), new Date()) != -1) {
					return true;
				
				/*	Else return to error page with a message	*/
				} else {
					request.setAttribute("errorMessage", "Your birthday cannot exceed current date!");
					request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
					return false;
				}
				
			/*	If the Date parameter is unparsable, return to error page with a message	*/
			} catch (Exception e) {
				request.setAttribute("errorMessage", "Your birthday format should be MM/dd/yyyy (i.e. month/day/year)!");
				request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
				return false;
			}
		}
	}
	
	/**
	 * 	Object handler: This is the H found by HM, which only contains 1 target method mapped by request URL
	 *  -- This target method is namely the handler|action method that will process the request (according to the SpringMVC request processing workflow)
	 *  -- To get this method: use "((HandlerMethod) handler).getMethod()"
	 * 	When to execute this method:
	 *  -- After executing the method of H|AC, before returning ModelAndView
	 * 	In what situation to use:
	 * 	-- Use ModelAndView to pass common data (e.g. username, userpoints(用户积分), etc.)
	 *  -- Use ModelAndView to unify the view
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		
		System.out.println("DateValidator.postHandle()...");
		
	}

	/**
	 * 	Object handler: This is the H found by HM, which only contains 1 target method mapped by request URL
	 *  -- This target method is namely the handler|action method that will process the request (according to the SpringMVC request processing workflow)
	 *  -- To get this method: use "((HandlerMethod) handler).getMethod()"
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

		System.out.println("DateValidator.afterCompletion()...");
		
	}

}
