package cn.itcast.global.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 	This class is to customize a JSP tag for the token of duplicate submission avoidance
 * 	1. SimpleTagSupport has implemented SimpleTag
 * 	2. SessionProvider DI is not allowed in tag implementation since it is out of JSP range
 *     -- Here using sessionProvider will lead to a failure of parsing JSP view
 * @author Vince Xu Yuan
 */
public class TokenTag extends SimpleTagSupport {
	
	/**
	 * 	This method is to execute the tag|element
	 *  1. All setXxx() methods will be called by tomcat before doTag(), hence in doTag() can use the object transmitted from tomcat
	 */
	@Override
	public void doTag() throws JspException, IOException {
		
		/*	Get the HttpServletRequest object & HttpServletResponse object in the JSP view	 */
		HttpSession session = ((PageContext) this.getJspContext()).getSession();
		
		/*	Output the content of the tag	*/
		this.getJspContext().getOut().println("<input type=\"hidden\" name=\"token\"  value=\"" + session.getAttribute("token") + "\""+ "/>");
	}
}
