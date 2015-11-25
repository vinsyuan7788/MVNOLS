package cn.itcast.global.captcha.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.global.session.SessionProvider;

@Controller
@RequestMapping("/captchaAjax")
public class CaptchaAjaxAction {

	/*	IOP: IOC & DI	*/
	@Resource
	private SessionProvider sessionProvider;
	
	/**
	 * 	This is an action method for captcha validation through AJAX in JSP view
	 * 	1. If the captcha is matched, then return "true" (i.e. no error message)
	 *  2. If the captcha is not matched, then return "false" (i.e. pop out error message)
	 * @param captcha
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/captchaValidation")
	public @ResponseBody String captchaValidation (String captcha, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String existingCaptcha = (String) sessionProvider.getAttribute("captcha", request, response);
		if (captcha.equalsIgnoreCase(existingCaptcha)) {
			return "true";
		} else {
			return "false";
		}
	}
}
