package cn.itcast.global.captcha.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dsna.util.images.ValidateCode;
import cn.itcast.global.configuration.Constants;

/**
 * 	This is a JavaWeb servlet to obtain the captcha
 *  1. This servlet needs to be configured in "web.xml"
 *  2. From "web.xml": This servlet process the request ending with ".servlet", not intersected with SpringMVC
 *     -- Hence this URL is naturally public, not intercepted by SpringMVC interceptors
 * 	3. Since JSP is servlet as well, this program can be written in JSP (e.g "xxx.jsp")
 *     -- In JSP view: <img src="<c:url value = '/.../.../xxx.jsp'/>"
 */
public class CaptchaServlet extends HttpServlet {
	
	/**
	 * 	This is a Java web servlet method to obtain the captcha
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		/*	Create a captcha   */
		ValidateCode captcha = new ValidateCode(Constants.CAPTCHA_WIDTH, Constants.CAPTCHA_HEIGHT, Constants.CAPTCHA_CODE_COUNT, Constants.CAPTCHA_LINE_COUNT);
		BufferedImage image = captcha.getBuffImg();
		
		/* Save the captcha code & Output the captcha image as stream */
		request.getSession().setAttribute("captcha", captcha.getCode());
		ImageIO.write(image, "jpeg", response.getOutputStream());
	}
}
