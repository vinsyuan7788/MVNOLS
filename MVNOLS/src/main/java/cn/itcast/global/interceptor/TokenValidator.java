package cn.itcast.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.global.configuration.BusinessConstants;
import cn.itcast.global.exception.CustomException;

/**
 * 	This is an interceptor that to avoid duplicate submission
 *  1. This interceptor will do the duplication submission processing for all necessary requests
 *     -- (1) Those URLs that need to token validation are specified in "token_url.properties"
 *     -- (2) The rest of URLs will be directly released
 *     -- This is the most universal way
 *  2. Another method refers to "cn.itcast.global.annotation.Token.java".
 *     -- This is the method in the midst of universal & specific
 *  3. The 3rd way to do the duplication submission processing:
 *     -- (1) In each action method that is necessary to be token validated (i.e. avoid the duplicate submission):
 *        -- save the token if the request is the first time submitted as a serverToken
 *        -- if there is a new request, check if there is a serverToken:
 *           -- if yes: forward back to the original view
 *           -- if no: then go on processing the request
 *     -- (2) This is the most specific way: there is no need to configure an interceptor for duplicate submission avoidance
 */
public class TokenValidator implements HandlerInterceptor {
	
	/**
	 * 	Object handler: This is the H(Handler) found by HM, which only contains 1 target method mapped by request URL
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
		
		/*	For those specified URLs, do the token validation	*/
		if (BusinessConstants.TOKEN_URL.contains(request.getServletPath())) {
			
			/*	If the request is submitted the first time: release	 */
			if (isFirstTime(request)) {
				return true;
				
			/*	Else: return to the error view by throwing out an custom exception	*/
			} else {
				throw new CustomException("Your request is submitted successfully. Please do not repeat submission");
			}
		} 
		
		/*	For the rest of URLs: release	*/
		return true;
	}
	
	
	/**
	 * 	This is an internal method to predicate if the request is the 1st time submitted
	 * @param request
	 * @return
	 */
	private boolean isFirstTime(HttpServletRequest request) {
		
		/*	See if there is a token	when the request is submitted	*/ 
		String clientToken = (String) request.getSession().getAttribute("token");
		
		/*	If there is a token: predicate this is the first time submission & remove the token from session scope	*/
		if (clientToken != null) {
			request.getSession().removeAttribute("token");
			return true;
		
		/*	If there is no token: predicate this is not the first time submission since the token has been removed	 */
		} else {
			return false;
		}
	}

	/**
	 * 	Object handler: This is the H found by HM, which only contains 1 target method mapped by request URL
	 * 	When to execute this method:
	 *  -- After executing the method of H|AC, before returning ModelAndView
	 * 	In what situation to use:
	 * 	-- Use ModelAndView to pass common data (e.g. username, userpoints(用户积分), etc.)
	 *  -- Use ModelAndView to unify the view
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		
		System.out.println("TokenValidator.postHandle()...");	
	}

	/**
	 * 	Object handler: This is the H found by HM, which only contains 1 target method mapped by request URL
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
		
		System.out.println("TokenValidator.afterCompletion()...");
	}

}
