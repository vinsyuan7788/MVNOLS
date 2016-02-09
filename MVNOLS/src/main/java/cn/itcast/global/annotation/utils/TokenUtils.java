package cn.itcast.global.annotation.utils;

import java.lang.reflect.Method;

import cn.itcast.global.annotation.Token;

/**
 * 	This is an utility class to parse the Token annotation
 * @author Vince Xu Yuan
 */
public class TokenUtils {

	/**
	 * 	This is the static method to parse the saveToken()
	 * @param targetClass
	 * @param methodName
	 * @throws Exception
	 */
	public static boolean parseSaveToken (Class targetClass, String methodName) throws Exception {
		
		Method targetMethod = targetClass.getMethod(methodName);  
		
		if (targetMethod.isAnnotationPresent(Token.class)) {
			Token token = targetMethod.getAnnotation(Token.class);
			return token.saveToken();
		} else {
			return false;
		}
	}

	/**
	 * 	This is the static method to parse the saveToken()
	 * @param targetClass
	 * @param method
	 * @throws Exception
	 */
	public static boolean parseSaveToken (Method method) throws Exception {
		
		if (method.isAnnotationPresent(Token.class)) {
			Token token = method.getAnnotation(Token.class);
			return token.saveToken();
		} else {
			return false;
		}
	}
	
	/**
	 * 	This is the static method to parse the validateToken()
	 * @param targetClass
	 * @param methodName
	 * @throws Exception
	 */	
	public static boolean parseValidateToken (Class targetClass, String methodName) throws Exception {
		
		Method targetMethod = targetClass.getMethod(methodName);	
		
		if (targetMethod.isAnnotationPresent(Token.class)) {
			Token token = targetMethod.getAnnotation(Token.class);
			return token.validateToken();
		} else {
			return false;
		}
	}
	
	/**
	 * 	This is the static method to parse the validateToken()
	 * @param targetClass
	 * @param method
	 * @throws Exception
	 */	
	public static boolean parseValidateToken (Method method) throws Exception {
		
		if (method.isAnnotationPresent(Token.class)) {
			Token token = method.getAnnotation(Token.class);
			return token.validateToken();
		} else {
			return false;
		}
	}
}
