package cn.itcast.user.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.user.bean.User;
import cn.itcast.user.service.UserService;

/**
 * 	This is an action class to process AJAX request
 */
@Controller
@RequestMapping("/userAjax")
public class UserAjaxAction {
	
	/*	IOP: IOC & DI	*/
	@Resource
	private UserService userService;
	
	/**
	 * 	This is an action method for duplication validation through AJAX in JSP view
	 * 	1. If there is no username duplication, then return "true" (i.e. no error message)
	 *  2. If there is a username duplication, then return "false" (i.e. pop out error message)
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/duplicationValidation")
	public @ResponseBody String duplicationValidation (String username) throws Exception {
		
		int count = userService.queryUserbyUsername(username);
		if (count == 0) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 	This is an action method for existence validation through AJAX in JSP view
	 * 	1. If there is an existing user according to the login information, return "true" (i.e. no error message)
	 *  2. If there is no or more than one existing user according to the login information, return "false" (i.e. pop out error message)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/existenceValidation")
	public @ResponseBody String existenceValidation (String username, String password) throws Exception {
		
		int count = userService.queryUserByLoginInfo(username, password);
		if (count == 1) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 	This is an action method for update validation through AJAX in JSP view
	 * 	1. If the user name remains unchanged, then return "true" (i.e. no error message)
	 *  2. If the user name is changed, see if there is an existing user with the new user name
	 *     -- If yes: return "false" (i.e. pop out error message)
	 *     -- If no: return "true" (i.e. no error message)
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateValidation")
	public @ResponseBody String updateValidation (String username, HttpSession httpSession) throws Exception {
		
		User currentUser = (User) httpSession.getAttribute("user");
		String currentUsername = currentUser.getUsername();
		
		if (username.equals(currentUsername)) {
			return "true";
		} else {
			int count = userService.queryUserbyUsername(username);
			if (count != 0) {
				return "false";
			} else {
				return "true";
			}
		}
	}
}
