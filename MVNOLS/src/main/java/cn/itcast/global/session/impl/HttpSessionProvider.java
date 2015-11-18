package cn.itcast.global.session.impl;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.itcast.global.session.SessionProvider;

/**
 * 	This is an implementation class of SessionProvider interface
 */
public class HttpSessionProvider implements SessionProvider {

	/**
	 * 	Set attribute into current session scope
	 */
	@Override
	public void setAttribute(String key, Serializable value,
			HttpServletRequest request) throws Exception {
		
		/*	Get a session, if find no session according to JSESSIONID, create a new one	*/
		HttpSession session = request.getSession();
		
		/*	Set the attribute into session scope	*/
		session.setAttribute(key, value);
	}

	/**
	 * 	Get attribute from original session scope
	 */
	@Override
	public Serializable getAttribute(String key, HttpServletRequest request) throws Exception {
		
		/*	Get the original session  */
		HttpSession session = request.getSession(false);
		
		/*	If get the original session, return the attribute value	 */
		if (session != null) {
			return (Serializable) session.getAttribute(key);
		}
		
		/*	Otherwise return null	*/
		return null;
	}

	/**
	 * 	Invalidate session
	 */
	@Override
	public void invalidate(HttpServletRequest request) throws Exception {
		
		/*	Get the original session  */
		HttpSession session = request.getSession(false);
		
		/*	If get the original session, invalidate it	 */
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * 	Get the JSESSIONID of current session. 
	 * 	If original session is not found, create a new session & corresponding JSESSIONID
	 */
	@Override
	public String getSessionId(HttpServletRequest request) throws Exception {
		
		return request.getSession().getId();
	}

}
