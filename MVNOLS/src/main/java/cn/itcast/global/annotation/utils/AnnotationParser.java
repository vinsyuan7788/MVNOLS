package cn.itcast.global.annotation.utils;

import java.lang.reflect.Method;

import cn.itcast.global.annotation.Token;



/**
 * 	This is an utility class to parse the annotation
 */
public class AnnotationParser {

	/**
	 * 	This is the method to parse the annotation
	 * @param targetClass
	 * @param methodName
	 * @throws Exception
	 */
	public static boolean parseToken (Class targetClass, String methodName) throws Exception {
		
		Method method = targetClass.getMethod(methodName);	// Assume the method has no arguments
		
		if (method.isAnnotationPresent(Token.class)) {
			Token token = method.getAnnotation(Token.class);
			return token.saveToken();
		} else {
			return false;
		}
	}
}
