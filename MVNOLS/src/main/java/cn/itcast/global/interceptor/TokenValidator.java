package cn.itcast.global.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.global.annotation.utils.TokenUtils;
import cn.itcast.global.configuration.Constants;
import cn.itcast.global.exception.bean.CustomException;
import cn.itcast.global.session.SessionProvider;

/**
 * 	This is an interceptor that to avoid duplicate submission
 * 	1. The 1st way: interceptor + annotation + custom tag <token/>
 *     -- (1) Develop the annotation (i.e. Token in this case) to specify token save & validation
 *     -- (2) In the interceptor (i.e. this TokenVlidator class), parse the annotation for those specified URLs (i.e. TOKEN_URL)
 *     -- (3) If the method is annotated to save token, then save a token to session scope
 *     -- (4) If the method is annotated to validate token, then validate the token for duplicate submission check 
 *     -- (5) The rest of URLs & situations will be released
 *  2. The 2nd way: only use interceptor: 
 *     -- (1) Hard-code to save a token in the methods that will return or go to the JSP with forms that needs to perform duplicate submission check
 *        -- Hard-coding here is a big disadvantage, negatively affecting the system flexibility & scalability
 *     -- (2) For those specified URLs (i.e. TOKEN_URL), do the duplicate submission check
 *     -- (3) The rest of URLs will be directly released
 *     -- This is the most universal way
 *  3. The 3rd way to do the duplication submission processing:
 *     -- (1) In each action method that is necessary to be token validated (i.e. avoid the duplicate submission):
 *        -- save the token if the request is the first time submitted as a serverToken
 *        -- if there is a new request, check if there is a serverToken:
 *           -- if yes: forward back to the original view
 *           -- if no: then go on processing the request
 *     -- (2) This is the most specific way: there is no need to configure an interceptor for duplicate submission avoidance
 *     -- However this method is performance-sensitive. If the request is re-submitted before the server token is saved, then this request will be released, which is not desirable
 *     
 * @author Vince Xu Yuan    
 */
public class TokenValidator implements HandlerInterceptor {
	
	/*	IOP: IOC & DI	*/
	@Resource
	private SessionProvider sessionProvider;
	
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
		
		/*	For those specified URLs, do the token validation	*/
		if (Constants.TOKEN_URL.contains(request.getServletPath())) {
			
			/*	Get the handler|action method that will process the request	 */
			Method method = ((HandlerMethod) handler).getMethod();
			
			/*	If there is a "Token" annotation applied on the method & "saveToken" is true, remove the previous token & save a new token into session scope	*/
			if (TokenUtils.parseSaveToken(method) == true) {
				sessionProvider.setAttribute("token", UUID.randomUUID().toString(), request, response);
			}
			
			/*	If there is a "Token" annotation applied on the method & validToken() is true, predicate if the request is duplicate with the token	 */
			else if (TokenUtils.parseValidateToken(method) == true) {
				
        		/*	If the request is submitted the first time: release	 */
    			if (isFirstTime(request, response)) {
    				return true;
				
				/*	Else: return to the error view by throwing out an custom exception	*/
    			} else {
    				throw new CustomException("Your request is submitted successfully. Please do not repeat submission");
    			}	
			}
			
			/*	If there is no Token annotation applied on the method, make sure there is no token saved in the session	 */
			else {
				sessionProvider.removeAttribute("token", request, response);
			}
		}
		
		/*	For the rest of URLs & situations: release	*/
		return true;
	}

	/**
	 * 	Object handler: This is the H found by HM, which only contains 1 target method mapped by request URL
	 *  -- This target method is namely the handler|action method that will process the request (according to the SpringMVC request processing workflow)
	 *  -- To get this method: use "((HandlerMethod) handler).getMethod()"
	 * 	When to execute this method:
	 *  -- After executing the method of H|AC, before returning ModelAndView
	 * 	In what situation to use:
	 * 	-- Use ModelAndView to pass common data (e.g. username, userpoints|usercredits|userscores, etc.)
	 *  -- Use ModelAndView to unify the view
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		
		System.out.println("TokenValidator.postHandle()...");	
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
		
		System.out.println("TokenValidator.afterCompletion()...");
	}

	//-------------------------------------------------------------------------------------------
	/**
	 * 	This is an internal method to predicate if the request is the 1st time submitted with the token
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	private boolean isFirstTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*	See if there is a token	when the request is submitted	*/ 
		String clientToken = (String) sessionProvider.getAttribute("token", request, response);
		
		/*	If there is a token: predicate this is the first time submission & remove the token from session scope	*/
		if (clientToken != null) {
			sessionProvider.removeAttribute("token", request, response);
			return true;
		
		/*	If there is no token: predicate this is not the first time submission since the token has been removed	 */
		} else {
			return false;
		}
	}

}
