package cn.itcast.temporary.action.user;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.itcast.global.pagination.bean.PageBean;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.user.bean.User;
import cn.itcast.user.bean.Userupload;
import cn.itcast.user.bean.wrapper.PageUserBean;
import cn.itcast.user.service.UserService;

/**
 * 	This is an action that stores some alternative methods for specific goals
 */
@Controller
@RequestMapping("/userBackup")
public class BackupAction {
	
	@Resource
	private UserService userService;
	
	/**
	 * 	This is an action method for paging query of ALL users
	 * 	1. Using DB paging query + cache for pagination
	 *  2. Use the PageBean for paging query
	 * @param currentPageCode
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryUserList")
	public String queryUserList (Integer currentPageCode, HttpSession httpSession, String username, String gender) throws Exception {
		
		/*	Instantiate a PageBean object	*/
		PageBean<User> pageBean = new PageUserBean();
		
		/*	Set current page code	*/
		if (currentPageCode == null) {
			pageBean.setCurrentPageCode(1);
		} else {
			pageBean.setCurrentPageCode(currentPageCode);
		}
		
		/*	Set the page size: 8	*/
		pageBean.setPageSize(8);
		
		/*	Set the number of total records	*/
		int total = userService.queryTotalUsers();
		pageBean.setTotalRecords(total);
		
		/*	Set the List<JavaBean>	*/
		List<User> users = userService.queryUserList(pageBean);
		pageBean.setBeanListForCurrentPage(users);
		
