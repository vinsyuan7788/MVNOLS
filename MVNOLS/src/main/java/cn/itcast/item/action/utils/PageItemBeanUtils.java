package cn.itcast.item.action.utils;

import cn.itcast.global.configuration.BusinessConstants;
import cn.itcast.global.pagination.utils.PageBeanFieldProcessor;
import cn.itcast.global.pagination.utils.PageBeanFieldSetter;
import cn.itcast.item.bean.wrapper.PageItemBean;

/**
 * 	This is a utility class to set parameters for pagination
 */
public class PageItemBeanUtils extends PageBeanFieldSetter<PageItemBean> implements PageBeanFieldProcessor<PageItemBean> {	
	
	/**
	 * 	This is a static method to process the criteria & set the current page code for pagination query
	 * @param pageItemBean
	 */
	@Override
	public void ProcessFieldsForQuery (PageItemBean pageItemBean) throws Exception {
		
		/*	Process the criteria	*/
		if (pageItemBean.getItemName() == null || pageItemBean.getItemName().equalsIgnoreCase("null") || pageItemBean.getItemName().trim().length() == 0) {
			pageItemBean.setItemName(null);
		}
		if (pageItemBean.getItemPriceInterval() == null || pageItemBean.getItemPriceInterval().equalsIgnoreCase("null") || pageItemBean.getItemPriceInterval().trim().length() == 0 || pageItemBean.getItemPriceInterval().equalsIgnoreCase("all")) {
			pageItemBean.setItemPriceInterval(null);
		}
		if (pageItemBean.getItemState() == null || pageItemBean.getItemState().equalsIgnoreCase("null") || pageItemBean.getItemState().trim().length() == 0) {
			pageItemBean.setItemState("active");
		}

		/*	Process the current page code	*/
		super.setParametersForQuery(pageItemBean);
	}
	
	/**
	 * 	This is a static method to set necessary parameters for pagination display
	 * @param pageUserBean
	 */
	@Override
	public void ProcessFieldsForDisplay (PageItemBean pageItemBean) throws Exception {
		
		/* 	Set the page size, maximum display pages, offset position	*/
		super.setParametersForDisplay(pageItemBean, BusinessConstants.ITEM_PAGE_SIZE, BusinessConstants.ITEM_MAXIMUM_DISPLAY_PAGES, BusinessConstants.ITEM_OFFSET_POSITION);
	}

	/**
	 * 	This is a static method to process the criteria & set the current page code for pagination query
	 * @param pageItemBean
	 */
	@Override
	public void ProcessFieldsForAdminQuery(PageItemBean pageItemBean) throws Exception {
		
		/*	Process the criteria	*/
		if (pageItemBean.getItemName() == null || pageItemBean.getItemName().equalsIgnoreCase("null") || pageItemBean.getItemName().trim().length() == 0) {
			pageItemBean.setItemName(null);
		}
		if (pageItemBean.getItemPriceInterval() == null || pageItemBean.getItemPriceInterval().equalsIgnoreCase("null") || pageItemBean.getItemPriceInterval().trim().length() == 0 || pageItemBean.getItemPriceInterval().equalsIgnoreCase("all")) {
			pageItemBean.setItemPriceInterval(null);
		}
		if (pageItemBean.getItemState() == null || pageItemBean.getItemState().equalsIgnoreCase("null") || pageItemBean.getItemState().trim().length() == 0 || pageItemBean.getItemState().equalsIgnoreCase("all")) {
			pageItemBean.setItemState(null);
		}
		
		/*	Process the current page code	*/
		super.setParametersForQuery(pageItemBean);
	}

	/**
	 * 	This is a static method to set necessary parameters for pagination display
	 * @param pageUserBean
	 */
	@Override
	public void ProcessFieldsForAdminDisplay(PageItemBean pageItemBean) throws Exception {
		
		/* 	Set the page size, maximum display pages, offset position	*/
		super.setParametersForDisplay(pageItemBean, BusinessConstants.ADMIN_ITEM_PAGE_SIZE, BusinessConstants.ADMIN_ITEM_MAXIMUM_DISPLAY_PAGES, BusinessConstants.ADMIN_ITEM_OFFSET_POSITION);
	}
}
