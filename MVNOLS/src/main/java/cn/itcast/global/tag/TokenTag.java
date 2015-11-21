package cn.itcast.global.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 	This class is to customize a JSP tag for the token of duplicate submission avoidance
 * 	1. SimpleTagSupport has implemented SimpleTag
 */
public class TokenTag extends SimpleTagSupport {

	/**
	 * 	This method is to execute the tag|element
	 *  1. All setXxx() methods will be called by tomcat before doTag(), hence in doTag() can use the object transmitted from tomcat
	 */
	@Override
	public void doTag() throws JspException, IOException {
		
		/*	Get the session in the JSP view	 */
		HttpSession httpSession = ((PageContext) this.getJspContext()).getSession();
		
		/*	Output the content of the tag	*/
		this.getJspContext().getOut().println("<input type=\"hidden\" name=\"token\"  value=\"" + httpSession.getAttribute("token") + "\""+ "/>");
		
	}
}
