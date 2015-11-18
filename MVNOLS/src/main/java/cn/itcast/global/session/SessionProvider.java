package cn.itcast.global.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * 	This is the session provider interface
 */
public interface SessionProvider {

	/*	Set attribute into session scope	*/
	public void setAttribute(String key, Serializable value, HttpServletRequest request) throws Exception;
	
	/*	Get attribute from session scope	*/
	public Serializable getAttribute(String key, HttpServletRequest request) throws Exception;
	
	/*	Session invalidation	*/
	public void invalidate (HttpServletRequest request) throws Exception;
	
	/*	Get session ID	 */
	public String getSessionId (HttpServletRequest request) throws Exception;
}
