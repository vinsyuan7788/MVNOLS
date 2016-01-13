package cn.itcast.global.pagination.utils;

import cn.itcast.global.pagination.bean.PageBean;

/**
 * 	This is a page bean utility class that can be extended to process page bean fields in action layer
 * @param <T>
 */
public class PageBeanFieldSetter<T extends PageBean> {
	
	/**
	 * 	This method is to set the parameters for pagination query
	 * @param pageBean
	 * @throws Exception
	 */
	public final void setParametersForQuery (T pageBean) throws Exception {
		
		/*	Process the current page code	*/
		if (pageBean.getCurrentPageCode() == null) {
			pageBean.setCurrentPageCode(1);
		} 
	}
	
	/**
	 * 	This method is to set the parameters for pagination display
	 * @param pageBean
	 * @param pageSize
	 * @param maximumDisplayPages
	 * @param offsetPosition
	 * @throws Exception
	 */
	public final void setParametersForDisplay (T pageBean, int pageSize, int maximumDisplayPages, int offsetPosition) throws Exception {
		
		/*	Set the page size, maximum display pages, offset position	*/
		pageBean.setPageSize(pageSize);
		pageBean.setMaximumDisplayPages(maximumDisplayPages);
		pageBean.setOffSetPosition(offsetPosition);
	}
}
