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
	 */
	public static boolean parseSaveToken (Class targetClass, String methodName) {
		
		try {
			/*	Get the target method	*/
			Method targetMethod = targetClass.getMethod(methodName);  
			
			/*	If there is an annotation on the method, return the value of the annotation		*/
			if (targetMethod.isAnnotationPresent(Token.class)) {
				Token token = targetMethod.getAnnotation(Token.class);
				return token.saveToken();
				
			/*	Else return the default value: false	*/
			} else {
				return false;
			}
		
		/*	If catch any checked exception, throw it out using RuntimeException 	*/
		} catch (Exception e) {
			throw new RuntimeException("Exception raised by \"class.getMethod()\"", e);
		}
	}

	/**
	 * 	This is the static method to parse the saveToken()
	 * @param targetClass
	 * @param method
	 */
	public static boolean parseSaveToken (Method method) {
		
		/*	If there is an annotation on the method, return the value of the annotation		*/
		if (method.isAnnotationPresent(Token.class)) {
			Token token = method.getAnnotation(Token.class);
			return token.saveToken();
		
		/*	Else return the default value: false	*/
		} else {
			return false;
		}
	}
	
	/**
	 * 	This is the static method to parse the validateToken()
	 * @param targetClass
	 * @param methodName
	 */	
	public static boolean parseValidateToken (Class targetClass, String methodName) {
		
		try {
			/*	Get the target method	*/
			Method targetMethod = targetClass.getMethod(methodName);	
			
			/*	If there is an annotation on the method, return the value of the annotation		*/
			if (targetMethod.isAnnotationPresent(Token.class)) {
				Token token = targetMethod.getAnnotation(Token.class);
				return token.validateToken();
			
			/*	Else return the default value: false	*/
			} else {
				return false;
			}
			
		/*	If catch any checked exception, throw it out using RuntimeException 	*/
		} catch (Exception e) {
			throw new RuntimeException("Exception raised by \"class.getMethod()\"", e);
		}
	}
	
	/**
	 * 	This is the static method to parse the validateToken()
	 * @param targetClass
	 * @param method
	 */	
	public static boolean parseValidateToken (Method method) {
		
		/*	If there is an annotation on the method, return the value of the annotation		*/
		if (method.isAnnotationPresent(Token.class)) {
			Token token = method.getAnnotation(Token.class);
			return token.validateToken();
		
		/*	Else return the default value: false	*/
		} else {
			return false;
		}
	}
}
