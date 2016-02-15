package cn.itcast.global.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * 	This is a utility class to process all formats in AJAX Response, which includes
 *  1. JSON format
 *  2. XML format
 *  3. Text format
 * @author Vince Xu Yuan
 */
public class AjaxResponseUtils {
	
	/**
	 * 	This is a private method that can be invoked for AJAX response with JSON, XML, or text  
	 * @param response
	 * @param contentType
	 * @param responseContent
	 */
	private static void ajaxResponse (HttpServletResponse response, String contentType, String responseContent) {
		
		try {
			response.setContentType(contentType);
			response.getWriter().write(responseContent);
		} catch (Exception e) {
			new RuntimeException("Excetpion raised by \"response.getWriter()\"", e);
		}
		
	}

	/**
	 * 	This is a static method to provide AJAX response with JSON
	 * @param response
	 * @param responseContent
	 */
	public static void responseWithJson (HttpServletResponse response,String responseContent) {
		
		ajaxResponse(response, "application/json;charset=UTF-8", responseContent);
	}

	/**
	 * 	This is a static method to provide AJAX response with XML
	 * @param response
	 * @param responseContent
	 */
	public static void responseWithXml(HttpServletResponse response,String responseContent) {
		
		ajaxResponse(response, "text/xml;charset=UTF-8", responseContent);
	}
	
	/**
	 * 	This is a static method to provide AJAX Response with text
	 * @param response
	 * @param responseContent
	 */
	public static void responseWithText(HttpServletResponse response,String responseContent) {
		
		ajaxResponse(response, "text/plain;charset=UTF-8", responseContent);
	}
}
