package cn.itcast.user.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.user.action.utils.PageUserBeanUtils;
import cn.itcast.user.bean.User;
import cn.itcast.user.bean.wrapper.PageUserBean;
import cn.itcast.user.service.UserService;

@Controller
@RequestMapping("/userPagination")
public class UserPaginationAction {
	
	/*	IOP: IOC & DI	*/
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
	public String queryUserListByAdminCriteria (PageUserBean pageUserBean, HttpSession httpSession) throws Exception {
		
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
			httpSession.setAttribute("buttonFlag", "enabled");
		} else if (pageUserBean.getState() != null && pageUserBean.getState().equalsIgnoreCase("disabled")) {
			httpSession.setAttribute("buttonFlag", "disabled");
		} else {
			httpSession.removeAttribute("buttonFlag");
		}
		
		/*	Save the pageBean into session scope & return  */
		httpSession.setAttribute("pageUserBean", pageUserBean);
		return "redirect:/redirection/userAdminList.action";
	}
}
