package cn.itcast.test;

import java.util.List;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.dsna.util.images.ValidateCode;
import cn.itcast.user.action.utils.PageUserBeanUtils;
import cn.itcast.user.bean.wrapper.PageUserBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext.xml")
public class TestBeans {
	
	/**
	 * 	Test captcha
	 */
	@Resource
	private ValidateCode captcha;
	@Test
	public void testCaptcha () throws Exception {
		System.out.println(captcha.getCode());
	}
	
	/**
	 * 	Test pagination
	 */
	@Resource
	private PageUserBeanUtils pageUserBeanUtils;
	@Test 
	public void testPaginationDisplay () throws Exception {
		
		PageUserBean pageUserBean = new PageUserBean();
		pageUserBean.setCurrentPageCode(4);
		pageUserBeanUtils.ProcessFieldsForDisplay(pageUserBean);
		pageUserBean.setTotalRecords(28);
		
		System.out.println("totalPages: " + pageUserBean.getTotalPages());
		System.out.println("currentPageCode:" + pageUserBean.getCurrentPageCode());
		List<Integer> displayPageCodes = pageUserBean.getDisplayPageCodes();
		System.out.println("displayPageCodes size: " + displayPageCodes.size());
		System.out.println("The display pageCodes:");
		for (Integer displayPageCode : displayPageCodes) {
			System.out.print(displayPageCode + " ");
		}
		System.out.println();
	}
	
}
