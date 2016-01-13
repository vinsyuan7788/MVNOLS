package cn.itcast.test.others.javase.testclass.annotation.utils;

import java.lang.reflect.Method;

import cn.itcast.test.others.javase.testclass.annotation.MethodAnnotation;

/**
 * 	This is an utility class to parse the method annotation to get the method name
 */
public class MethodAnnotationParser {

	/**
	 * 	This is the method to get the name of all methods with @MethodAnnotation()
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static String getMethodNames (Class clazz) throws Exception {
		
		/*	Get all the methods from the class	 */
		Method[] methods = clazz.getDeclaredMethods();
		
		/*	Instantiate a string buffer to store all the method names	*/
		StringBuffer methodNames = new StringBuffer();
		
		/*	Traverse the method	 */
		for (Method method : methods) {
			
			/*	Set accessible to ALL methods (including the private method)	*/
			method.setAccessible(true);
			
			/*	If there is a method annotation: get the annoation & continue	*/
			if (method.isAnnotationPresent(MethodAnnotation.class)) {	
				MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
				
				/*	If the "getName" is true: then store the name to the string buffer	 */
				if (methodAnnotation.getName() == true) {
					if (methodNames.length() > 0) methodNames.append(", ");
					methodNames.append(method.getName());
				}
				
				/*	If the "getName" is false: do nothing	*/
			}
			
			/*	If there is a method annotation: do nothing	 */
		}
		
		/*	Return the string converted from string buffer	 */
		return methodNames.toString();
	}
}
