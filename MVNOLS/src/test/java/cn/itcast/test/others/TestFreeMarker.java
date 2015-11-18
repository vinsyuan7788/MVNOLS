package cn.itcast.test.others;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.cms.service.StaticPageGenerationService;
import cn.itcast.cms.service.utils.StaticPageGenerationUtils;
import cn.itcast.user.bean.User;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 	This class is to test FreeMarker & corresponding static page generator
 * 	1. FreeMarker is Java-based engine to generate static pages
 * 	2. It allows stationary page such as HTML to accept the value from back-end, just like JSP
 *     -- the operation is similar as JSP, refers to "src/main/webapp/ftl/freemarker.html"
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext.xml")
public class TestFreeMarker {

	/**
	 * 	This is a method to test FreeMarker
	 * 	1. To see the result: refresh "src/main/webapp/html/"
	 * @throws Exception
	 */
	@Test
	public void testFreeMarker () throws Exception {
		
		/*	Get a configuration instance	*/
		Configuration configuration = new Configuration();
		
		/*	Specify the template directory	*/
		String templateDirectory = "D:/JavaWeb/MyEclipse 10/MVNOLS/src/main/webapp/";
		
		/*	Load the template directory & get the raw template	*/
		configuration.setDirectoryForTemplateLoading(new File(templateDirectory + "WEB-INF/ftl/"));
		Template template = configuration.getTemplate("freemarker.html");
		
		/*	
		 * 	Specify the data that needs to be put in the template	
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
		
		/*	generate the static page	*/
		Writer out = new FileWriter(new File(templateDirectory + "html/test_freemarker.html"));
		template.process(dataModel, out);
		
		/*	Close the file stream	*/
		out.flush();
		out.close();
		
		/*	Print some information	*/
		System.out.println("static page generation completed");
	}
}
