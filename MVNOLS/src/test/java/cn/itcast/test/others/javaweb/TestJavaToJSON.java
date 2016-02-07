package cn.itcast.test.others.javaweb;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;

import cn.itcast.test.others.javaweb.testclass.bean.Student;
import cn.itcast.test.others.javaweb.testclass.factory.StudentMultitonFactory;

/**
 * 	This class is to perform testing regarding conversion from Java object to JSON object 
 * 	1. JSONObject: get JSON object
 *  2. ObjectMapper: get JSON value
 *  3. Use @ResponseBody in SpringMVC framework
 *     -- Refer to "src/main/java/cn/itcast/test/action/TestAjaxAction.java"
 * @author Vince Xu Yuan
 */
public class TestJavaToJSON {

	/**
	 * 	Test the conversion using JSONObject class
	 * 	1. This way will get a complete JSON object: includes name & value
	 *  2. When to use this way: 
	 *     -- Refer to "src/main/java/cn/itcast/global/upload/action/UploadAjaxAction.java"
	 * @throws Exception
	 */
	@Test
	public void testJSONObject () throws Exception {
		
		/*	Instantiate some Java objects	*/
		Student student = StudentMultitonFactory.getInstance();
		int[] integers = {(new Random().nextInt(6) + 5), (int) (Math.random() * 5 + 5)};
		List<Object> javaObjects = new ArrayList<Object>();
		javaObjects.add(student);
		javaObjects.add(integers);
		
		/*	Convert the Java object to JSON object	*/
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("student", student);
		jsonObject1.put("integers", integers);
		jsonObject1.put("list", javaObjects);
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("javaObjects", javaObjects);
		
		/*	Print out the JSON object	*/
		System.out.println("The 1st JSON object: " + jsonObject1);
		System.out.println("The 2nd JSON object: " + jsonObject2);
	}
	
	/**
	 * 	Test the conversion using ObjectMapper class
	 * 	1. This method only gets a JSON value (as string)
	 *  2. When to use this way: 
	 *     -- When using "new Cookie(key, value)", value can be JSON value
	 * @throws Exception
	 */
	@Test
	public void testObjectMapper () throws Exception {
		
		/*	Instantiate some Java objects	*/
		Student student = StudentMultitonFactory.getInstance();
		int[] integers = {(new Random().nextInt(6) + 5), (int) (Math.random() * 5 + 5)};
		List<Object> javaObjects = new ArrayList<Object>();
		javaObjects.add(student);
		javaObjects.add(integers);
		
		/*	Convert the Java object to JSON object	*/
		ObjectMapper objectMapper = new ObjectMapper();
		StringWriter stringWriter1 = new StringWriter();
		objectMapper.writeValue(stringWriter1, student);
		StringWriter stringWriter2 = new StringWriter();
		objectMapper.writeValue(stringWriter2, integers);
		StringWriter stringWriter3 = new StringWriter();
		objectMapper.writeValue(stringWriter3, javaObjects);
		
		/*	Output the JSON value string	*/
		System.out.println("The 1st JSON value: " + stringWriter1.toString());
		System.out.println("The 2nd JSON value: " + stringWriter2.toString());
		System.out.println("The 3rd JSON value: " + stringWriter3.toString());
	}
}
