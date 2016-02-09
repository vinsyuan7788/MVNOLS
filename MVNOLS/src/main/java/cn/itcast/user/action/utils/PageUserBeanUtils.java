package cn.itcast.user.action.utils;

import cn.itcast.global.configuration.Constants;
import cn.itcast.global.pagination.utils.PageBeanFieldProcessor;
import cn.itcast.global.pagination.utils.PageBeanFieldSetter;
import cn.itcast.user.bean.wrapper.PageUserBean;

/**
 * 	This is a utility class to set parameters for pagination
 * @author Vince Xu Yuan
 */
public class PageUserBeanUtils extends PageBeanFieldSetter<PageUserBean> implements PageBeanFieldProcessor<PageUserBean>{

	@Override
	public void ProcessFieldsForQuery(PageUserBean pageBean) throws Exception {}

	@Override
	public void ProcessFieldsForDisplay(PageUserBean pageBean) throws Exception {}
	
	/**
	 * 	This is a static method to process the criteria & set the current page code for pagination query
	 * @param pageUserBean
	 */
	@Override
	public void ProcessFieldsForAdminQuery (PageUserBean pageUserBean) throws Exception {
		
		/*
		 * 	Process the criteria
		 *  1. For the 1st time accessing the "userListByCriteria.jsp" view: hence need "== null" predication
		 *  2. the value "null" obtained from <input> is string "null" , not key word "null": hence need "equalsIgnoreCase("null")" predication
		 *     -- Doing <if test = "xxx != 'null'" also resolves the problem (which however may be format-messy, some "!= null" and some "!= 'null'")
		 *  3. When key word "null" is obtained by JSP through EL, it becomes string "null": hence similarly as above, need "equalsIgnoreCase("null")" predication once the value is obtained by action method again
		 *  4. Once receive the value from the <input>|<select> tags in JSP view: need "equals(IgnoreCase)("xxx")" predication
		 *  
		 *  Hence in most of situations: "== null || == 0" predication is necessary
		 */
		if (pageUserBean.getUsername() == null || pageUserBean.getUsername().equalsIgnoreCase("null") || pageUserBean.getUsername().trim().length() == 0) {
			pageUserBean.setUsername(null);
		}
		if (pageUserBean.getGender() == null || pageUserBean.getGender().equalsIgnoreCase("null") || pageUserBean.getGender().trim().length() == 0 || pageUserBean.getGender().equalsIgnoreCase("all")) {
			pageUserBean.setGender(null);
		}
		if (pageUserBean.getState() == null || pageUserBean.getState().equalsIgnoreCase("null") || pageUserBean.getState().trim().length() == 0 || pageUserBean.getState().equalsIgnoreCase("all")) {
			pageUserBean.setState(null);
		}
		
		/*	Set current page code	*/
		super.setParametersForQuery(pageUserBean);
	}
	
	/**
	 * 	This is a static method to set necessary parameters for pagination
	 * @param pageUserBean
	 */
	@Override
	public void ProcessFieldsForAdminDisplay (PageUserBean pageUserBean) throws Exception {
		
		/* 	Set the page size, maximum display pages, offset position	*/
		super.setParametersForDisplay(pageUserBean, Constants.USER_PAGE_SIZE, Constants.USER_MAXIMUM_DISPLAY_PAGES, Constants.USER_OFFSET_POSITION);
	}
}
