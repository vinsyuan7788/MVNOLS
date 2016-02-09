package cn.itcast.test.others.javaweb.testclass.json;

import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONObject;

/**
 * 	This is the class to convert Java object to JSON object
 * @author Vince Xu Yuan
 */
public class JavaToJSON {

	/**
	 * 	This is a static method to return JSON object using JSONObject
	 * @param keys
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public static JSONObject toJsonObject (String[] keys, Object... values) throws Exception {

		/*	Create a JSON object	*/
		JSONObject jsonObject = new JSONObject();
		
		/*	Put Java object(s) into a JSON object	*/
		for (int i = 0; i < values.length; i++) {
			jsonObject.put(keys[i], values[i]);
		}
		
		/*	Return the JSON object	*/
		return jsonObject;
	}
	
	/**
	 * 	This is a static method to return JSON string using ObjectMapper 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String toJsonString (Object value) throws Exception {
		
		/*	Get an ObjectMapper object	*/
		ObjectMapper objectMapper = new ObjectMapper();
		
		/*	Get a StringWriter object	*/
		StringWriter stringWriter = new StringWriter();
		
		/*	Write the Java object as JSON string	*/
		objectMapper.writeValue(stringWriter, value);
		
		/*	Return the JSON string	*/
		return stringWriter.toString();
	}
}
