package cn.itcast.test.others.javaweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.junit.Test;

import cn.itcast.test.others.javaweb.testclass.json.JSONToJava;
import cn.itcast.test.others.javaweb.testclass.json.JavaToJSON;
import cn.itcast.test.others.javaweb.testclass.json.bean.Student;
import cn.itcast.test.others.javaweb.testclass.json.factory.StudentMultitonFactory;

/**
 * 	This class is to perform testing regarding conversion between Java object and JSON object 
 * 
 * 	From Java object to JSON object:
 * 	1. JSONObject: get JSON object
 *  2. ObjectMapper: get JSON value
 *  3. Use @ResponseBody in SpringMVC framework
 *     -- Refer to "src/main/java/cn/itcast/test/action/TestAjaxAction.java"
 *     
 *  From JSON object to Java object:
 *  1. JSONObject: "JSONObject.toBean()"
 *  
 * @author Vince Xu Yuan
 */
public class TestJSON {

	/**
	 * 	Test the conversion to JSON using JSONObject class
	 * 	1. This way will get a complete JSON object: includes name & value
	 *  2. When to use this way: 
	 *     -- Refer to "src/main/java/cn/itcast/global/upload/action/UploadAjaxAction.java"
	 * @throws Exception
	 */
	@Test
	public void testToJSONObject () throws Exception {
		
		/*	Instantiate some Java objects	*/
		Student student = StudentMultitonFactory.getInstance();
		Integer[] integers = {(new Random().nextInt(6) + 5), (int) (Math.random() * 5 + 5)};
		List<Object> javaObjects = new ArrayList<Object>();
		javaObjects.add(student);
		javaObjects.add(integers);
		
		/*	Convert the Java object to JSON object	*/
		String[] keys1 = {"student", "integers", "list"};
		Object[] values1 = {student, integers, javaObjects};
		JSONObject jsonObject1 = JavaToJSON.toJsonObject(keys1, values1);
		String[] keys2 = {"javaObjects"};
		Object[] values2 = {javaObjects};
		JSONObject jsonObject2 = JavaToJSON.toJsonObject(keys2, values2);
		
		/*	Print out the JSON object	*/
		System.out.println("The 1st JSON object: " + jsonObject1);
		System.out.println("The 2nd JSON object: " + jsonObject2);
	}
	
	/**
	 * 	Test the conversion to JSON using ObjectMapper class
	 * 	1. This method only gets a JSON string
	 *  2. When to use this way: 
	 *     -- When using "new Cookie(key, value)", value can be JSON value
	 * @throws Exception
	 */
	@Test
	public void testToJSONString () throws Exception {
		
		/*	Instantiate some Java objects	*/
		Student student = StudentMultitonFactory.getInstance();
		int[] integers = {(new Random().nextInt(6) + 5), (int) (Math.random() * 5 + 5)};
		List<Object> javaObjects = new ArrayList<Object>();
		javaObjects.add(student);
		javaObjects.add(integers);
		
		/*	Convert the Java object to JSON string	*/
		String jsonString1 = JavaToJSON.toJsonString(student);
		String jsonString2 = JavaToJSON.toJsonString(integers);
		String jsonString3 = JavaToJSON.toJsonString(javaObjects);
		
		/*	Output the JSON value string	*/
		System.out.println("The 1st JSON value: " + jsonString1);
		System.out.println("The 2nd JSON value: " + jsonString2);
		System.out.println("The 3rd JSON value: " + jsonString3);
	}
	
	/**
	 * 	Test the conversion to Java using JavaObject
	 * @throws Exception
	 */
	@Test
	public void testToJavaObject () throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("studentId", 100);
		jsonObject.put("studentName", "Josh");
		jsonObject.put("gender", "MALE");
		
		Student student = (Student) JSONToJava.toJavaObject(jsonObject, Student.class);
		System.out.println("The student: " + student);
	}
}
