package cn.itcast.user.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.global.session.SessionProvider;
import cn.itcast.user.action.utils.PageUserBeanUtils;
import cn.itcast.user.bean.User;
import cn.itcast.user.bean.wrapper.PageUserBean;
import cn.itcast.user.service.UserService;

/**
 *	This is an action class to process request with pagination:
 * @author Vince Xu Yuan
 */
@Controller
@RequestMapping("/userPagination")
public class UserPaginationAction {
	
	/*	IOP: IOC & DI	*/
	@Resource
	private SessionProvider sessionProvider;
	@Resource
	private UserService userService;
	@Resource
	private PageUserBeanUtils pageUserBeanUtils;
	
	/**
	 * 	This is an action for paging comprehensive query of users
	 * 	1. Using DB paging query + cache for pagination
	 *  2. Use the PageUserBean (which extends PageBean) for paging query
	 *  3. Can query ALL users by specifying the criteria in view (e.g. JSP view)
	 * @param currentPageCode
	 * @param pageUserBean
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryUserListByAdminCriteria")
	public String queryUserListByAdminCriteria (PageUserBean pageUserBean, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*	Process the criteria & set the currentPageCode	*/
		pageUserBeanUtils.ProcessFieldsForAdminQuery(pageUserBean);
		
		/* 	Set the PageSize, MaximumDisplayPages, OffSetPosition	*/
		pageUserBeanUtils.ProcessFieldsForAdminDisplay(pageUserBean);
		
		/*	Set the number of total records	 */
		int total = userService.queryTotalUsersByCriteria(pageUserBean);
		pageUserBean.setTotalRecords(total);
		
		/*	Set the List<User>	*/
		List<User> users = userService.queryUserListByCriteria(pageUserBean);
		pageUserBean.setBeanListForCurrentPage(users);
		
		/*	Set a flag to specify which button is displayed in JSP view	*/
		if (pageUserBean.getState() != null && (pageUserBean.getState().equalsIgnoreCase("active") || pageUserBean.getState().equalsIgnoreCase("unactive"))) {
			sessionProvider.setAttribute("buttonFlag", "enabled", request, response);
		} else if (pageUserBean.getState() != null && pageUserBean.getState().equalsIgnoreCase("disabled")) {
			sessionProvider.setAttribute("buttonFlag", "disabled", request, response);
		} else {
			sessionProvider.removeAttribute("buttonFlag", request, response);
		}
		
		/*	Save the pageBean into session scope & return  */
		sessionProvider.setAttribute("pageUserBean", pageUserBean, request, response);
		return "redirect:/redirection/userAdminList.action";
	}
}
