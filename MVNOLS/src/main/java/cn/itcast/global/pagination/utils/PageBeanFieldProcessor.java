package cn.itcast.global.pagination.utils;

import cn.itcast.global.pagination.bean.PageBean;

/**
 * 	This is a page bean utility interface to process page bean fields in action layer
 * @author Vince Xu Yuan
 * @param <T>
 */
public interface PageBeanFieldProcessor<T extends PageBean> {
	
	/*	This method is used to process the fields for service pagination query & display	*/
	public void ProcessFieldsForQuery (T pageBean);
	public void ProcessFieldsForDisplay (T pageBean);
	
	/*	This method is used to process the fields for administration pagination query & display	*/
	public void ProcessFieldsForAdminQuery (T pageBean);	
	public void ProcessFieldsForAdminDisplay (T pageBean);
}
