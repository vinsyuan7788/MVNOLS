package cn.itcast.user.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.global.session.SessionProvider;
import cn.itcast.user.bean.User;
import cn.itcast.user.service.UserService;

/**
 * 	This is an action class to process AJAX request
 * @author Vince Xu Yuan
 */
@Controller
@RequestMapping("/userAjax")
public class UserAjaxAction {
	
	/*	IOP: IOC & DI	*/
	@Resource
	private UserService userService;
	@Resource
	private SessionProvider sessionProvider;
	
	/**
	 * 	This is an action method for existence validation through AJAX in JSP view for registration
	 * 	1. If the username is not existing, then return "true" (i.e. no error message)
	 *  2. If the username is existing, then return "false" (i.e. pop out error message)
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/signUpValidation")
	public @ResponseBody String signUpValidation (String username) throws Exception {
		
		int existenceCount = userService.queryUserbyUsername(username);
		if (existenceCount == 0) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 	This is an action method for existence validation through AJAX in JSP view for login
	 * 	1. If there is an existing user according to the login information, return "true" (i.e. no error message)
	 *  2. If there is no or more than one existing user according to the login information, return "false" (i.e. pop out error message)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/signInValidation")
	public @ResponseBody String signInValidation (String username, String password) throws Exception {
		
		int existenceCount = userService.queryUserByLoginInfo(username, password);
		if (existenceCount == 1) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 	This is an action method for update validation through AJAX in JSP view for update
	 * 	1. If the user name remains unchanged, then return "true" (i.e. no error message)
	 *  2. If the user name is changed, see if there is an existing user with the new user name
	 *     -- If yes: return "false" (i.e. pop out error message)
	 *     -- If no: return "true" (i.e. no error message)
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateValidation")
	public @ResponseBody String updateValidation (String username, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User currentUser = (User) sessionProvider.getAttribute("user", request, response);
		
		if (username.equals(currentUser.getUsername())) {
			return "true";
		} else {
			int existenceCount = userService.queryUserbyUsername(username);
			if (existenceCount == 0) {
				return "true";
			} else {
				return "false";
			}
		}
	}
}