		/*	Save the pageBean into session scope & return  */
		httpSession.setAttribute("pageBean", pageBean);
		return "redirect:/redirection/userList.action";
	}

	/**
	 * 	This is an action method for server-part user registration
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userRegister")
	public String userRegister (User user, String captcha, HttpSession httpSession, Model model) throws Exception {
		
		/*	Create a map to store check failure information	*/
		Map<String, String> errorMessages = new HashMap<String, String>();
		
		/*	Here is the user infomration check	*/
		String existingCaptcha = (String) httpSession.getAttribute("captcha");
		if (!captcha.equalsIgnoreCase(existingCaptcha)) {
			errorMessages.put("captcha", "The captcha is incorrect.");
		}
		// Format check here....
		// ...
		
		/*	Here is the user registration information check	*/
		errorMessages = this.userDuplicateCheck(errorMessages, user);
		
		/*	If there is any check failure messages, return to original page	 */
		if (!errorMessages.isEmpty()) {	 
			model.addAttribute("errorMessages", errorMessages);
			return "forward:/register.jsp";	
//			httpSession.setAttribute("errorMessages", errorMessages);
//			return "redirect:/test_register.jsp";				// redirection will remain the effect of JS in JSP view, while forward makes the JS lose effect
		}
		
		/*	Assign user state, role, activation uuid	*/
		user.setState("unactive");
		user.setRoleid(1);
		user.setActivationuuid(UUID.randomUUID().toString());
		
		/*	Insert the user information into DB & save success message & return  */
		userService.insertUser(user);
		
		/*	Send the email	*/
		this.sendEmail(user);
		
		/*	Save the success message & return	*/ 
		model.addAttribute("successMessage", "Congratulations! Registration succeeds! Please check your email for activation!");
		return "forward:/redirection/success.action";
	}

	/**
	 * 	This is an action method for user login
	 */
	@RequestMapping("/userLogin")
	public String userLogin (User user, String captcha, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		/*	Create a map to store check failure information	*/
		Map<String, String> errorMessages = new HashMap<String, String>();
		
		/*	Here is the captcha check	*/
		String existingCaptcha = (String) request.getSession().getAttribute("captcha");
		if (!captcha.equalsIgnoreCase(existingCaptcha)) {
			errorMessages.put("captcha", "The captcha is incorrect.");
		}
		
		/*	
		 * 	Here is the user login information check	
		 *	1. If there is no existing user according to user login information, return Map<String, String> errorMessages
		 *	2. If there is an existing user according to user login information, return User existingUser
		 */
		Object object = this.userExistenceCheck(errorMessages, user);
		if (object instanceof Map) {
			errorMessages = (Map<String, String>) object;
		}
		User existingUser = new User();
		if (object instanceof User) {
			existingUser = (User) object;
		}
		
		/*	If there is any error message, save the message & return	*/
		if (!errorMessages.isEmpty()) {
			model.addAttribute("errorMessages", errorMessages);
			return "forward:/login.jsp";
		}
		
		/*	If there are no error messages. Save the user information into session scope & redirect	*/
		request.getSession().setAttribute("user", existingUser);
		return "redirect:/home.jsp";
	}
	
	/**
	 * 	This is an action method to modify user information
	 */
	@RequestMapping("/updateUser")
	public String updateUser (User user, MultipartFile uploadFile, HttpSession httpSession, Model model) throws Exception {
		
		/*	Create a map to store check failure information	*/
		Map<String, String> errorMessages = new HashMap<String, String>();
		
		/*	Here is the user information modification check	*/
		errorMessages = this.userUpdateCheck(errorMessages, user, httpSession);
		
		/*	Create a new Userupload object	*/
		Userupload userupload = new Userupload();
		
		/*	
		 * 	Process upload file:
		 * 	1. If there is a upload file whose name is not null or no upload file: return the new uploadfile
		 *     If there is no upload file: return the original uploadfile
		 *  2. If there is a upload file whose name however is null: return the Map<String, String> errorMessages that contains the error message 
		 */
		Object object = this.processUploadFile(errorMessages, user, userupload, uploadFile);
		if (object instanceof Userupload) {
			userupload = (Userupload) object;
		}
		if (object instanceof Map) {
			errorMessages = (Map<String, String>) object;
		}
		
		/*	If there is any check failure messages, return to original page	 */
		if (!errorMessages.isEmpty()) {
			model.addAttribute("errorMessages", errorMessages);
			return "user/userAccount";
		}
		
		/*	Update & save success information & return	*/
		userService.updateUser(user, userupload);
		model.addAttribute("successMessage", "User information has been updated!");
		return "user/userAccount";
	}
	
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * 	This can be an internal method for duplicate check for userRegister(): format check is in JSP view 
	 *  1. Check if there is an existing user according to the user name
	 *     -- If yes: return a new errorMessages that contains an error message
	 *     -- If no: return the original errorMessages
	 *  2. This method is not open to any request URL since this method serves for userLogin()
	 */
	private Map<String, String> userDuplicateCheck (Map<String, String> errorMessages, User user) throws Exception {
		
		/*	Query if there is an existing user according to username	*/
		int count = userService.queryUserbyUsername(user);
		
		/*	If yes, then current username is not allowed	*/
		if (count > 0) {
			errorMessages.put("username", "Current user exists! Please user another name");
		}
		
		/*	Return the errorMessages  */
		return errorMessages;
	}
	
	/**
	 * 	This is an internal method to send email for userRegister():
	 * @param user
	 * @throws Exception
	 */
	private void sendEmail (User user) throws Exception {
		
		/*	Load the property file	*/
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		
		/*	Get the initiation parameters	*/
		String host = props.getProperty("host");
		String uname = props.getProperty("uname");
		String pwd = props.getProperty("pwd");
		String sender = props.getProperty("sender");
		String receiver = user.getEmail();
		String subject = props.getProperty("subject");
		String content = props.getProperty("content");
		content = MessageFormat.format(content, user.getActivationuuid());	// use format() to replace the placeholder {0}
		
		/*	Send email	*/
		try {
			Session session = MailUtils.createSessionUsingSSL(host, "465", uname, pwd);
			Mail mail = new Mail(sender, receiver, subject, content);
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
		/*	Can invoke a method to process re-sending email if sending email fails	*/
		}
	}
	
	/**
	 * 	This is an internal method for existence check for userLogin(): format check is in JSP view
	 * 	1. If there is no existing user, return Map<String, String> errorMessages
	 *  2. If there is an existing user, return User exsitingUser
	 *  3. In Whatever invokes this method (i.e. userLogin()): use if (object instanceof Xxx) {...} statement
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	private Object userExistenceCheck(Map<String, String> errorMessages, User user) throws Exception {
		
		/*	Query if there is any existing user according to user login information	*/
		List<User> existingUsers = userService.queryUserByLoginInfo(user);

		/*	If there is not 1 existing user, add an error message & return errorMessages	*/
		if (existingUsers.size() != 1) {
			errorMessages.put("user", "User information is incorrect");
			return errorMessages;
		}
		
		/*	If there is an existing user, return existing user	 */
		User existingUser = existingUsers.get(0);
		return existingUser;
	}
	
	/**
	 * 	This is a method for update check for userUpdate(): format check is in JSP view
	 * 	1. If the user name remains the same: return the original errorMessages
	 *  2. If the user name is changed, check if there is an user using the new name
	 *     -- If yes: return the new errorMessages containing an error message
	 *     -- If no: return the original errorMessages
	 * @param errorMessages
	 * @param user
	 * @return
	 */
	private Map<String, String> userUpdateCheck(Map<String, String> errorMessages, User user, HttpSession httpSession) throws Exception {
		
		/*	Obtain current user name	*/
		User currentUser = (User) httpSession.getAttribute("user");
		String currentUsername = currentUser.getUsername();
		
		/*	If new user name and current user name are the same, then return the original errorMessages	*/
		if (user.getUsername().equals(currentUsername)) {
			return errorMessages;
			
		/*	If new user name and current user name are different, see if there is an existing user with the new username	*/
		} else {
			int count = userService.queryUserbyUsername(user);
			/*	If yes, add an error message & return errorMessages 	*/
			if (count > 0) {
				errorMessages.put("username", "current User exists! Please use another usename");
				return errorMessages;
				
			/*	If no, return the original errorMessages	*/
			} else {
				return errorMessages;
			}
		} 
	}
	
	/**
	 * 	This is an internal method to process upload file for updateUser()
	 * 	1. Since this method either return a Userupload instance or Map<String, String> instance
	 *     -- User Object to return
	 *     -- In Whatever invokes this method (i.e. updateUser()): use if (object instanceof Xxx) {...} statement
	 * @param user
	 * @param uploadFile
	 */
	private Object processUploadFile(Map<String, String> errorMessages, User user, Userupload userupload, MultipartFile uploadFile) throws Exception {
		
		/*	Get the file name & real path	*/
		String fileName = uploadFile.getOriginalFilename();
		String realPath = "D:\\JavaWeb\\upload_files";
		
		/*	If upload file size > 0, some file is uploaded */	
		if (uploadFile.getSize() > 0) {
			
			/*	If the name of upload file contains at least 1 character: continue  */
			if (fileName.trim().length() > 0) {
				int index = fileName.lastIndexOf("\\");							// "\\" equals "/
				if (index != -1) {
					fileName = UUID.randomUUID() + "_" + fileName.substring(index+1);
				} else {
					fileName = UUID.randomUUID() + "_" + fileName;
				}
				
				/*	Specify the path of the upload file	*/
				int hashCode = fileName.hashCode();
				String hex = Integer.toHexString(hashCode);
				File folderDir = new File(realPath + "/" + hex.charAt(0) + "/" + hex.charAt(1));
				folderDir.mkdirs();		// create the directory according to dirFile if not exists
				
				/*	Get the upload directory & upload the file to it	 */
				File fileDir = new File(folderDir, fileName);
				uploadFile.transferTo(fileDir);
				
				/*	Set the information of Userupload object & reutrn the new userupload	*/
				userupload.setUserId(user.getId());
				userupload.setUploadfile(fileDir.toString());
				return userupload;
			
			/*	If the name of upload file contains no character: save error message	*/
			} else {
				errorMessages.put("uploadFailure", "Upload file name must contain 1 character at least");
				return errorMessages;
			}
		}
		
		/*	If no file is uploaded, then return the original userupload	 */
		return userupload;
	}
}
