package cn.itcast.test.others.javaweb.testclass.json;

import net.sf.json.JSONObject;

/**
 * 	This is the class to convert JSON object to Java object
 * @author Vince Xu Yuan
 */
public class JSONToJava {

	/**
	 * 	This is a static method to return Java object
	 * @param jsonObject
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object toJavaObject (JSONObject jsonObject, Class clazz) throws Exception {
		return JSONObject.toBean(jsonObject, clazz);
	}
}
