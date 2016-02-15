package cn.itcast.global.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.global.exception.bean.CustomException;
import cn.itcast.global.exception.utils.ExceptionUtils;


/**
 * 	This is a global exception resolver to process ALL exceptions (java.lang.exceptions and custom exceptions):
 *  1. Must extend HandlerExceptionResolver
 *     -- So that SpringMVC will treat this class as a global exception resolver when configured in "springmvc.xml"
 *  2. This CustomExceptionResolver needs to be configured in "springmvc.xml"
 *  3. In a system, there is ONLY 1 global exception resolver
 *  
 *  Before the exception is thrown to global excpetion resolver, can catch & print out the exception to log files, then throw the exception
 *  Or when the exception is thrown to global exception resolver, print out the exception to log files, then return a message to front-end view
 *  -- To print out the exception to log files: use "PrintOutStream" class
 *     -- In this project, only the runtime exception will be printed to log files 
 *  -- The log files can be named according to the current date
 *     -- To periodically generate the log file names: use "Timer" class or "Quartz" jars|framework
 *     
 * @author Vince Xu Yuan
 */
public class GlobalExceptionResolver extends ExceptionUtils implements HandlerExceptionResolver {

	/**
	 * 	Object handler: This is the H found by HM, which only contains 1 target method mapped by request URL
	 * 	Exception ex: This is the exception thrown out by the system (From DAO to Service to Action to DS) or captured by DS
	 * 	When to execute this method:
	 *  -- When exception is thrown out during the  execution of method in H
	 * 	In what situation to use:
	 * 	-- Process exception in global
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		/*	Instantiate a String object to store error message	*/
		String errorMessage = null;
		
		/*	If the exception belongs to CustomException, then get the exception message	*/
		if (ex instanceof CustomException) {
			errorMessage = ((CustomException)ex).getMessage();
			
		/*	If the exception are not in CustomException, get a message specifying "system runtime error" & print the exception trace to logs	*/	
		} else {
			errorMessage = "System runtime error";
			this.printToLogs(ex);
		}
		
		/*	Return the error message to view	*/
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", errorMessage);
		modelAndView.setViewName("error");				// return to prefix+"error"+suffix view	
		return modelAndView;
	}
}
