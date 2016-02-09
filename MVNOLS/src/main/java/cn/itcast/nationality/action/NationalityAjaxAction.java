package cn.itcast.nationality.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.nationality.service.NationalityService;

/**
 * 	This is the class to process AJAX request
 * @author Vince Xu Yuan
 */
@Controller
@RequestMapping("/nationalityAjax")
public class NationalityAjaxAction {

	/*	IOP: IOC & DI	*/
	@Resource
	private NationalityService nationalityService;
	
	/**
	 * 	This is an action method to query ALL nationalities
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryNationality")
	public @ResponseBody List<String> queryNationality () throws Exception {
		List<String> nationalities = nationalityService.queryNationalities();
		return nationalities;
	}
}
