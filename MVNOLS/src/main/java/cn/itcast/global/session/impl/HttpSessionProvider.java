package cn.itcast.global.session.impl;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.global.session.SessionProvider;

/**
 * 	This is an implementation class of SessionProvider interface
 * @author Vince Xu Yuan
 */
public class HttpSessionProvider implements SessionProvider {

	/**
	 * 	Set attribute into current session scope
	 */
	@Override
	public void setAttribute(String attributeName, Serializable value,
			HttpServletRequest request, HttpServletResponse response) {
		
		/*	Get a session, if find no session according to JSESSIONID, create a new one	*/
		HttpSession session = request.getSession();
		
		/*	Set the attribute into session scope	*/
		session.setAttribute(attributeName, value);
	}

	/**
	 * 	Get attribute from original session scope
	 */
	@Override
	public Serializable getAttribute(String attributeName, HttpServletRequest request, HttpServletResponse response) {
		
		/*	Get the original session  */
		HttpSession session = request.getSession(false);
		
		/*	If get the original session, return the attribute value	 */
		if (session != null) {
			return (Serializable) session.getAttribute(attributeName);
		}
		
		/*	Otherwise return null	*/
		return null;
	}

	/**
	 * 	Invalidate session
	 */
	@Override
	public void invalidate(HttpServletRequest request, HttpServletResponse response) {
		
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
	public String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		
		return request.getSession().getId();
	}

	/**
	 * 	Remove attribute from session scope
	 */
	@Override
	public void removeAttribute(String attributeName,
			HttpServletRequest request, HttpServletResponse response) {
		
		/*	Get the original session  */
		HttpSession session = request.getSession(false);
		
		/*	If get the original session, return the attribute value	 */
		if (session != null) {
			session.removeAttribute(attributeName);
		}
	}

}
