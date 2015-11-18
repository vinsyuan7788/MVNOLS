package cn.itcast.global.captcha.action;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/captchaAjax")
public class CaptchaAjaxAction {

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
	public @ResponseBody String captchaValidation (String captcha, HttpSession httpSession) throws Exception {
		
		String existingCaptcha = (String) httpSession.getAttribute("captcha");
		if (captcha.equalsIgnoreCase(existingCaptcha)) {
			return "true";
		} else {
			return "false";
		}
	}
}
