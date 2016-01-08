package cn.itcast.test.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import cn.itcast.cms.service.StaticPageGenerationService;
import cn.itcast.global.utils.ArrayUtils;
import cn.itcast.test.service.TestService;
import cn.itcast.user.bean.User;
import cn.itcast.user.service.UserService;

/**
 * 	This is a action class to process regular request for action testing module 
 */
@Controller
@RequestMapping("/test")
public class TestAction implements WebBindingInitializer {

	/*	IOP: IOC & DI	*/
	@Resource
	private UserService userService;
	@Resource
	private TestService testService;
	@Resource
	private StaticPageGenerationService staticPageGenerationService;
	
	/**
	 * 	This is an action method to test querying user information by id
	 * @param id: to accept the parameter value from post|get request: request parameter binding|acquisition
	 * @param model: to put the processed result into requestScope
	 * @return: return to a view
	 * @throws Exception: can be processed by an global exception resolver
	 */
	@RequestMapping("/queryUser")
	public String queryUser(Integer id, Model model) throws Exception {
		
		User user = userService.queryUserById(id);
		model.addAttribute("user", user);
		
		return "test/testUserInfo";
	}
	
	/**
	 * 	This is an action method to test the global date converter "DateConverter.java" configured in "SpringMVC.xml"
	 * @throws Exception
	 */
	@RequestMapping("/dateConversion")
	public String dateConversion (String name, String brand, Date date, Date datetime, Model model) throws Exception {
		
		System.out.println("The name is: " + name);
		System.out.println("The brand is: " + brand);
		System.out.println("The date is: " + date);
		System.out.println("The datetime is " + datetime);
		
		model.addAttribute("successMessage", "Advanced search and date conversion succeeds! Please refer to the console to see the result!");
		return "forward:/redirection/success.action";
	}
	
	/**
	 * 	This is an action method to test processing array parameters
	 * @param hobby
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/processCheckbox")
	public String processCheckbox (String[] hobby, Model model) throws Exception {
		
		/*	If there is any element in the array, process the array 	*/
		if (hobby != null && hobby.length > 0) {
			
			/*	Convert the String array to one String: e.g. if hobby is a field in JavaBean	*/
			String hobbyString = ArrayUtils.toString(hobby);
			System.out.println("Your hobby: " + hobbyString);
			
			/*	Convert the String array to a String list: e.g. if hobby is used as range query  */
			List<String> hobbyList = Arrays.asList(hobby);
			System.out.println("Your hobby: " + hobbyList);
		
		/*	Else output a message	*/
		} else {
			System.out.println("No hobby is checked.");
		}
		
