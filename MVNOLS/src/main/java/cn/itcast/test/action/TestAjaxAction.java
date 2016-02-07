package cn.itcast.test.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.test.bean.City;
import cn.itcast.test.bean.Country;
import cn.itcast.test.bean.State;
import cn.itcast.test.service.TestService;
import cn.itcast.user.service.UserService;

/**
 * 	This is a action class to process AJAX request for action testing module
 * @author Vince Xu Yuan 
 */
@Controller
@RequestMapping("/testAjax")
public class TestAjaxAction {

	/*	IOP: IOC & DI	*/
	@Resource
	private UserService userService;
	@Resource
	private TestService testService;
	
	/**
	 * 	This is an action method to query the total of users 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryTotalUsers")
	public @ResponseBody int queryTotalUsers () throws Exception {
		
		int userTotal = userService.queryTotalUsers();
		return userTotal;
	}
	
	/**
	 * 	This is an action method to query all countries
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryCountries")
	public @ResponseBody List<Country> queryCountries () throws Exception {
		
		List<Country> countries = testService.queryAllCountries();
		return countries;
	}

	/**
	 * 	This is an action method to query all states according to country id
	 * @param countryId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryStates")
	public @ResponseBody List<State> queryStates (@RequestParam Integer countryId) throws Exception {
		
		List<State> states = testService.queryAllStates(countryId);
		return states;
	}
	
	/**
	 * 	This is an action method to query all cities according to state id
	 * @param stateId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryCities")
	public @ResponseBody List<City> queryCities (@RequestParam Integer stateId) throws Exception {
		
		List<City> cities = testService.queryAllCities(stateId);
		return cities;
	}
}
