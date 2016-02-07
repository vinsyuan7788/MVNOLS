package cn.itcast.item.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.global.session.SessionProvider;
import cn.itcast.item.action.utils.PageItemBeanUtils;
import cn.itcast.item.bean.Item;
import cn.itcast.item.bean.wrapper.PageItemBean;
import cn.itcast.item.service.ItemService;

/**
 *	This is an action class to process request with pagination:
 * @author Vince Xu Yuan
 */
@Controller
@RequestMapping("/itemPagination")
public class ItemPaginationAction {

	/*	IOP: IOC & DI	*/
	@Resource
	SessionProvider sessionProvider;
	@Resource
	private ItemService itemService;
	@Resource
	private PageItemBeanUtils pageItemBeanUtils;
	
	/**
	 * 	This is an action for paging comprehensive query of items
	 * 	1. Using DB paging query + cache for pagination
	 *  2. Use the PageItemBean (which extends PageBean) for paging query
	 *  3. Can query ALL items by specifying the criteria in view (e.g. JSP view)
	 * @param currentPageCode
	 * @param pageItemBean
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryItemListByCriteria")
	public String queryItemListByCriteria (PageItemBean pageItemBean, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/*	Process the criteria & set the currentPageCode	*/
		pageItemBeanUtils.ProcessFieldsForQuery(pageItemBean);
		
		/* 	Set the PageSize, MaximumDisplayPages, OffSetPosition	*/
		pageItemBeanUtils.ProcessFieldsForDisplay(pageItemBean);
		
		/*	Set the number of total records	 */
		int total = itemService.queryTotalItemsByCriteria(pageItemBean);
		pageItemBean.setTotalRecords(total);
		
		/*	Set the List<Item>	*/
		List<Item> items = itemService.queryItemListByCriteria(pageItemBean);
		pageItemBean.setBeanListForCurrentPage(items);
		
		/*	Save the pageBean into session scope & return  */
		sessionProvider.setAttribute("pageItemBean", pageItemBean, request, response);
		return "item/itemList";
	}
	
	@RequestMapping("/queryItemListByAdminCriteria")
	public String queryItemListByAdminCriteria (PageItemBean pageItemBean, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/*	Process the criteria & set the currentPageCode	*/
		pageItemBeanUtils.ProcessFieldsForAdminQuery(pageItemBean);
		
		/* 	Set the PageSize, MaximumDisplayPages, OffSetPosition	*/
		pageItemBeanUtils.ProcessFieldsForAdminDisplay(pageItemBean);
		
		/*	Set the number of total records	 */
		int total = itemService.queryTotalItemsByCriteria(pageItemBean);
		pageItemBean.setTotalRecords(total);
		
		/*	Set the List<Item>	*/
		List<Item> items = itemService.queryItemListByCriteria(pageItemBean);
		pageItemBean.setBeanListForCurrentPage(items);
		
		/*	Set a flag to specify which button is displayed in JSP view	*/
		if (pageItemBean.getItemState() != null && pageItemBean.getItemState().equalsIgnoreCase("active")) {
			sessionProvider.setAttribute("buttonFlag", "putOnSale", request, response);
		} else if (pageItemBean.getItemState() != null && pageItemBean.getItemState().equalsIgnoreCase("unactive")) {
			sessionProvider.setAttribute("buttonFlag", "putOffSale", request, response);
		} else {
			sessionProvider.removeAttribute("buttonFlag", request, response);
		}
		
		/*	Save the pageBean into session scope & return  */
		sessionProvider.setAttribute("pageItemBean", pageItemBean, request, response);
		return "redirect:/redirection/itemAdminList.action";
	}
}