		/*	Save the success mesage & return	*/
		model.addAttribute("successMessage", "Array parameter is processed succesfully. Please refer to the console!");
		return "forward:/redirection/success.action";
	}
	
	/**
	 * 	This is an action method to test the reception of current page URL
	 * @throws Exception
	 */
	@RequestMapping("/getCurrentPageURL")
	public String getCurrentPageURL (String currentPageURL, String currentPageURLWithParameters, 
			String parameterA, String parameterB, String parameterC, HttpServletRequest request, Model model) throws Exception {
		
		/*	Get current request URL	 */
		System.out.println("Current Request URL: " + request.getRequestURL());
		
		/*	Get the current page URL with no parameters: no problems	*/
		System.out.println("Current Page URL: " + currentPageURL);
		
		/*
		 * 	Get the current page URL with parameters:
		 *  CurrentPageURL cannot completely receive the original current page URL with multiple parameters: 
		 *  "&parameterB=bbb&parameterC=ccc" is missing in this case
		 * 	Original current page URL: http://localhost:8080/mvnols/redirection/testModule.action?parameterA=aaa&parameterB=bbb&parameterC=ccc
		 * 	Received current page URL: http://localhost:8080/mvnols/redirection/testModule.action?parameterA=aaa
		 */
		System.out.println("Current Page URL With Parameters: " + currentPageURLWithParameters);
		
		/*	Receive the value of the parameters in original current page URL	 */
		System.out.println("ParameterA: " + parameterA);	// null: the leftmost parameter value is missing
		System.out.println("ParameterB: " + parameterB);	// bbb
		System.out.println("ParameterC: " + parameterC);	// ccc
		
		/*	
		 * 	Assuming the parameter sequence (i.e. ParameterA to ParameterC) is unchanged:
		 * 	Simulate a real scenario: there may be parameters or no parameters, so use a random number to 
		 * 	randomly choose one as the received current page URL, then process it to become a returnURL.
		 *  1. If there are parameters, get the value of each parameter, including the missing one
		 *  
		 *  Notice that in actual using "window.location.href" will miss the value of leftmost parameter which is 
		 *  not necessarily the same parameter each time (it depends on the front-end view <input/> arrangement, etc).
		 *  Namely as shows as getReturnURLExperiment(): parameterB & parameterC are assumed to be known, which in actual
		 *  is not guaranteed. Once the parameter sequence is changed, the known parameter may become {parameterA and 
		 *  parameterB} or {parameterA and parameterC}
		 *  Hence:
		 *  1. Fix the parameter sequence:
		 *     -- e.g. by fixing or regulating the arrangement of <input/> element in JSP view, etc.
		 *  2. Each time do the predication for all parameters (which are stored in a queryBean|JavaBeanWrapper, etc.) to 
		 *     see which parameter is missing, then concatenate the rest of parameters for returnURL.
		 *     -- If there are multiple parameter sequence, then there are multiple predication & URL concatenation. 
		 *     -- The parameter sequence is uncertain in the whole project
		 *  3. Do not use "window.location.href", use {"window.location.host" + "window.location.search" + URL concatenation} instead
		 *     -- URL Concatenation is very direct, no need to do extra prediction once the parameters (obtained by "window.location.search")
		 *        are processed properly.
		 *     -- This is the strategy adopted in this project, refers to "WEB-INF/jsp/item/itemList.jsp"
	     */
		List<String> returnURLs = testService.getReturnURLExperiment(currentPageURL, currentPageURLWithParameters, parameterB, parameterC);
		System.out.println("The returnURLs are:");
		for (String returnURL : returnURLs) {
			System.out.println(returnURL);
		}
		
		model.addAttribute("successMessage", "Get current page URL succeeds! Please refer to the console to see the result!");
		return "forward:/redirection/success.action";
	}
	
	/**
	 * 	This is an action method to test if the error page works
	 * @throws Exception
	 */
	@RequestMapping("/returnException")
	public void returnException () throws Exception {
		
		int computationResult = 1 / 0;
		System.out.println("The computation result is: " + computationResult);
	}
	
	/**
	 * 	This is an action method to test the static page generation
	 * @throws Exception
	 */
	@RequestMapping("/generateStaticPage")
	public String generateStaticPage (HttpServletResponse response, Model model) throws Exception {
		
		/*	
		 * 	Specify the data that needs to be put in the template for static page generation	
		 * 	1. Regular object
		 * 	2. JavaBean
		 * 	3. List
		 *  4. Map
		 *  5. List<Map>
		 *  6. Date
		 *  7. keyword: null
		 */
		Map<String, Object> dataModel = new HashMap<String, Object>();
		//	Regular object
		dataModel.put("HelloWorld", "hello world!");
		dataModel.put("HelloFreeMarker", "hello freemarker!");
		//	JavaBean
		User user = new User();
		user.setId(1);
		user.setUsername("Vince");
		dataModel.put("user", user);
		//	List
		List<String> names = new ArrayList<String>();
		names.add("Vince");
		names.add("Violet");
		names.add("Kity");
		dataModel.put("names", names);
		//	Map
		Map<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("V1", "Vince");
		nameMap.put("V2", "Violet");
		nameMap.put("K1", "Kity");
		dataModel.put("nameMap", nameMap);
		//	List<Map>
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("name1", "Haha");
		map1.put("name2", "Gary");
		maps.add(map1);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name1", "Vince");
		map2.put("name2", "Violet");
		maps.add(map2);
		dataModel.put("maps", maps);
		//	Date
		dataModel.put("currentTime", new Date());
		//	keyword: null
		dataModel.put("word", null);
		
		/*	Generate the static page	*/
		staticPageGenerationService.freemarkerPageGeneration(dataModel, "freemarker");
		
		/*	
		 * 	Save success message & return:
		 * 	1. Here return a hyper-text reference (href) to JSP view
		 * 	2. Type the <a/> tag here return the correct string but wrong hyper-text reference (href)  
		 */
		String href = "<a href = \"http://localhost:8080/mvnols/html/freemarker.html\">here</a>";	
		model.addAttribute("successMessage", "Static page generation succeeds! Please click " + href + " by opening a new window to view");
		return "forward:/redirection/success.action";
		
	}

	//--------------------------------------------------------------------------------
	/**
	 * 	This is the local data converter (in this case it does not work since there is a global date converter)
	 * 	1. First specify the format with which the string will be converted to Date type
	 * 	2. Register an editor (i.e. converter) to complete the date conversion for request binding
	 * @param request
	 */
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		
		/*	Date format specification	*/
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		/*	Date converter registration for request binding	 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
