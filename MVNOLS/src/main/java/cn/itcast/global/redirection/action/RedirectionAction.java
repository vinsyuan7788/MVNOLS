package cn.itcast.global.redirection.action;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.global.session.impl.HttpSessionProvider;
import cn.itcast.item.action.utils.PageItemBeanUtils;
import cn.itcast.item.bean.wrapper.PageItemBean;

/**
 * 	This is a relocation action for page redirection
 * 	1. Test URL: refers to "UserAction.java"
 * 	2. For convenient modification of view redirection: 
 *     -- Collect the paths of redirection view in an action class, instead of being dispersed in each JSP view
 */
@Controller
@RequestMapping("/redirection")
public class RedirectionAction {
	
	/*	IOP: IOC & DI	*/
	@Resource
	private HttpSessionProvider sessionProvider;
	@Resource
	private PageItemBeanUtils pageItemBeanUtils;
	
	//---------	This is for global and test module ------------------------------------
	/**
	 * 	This is an action method to go to "testAction.jsp"
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/testModule")
	public String testAction () throws Exception {
		return "testModule";
	}
	
	/**
	 * 	This is an action method to go to "success.jsp"
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/success")
	public String success () throws Exception {
		return "success";
	}
	
	/**
	 * 	This is an action method to go to "login.jsp" with returnURL
	 *  e.g. for "http://localhost:8080/projectName/requestPath.action?XXX=xxx&YYY=yyy"
	 *  1. window.location.href = "http://localhost:8080/projectName/requestPath.action?XXX=xxx&YYY=yyy"
	 *  2. window.location.host = "localhost:8080"
	 *  3. window.location.pathname = "/projectName/requestPath.action"
	 * @param pageItemBean
	 * @param host
	 * @param pathname
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login (PageItemBean pageItemBean, String host, String pathname, HttpServletRequest request) throws Exception {
		
		/*	Process the criteria & set the currentPageCode	*/
		pageItemBeanUtils.ProcessFieldsForQuery(pageItemBean);
		
		/*	Get the returnURL and save it into session scope	*/	
		String returnURL = "http://" + host + pathname +
				"?currentPageCode=" + pageItemBean.getCurrentPageCode() + 
				"&itemName=" + pageItemBean.getItemName() + 
				"&itemPriceInterval=" + pageItemBean.getItemPriceInterval();
		sessionProvider.setAttribute("returnURL", returnURL, request);
		
		/*	Redirect to "login.jsp" view	*/
		return "redirect:/login.jsp";
	}
	//---------	This is for user module ------------------------------------
	/**
	 * 	This is an action method to go to "userAccount.jsp"
	 * 	1. Save a token into session scope
	 * 	2. Return to JSP view
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userAccount")
	public String userAccount (HttpServletRequest request) throws Exception {
		
		sessionProvider.setAttribute("token", UUID.randomUUID().toString(), request);
		return "user/userAccount";
	}
	
	/**
	 * 	This is an action method to go to "admin/user/userList.jsp"
	 * 	1. Save a token into session scope
	 * 	2. Return to JSP view
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userAdminList")
	public String userAdminList (HttpServletRequest request) throws Exception {
		
		sessionProvider.setAttribute("token", UUID.randomUUID().toString(), request);
		return "admin/user/userList";
	}
	
	/**
	 * 	This is an action method to go to "admin/user/userCriteria.jsp"
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userAdminCriteria")
	public String userAdminCriteria () throws Exception {
		return "admin/user/userCriteria";
	}
	
	//---------	This is for item module ------------------------------------
	/**
	 * 	This is an action method to go to "itemCriteria.jsp"
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/itemCriteria")
	public String itemCriteria () throws Exception {
		return "item/itemCriteria";
	}

	/**
	 * 	This is an action method to go to "admin/item/itemList.jsp"
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/itemAdminList")
	public String itemAdminList () throws Exception {
		return "admin/item/itemList";
	}
	
	/**
	 * 	This is an action method to go to "admin/item/itemCriteira.jsp"
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/itemAdminCriteria")
	public String itemAdminCriteria () throws Exception {
		return "admin/item/itemCriteria";
	}
	
	/**
	 * 	This is an action method to go to "admin/item/itemAdd.jsp"
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/itemAdminAdd")
	public String itemAdminAdd () throws Exception {
		return "admin/item/itemAdd";
	}
}
